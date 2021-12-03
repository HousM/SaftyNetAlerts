package com.openclassrooms.safetynets.unit.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openclassrooms.safetynets.alerts.model.FireStation;
import com.openclassrooms.safetynets.alerts.repository.FireStationRepository;
import com.openclassrooms.safetynets.alerts.repository.ReadJsonData;

@ExtendWith(MockitoExtension.class)
public class RepositoryFireTest {

	@InjectMocks
	private FireStationRepository fireStationRepository;

	@Mock
	private static FireStation fire1;
	@Mock
	private static FireStation fire2;

	@Mock
	private ReadJsonData dataStore;

	@Before
	public void setUp() {
		fire1 = new FireStation("29 15th St", 2);
		fire2 = new FireStation("644 Gershwin Cir", 1);

		when(dataStore.getFireStationList()).thenReturn(Arrays.asList(fire1, fire2));

		fireStationRepository = new FireStationRepository(dataStore);
	}

	@Test
	@Tag("Save")
	@DisplayName("Given a FireStation, when save, then FireStation should be saved successfully")
	public void givenAFireStation_whenSave_thenFireStationShouldBeSaveCorrectly() {
		FireStation fireStationToSave = new FireStation("480 Manchester St", 2);

		FireStation fireStationSaved = fireStationRepository.save(fireStationToSave);

		assertThat(fireStationSaved).isEqualTo(fireStationToSave);
	}

	@Test
	@Tag("Delete")
	@DisplayName("Given a FireStation, when delete, then FireStation should be deleted successfully")
	public void givenAFireStation_whenDelete_thenFireStationShouldBeDeleteCorrectly() {
		fireStationRepository.delete(fire2);

		assertThat(fireStationRepository.find(fire2.getAddress(), fire2.getStation())).isEqualTo(null);
	}

	@Test
	@Tag("Find")
	@DisplayName("Given an unregistered FireStation, when find, then return null")
	public void givenAUnRegisteredFireStation_whenFind_thenReturnNull() {
		FireStation unRegisteredFireStation = new FireStation("489 Manchester St", 1);

		FireStation fireStationFound = fireStationRepository.find(unRegisteredFireStation.getAddress(),
				unRegisteredFireStation.getStation());

		assertThat(fireStationFound).isEqualTo(null);
	}

	@Test
	@Tag("FindByAddress")
	@DisplayName("Given an unregistered address, when findByAddress, then return null")
	public void givenAnUnRegisteredAddress_whenFindByAddress_thenReturnNull() {
		String unRegisteredAddress = "947 E. Rose Dr";

		FireStation fireSFoundByAddress = fireStationRepository.findByAddress(unRegisteredAddress);

		assertThat(fireSFoundByAddress).isEqualTo(null);
	}

	@Test
	@Tag("FindByStation")
	@DisplayName("Given a station number, when findByStation, then return FireStation associated with that station number")
	public void givenAStationNumber_whenFindByStation_thenReturnFireStationAssociatedWithThatStationNumber() {
		List<FireStation> fireSFoundByStation = fireStationRepository.findByStation(1);

		assertThat(fireSFoundByStation.contains(fire2));
	}

}
