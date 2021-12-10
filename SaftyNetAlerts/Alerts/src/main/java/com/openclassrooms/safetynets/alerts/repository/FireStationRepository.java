package com.openclassrooms.safetynets.alerts.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynets.alerts.model.FireStation;

@Repository
public class FireStationRepository {
	private Logger logger = LogManager.getLogger(FireStationRepository.class);
	/**
	 * Creates a HashMap instance to map FireStations data.
	 */
	private Map<String, FireStation> fireStationMap = new HashMap<>();

	@Autowired
	public FireStationRepository(ReadJsonData dataJson) {
		logger.debug("Map FireStationList");
		dataJson.getFireStationList().forEach(fireS -> fireStationMap.put(fireS.getAddress() + fireS.getStation(),
				fireS));
	}

	/**
	 * Adds the given fire station to fireStationMap.
	 */
	public FireStation save(FireStation fireSt) {
		logger.debug("Inside FireStationRepository.save for fireStation : " + fireSt.getAddress(),
				fireSt.getStation());
		fireStationMap.put(fireSt.getAddress() + fireSt.getStation(), fireSt);
		return fireStationMap.get(fireSt.getAddress() + fireSt.getStation());
	}

	/**
	 * Deletes the given fire station from fireStationMap.
	 */
	public void delete(FireStation fireSt) {
		logger.debug("Inside FireStationRepository.delete for fireStation : " + fireSt.getAddress(),
				fireSt.getStation());
		fireStationMap.remove(fireSt.getAddress() + fireSt.getStation());
	}

	/**
	 * Gets the fire station with the given key identifier from fireStationMap.
	 */
	public FireStation find(String address, Integer station) {
		logger.debug("Inside FireStationRepository.find for fireStation : " + address,
				station);
		return fireStationMap.get(address + station);
	}

	/**
	 * Loops through HashMap values in order to detect the fire station which covers
	 * the given address.
	 */
	public FireStation findByAddress(String address) {
		logger.debug("Inside FireStationRepository.findByAddress for address : " + address);
		Collection<FireStation> fireStations = fireStationMap.values();

		for (FireStation fireStation : fireStations) {
			if (fireStation.getAddress().equals(address)) {
				return fireStation;
			}
		}

		return null;
	}

	/**
	 * Loops through HashMap values in order to detect fire stations with the given
	 * station number.
	 */

	public List<FireStation> findByStation(int station) {
		logger.debug("Inside FireStationRepository.findByStation for station : " + station);
		Collection<FireStation> fireStations = fireStationMap.values();
		List<FireStation> fireStationsByStation = new ArrayList<>();

		for (FireStation fireS : fireStations) {
			if (fireS.getStation() == station) {
				fireStationsByStation.add(fireS);
			}
		}

		return fireStationsByStation;
	}
}
