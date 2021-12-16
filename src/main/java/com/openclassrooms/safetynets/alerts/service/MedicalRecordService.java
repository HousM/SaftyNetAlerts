package com.openclassrooms.safetynets.alerts.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetynets.alerts.dto.MedicalRecordDTO;
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
	private MedicalRecordDTO medicalRecordDTO;

	public MedicalRecordDTO saveMedicalRecord(MedicalRecordDTO med) throws Exception {
		logger.debug("Inside MedicalRecordService.createMedicalRecord for: " + med.getFirstName(), med.getLastName());

		MedicalRecord medFound = medicalRecordRepository.findByIdentity(med.getFirstName(),
				med.getLastName());

		if (medFound != null) {
			throw new Exception("MedicalRecord already registered");
		}

		MedicalRecord medtoSaved = medicalRecordRepository.save(medFound);
		MedicalRecordDTO medSaved = medicalRecordDTO.toMedicalRecordDTO(medtoSaved);

		return medSaved;
	}

	public MedicalRecordDTO updateMedicalRecord(MedicalRecordDTO med) throws Exception {
		logger.debug("Inside MedicalRecordService.updateMedicalRecord for: " + med.getFirstName(), med.getLastName());
		MedicalRecord medFound = medicalRecordRepository.findByIdentity(med.getFirstName(),
				med.getLastName());

		if (medFound == null) {
			throw new Exception("MedicalRecord not found");
		}
		medFound.setBirthdate(med.getBirthDate());
		medFound.setMedicationsList(med.getMedications());
		medFound.setAllergiesList(med.getAllergies());

//		MedicalRecord medtoSaved = medicalRecordRepository.save(medFound);
		MedicalRecordDTO medSaved = medicalRecordDTO.toMedicalRecordDTO(medFound);

		return medSaved;
	}

	public void deleteMedicalRecord(String firstName, String lastName) throws Exception {
		logger.debug("Inside MedicalRecordService.deleteMedicalRecord for {} {}", firstName, lastName);
		MedicalRecord medicalRecordToDelete = medicalRecordRepository.findByIdentity(firstName, lastName);

		if (medicalRecordToDelete == null) {
			throw new Exception("MedicalRecord not found");
		}
		medicalRecordRepository.delete(medicalRecordToDelete);
	}

	public MedicalRecordDTO getMedicalRecordById(String firstName, String lastName) throws Exception {
		logger.debug("Inside MedicalRecordService.getMedicalRecordByID for {} {}",
				firstName, lastName);
		MedicalRecord medicalRecord = medicalRecordRepository.findByIdentity(firstName, lastName);

		if (medicalRecord == null) {
			throw new Exception("Failed to get medicalRecord for : " + firstName + " " + lastName);
		}
		MedicalRecordDTO medicalRecorddto = medicalRecordDTO.toMedicalRecordDTO(medicalRecord);
		return medicalRecorddto;
	}

}
