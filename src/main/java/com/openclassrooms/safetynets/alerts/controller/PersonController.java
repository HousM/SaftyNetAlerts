package com.openclassrooms.safetynets.alerts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetynets.alerts.model.Email;
import com.openclassrooms.safetynets.alerts.model.Person;
import com.openclassrooms.safetynets.alerts.repository.PersonRepository;
import com.openclassrooms.safetynets.alerts.service.PersonService;

@RestController
public class PersonController {
	private final Logger logger = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	PersonService personService;

	@Autowired
	private PersonRepository personRepository;

	// Persons list
	@GetMapping("/listPersons")
	public Iterable<Person> list() {
		return personService.listAllPersonns();
	}

	// List of person living at the address
	@GetMapping("/fire")
	public List<Person> listFull(@RequestParam(required = false) String address) {
		return personService.getAllInformationsForPersonnAtAnAddress(address);
	}

	// Add a person
	@PostMapping("/person")
	public ResponseEntity<Person> addPerson(@RequestBody Person abstractPerson) {
		return ResponseEntity.ok(personRepository.save(abstractPerson));
	}

	// Email address for inhabitant
	@GetMapping("/communityEmail")
	public List<Email> listofEmailByCity(@RequestParam(required = false) String city) {
		return personService.getEmailPerCity(city);
	}

	// Delete a person
	@DeleteMapping("/person")
	public void removePerson(@RequestParam(required = false) String firstName,
			@RequestParam(required = false) String lastName) {
		personService.deletePerson(firstName, lastName);
		logger.info("Remove person(s) and associated medical records succeeded");

	}

	// update a person
	@PutMapping("/person")

	public ResponseEntity<Person> UpdatePerson(@RequestBody Person abstractPersonDetails,
			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
		Person person = personService.updatePerson(firstName, lastName, abstractPersonDetails);
		return ResponseEntity.ok(person);

	}

}
