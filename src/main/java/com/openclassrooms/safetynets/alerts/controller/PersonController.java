package com.openclassrooms.safetynets.alerts.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetynets.alerts.model.Person;
import com.openclassrooms.safetynets.alerts.repository.PersonRepository;
import com.openclassrooms.safetynets.alerts.service.PersonService;

@RestController
public class PersonController {
	private Logger logger = LogManager.getLogger(PersonController.class);

	@Autowired
	private PersonService personService;
	@Autowired
	private PersonRepository personRepository;

	// Add a new person
	@PostMapping("/person")
	public ResponseEntity<Person> createPerson(@RequestBody Person person) throws Exception {
		logger.debug("Person CREATE Request on : " + person.getFirstName() + " " + person.getLastName());

		if (person.getFirstName() == null || person.getFirstName().isEmpty() || person.getLastName() == null
				|| person.getLastName().isEmpty()) {
			throw new Exception("Bad request :  missing or incomplete body request");
		}
//		Person personCreated = personService.createPerson(person);
		Person personCreated = personRepository.save(person);

		logger.info("POST /person response : CREATED");
		return new ResponseEntity<>(personCreated, HttpStatus.CREATED);
	}

	// Update a stored person
	@PutMapping("/person")
	public ResponseEntity<Person> updatePerson(@RequestBody Person person) throws Exception {
		logger.debug("Person UPDATE Request on : " + person.getFirstName() + " " + person.getLastName());

		if (person.getFirstName() == null || person.getFirstName().isEmpty() || person.getLastName() == null
				|| person.getLastName().isEmpty()) {
			throw new Exception("Bad request : missing or incomplete body request");
		}
		Person personUpdated = personService.updatePerson(person);

		logger.info("Person PUT request - SUCCESS");
		return new ResponseEntity<>(personUpdated, HttpStatus.OK);
	}

	// Delete a person
	@DeleteMapping("/person")
	public ResponseEntity<Void> deletePerson(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") final String lastName) throws Exception {
		logger.debug("Person DELETE Request on : " + firstName + " " + lastName);

		if (firstName == null || firstName.trim().length() == 0 || lastName == null
				|| lastName.trim().length() == 0) {
			throw new Exception("Bad request : missing or incomplete parameter");
		}
		personService.deletePerson(firstName, lastName);

		logger.info("Person DELETE request - SUCCESS");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// Retrieves a stored person
	@GetMapping("/person/")
	public ResponseEntity<Person> getPerson(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) throws Exception {
		logger.debug("Person GET Request on : {} {}", firstName, lastName);

		if (firstName == null || firstName.trim().length() == 0 || lastName == null
				|| lastName.trim().length() == 0) {
			throw new Exception("Bad request : missing or incomplete parameter");
		}
		Person person = personService.getPersonById(firstName, lastName);

		logger.info("Person GET Request - SUCCESS");
		return new ResponseEntity<>(person, HttpStatus.OK);
	}

}
