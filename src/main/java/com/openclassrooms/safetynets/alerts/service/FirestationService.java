package com.openclassrooms.safetynets.alerts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirestationService {

	@Autowired
	PersonService personService;
	@Autowired
	MedicalrecordService medicalrecordService;

}
