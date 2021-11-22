package com.openclassrooms.safetynets.alerts.repository;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynets.alerts.model.FireStation;
import com.openclassrooms.safetynets.alerts.model.MedicalRecord;
import com.openclassrooms.safetynets.alerts.model.Person;

@Component
public class ReadJsonData {

	private List<Person> personList = new ArrayList<>();
	private List<FireStation> fireStationList = new ArrayList<>();
	private List<MedicalRecord> medicalRecordList = new ArrayList<>();

	/**
	 * Logger
	 */

	private Logger logger = LogManager.getLogger(ReadJsonData.class);

	/**
	 * Json data Load
	 */

	@Value("${dataFile}")
	private String dataFilePath;

	@PostConstruct
	public void loadFile() throws Exception {

		logger.debug("Inside DataStore.loadData() method");

		ObjectMapper mapper = new ObjectMapper();

		try (InputStream fileInputStream = new FileInputStream(dataFilePath)) {
			JsonNode dataRead = mapper.readTree(fileInputStream);

			// Stores each person into personList
			JsonNode persons = dataRead.at("/persons");
			for (JsonNode node : persons) {
				Person person = new Person(node.get("firstName").asText(),
						node.get("lastName").asText(),
						node.get("address").asText(),
						node.get("city").asText(),
						node.get("zip").asInt(),
						node.get("phone").asText(),
						node.get("email").asText());

				personList.add(person);
			}

			// Stores each fire station into fireStationList
			JsonNode fireStations = dataRead.at("/firestations");
			for (JsonNode node : fireStations) {
				FireStation firestation = new FireStation(node.get("address").asText(),
						node.get("station").asInt());

				fireStationList.add(firestation);
			}

			// Stores each medical record file into medicalRecordList
			JsonNode medicalRecords = dataRead.at("/medicalrecords");
			for (JsonNode node : medicalRecords) {
				List<String> medicationsList = new ArrayList<>();
				List<String> allergiesList = new ArrayList<>();

				JsonNode medications = node.at("/medications");
				for (JsonNode nodeMed : medications) {
					medicationsList.add(nodeMed.textValue());
				}

				JsonNode allergies = node.at("/allergies");
				for (JsonNode nodeAll : allergies) {
					allergiesList.add(nodeAll.textValue());
				}
				MedicalRecord medicalrecord = new MedicalRecord(node.get("firstName").asText(),
						node.get("lastName").asText(),
						node.get("birthdate").asText(),
						medicationsList, allergiesList);

				medicalRecordList.add(medicalrecord);
			}
		}
	}

	public List<Person> getPersonList() {
		return personList;
	}

	public List<FireStation> getFireStationList() {
		return fireStationList;
	}

	public List<MedicalRecord> getMedicalRecordList() {
		return medicalRecordList;
	}

}
