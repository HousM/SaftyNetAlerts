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

import com.openclassrooms.safetynets.alerts.dto.ChildDTO;
import com.openclassrooms.safetynets.alerts.dto.CommunityEmailDTO;
import com.openclassrooms.safetynets.alerts.dto.FireDTO;
import com.openclassrooms.safetynets.alerts.dto.FloodDTO;
import com.openclassrooms.safetynets.alerts.dto.PersonDTO;
import com.openclassrooms.safetynets.alerts.dto.PersonInfoDTO;
import com.openclassrooms.safetynets.alerts.dto.PhoneDTO;
import com.openclassrooms.safetynets.alerts.service.AlertsService;

@RestController
public class AlertsController {

	private Logger logger = LogManager.getLogger(AlertsController.class);

	@Autowired
	private AlertsService alertsService;

	// pour tester l'endpoint
	// http://localhost:8080/fireStation?stationNumber=<station_number>

	@GetMapping("/fireStation")
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

	// pour tester l'endpoint http://localhost:8080/childAlert?address=<address>

	@GetMapping("/childAlert")
	public ResponseEntity<ChildDTO> getChildByAddress(@RequestParam("address") final String address) throws Exception {

		logger.debug("GET Request on /childAlert with address {}", address);

		if (address.trim().length() == 0) {
			throw new Exception("Bad request : missing address parameter");
		}
		ChildDTO childAlertDTO = alertsService.getChildByAddress(address);

		logger.info("GET Request on /childAlert - SUCCESS");
		return new ResponseEntity<>(childAlertDTO, HttpStatus.OK);
	}

	// Pour tester http://localhost:8080/phoneAlert?firestation=<firestation_number>

	@GetMapping("/phoneAlert")
	public ResponseEntity<PhoneDTO> getPhonesByStation(@RequestParam("firestation") Integer station) throws Exception {

		logger.debug("GET Request on /phoneAlert with station number {}", station);

		if (station == null) {
			throw new Exception("Bad request : missing station parameter");
		}
		PhoneDTO phoneAlertDTO = alertsService.getPhonesByStation(station);

		logger.info("GET Request on /phoneAlert - SUCCESS");
		return new ResponseEntity<>(phoneAlertDTO, HttpStatus.OK);
	}

	// Pour tester http://localhost:8080/fire?address=<address>

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

	// Pour Tester http://localhost:8080/flood/stations?stations=<a list of
	// station_numbers>

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

	// Pour tester
	// http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>

	@GetMapping("/personInfo")
	public ResponseEntity<PersonInfoDTO> getPersonInfoByIdentity(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) throws Exception {

		logger.debug("GET Request on /personInfo with firstName {} and lastName {}", firstName, lastName);

		if (firstName.trim().length() == 0 || lastName.trim().length() == 0) {
			throw new Exception("Bad request : missing identity parameters");
		}
		PersonInfoDTO personInfoDTO = alertsService.getPersonByIdentity(firstName, lastName);

		logger.info("GET Request on /personInfo - SUCCESS");
		return new ResponseEntity<>(personInfoDTO, HttpStatus.OK);
	}

	// Pour tester http://localhost:8080/communityEmail?city=<city>

	@GetMapping("/communityEmail")
	public ResponseEntity<CommunityEmailDTO> getEmailsByCity(@RequestParam("city") String city) throws Exception {

		logger.debug("GET Request on /communityEmail with city {}", city);

		if (city.trim().length() == 0) {
			throw new Exception("Bad request : missing city parameter");
		}
		CommunityEmailDTO communityEmailDTO = alertsService.getEmailsByCity(city);

		logger.info("GET Request on /communityEmail - SUCCESS");
		return new ResponseEntity<>(communityEmailDTO, HttpStatus.OK);
	}

}
