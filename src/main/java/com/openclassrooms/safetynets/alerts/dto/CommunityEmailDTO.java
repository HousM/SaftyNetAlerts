package com.openclassrooms.safetynets.alerts.dto;

import java.util.List;

public class CommunityEmailDTO {
	private List<String> emails;

	/**
	 * Constructor of class CommunityEmailDTO. Initialize emails.
	 * 
	 */
	public CommunityEmailDTO(final List<String> emails) {
		this.emails = emails;
	}

	/**
	 * Empty constructor of class CommunityEmailDTO.
	 */
	public CommunityEmailDTO() {
	}

	/**
	 * Getter of CommunityEmailDTO.emails.
	 * 
	 */
	public List<String> getEmails() {
		return emails;
	}

	/**
	 * Setter of CommunityEmailDTO.emails.
	 * 
	 */
	public void setEmails(final List<String> emails) {
		this.emails = emails;
	}
}
