package com.openclassrooms.safetynets.alerts.model;

import java.util.ArrayList;
import java.util.List;

public class FireStation {
	private String address;
	private int station;
	private List<Person> personToSave = new ArrayList<Person>();
	private List<Person> personAddress;
	private List<FireStation> households;
	private List<FireStation> fireStation = new ArrayList<FireStation>();;

	// Default constructor
	public FireStation(String address, int station) {
		super();
		this.address = address;
		this.station = station;
	}

	public FireStation(String address, List<Person> personAddress) {
		this.address = address;
		this.setPersonAddress(personAddress);
	}

	public FireStation(int station, List<FireStation> households) {
		this.station = station;
		this.setHouseholds(households);
	}

	public void addFireSatation(FireStation fireSt) {
		fireStation.add(fireSt);

	}

	public void addPerson(Person person) {
		personToSave.add(person);
	}

	public List<Person> getPersonToSave() {
		return personToSave;
	}

	public void setPersonToSave(List<Person> personToSave) {
		this.personToSave = personToSave;

	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStation() {
		return station;
	}

	public void setStation(int station) {
		this.station = station;
	}

	public List<Person> getPersonAddress() {
		return personAddress;
	}

	public void setPersonAddress(List<Person> personAddress) {
		this.personAddress = personAddress;
	}

	public List<FireStation> getHouseholds() {
		return households;
	}

	public void setHouseholds(List<FireStation> households) {
		this.households = households;
	}
}
