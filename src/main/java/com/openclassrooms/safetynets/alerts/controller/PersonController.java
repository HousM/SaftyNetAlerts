package com.openclassrooms.safetynets.alerts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetynets.alerts.model.Person;
import com.openclassrooms.safetynets.alerts.service.PersonService;

@RestController
public class PersonController {
	private final Logger logger = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	PersonService personService;

	// Persons list
	@GetMapping("/listPersons")
	public List<Person> listPerson() throws Exception {
		return personService.getPersonList();
	}

	// List of person living at the address
	@GetMapping("/address")
	public List<Person> listByAddress(@RequestParam(required = false) String address) throws Exception {
		return personService.getPersonsByAddress(address);
	}

	// City for inhabitant
	@GetMapping("/city")
	public List<Person> listByCity(@RequestParam(required = false) String city) throws Exception {
		return personService.getPersonsByCity(city);
	}

	// ID for inhabitant
	@GetMapping("/id")
	public Person listById(@RequestParam(required = false) String firstName, String lastName) throws Exception {
		return personService.getPersonById(firstName, lastName);
	}

	// Delete a person
	@DeleteMapping("/person")
	public void removePerson(@RequestParam(required = false) String firstName,
			@RequestParam(required = false) String lastName) throws Exception {
		personService.deletePerson(firstName, lastName);
		logger.info("Remove person(s) and associated medical records succeeded");

	}

}