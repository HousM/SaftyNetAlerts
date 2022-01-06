package com.openclassrooms.safetynets.alerts.dto;

import java.util.List;

import com.openclassrooms.safetynets.alerts.model.PersonInfo;

public class PersonInfoDTO {
	/**
	 * List composed of the person with the given identity as well as persons with
	 * the same name.
	 */
	private List<PersonInfo> personsInfo;

	/**
	 * Constructor of class PersonInfoDTO. Initialize personsInfo.
	 *
	 * @param personsInfo
	 */
	public PersonInfoDTO(final List<PersonInfo> personsInfo) {
		this.personsInfo = personsInfo;
	}

	/**
	 * Empty constructor of class PersonInfoDTO.
	 */
	public PersonInfoDTO() {
	}

	/**
	 * Getter of PersonInfoDTO.personsInfo.
	 *
	 * @return The list composed of the person with the given identity as well as
	 *         persons with the same name
	 */
	public List<PersonInfo> getPersonsInfo() {
		return personsInfo;
	}

	/**
	 * Setter of PersonInfoDTO.personsInfo.
	 *
	 * @param personsInfo list composed of the person with the given identity as
	 *                    well as persons with the same name
	 */
	public void setPersonsInfo(final List<PersonInfo> personsInfo) {
		this.personsInfo = personsInfo;
	}
}
