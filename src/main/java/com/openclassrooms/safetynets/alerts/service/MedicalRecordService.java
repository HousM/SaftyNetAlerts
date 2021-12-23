package com.openclassrooms.safetynets.alerts.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetynets.alerts.model.MedicalRecord;
import com.openclassrooms.safetynets.alerts.repository.MedicalRecordRepository;

@Service
public class MedicalRecordService {

	/**
	 * PersonRepository logger
	 */
	private Logger logger = LogManager.getLogger(MedicalRecordService.class);

	/**
	 * MedicalRecordRepository instance.
	 */

	@Autowired
	private MedicalRecordRepository medicalRecordRepository;

	public MedicalRecord saveMedicalRecord(MedicalRecord med) throws Exception {
		logger.debug("Inside MedicalRecordService.createMedicalRecord for: " + med.getFirstName(), med.getLastName());

		MedicalRecord medFound = medicalRecordRepository.findByIdentity(med.getFirstName(),
				med.getLastName());

		if (medFound != null) {
			throw new Exception("MedicalRecord already registered");
		}

		MedicalRecord medSaved = medicalRecordRepository.save(med);
		return new MedicalRecord(medSaved.getFirstName(), medSaved.getLastName(), medSaved.getBirthdate(),
				medSaved.getMedicationsList(), medSaved.getAllergiesList());
	}

	public MedicalRecord updateMedicalRecord(MedicalRecord med) throws Exception {
		logger.debug("Inside MedicalRecordService.updateMedicalRecord for: " + med.getFirstName(), med.getLastName());
		MedicalRecord medToUpdate = medicalRecordRepository.findByIdentity(med.getFirstName(),
				med.getLastName());

		if (medToUpdate == null) {
			throw new Exception("MedicalRecord not found");
		}

		MedicalRecord medUpdated = medicalRecordRepository.update(med);
		return medUpdated;
	}

	public void deleteMedicalRecord(String firstName, String lastName) throws Exception {
		logger.debug("Inside MedicalRecordService.deleteMedicalRecord for {} {}", firstName, lastName);
		MedicalRecord medicalRecordToDelete = medicalRecordRepository.findByIdentity(firstName, lastName);

		if (medicalRecordToDelete == null) {
			throw new Exception("MedicalRecord not found");
		}
		medicalRecordRepository.delete(medicalRecordToDelete);
	}

	public MedicalRecord getMedicalRecordById(String firstName, String lastName) throws Exception {
		logger.debug("Inside MedicalRecordService.getMedicalRecordByID for {} {}",
				firstName, lastName);
		MedicalRecord medicalRecord = medicalRecordRepository.findByIdentity(firstName, lastName);

		if (medicalRecord == null) {
			throw new Exception("Failed to get medicalRecord for : " + firstName + " " + lastName);
		}

		return medicalRecord;
	}

}
