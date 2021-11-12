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

import com.openclassrooms.safetynets.alerts.model.FireStation;
import com.openclassrooms.safetynets.alerts.service.FireStationService;

@RestController
public class FireStationController {
	private Logger logger = LoggerFactory.getLogger(FireStationController.class);

	/**
	 * FireStationService's implement class reference.
	 */
	@Autowired
	private FireStationService fireStationService;

	@GetMapping("/fireStation")
	public ResponseEntity<FireStation> getFireStation(@RequestParam("address") String address,
			@RequestParam("station") Integer station) throws Exception {
		logger.debug("FireStation GET Request on : {} {}", address, station);

		if (address == null || address.trim().length() == 0 || station == null) {
			throw new Exception("Bad request : missing or incomplete parameter");
		}
		FireStation fireDTO = fireStationService.getFireStationByKeyId(address, station);

		logger.info("FireStation GET Request - SUCCESS");
		return new ResponseEntity<>(fireDTO, HttpStatus.OK);
	}

	@PostMapping("/firestation")
	public ResponseEntity<FireStation> createFireStation(@RequestBody FireStation fireSt) throws Exception {
		logger.debug("FireStation POST Request with address : {} and station number : {}",
				fireSt.getAddress(), fireSt.getStation());

		if (fireSt.getAddress() == null || fireSt.getAddress().isEmpty()) {
			throw new Exception("Bad request : missing or incomplete body request");
		}
		FireStation fireStationCreated = fireStationService.createFireStation(fireSt);

		logger.info("FireStation POST request - SUCCESS");
		return new ResponseEntity<>(fireStationCreated, HttpStatus.CREATED);
	}

	@PutMapping("/firestation")
	public ResponseEntity<FireStation> updateFireStation(@RequestBody FireStation fireSt) throws Exception {
		logger.debug("FireStation PUT Request with address : {} and station number : {}",
				fireSt.getAddress(), fireSt.getStation());

		if (fireSt.getAddress() == null || fireSt.getAddress().isEmpty()) {
			throw new Exception("Bad request : missing or incomplete body request");
		}
		FireStation fireStationUpdated = fireStationService.updateFireStation(fireSt);

		logger.info("FireStation PUT request - SUCCESS");
		return new ResponseEntity<>(fireStationUpdated, HttpStatus.OK);
	}

	@DeleteMapping("/firestation")
	public ResponseEntity<Void> deleteFireStation(@RequestParam("address") String address,
			@RequestParam("station") Integer station) throws Exception {
		logger.debug("FireStation DELETE Request on : {} {}", address, station);

		if (address == null || address.trim().length() == 0 || station == null) {
			throw new Exception("Bad request : missing or incomplete parameter");
		}
		fireStationService.deleteFireStation(address, station);

		logger.info("FireStation DELETE request - SUCCESS");
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
