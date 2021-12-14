package com.openclassrooms.safetynets.alerts.dto;

import java.util.List;

import com.openclassrooms.safetynets.alerts.model.MedicalRecord;

public class MedicalRecordDTO {

	private String firstName;

	private String lastName;

	private String birthdate;

	private List<String> medications;

	private List<String> allergies;

	public MedicalRecordDTO(String firstName, String lastName, String birthDate,
			List<String> medications, List<String> allergies) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthDate;
		this.medications = medications;
		this.allergies = allergies;
	}

	public MedicalRecordDTO toMedicalRecordDTO(MedicalRecord med) {

		return new MedicalRecordDTO(med.getFirstName(), med.getLastName(), med.getBirthdate(),
				med.getMedicationsList(), med.getAllergiesList());
	}

	public MedicalRecordDTO() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getBirthDate() {
		return birthdate;
	}

	public void setBirthDate(final String birthDate) {
		this.birthdate = birthDate;
	}

	public List<String> getMedications() {
		return medications;
	}

	public void setMedications(final List<String> medications) {
		this.medications = medications;
	}

	public List<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(final List<String> allergies) {
		this.allergies = allergies;
	}

}
