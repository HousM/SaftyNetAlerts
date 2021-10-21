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

import com.openclassrooms.safetynets.alerts.model.Medicalrecord;
import com.openclassrooms.safetynets.alerts.service.MedicalrecordService;

@RestController
public class MedicalrecordController {
	private final Logger logger = LoggerFactory.getLogger(MedicalrecordController.class);

	@Autowired
	MedicalrecordService medicalrecordService;

	@GetMapping(value = "/medicalRecord")
	public MappingJacksonValue afficherMedicalrecord() throws Exception {
		return null;

	}

	@PostMapping(value = "/medicalRecord")
	public ResponseEntity<Void> addMedicalrecord(@RequestBody Medicalrecord medicalrecord) throws Exception {
		return null;

	}
}
