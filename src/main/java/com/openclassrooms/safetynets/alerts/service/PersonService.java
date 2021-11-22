package com.openclassrooms.safetynets.alerts.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetynets.alerts.model.Person;
import com.openclassrooms.safetynets.alerts.repository.PersonRepository;

@Service
public class PersonService {

	/**
	 * PersonService logger.
	 */
	private Logger logger = LogManager.getLogger(PersonService.class);

	/**
	 * Instance
	 */

	@Autowired
	private PersonRepository personRepository;

	public Person createPerson(Person pers) throws Exception {
		logger.debug("Inside PersonService.createPerson");
		Person personFound = personRepository.findByIdentity(pers.getFirstName(), pers.getLastName());

		if (personFound != null) {
			throw new Exception("Person already registered");
		}

		Person personSaved = personRepository.save(personFound);

		return personSaved;
	}

	public Person updatePerson(Person pers) throws Exception {
		logger.debug("Inside PersonService.updatePerson");
		Person personFound = personRepository.findByIdentity(pers.getFirstName(), pers.getLastName());

		if (personFound == null) {
			throw new Exception("Person not found");
		}

		personFound.setAddress(pers.getAddress());
		personFound.setCity(pers.getCity());
		personFound.setZip(pers.getZip());
		personFound.setPhone(pers.getPhone());
		personFound.setEmail(pers.getEmail());

		return personFound;
	}

	/*
	 * Retrieve the person with the given identity by calling PersonRepository's
	 */
	public Person getPersonById(String firstName, String lastName) throws Exception {
		logger.debug("Inside PersonService.getPerson for address : " + firstName, lastName);

		Person person = personRepository.findByIdentity(firstName, lastName);

		if (person == null) {
			throw new Exception("Failed to get person with Id : " + firstName + lastName);
		}

		return person;
	}

	/*
	 * Retrieve the list of people by calling the getPersonList method of
	 * PersonRepository
	 */

	public List<Person> getPersonList() throws Exception {
		logger.debug("Inside PersonService.getPersonList");
		List<Person> personList = personRepository.getPersonList();

		if (personList.isEmpty()) {
			throw new Exception("Failed to get person list");
		}

		return personList;
	}

	/*
	 * Retrieve people living in the given city by calling PersonRepository's
	 * findByCity method
	 */

	public List<Person> getPersonsByCity(String city) throws Exception {
		logger.debug("Inside PersonService.getPersonsByCity method for city : " + city);

		List<Person> personsByCity = personRepository.findByCity(city);

		if (personsByCity.isEmpty()) {
			throw new Exception("Failed to get persons for city : " + city);
		}

		return personsByCity;
	}

	/*
	 * Retrieve people living at the given address by calling the findByAddress
	 * method of PersonRepository
	 */

	public List<Person> getPersonsByAddress(String address) throws Exception {
		logger.debug("Inside PersonService.getPersonsByAddress for address : " + address);

		List<Person> personsByAddress = personRepository.findByAddress(address);

		if (personsByAddress.isEmpty()) {
			throw new Exception("Failed to get persons for address : " + address);
		}

		return personsByAddress;
	}

	/*
	 * Checking if the given person to delete is registered by calling
	 * PersonRepository's
	 */
	public void deletePerson(String firstName, String lastName) throws Exception {
		logger.debug("Inside PersonService.deletePerson : " + firstName, lastName);
		Person personToDelete = personRepository.findByIdentity(firstName, lastName);

		if (personToDelete == null) {
			throw new Exception("Person not found");
		}

		personRepository.delete(personToDelete);
	}
}
