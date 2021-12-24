package com.openclassrooms.safetynets.alerts.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.openclassrooms.safetynets.alerts.dto.FireDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AlertsControlITTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	// URLS

	private final static String FIRE_URL = "/fire?address={address}";

	@Test
	public void givenAValidAddress_whenFireRequest_thenAccordingDTOAndOKStatusIsReturned() {
		ResponseEntity<FireDTO> response = restTemplate.getForEntity("http://localhost:" + port +
				FIRE_URL, FireDTO.class, "892 Downing Ct");
		assertNotNull(response);

	}

	@Test
	public void GivenEmptyAddress_whenFireRequest_thenReturnBadRequestStatus() {
		ResponseEntity<FireDTO> response = restTemplate.getForEntity(FIRE_URL, FireDTO.class,
				"");

		assertThat(response.getBody().getPersons()).isNull();
	}

}
