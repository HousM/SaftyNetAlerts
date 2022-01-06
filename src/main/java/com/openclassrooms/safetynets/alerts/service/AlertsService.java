package com.openclassrooms.safetynets.alerts.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynets.alerts.dto.ChildDTO;
import com.openclassrooms.safetynets.alerts.dto.CommunityEmailDTO;
import com.openclassrooms.safetynets.alerts.dto.FireDTO;
import com.openclassrooms.safetynets.alerts.dto.FloodDTO;
import com.openclassrooms.safetynets.alerts.dto.PersonDTO;
import com.openclassrooms.safetynets.alerts.dto.PersonInfoDTO;
import com.openclassrooms.safetynets.alerts.dto.PhoneDTO;
import com.openclassrooms.safetynets.alerts.model.Child;
import com.openclassrooms.safetynets.alerts.model.FireStation;
import com.openclassrooms.safetynets.alerts.model.MedicalRecord;
import com.openclassrooms.safetynets.alerts.model.Person;
import com.openclassrooms.safetynets.alerts.model.PersonInfo;
import com.openclassrooms.safetynets.alerts.repository.ReadJsonData;
import com.openclassrooms.safetynets.alerts.util.AgeCalcul;

@Service
public class AlertsService {
	private Logger logger = LogManager.getLogger(AlertsService.class);
	ObjectMapper mapper = new ObjectMapper();
	private static int ADULT_AGE = 19;
	@Autowired
	private ReadJsonData dataStore;
	@Autowired
	private FireStationService fireStationService;
	/*** PersonService's class reference. */
	@Autowired
	private PersonService personService;

	/*** MedicalRecordService's class reference. */
	@Autowired
	private MedicalRecordService medicalRecordService;

	/*** AgeCalcul instance. */
	@Autowired
	private AgeCalcul ageCalcul;

	private int MAX_AGE_FOR_CHILD_ALERT = 18;

	public PersonDTO getPersonsByStation(int station) throws Exception {
		logger.debug("Inside AlertsService.getPersonsByStation for station number : " + station);
		int adultCount = 0;
		int childCount = 0;

		List<Person> persons = dataStore.getPersonList();
		if (persons == null) {
			throw new Exception("PersonList null");
		}

//		List<Person> persons = personService.getPersonList();
		// Retrieves addresses covered by the given station number
		List<String> addresses = fireStationService.getAddressesByStation(station);
		List<Person> list = new ArrayList<>();

		// Loop through the list of person to find the persons living at these
		// addresses.
		for (Person pers : persons) {
			for (String address : addresses) {

				if (pers.getAddress().equals(address)) {
					// Determination if it is an adult or a child
					// by retrieving his medical file to obtain his date of birth and calculate his
					// age.
					MedicalRecord med = medicalRecordService.getMedicalRecordById(pers.getFirstName(),
							pers.getLastName());
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
					LocalDate birthDate = LocalDate.parse(med.getBirthdate(), formatter);
					int age = ageCalcul.getAge(birthDate);
					if (age <= MAX_AGE_FOR_CHILD_ALERT) {
						childCount++;
					} else {
						adultCount++;
					}
					// Create a "Person" object that contains the specific data required of each
					// person covered
					// by the given fire station number and adds it to an ArrayList.
					list.add(new Person(pers.getFirstName(), pers.getLastName(), pers.getAddress(),
							pers.getPhone()));
				}
			}
		}

		return new PersonDTO(list, adultCount, childCount);

	}

	public PersonInfoDTO getPersonByIdentity(String firstName, String lastName) throws Exception {
		logger.debug("Inside AlertsService.getInfoByIdentity for : " + firstName, lastName);
		List<Person> persons = personService.getPersonList();
		List<PersonInfo> personsInfo = new ArrayList<>();

		// Loops the person list to detect persons with the given last name and
		// calculates their age.
		for (Person pers : persons) {

			// Calculates person's age by retrieving his medical record to obtain his date
			// of birth.
			if (pers.getLastName().equals(lastName)) {
				MedicalRecord med = medicalRecordService.getMedicalRecordById(pers.getFirstName(),
						pers.getLastName());
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
				LocalDate birthDate = LocalDate.parse(med.getBirthdate(), formatter);
				int age = ageCalcul.getAge(birthDate);

				// Create a "Person" object that contains specific data required of each person
				// with the given last name and adds it to an ArrayList.
				personsInfo.add(new PersonInfo(pers.getLastName(), pers.getAddress(),
						age, pers.getEmail(), med.getMedicationsList(), med.getAllergiesList()));
			}
		}

		return new PersonInfoDTO(personsInfo);
	}

