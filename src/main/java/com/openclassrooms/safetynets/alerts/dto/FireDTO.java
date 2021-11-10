package com.openclassrooms.safetynets.alerts.dto;

import java.util.List;

import com.openclassrooms.safetynets.alerts.model.Person;

public class FireDTO {

	/**
	 * Fire station number which covers the given address.
	 */
	private int station;

	/**
	 * List of all persons living at the given address.
	 */
	private List<Person> persons;

	/**
	 * Constructor of class FireDTO.
	 *
	 */
	public FireDTO(int station, List<Person> persons) {
		this.station = station;
		this.persons = persons;
	}

	/**
	 * Empty constructor of class FireDTO.
	 */
	public FireDTO() {
	}

	/**
	 * Getter of FireDTO.station.
	 *
	 */
	public int getStation() {
		return station;
	}

	/**
	 * Setter of FireDTO.station.
	 * 
	 */
	public void setStation(int station) {
		this.station = station;
	}

	/**
	 * Getter of FireDTO.persons.
	 *
	 */
	public List<Person> getPersons() {
		return persons;
	}

	/**
	 * Setter of FireDTO.emails.
	 *
	 */
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

}
