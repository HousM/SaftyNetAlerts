package com.openclassrooms.safetynets.alerts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.openclassrooms.safetynets.alerts.dto.FireDTO;
import com.openclassrooms.safetynets.alerts.dto.FloodDTO;
import com.openclassrooms.safetynets.alerts.dto.PersonDTO;
import com.openclassrooms.safetynets.alerts.dto.PhoneDTO;
import com.openclassrooms.safetynets.alerts.service.AlertsService;

public class AlertsController {

	private Logger logger = LoggerFactory.getLogger(AlertsController.class);

	@Autowired
	AlertsService alertsService;

	@GetMapping("/firestation")

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

	@GetMapping("/fire")
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

	@GetMapping("/phoneAlert")
	public ResponseEntity<PhoneDTO> getPhonesByStation(@RequestParam("firestation") Integer station)
			throws Exception {

		logger.debug("GET Request on /phoneAlert with station number {}", station);

		if (station == null) {
			throw new Exception("Bad request : missing station parameter");
		}
		PhoneDTO phoneAlertDTO = alertsService.getPhonesByStation(station);

		logger.info("GET Request on /phoneAlert - SUCCESS");
		return new ResponseEntity<>(phoneAlertDTO, HttpStatus.OK);
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