	public FireDTO getPersonsByAddress(String address) throws Exception {
		logger.debug("Inside AlertsService.getPersonsByAddress for address : " + address);
		// Retrieves persons living at given address
		List<Person> personsByAddress = personService.getPersonsByAddress(address);
		List<Person> persons = new ArrayList<>(); /* Person's Address */

		for (Person pers : personsByAddress) {

			// Calculation of person's age by retrieving
			// his medical record to obtain his date of birth.

			MedicalRecord med = medicalRecordService.getMedicalRecordById(pers.getFirstName(),
					pers.getLastName());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
			LocalDate birthDate = LocalDate.parse(med.getBirthdate(), formatter);
			int age = ageCalcul.getAge(birthDate);

			// Create a "Person" object containing specific data required of each
			// person living at a given address and add it to an ArrayList
			persons.add(new Person(pers.getLastName(), pers.getPhone(),
					age, med.getMedicationsList(), med.getAllergiesList()));
		}

		// Retrieves fire station that covered the given address to get it station
		// number
		FireStation fireStation = fireStationService.getFireStationByAddress(address);
		int station = fireStation.getStation();

		return new FireDTO(station, persons);
	}

	public FloodDTO getHouseholdsByStation(List<Integer> stations) throws Exception {
		logger.debug("Inside AlertsService.getHouseholdsByStation for stations : " + stations);
		List<FireStation> householdsByStationDTO = new ArrayList<>();
		List<String> addressAll = new ArrayList<>();

		for (int station : stations) {
			// For each given fire station numbers retrieves addresses covered by it.
			List<String> addressesByStation = fireStationService.getAddressesByStation(station);
			List<FireStation> households = new ArrayList<>();

			for (String address : addressesByStation) {

				// For each address check if is not already managed by another station to avoid
				// having same address
				// for two station number. if so, adds it to an ArrayList and process further.
				if (!addressAll.contains(address)) {
					addressAll.add(address);

					// Retrieves persons living at given address.
					List<Person> persons = personService.getPersonsByAddress(address);
					List<Person> personAddress = new ArrayList<>();

					// Calculates age for each person from household.
					for (Person pers : persons) {
						MedicalRecord med = medicalRecordService.getMedicalRecordById(pers.getFirstName(),
								pers.getLastName());
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
						LocalDate birthDate = LocalDate.parse(med.getBirthdate(), formatter);
						int age = ageCalcul.getAge(birthDate);
						// Creates a PersonAddress object that contains specific data requires for each
						// person from
						// household and adds it to an ArrayList.
						personAddress.add(new Person(pers.getLastName(), pers.getPhone(),
								age, med.getMedicationsList(), med.getAllergiesList()));
					}
					// Creates a Household object that contains its address and list of persons
					// living in it.
					households.add(new FireStation(address, personAddress));
				}
			}
			householdsByStationDTO.add(new FireStation(station, households));
		}

		return new FloodDTO(householdsByStationDTO);
	}

	public ChildDTO getChildByAddress(String address) throws Exception {
		logger.debug("Inside AlertsService.getChildByAddress for adress : " + address);
		List<Person> personsByAddress = personService.getPersonsByAddress(address);
		List<Child> childList = new ArrayList<>();
		List<String> adultList = new ArrayList<>();

		// Loops the person list to detect the persons with given last name and
		// calculates their age.
		for (Person pers : personsByAddress) {

			// Determines if it's an adult or child by retrieving his medical record to
			// obtain his date of birth.
			MedicalRecord med = medicalRecordService.getMedicalRecordById(pers.getFirstName(),
					pers.getLastName());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
			LocalDate birthDate = LocalDate.parse(med.getBirthdate(), formatter);
			int age = ageCalcul.getAge(birthDate);

			// Creates Child object that contains specific data required and it to a child
			// list or adult list
			// depending the age calculated.
			if (age < ADULT_AGE) {
				childList.add(new Child(pers.getFirstName(), pers.getLastName(), age));
			} else {
				adultList.add("FirstName : " + pers.getFirstName() + " LastName : " + pers.getLastName());
			}
		}

		return new ChildDTO(childList, adultList);
	}

	public PhoneDTO getPhonesByStation(int station) throws Exception {
		logger.debug("Inside AlertsService.getPhonesByStation for station : " + station);
		List<Person> persons = personService.getPersonList();
		// Retrieves addresses covered by the given station number
		List<String> addresses = fireStationService.getAddressesByStation(station);
		List<String> phones = new ArrayList<>();

		// Loops the person list to find the persons living at these addresses to get
		// their phone number and
		// adds it to an ArrayList.
		for (Person pers : persons) {
			for (String address : addresses) {
				if (pers.getAddress().equals(address)) {
					phones.add(pers.getPhone());
				}
			}
		}

		return new PhoneDTO(phones);
	}

	public CommunityEmailDTO getEmailsByCity(String city) throws Exception {
		logger.debug("Inside FireStation.getEmailsByCity method for city : " + city);
		List<Person> personsByCity = personService.getPersonsByCity(city);
		List<String> emails = new ArrayList<>();

		for (Person person : personsByCity) {
			emails.add(person.getEmail());
		}

		return new CommunityEmailDTO(emails);
	}

}
