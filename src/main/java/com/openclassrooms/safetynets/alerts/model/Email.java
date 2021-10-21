package com.openclassrooms.safetynets.alerts.model;

import lombok.Data;

@Data
public class Email {
	private String email;
	private String city;

	public Email(String email, String city) {
		this.setEmail(email);
		this.setCity(city);
	}

	public Email() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
