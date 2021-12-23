package com.openclassrooms.safetynets.alerts.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.openclassrooms.safetynets.alerts.model.FireStation;
import com.openclassrooms.safetynets.alerts.model.MedicalRecord;
import com.openclassrooms.safetynets.alerts.model.Person;
import com.openclassrooms.safetynets.alerts.repository.ReadJsonData;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataTest {

	@Autowired
	private ReadJsonData dataStore;

	@Test
	public void whenGetPersonList_thenReturnExpectedPersonList() {
		List<Person> personList = dataStore.getPersonList();

		assertThat(personList).isNotEmpty();
		assertThat(personList.size()).isEqualTo(23);
	}

	@Test
	public void whenGetFireStationList_thenReturnExpectedFireStationList() {
		List<FireStation> fireStationList = dataStore.getFireStationList();

		assertThat(fireStationList).isNotEmpty();
		assertThat(fireStationList.size()).isEqualTo(13);
	}

	@Test
	public void whenGetMedicalRecordList_thenReturnExpectedMedicalRecordList() {
		List<MedicalRecord> medicalRecordList = dataStore.getMedicalRecordList();

		assertThat(medicalRecordList).isNotEmpty();
		assertThat(medicalRecordList.size()).isEqualTo(23);
	}

}
