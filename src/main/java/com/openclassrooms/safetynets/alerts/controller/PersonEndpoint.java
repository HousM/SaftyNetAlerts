package com.openclassrooms.safetynets.alerts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetynets.alerts.model.Person;
import com.openclassrooms.safetynets.alerts.service.PersonService;

@RestController
public class PersonEndpoint {
	private final Logger logger = LoggerFactory.getLogger(PersonEndpoint.class);

	@Autowired
	PersonService personService;

	// Récupérer la liste des personnes

	@GetMapping(value = "/person")
	public MappingJacksonValue afficherPersons() throws Exception {
		return null;

	}

	// Ajouter une personne
	@PostMapping(value = "/person")
	public ResponseEntity<Void> addPersons(@RequestBody Person person) throws Exception {
		return null;

	}
}
