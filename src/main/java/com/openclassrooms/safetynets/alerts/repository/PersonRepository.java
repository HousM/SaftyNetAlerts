package com.openclassrooms.safetynets.alerts.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynets.alerts.model.Person;

@Repository
public class PersonRepository {

	/**
	 * PersonRepository logger.
	 */

	private final Logger logger = LoggerFactory.getLogger(PersonRepository.class);

	/**
	 * HashMap instance for map Persons data.
	 */
	private final Map<String, Person> personsMap = new HashMap<>();

	/**
	 * Constructor of class PersonRepository.
	 */

	@Autowired
	public PersonRepository(ReadJsonData dataJson) {
		logger.debug("Map PersonList");
		dataJson.getPersonList().forEach(person -> personsMap.put(person.getFirstName()
				+ person.getLastName(), person));
	}

	public List<Person> getPersonList() {
		logger.debug("Inside PersonRepository.getPersonList");
		Collection<Person> personList = personsMap.values();
		return new ArrayList<>(personList);

	}

	/**
	 * Gets Person with the given key Id
	 */
	public Person findByIdentity(String firstName, String lastName) {
		logger.debug("Inside PersonRepository.findByIdentity for : " + firstName, lastName);
		return personsMap.get(firstName + lastName);
	}

	/**
	 * Loops through HashMap values to detect Persons living at the given City
	 */

	public List<Person> findByCity(String city) {
		logger.debug("Inside PersonRepository.findByCity for city : " + city);
		Collection<Person> persons = personsMap.values();
		List<Person> personsByCity = new ArrayList<>();

		for (Person person : persons) {
			if (person.getCity().equals(city)) {
				personsByCity.add(person);
			}
		}

		return personsByCity;
	}

	/**
	 * Loops through HashMap values to detect Persons living at the given address
	 */

	public List<Person> findByAddress(String address) {
		logger.debug("Inside PersonRepository.findByAddress for address : " + address);
		Collection<Person> persons = personsMap.values();
		List<Person> personsByAddress = new ArrayList<>();

		for (Person person : persons) {
			if (person.getAddress().equals(address)) {
				personsByAddress.add(person);
			}
		}

		return personsByAddress;
	}

	/**
	 * Adds the given Person to personMap.
	 */
	public Person save(Person personFound) {
		logger.debug("Inside PersonRepository.save for : " + personFound.getFirstName(), personFound.getLastName());
		personsMap.put(personFound.getFirstName() + personFound.getLastName(), personFound);

		return personsMap.get(personFound.getFirstName() + personFound.getLastName());
	}

	/**
	 * Deletes the given Person
	 */
	public void delete(final Person pers) {
		logger.debug("Inside PersonRepository.delete for : " + pers.getFirstName(), pers.getLastName());
		personsMap.remove(pers.getFirstName() + pers.getLastName());
	}

}
