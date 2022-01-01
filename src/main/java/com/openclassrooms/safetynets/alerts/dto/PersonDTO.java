package com.openclassrooms.safetynets.alerts.dto;

import java.util.List;

import com.openclassrooms.safetynets.alerts.model.FireStation;
import com.openclassrooms.safetynets.alerts.model.Person;

public class PersonDTO {

	/**
	 * The list of persons covered by the given fire station number.
	 */
	private List<Person> person;
	/**
	 * Count of adults inside the list of persons covered by the given fire station
	 * number.
	 */
	private int adultNumber;

	/**
	 * Count of children inside the list of persons covered by the given fire
	 * station number.
	 */
	private int childNumber;
	private String address;
	private List<Person> personAddress;
	private int station;
	private List<FireStation> households;

	public PersonDTO(List<Person> person, int adultNumber,
			int childNumber) {
		this.person = person;
		this.adultNumber = adultNumber;
		this.childNumber = childNumber;
	}

	public PersonDTO(List<Person> person) {
		this.person = person;

	}

	public PersonDTO(String address, List<Person> personAddress) {
		this.setAddress(address);
		this.setPersonAddress(personAddress);

	}

	public PersonDTO(Integer station, List<Person> personAddress) {
		this.setStation(station);
		this.setPersonAddress(personAddress);

	}

	public PersonDTO(int station, List<FireStation> households) {
		this.setStation(station);
		this.setHouseholds(households);

	}

//	public List<Person> getPersonInfo() {
//		return personsInfo;
//	}

	public List<Person> getPerson() {
		return person;
	}

	public void setPerson(List<Person> person) {
		this.person = person;
	}

	public int getAdultNumber() {
		return adultNumber;
	}

	public void setAdultNumber(int adultNumber) {
		this.adultNumber = adultNumber;
	}

	public int getChildNumber() {
		return childNumber;
	}

	public void setChildNumber(int childNumber) {
		this.childNumber = childNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Person> getPersonAddress() {
		return personAddress;
	}

	public void setPersonAddress(List<Person> personAddress) {
		this.personAddress = personAddress;
	}

	public int getStation() {
		return station;
	}

	public void setStation(int station) {
		this.station = station;
	}

	public List<FireStation> getHouseholds() {
		return households;
	}

	public void setHouseholds(List<FireStation> households) {
		this.households = households;
	}

}
