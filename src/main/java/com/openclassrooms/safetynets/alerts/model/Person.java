package com.openclassrooms.safetynets.alerts.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("monFiltreDynamique")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private int zip;
	private String phone;
	private String email;

	private String birthdate;
	private long age;
	@ElementCollection(targetClass = String.class)
	private List<String> medications;
	@ElementCollection(targetClass = String.class)
	private List<String> allergies;
	@ElementCollection(targetClass = String.class)
	private List<String> station;
	private List<String> medicationsList;
	private List<String> allergiesList;

	// Default constructor
	public Person(String firstName, String lastName, String address, String city, int zip, String phone,
			String email) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;

	}

	// Tests for constructor
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Person(String firstName, String lastName, long age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public Person(String firstName, String lastName, long age, List<String> medications, List<String> allergies) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.medications = medications;
		this.allergies = allergies;
	}

	public Person(String firstName, String lastName, String address, String phone) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
	}

	public Person(String lastName, String address, int age, String email, List<String> medicationsList,
			List<String> allergiesList) {

		this.lastName = lastName;
		this.address = address;
		this.age = age;
		this.email = email;
		this.setMedicationsList(medicationsList);
		this.setAllergiesList(allergiesList);
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
