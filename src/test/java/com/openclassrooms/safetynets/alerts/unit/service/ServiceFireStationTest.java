package com.openclassrooms.safetynets.alerts.unit.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openclassrooms.safetynets.alerts.model.FireStation;
import com.openclassrooms.safetynets.alerts.repository.FireStationRepository;
import com.openclassrooms.safetynets.alerts.service.FireStationService;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class ServiceFireStationTest {

	@InjectMocks
	private FireStationService fireStationService;

	@Mock
	private FireStation fireStation;
	@Mock
	private FireStationRepository fireStationRepositoryMock;

	@Test
	public void createFireStationtest() throws Exception {

		FireStation fire = new FireStation("NewAddress", 1);

		when(fireStationRepositoryMock.save(fire)).thenReturn(fire);

		FireStation fireToCreate = fireStationService.createFireStation(fire);

		assertNotNull(fireToCreate);
//		assertThat(fireToCreate).isEqualTo(fire);

	}

	@Test
	public void updateFireStationTest()
			throws Exception {

		FireStation fire = new FireStation("29 15th St", 2);

		when(fireStationRepositoryMock.update(any(FireStation.class))).thenReturn(fire);
		when(fireStationRepositoryMock.findByAddress(fire.getAddress())).thenReturn(fire);

		FireStation fireToUpdate = fireStationService.updateFireStation(fire);

		assertNotNull(fireToUpdate);
		assertEquals(fireToUpdate, fire);

	}

	@Test
	public void deleteFireStationTest()
			throws Exception {

		FireStation fire = new FireStation("NewAddress", 1);
		when(fireStationRepositoryMock.find(anyString(), anyInt())).thenReturn(fire);
		fireStationService.deleteFireStation(fire.getAddress(), fire.getStation());

	}

	@Test
	@DisplayName("Given a FireStation by address, when getFireStationByAddress, then the FireStation by address " +
			"should be Returned correctly")
	public void getFireStationByAddressTest()
			throws Exception {

		FireStation fire = new FireStation("NewAddress", 1);

		when(fireStationRepositoryMock.findByAddress(anyString())).thenReturn(fire);

		FireStation fireTofind = fireStationService.getFireStationByAddress("NewAddress");

		assertThat(fireTofind).isEqualTo(fire);

	}

	@Test
	@DisplayName("Given a FireStation key ID, when getFireStationByKeyId, then expected FireStation " +
			"should be returned correctly")
	public void getFireStationByKeyIdTest()
			throws Exception {

		FireStation fire = new FireStation("NewAddress", 1);

		when(fireStationRepositoryMock.find(anyString(), anyInt())).thenReturn(fire);

		FireStation fireTofind = fireStationService.getFireStationByKeyId(fire.getAddress(), fire.getStation());

		assertThat(fireTofind).isEqualTo(fire);

	}

	@Test
	public void getAddressesByStationTest()
			throws Exception {

		FireStation fire = new FireStation("NewAddress", 1);
		List<FireStation> fireStations = Arrays.asList(fire);

		when(fireStationRepositoryMock.findByStation(anyInt())).thenReturn(fireStations);

		List<String> fireTofind = fireStationService.getAddressesByStation(1);

		assertThat(fireTofind).isEqualTo(Arrays.asList("NewAddress"));

	}
}
