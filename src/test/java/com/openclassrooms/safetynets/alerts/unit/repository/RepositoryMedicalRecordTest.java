package com.openclassrooms.safetynets.alerts.unit.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openclassrooms.safetynets.alerts.model.MedicalRecord;
import com.openclassrooms.safetynets.alerts.repository.MedicalRecordRepository;
import com.openclassrooms.safetynets.alerts.repository.ReadJsonData;

@ExtendWith(MockitoExtension.class)
public class RepositoryMedicalRecordTest {

	@InjectMocks
	MedicalRecordRepository medicalRecordRepository;

	@Mock
	private static MedicalRecord med1;
	@Mock
	private static MedicalRecord med2;

	@Mock
	private ReadJsonData dataStore;

	@Before
	public void setUp() {
		med1 = new MedicalRecord("John", "Boyd", "03/06/1984",
				Arrays.asList("aznol:350mg"), Arrays.asList("nillacilan"));
		med2 = new MedicalRecord("Tony", "Cooper", "04/20/2002",
				Arrays.asList("ibupurin:200mg"), Arrays.asList("peanut"));

		when(dataStore.getMedicalRecordList()).thenReturn(Arrays.asList(med1, med2));

		medicalRecordRepository = new MedicalRecordRepository(dataStore);
	}

	@Test
	@Tag("Save")
	@DisplayName("Given a MedicalRecord, when save, then MedicalRecord should be saved successfully")
	public void givenAMedicalRecord_whenSave_thenMedicalRecordShouldBeSaveCorrectly() {
		MedicalRecord med3 = new MedicalRecord("Paul", "Duncan", "02/11/1980",
				Arrays.asList(""), Arrays.asList("shellfish"));

		MedicalRecord medSaved = medicalRecordRepository.save(med3);

		assertThat(medSaved).isEqualTo(med3);
	}

	@Test
	@Tag("Update")
	public void UpdateTest() {
		MedicalRecord med3 = new MedicalRecord("Paul", "Duncan", "02/11/1980",
				Arrays.asList(""), Arrays.asList("shellfish"));

		MedicalRecord medUpdate = medicalRecordRepository.update(med3);

		assertThat(medUpdate).isNull();

	}

	@Test
	@Tag("Delete")
	@DisplayName("Given a MedicalRecord, when delete, then MedicalRecord should be deleted successfully")
	public void givenAMedicalRecord_whenDelete_thenMedicalRecordShouldBeDeleteCorrectly() {
		medicalRecordRepository.delete(med1);

		assertThat(medicalRecordRepository.findByIdentity("John", "Boyd")).isEqualTo(null);
	}

	@Test
	@Tag("FindByIdentity")
	@DisplayName("Given an unregistered person, when findByIdentity, then return null")
	public void givenAnUnRegisteredIdentity_whenFindByIdentity_thenReturnNull() {
		MedicalRecord medFound = medicalRecordRepository.findByIdentity("Paul", "Duncan");

		assertThat(medFound).isEqualTo(null);
	}

}
