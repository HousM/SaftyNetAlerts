package com.openclassrooms.safetynets.alerts.dto;

import java.util.List;

import com.openclassrooms.safetynets.alerts.model.FireStation;
import com.openclassrooms.safetynets.alerts.model.Person;

public class FloodDTO {

	/**
	 * List of households covered by given fire stations.
	 */

	private List<FireStation> householdsByStations;
	private int station;
	private List<Person> households;

	public FloodDTO(List<FireStation> householdsByStations) {
		this.householdsByStations = householdsByStations;
	}

	public FloodDTO(int station, List<Person> households) {
		this.setStation(station);
		this.setHouseholds(households);

	}

	public List<FireStation> gethouseholdsByStations() {
		return householdsByStations;
	}

	public void sethouseholds(List<FireStation> householdsByStations) {
		this.householdsByStations = householdsByStations;
	}

	public int getStation() {
		return station;
	}

	public void setStation(int station) {
		this.station = station;
	}

	public List<Person> getHouseholds() {
		return households;
	}

	public void setHouseholds(List<Person> households) {
		this.households = households;
	}
}
