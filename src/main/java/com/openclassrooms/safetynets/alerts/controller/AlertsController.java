package com.openclassrooms.safetynets.alerts.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetynets.alerts.dto.FireDTO;
import com.openclassrooms.safetynets.alerts.dto.FloodDTO;
import com.openclassrooms.safetynets.alerts.dto.PersonDTO;
import com.openclassrooms.safetynets.alerts.service.AlertsService;

@RestController
public class AlertsController {

	private Logger logger = LogManager.getLogger(AlertsController.class);

	@Autowired
	private AlertsService alertsService;

	@GetMapping("/persondto")

	public ResponseEntity<PersonDTO> getPersonsByStation(@RequestParam("stationNumber") Integer station)
			throws Exception {
		logger.debug("GET Request on /firestation with station number {}", station);

		if (station == null) {
			throw new Exception("Bad request : missing station parameter");
		}
		PersonDTO personsByStationDTO = alertsService.getPersonsByStation(station);

		logger.info("GET Request on /firestation - SUCCESS");
		return new ResponseEntity<>(personsByStationDTO, HttpStatus.OK);
	}

	@GetMapping("/firedto")
	public ResponseEntity<FireDTO> getPersonsByAddress(@RequestParam("address") String address) throws Exception {

		logger.debug("GET Request on /fire with address {}", address);

		if (address.trim().length() == 0) {
			throw new Exception("Bad request : missing address parameter");
		}
		FireDTO fireDTO = alertsService.getPersonsByAddress(address);

		logger.info("GET Request on /fire - SUCCESS");
		return new ResponseEntity<>(fireDTO, HttpStatus.OK);
	}

	@GetMapping("/flood/stations")
	public ResponseEntity<FloodDTO> getHouseholdsByStation(@RequestParam("stations") List<Integer> stations)
			throws Exception {

		logger.debug("GET Request on /flood with stations numbers {}", stations);

		if (stations.isEmpty()) {
			throw new Exception("Bad request : missing stations parameters");
		}
		FloodDTO floodDTO = alertsService.getHouseholdsByStation(stations);

		logger.info("GET Request on /flood - SUCCESS");
		return new ResponseEntity<>(floodDTO, HttpStatus.OK);
	}

	@GetMapping("/personInfo")
	public ResponseEntity<PersonDTO> getPersonInfoByIdentity(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) throws Exception {

		logger.debug("GET Request on /personInfo with firstName {} and lastName {}", firstName, lastName);

		if (firstName.trim().length() == 0 || lastName.trim().length() == 0) {
			throw new Exception("Bad request : missing identity parameters");
		}
		PersonDTO personInfoDTO = alertsService.getPersonByIdentity(firstName, lastName);

		logger.info("GET Request on /personInfo - SUCCESS");
		return new ResponseEntity<>(personInfoDTO, HttpStatus.OK);
	}

}
