package com.openclassrooms.safetynets.alerts.repository;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynets.alerts.model.Person;

@Component
public class ReadJsonDataPerson {

	private List<Person> personList = new ArrayList<>();

	/**
	 * Logger
	 */

	private static final Logger logger = LoggerFactory.getLogger(ReadJsonDataPerson.class);

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
						node.get("zip").asText(),
						node.get("phone").asText(),
						node.get("email").asText());

				personList.add(person);
			}
		}
	}

	public List<Person> getPersonList() {
		return personList;
	}

}
