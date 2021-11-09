package com.openclassrooms.safetynets.alerts.repository;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.openclassrooms.safetynets.alerts.model.MedicalRecord;

public class MedicalRecordRepository {
	/**
	 * PersonRepository logger.
	 */

	private final Logger logger = LoggerFactory.getLogger(MedicalRecordRepository.class);
	private final Map<String, MedicalRecord> medicalRecordsMap = new HashMap<>();

	@Autowired
	public MedicalRecordRepository(final ReadJsonData dataJson) {
		logger.debug("Map MedicalRecordList");
		dataJson.getMedicalRecordList().forEach(med -> medicalRecordsMap.put(med.getFirstName()
				+ med.getLastName(), med));
	}

	public MedicalRecord save(MedicalRecord med) {
		logger.debug("Inside MedicalRecordRepository.save for : " + med.getFirstName(), med.getLastName());
		medicalRecordsMap.put(med.getFirstName() + med.getLastName(), med);

		return medicalRecordsMap.get(med.getFirstName() + med.getLastName());
	}

	/**
	 * Deletes the given MedicalRecord
	 */
	public void delete(MedicalRecord med) {
		logger.debug("Inside MedicalRecordRepository.delete for : " + med.getFirstName(), med.getLastName());
		medicalRecordsMap.remove(med.getFirstName() + med.getLastName());
	}

	public MedicalRecord findByIdentity(String firstName, String lastName) {
		logger.debug("Inside MedicalRecordRepository.findByIdentity for : " + firstName, lastName);
		return medicalRecordsMap.get(firstName + lastName);
	}

}
