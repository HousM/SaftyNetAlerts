package com.openclassrooms.safetynets.alerts.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetynets.alerts.model.FireStation;
import com.openclassrooms.safetynets.alerts.repository.FireStationRepository;

@Service
public class FireStationService {

	/**
	 * PersonService logger.
	 */
	private Logger logger = LoggerFactory.getLogger(FireStationService.class);

	/**
	 * FireStationRepository instance.
	 */
	@Autowired
	private FireStationRepository fireStationRepository;

	public FireStation createFireStation(FireStation fireSt) throws Exception {
		logger.debug("Inside FireStationService.createFireStation");
		FireStation fireFound = fireStationRepository.find(fireSt.getAddress(), fireSt.getStation());

		if (fireFound != null) {
			throw new Exception("FireStation already registered");
		}

		FireStation fireSaved = fireStationRepository.save(fireFound);

		return fireSaved;
	}

	public FireStation updateFireStation(FireStation fireSt) throws Exception {
		logger.debug("Inside FireStationService.updateFireStation");
		FireStation fireToUpdate = fireStationRepository.findByAddress(fireSt.getAddress());

		if (fireToUpdate == null) {
			throw new Exception("FireStation not found");
		}

		fireToUpdate.setStation(fireSt.getStation());

		return fireToUpdate;
	}

	public void deleteFireStation(String address, Integer station) throws Exception {
		logger.debug("Inside FireStationService.deleteFireStation");
		FireStation fireStationToDelete = fireStationRepository.find(address, station);

		if (fireStationToDelete == null) {
			throw new Exception("FireStation not found");
		}

		fireStationRepository.delete(fireStationToDelete);
	}

	public FireStation getFireStationByKeyId(String address, Integer station) throws Exception {
		logger.debug("Inside FireStationService.getFireStation for fireStation: " + address, station);
		FireStation fireStation = fireStationRepository.find(address, station);

		if (fireStation == null) {
			throw new Exception("Failed to get the fireStations mapped to address : " + address);
		}

		return fireStation;
	}

	public FireStation getFireStationByAddress(String address) throws Exception {
		logger.debug("Inside FireStationService.getFireStationByAddress for address :" + address);
		FireStation fireStation = fireStationRepository.findByAddress(address);

		if (fireStation == null) {
			throw new Exception("Failed to get the fireStations mapped to address : " + address);
		}

		return fireStation;
	}

	public List<String> getAddressesByStation(int station) throws Exception {
		logger.debug("Inside FireStationService.getAddressesByStation for station {}", station);
		List<FireStation> fireStations = fireStationRepository.findByStation(station);
		List<String> addresses = new ArrayList<>();

		if (fireStations.isEmpty()) {
			throw new Exception("Failed to get the addresses mapped to station : " + station);
		}

		for (FireStation fireS : fireStations) {
			addresses.add(fireS.getAddress());
		}

		return addresses;
	}
}
