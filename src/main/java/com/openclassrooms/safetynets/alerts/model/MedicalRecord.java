package com.openclassrooms.safetynets.alerts.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("monFiltreDynamique")
public class MedicalRecord {
	private String firstName;
	private String lastName;
	private String birthdate;

	private List<String> medicationsList = new ArrayList<String>();
	private List<String> allergiesList = new ArrayList<String>();

	// Default constructor
	public MedicalRecord(String firstName, String lastName, String birthdate, List<String> medicationsList,
			List<String> allergiesList) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.medicationsList = medicationsList;
		this.allergiesList = allergiesList;

	}

	public String getFirstName() {
		return firstName;

	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;

	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public void addMedications(String medication) {
		medicationsList.add(medication);
	}

	public void addAllergies(String allergie) {
		allergiesList.add(allergie);
	}

	public List<String> getMedicationsList() {
		return medicationsList;
	}

	public void setMedicationsList(List<String> medicationsList) {
		this.medicationsList = medicationsList;
	}

	public List<String> getAllergiesList() {
		return allergiesList;
	}

	public void setAllergiesList(List<String> allergiesList) {
		this.allergiesList = allergiesList;
	}

}
