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

import com.openclassrooms.safetynets.alerts.model.Firestation;
import com.openclassrooms.safetynets.alerts.service.FirestationService;

@RestController
public class FirestationController {
	private final Logger logger = LoggerFactory.getLogger(FirestationController.class);

	@Autowired
	FirestationService firestationService;

	@GetMapping(value = "/firestations")
	public MappingJacksonValue afficherStations() throws Exception {
		return null;

	}

	@PostMapping(value = "/firestation")
	public ResponseEntity<Void> addFirestation(@RequestBody Firestation firestation) throws Exception {
		return null;

	}
}
