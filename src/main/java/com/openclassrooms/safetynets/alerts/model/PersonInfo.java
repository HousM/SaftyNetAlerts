package com.openclassrooms.safetynets.alerts.model;

import java.util.List;

public class PersonInfo {

	/**
	 * Last name of the person.
	 */
	private String lastName;

	/**
	 * Address where the person lives.
	 */
	private String address;

	/**
	 * Age of the person.
	 */
	private int age;

	/**
	 * Email of the person.
	 */
	private String email;

	/**
	 * List of medications needed by the person.
	 */
	private List<String> medications;

	/**
	 * List of allergies of the person.
	 */
	private List<String> allergies;

	/**
	 * Constructor of class PersonInfo. Initialize lastName, address, age, email,
	 * medications and allergies.
	 *
	 * @param lastName    last name of the person
	 * @param address     address where the person lives
	 * @param age         age of the person
	 * @param email       email of the person
	 * @param medications list of medications needed by the person
	 * @param allergies   list of allergies of the person
	 */
	public PersonInfo(final String lastName, final String address, final int age, final String email,
			final List<String> medications, final List<String> allergies) {
		this.lastName = lastName;
		this.address = address;
		this.age = age;
		this.email = email;
		this.medications = medications;
		this.allergies = allergies;
	}

	/**
	 * Getter of PersonInfo.lastName.
	 *
	 * @return The last name of the person
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter of PersonInfo.lastName.
	 *
	 * @param lastName last name of the person to set
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter of PersonInfo.address.
	 *
	 * @return The address where the person lives
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Setter of PersonInfo.address.
	 *
	 * @param address address where the person lives to set
	 */
	public void setAddress(final String address) {
		this.address = address;
	}

	/**
	 * Getter of PersonInfo.age.
	 *
	 * @return The age of the person
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Setter of PersonInfo.age.
	 *
	 * @param age age of the person to set
	 */
	public void setAge(final int age) {
		this.age = age;
	}

	/**
	 * Getter of PersonInfo.email.
	 *
	 * @return The email of the person
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter of PersonInfo.email.
	 *
	 * @param email email of the person to set
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * Getter of PersonInfo.medications.
	 *
	 * @return The list of medications needed by the person
	 */
	public List<String> getMedications() {
		return medications;
	}

	/**
	 * Setter of PersonInfo.medications.
	 *
	 * @param medications list of medications needed by the person to set
	 */
	public void setMedications(final List<String> medications) {
		this.medications = medications;
	}

	/**
	 * Getter of PersonInfo.allergies.
	 *
	 * @return The list of allergies of the person
	 */
	public List<String> getAllergies() {
		return allergies;
	}

	/**
	 * Setter of PersonInfo.allergies.
	 *
	 * @param allergies list of allergies of the person to set
	 */
	public void setAllergies(final List<String> allergies) {
		this.allergies = allergies;
	}
}
