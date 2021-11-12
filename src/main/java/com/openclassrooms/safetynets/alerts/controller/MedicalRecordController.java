package com.openclassrooms.safetynets.alerts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.openclassrooms.safetynets.alerts.model.MedicalRecord;
import com.openclassrooms.safetynets.alerts.service.MedicalRecordService;

@RestController
public class MedicalRecordController {

	/**
	 * MedicalRecordController logger.
	 */
	private Logger logger = LoggerFactory.getLogger(MedicalRecordController.class);

	@Autowired
	private MedicalRecordService medicalRecordService;

	/**
	 * Retrieve stored medicalRecord.
	 */

	@GetMapping("/medicalRecord")
	public ResponseEntity<MedicalRecord> getMedicalRecord(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) throws Exception {
		logger.debug("MedicalRecord GET Request on : {} {}", firstName, lastName);

		if (firstName == null || firstName.trim().length() == 0 || lastName == null
				|| lastName.trim().length() == 0) {
			throw new Exception("Bad request : missing or incomplete parameter");
		}
		MedicalRecord medDTO = medicalRecordService.getMedicalRecordById(firstName, lastName);

		logger.info("MedicalRecord GET Request - SUCCESS");
		return new ResponseEntity<>(medDTO, HttpStatus.OK);
	}

	/**
	 * Adds a new medicalRecord.
	 */
	@PostMapping("/medicalRecord")
	public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord med) throws Exception {
		logger.debug("MedicalRecord POST Request on : " + med.getFirstName() + " " + med.getLastName());

		if (med.getFirstName() == null || med.getFirstName().isEmpty() || med.getLastName() == null ||
				med.getLastName().isEmpty()) {
			throw new Exception("Bad request : missing or incomplete body request");
		}
		MedicalRecord medicalRecordCreated = medicalRecordService.createMedicalRecord(med);

		logger.info("MedicalRecord POST request - SUCCESS");
		return new ResponseEntity<>(medicalRecordCreated, HttpStatus.CREATED);
	}

	/**
	 * Update stored medicalRecord.
	 */

	@PutMapping("/medicalRecord")
	public ResponseEntity<MedicalRecord> updateMedicalRecord(@RequestBody MedicalRecord med) throws Exception {
		logger.debug("MedicalRecord PUT Request on : " + med.getFirstName() + " " + med.getLastName());

		if (med.getFirstName() == null || med.getFirstName().isEmpty() || med.getLastName() == null ||
				med.getLastName().isEmpty()) {
			throw new Exception("Bad request : missing or incomplete body request");
		}
		MedicalRecord medicalRecordUpdated = medicalRecordService.updateMedicalRecord(med);

		logger.info("MedicalRecord PUT request - SUCCESS");
		return new ResponseEntity<>(medicalRecordUpdated, HttpStatus.OK);
	}

	/**
	 * Delete stored medicalRecord.
	 */
	@DeleteMapping("/medicalRecord")
	public ResponseEntity<Void> deleteMedicalRecord(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) throws Exception {
		logger.debug("MedicalRecord DELETE Request on : " + firstName + " " + lastName);

		if (firstName == null || firstName.trim().length() == 0 || lastName == null
				|| lastName.trim().length() == 0) {
			throw new Exception("Bad request : missing or incomplete parameter");
		}
		medicalRecordService.deleteMedicalRecord(firstName, lastName);

		logger.info("MedicalRecord DELETE request - SUCCESS");
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
