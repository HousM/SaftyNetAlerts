package com.openclassrooms.safetynets.unit.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.openclassrooms.safetynets.alerts.dto.FireDTO;
import com.openclassrooms.safetynets.alerts.model.FireStation;
import com.openclassrooms.safetynets.alerts.repository.FireStationRepository;
import com.openclassrooms.safetynets.alerts.service.FireStationService;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class ServiceFireStationTest {

	@Spy
	private FireStationRepository fireStationRepositoryMock;

	@Mock
	private FireStationService fireStationService;

	@Mock
	private FireStation fire1;
	@Mock
	private FireStation fire2;
	@Mock
	List<FireStation> fireStations;

	@Mock
	private FireDTO firedto;

	@Autowired
	private MockMvc mockMvc;

//	@Test
//	public void createFireStationTest() throws Exception {
//		FireStation firestationToSave = new FireStation("NewAddress", 1);
//		when(fireStationRepositoryMock.find("NewAddress", 1)).thenReturn(firestationToSave);
//
//		mockMvc.perform(MockMvcRequestBuilders.get("/firestations")
//				.param("address", "29 15th St")
//				.param("station", "2"))
//				.andExpect(status().isOk());
//
//		verify(fireStationService).createFireStation(any(FireStation.class));
//	}

	@Test
	@DisplayName("Given a FireStation, when createFireStation, then FireStation should be saved successfully")
	public void createFireStationtes1() throws Exception {
		FireDTO firestationDTO = new FireDTO("NewAddress", 1);
		FireStation fire = new FireStation("NewAddressNew", 2);
		when(fireStationRepositoryMock.find(anyString(), anyInt())).thenReturn(null);
		when(fireStationRepositoryMock.save(any(FireStation.class))).thenReturn(fire1);
		when(firedto.toFireDTO(any(FireStation.class))).thenReturn(firestationDTO);

		FireStation fireCreated = fireStationService.createFireStation(fire);

		assertNotNull(fireCreated);
		assertThat(fireCreated).isEqualTo(fire);
		InOrder inOrder = Mockito.inOrder(fireStationRepositoryMock, firedto);
		inOrder.verify(fireStationRepositoryMock).find(anyString(), anyInt());
		inOrder.verify(fireStationRepositoryMock).save(any(FireStation.class));
		inOrder.verify(firedto).toFireDTO(any(FireStation.class));
	}

	@Test
	@DisplayName("Save Firestation: success cases")
	void createFireStationtes2() throws Exception {
		// Arrange
		FireStation firestationToSave = new FireStation("NewAddress", 1);
		FireStation firestationExpected = new FireStation("NewAddress", 1);
		doNothing().when(fireStationRepositoryMock).save(firestationToSave);
		when(fireStationRepositoryMock.findByAddress("NewAddress")).thenReturn(firestationToSave);

		// Act
		FireStation result = fireStationService.createFireStation(firestationToSave);

		// Assert
		assertNotNull(result);
		assertEquals(firestationExpected, result);
		verify(fireStationService).createFireStation(any(FireStation.class));
//		verify(fireStationRepositoryMock, times(1)).save(result);

	}

	@Test
	@DisplayName("Given a registered FireStation, when updateFireStation, then FireStation should be updated " +
			"correctly")
	public void updateFireStationTest()
			throws Exception {

		FireStation firestationToUpdate = new FireStation("address", 2);
		doNothing().when(fireStationRepositoryMock).save(firestationToUpdate);
		// Act
		FireStation result = fireStationService.updateFireStation(firestationToUpdate);
		// Assert
		assertNotNull(result);
		assertEquals(firestationToUpdate, result);

	}

	@Test
	@DisplayName("Given a registered FireStation, when deleteFireStation, then delete process should be done in " +
			"correct order")
	public void deleteFireStationTest()
			throws Exception {
		fire1 = new FireStation("29 15th St", 2);
		fireStationService.deleteFireStation(fire1.getAddress(), fire1.getStation());
		assertThat(fireStationRepositoryMock.find(fire1.getAddress(), fire1.getStation())).isEqualTo(null);

	}

	@Test
	@DisplayName("Given a FireStation by address, when getFireStationByAddress, then the FireStation by address " +
			"should be Returned correctly")
	public void getFireStationByAddressTest()
			throws Exception {
		fire1 = new FireStation("29 15th St", 2);
		when(fireStationRepositoryMock.findByAddress(anyString())).thenReturn(fire1);

		FireStation fireByAddress = fireStationService.getFireStationByAddress(anyString());

		assertThat(fireByAddress).isEqualTo(fire1);
//		verify(fireStationService).getFireStationByAddress(anyString());
	}

	@Test
	@DisplayName("Given an addresses by station list, when getAddressesByStation, then the addresses by station list " +
			"should be returned correctly")
	public void getAddressesByStationTest() throws Exception {
		fire1 = new FireStation("29 15th St", 2);
		fire2 = new FireStation("83 Binoc Ave", 2);
		fireStations = Arrays.asList(fire1, fire2);
		when(fireStationRepositoryMock.findByStation(anyInt())).thenReturn(fireStations);

		List<String> addresses = fireStationService.getAddressesByStation(anyInt());

		assertThat(addresses).isEqualTo(fireStations);
		assertThat(addresses.get(1)).isEqualTo("83 Binoc Ave");

	}

	@Test
	@DisplayName("Given a FireStation key ID, when getFireStationByKeyId, then expected FireStation " +
			"should be returned correctly")
	public void getFireStationByKeyIdTest()
			throws Exception {
		fire1 = new FireStation("29 15th St", 2);
		when(fireStationRepositoryMock.find(anyString(), anyInt())).thenReturn(fire1);

		FireStation fireByIdFound = fireStationService.getFireStationByKeyId(fire1.getAddress(), fire1.getStation());

		assertThat(fireByIdFound).isEqualTo(fire1);
//		verify(fireStationRepositoryMock).find(anyString(), anyInt());

	}
}
