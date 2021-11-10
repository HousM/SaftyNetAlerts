package com.openclassrooms.safetynets.alerts.dto;

import java.util.List;

public class PhoneDTO {
	/**
	 * The list of persons covered by the given fire station number.
	 */
	private List<String> phones;

	/**
	 * Constructor of class PhoneDTO. Initialize phones
	 */
	public PhoneDTO(List<String> phones) {
		this.phones = phones;
	}

	/**
	 * Empty constructor of class PhoneDTO.
	 */
	public PhoneDTO() {
	}

	/**
	 * Getter of PhoneDTO
	 */
	public List<String> getPhones() {
		return phones;
	}

	/**
	 * Setter of PhoneDTO
	 */
	public void setPhones(List<String> phones) {
		this.phones = phones;
	}
}
