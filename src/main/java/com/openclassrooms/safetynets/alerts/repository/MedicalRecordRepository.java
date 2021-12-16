package com.openclassrooms.safetynets.alerts.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynets.alerts.model.MedicalRecord;

@Repository
public class MedicalRecordRepository {
	/**
	 * PersonRepository logger.
	 */

	private Logger logger = LogManager.getLogger(MedicalRecordRepository.class);
	private Map<String, MedicalRecord> medicalRecordsMap = new HashMap<>();
	private List<MedicalRecord> medicalrecordList;

	@Autowired
	public MedicalRecordRepository(ReadJsonData dataJson) {
		logger.debug("Map MedicalRecordList");
		dataJson.getMedicalRecordList().forEach(med -> medicalRecordsMap.put(med.getFirstName()
				+ med.getLastName(), med));
	}

	public MedicalRecord save(MedicalRecord med) {
		logger.debug("Inside MedicalRecordRepository.save for : " + med.getFirstName(), med.getLastName());
		medicalRecordsMap.put(med.getFirstName() + med.getLastName(), med);
		return medicalRecordsMap.get(med.getFirstName() + med.getLastName());

	}

	public MedicalRecord update(MedicalRecord med) {
		logger.debug("Inside MedicalRecordRepository.update for : " + med.getFirstName(), med.getLastName());

		for (MedicalRecord m : medicalrecordList) {
			if (m.equals(med))

			{
				return m;
			}
		}
		return null;

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
