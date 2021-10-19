package com.openclassrooms.safetynets.alerts.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("monFiltreDynamique")
public class Firestation {
	private String address;
	private String station;
	private List<Person> personToSave = new ArrayList<Person>();

	public Firestation() {
	}

	public Firestation(String address, String station) {
		super();
		this.address = address;
		this.station = station;
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

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}
}
