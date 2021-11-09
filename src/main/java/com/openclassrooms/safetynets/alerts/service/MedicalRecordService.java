package com.openclassrooms.safetynets.alerts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.openclassrooms.safetynets.alerts.model.MedicalRecord;
import com.openclassrooms.safetynets.alerts.repository.MedicalRecordRepository;

public class MedicalRecordService {

	/**
	 * PersonRepository logger.
	 */

	private final Logger logger = LoggerFactory.getLogger(MedicalRecordService.class);

	/**
	 * MedicalRecordRepository instance.
	 */
	private final MedicalRecordRepository medicalRecordRepository;

	@Autowired
	public MedicalRecordService(final MedicalRecordRepository medicalRecordRepository) {
		this.medicalRecordRepository = medicalRecordRepository;

	}

	public MedicalRecord createMedicalRecord(final MedicalRecord med) throws Exception {
		logger.debug("Inside MedicalRecordService.createMedicalRecord for: " + med.getFirstName(), med.getLastName());

		MedicalRecord medFound = medicalRecordRepository.findByIdentity(med.getFirstName(),
				med.getLastName());

		if (medFound != null) {
			throw new Exception("MedicalRecord already registered");
		}

		MedicalRecord medSaved = medicalRecordRepository.save(medFound);

		return medSaved;
	}

	public MedicalRecord updateMedicalRecord(final MedicalRecord med) throws Exception {
		logger.debug("Inside MedicalRecordService.updateMedicalRecord for: " + med.getFirstName(), med.getLastName());
		MedicalRecord medFound = medicalRecordRepository.findByIdentity(med.getFirstName(),
				med.getLastName());

		if (medFound == null) {
			throw new Exception("MedicalRecord not found");
		}
		medFound.setBirthdate(med.getBirthdate());
		medFound.setMedicationsList(med.getMedicationsList());
		medFound.setAllergiesList(med.getAllergiesList());

		return medFound;
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