package com.openclassrooms.safetynets.alerts.repository;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openclassrooms.safetynets.alerts.model.Person;
import com.openclassrooms.safetynets.alerts.service.PersonService;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

public class ReadJsonDataPerson {
	private JSONObject object;
	private JSONArray persons;

	/**
	 * Persons list in data
	 */
	private List<Person> allAbstractPeople;

	private int idCounter;
	private PersonService personService;

	private ReadJsonDataPerson(PersonService personService) {
		this.personService = personService;
	}

	/**
	 * Logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(ReadJsonDataPerson.class);;

	@PostConstruct
	public void initDataHandlerJsonFile() throws IOException, ParseException {
		this.loadFile();
		if (object != null) {
			shareData(this.object);
			initPerson();
		}
	}

	/**
	 * Load Data JSON
	 */
	@SuppressWarnings("null")
	public void loadFile() {
		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("./data.json");
			JSONParser jsonParser = null;
			this.object = (JSONObject) jsonParser.parse(new InputStreamReader(inputStream));
			logger.info("Data loaded");
		} catch (Exception e) {
			logger.error("Data can't be loaded in Db : ", e);
		}

	}

	public void shareData(JSONObject obj) {
		persons = (JSONArray) obj.get("person");
	}

	public void initPerson() {
		if (persons != null) {
			idCounter = 1;
			Iterator<Object> iterator = persons.iterator();
			while (iterator.hasNext()) {
				JSONObject person = (JSONObject) iterator.next();
				String firstName = (String) person.get("firstName");
				String lastName = (String) person.get("lastName");
				String city = (String) person.get("city");
				String zip = (String) person.get("zip");
				String phone = (String) person.get("phone");
				String mail = (String) person.get("email");
				String address = (String) person.get("address");
				Person myPerson = new Person(idCounter, firstName, lastName, address, city, zip, phone, mail);
				this.allAbstractPeople = new ArrayList<>();
				allAbstractPeople.add(myPerson);
				personService.save(allAbstractPeople);
				idCounter += 1;
			}
			logger.info("Succes of loading Person");
		} else {
			logger.error("Fail of initiation Person");
		}

	}
}
