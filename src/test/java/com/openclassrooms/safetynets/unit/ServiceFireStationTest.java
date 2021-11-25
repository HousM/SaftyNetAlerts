package com.openclassrooms.safetynets.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.openclassrooms.safetynets.alerts.model.FireStation;
import com.openclassrooms.safetynets.alerts.repository.FireStationRepository;
import com.openclassrooms.safetynets.alerts.service.FireStationService;

@RunWith(MockitoJUnitRunner.class)
public class ServiceFireStationTest {

	@Mock
	private FireStationRepository fireStationRepositoryMock;

	@InjectMocks
	private FireStationService fireStationService;

	private FireStationRepository fireStationRepository;

	private static FireStation fire;

	private static FireStation fire1;

	private static FireStation fire2;

	List<FireStation> fireStations;

	@Before
	public void setUp() {

		fire1 = new FireStation("29 15th St", 2);
		fire2 = new FireStation("83 Binoc Ave", 2);
		fireStations = Arrays.asList(fire1, fire2);
	}

	@Test
	@Tag("CreateFireStation")
	@DisplayName("Given a FireStation, when createFireStation, then FireStation should be saved successfully")
	public void givenAFireStation_whenCreateFireStation_thenFireStationShouldBeSavedCorrectly() throws Exception {
		when(fireStationRepositoryMock.find(toString(), anyInt())).thenReturn(null);
		when(fireStationRepository.find("29 15th St", 2)).thenReturn(fire1);
		when(fireStationRepositoryMock.save(any(FireStation.class))).thenReturn(fire1);

		FireStation fireCreated = fireStationService.createFireStation(fire);

		assertNotNull(fireCreated);
		assertThat(fireCreated).isEqualTo(fire1);

		verify(fireStationRepositoryMock).find(toString(), anyInt());
		verify(fireStationRepository.find(fireCreated.getAddress(), fireCreated.getStation()));
		verify(fireStationRepositoryMock).save(any(FireStation.class));

	}

	@Test
	@Tag("UpdateFireStation")
	@DisplayName("Given a registered FireStation, when updateFireStation, then FireStation should be updated " +
			"correctly")
	public void givenARegisteredFireStation_whenUpdateFireStation_thenFireStationShouldBeUpdatedCorrectly()
			throws Exception {
		new FireStation("29 15th St", 1);
		when(fireStationRepositoryMock.findByAddress(toString())).thenReturn(fire1);

		FireStation fireUpdated = fireStationService.updateFireStation(fire);

		assertThat(fireUpdated.getStation()).isEqualTo(1);

		verify(fireStationRepositoryMock).findByAddress(toString());
	}

	@Test
	@Tag("DeleteFireStation")
	@DisplayName("Given a registered FireStation, when deleteFireStation, then delete process should be done in " +
			"correct order")
	public void givenARegisteredFireStation_whenDeleteFireStation_thenDeletingShouldBeDoneInCorrectOrder()
			throws Exception {
		when(fireStationRepositoryMock.find(toString(), anyInt())).thenReturn(fire1);

		fireStationService.deleteFireStation(fire1.getAddress(), fire1.getStation());

		verify(fireStationRepositoryMock).find(toString(), anyInt());
		verify(fireStationRepositoryMock).delete(any(FireStation.class));
	}

	@Test
	@Tag("GetFireStationByAddress")
	@DisplayName("Given a FireStation by address, when getFireStationByAddress, then the FireStation by address " +
			"should be Returned correctly")
	public void givenAFireStationByAddress_whenGetFireStationByAddress_thenFireStationByAddressShouldBeReturnCorrectly()
			throws Exception {
		when(fireStationRepositoryMock.findByAddress(toString())).thenReturn(fire1);

		FireStation fireByAddress = fireStationService.getFireStationByAddress(toString());

		assertThat(fireByAddress).isEqualTo(fire1);
		verify(fireStationRepositoryMock).findByAddress(toString());
	}

	@Test
	@Tag("GetAddressesByStation")
	@DisplayName("Given an addresses by station list, when getAddressesByStation, then the addresses by station list " +
			"should be returned correctly")
	public void givenAnAddressList_whenGetAddressesByStation_thenReturnExpectedAddressList() throws Exception {
		when(fireStationRepositoryMock.findByStation(anyInt())).thenReturn(fireStations);
		List<String> expectedAddresses = Arrays.asList("29 15th St", "83 Binoc Ave");

		List<String> addresses = fireStationService.getAddressesByStation(anyInt());

		assertThat(addresses).isEqualTo(expectedAddresses);
		assertThat(addresses.get(1)).isEqualTo("83 Binoc Ave");
		verify(fireStationRepositoryMock).findByStation(anyInt());
	}

	@Test
	@Tag("GetFireStationByKeyId")
	@DisplayName("Given a FireStation key ID, when getFireStationByKeyId, then expected FireStation " +
			"should be returned correctly")
	public void givenFireStationKeyId_whenGetFireStationByKeyId_thenExpectedFireStationShouldBeReturnCorrectly()
			throws Exception {
		when(fireStationRepositoryMock.find(toString(), anyInt())).thenReturn(fire1);

		FireStation fireByIdFound = fireStationService.getFireStationByKeyId(fire1.getAddress(), fire1.getStation());

		assertThat(fireByIdFound).isEqualTo(fire);
		verify(fireStationRepositoryMock).find(toString(), anyInt());

	}
}
