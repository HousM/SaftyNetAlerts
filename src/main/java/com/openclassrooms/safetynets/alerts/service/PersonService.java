package com.openclassrooms.safetynets.alerts.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.openclassrooms.safetynets.alerts.model.Email;
import com.openclassrooms.safetynets.alerts.model.Person;

public interface PersonService {

	Iterable<Person> listAllPersonns();

	public Person save(Person Person);

	public Iterable<Person> save(List<Person> people);

	public List<Person> getAllInformationsForPersonnAtAnAddress(String address);

	public List<Person> findPersonByAddress(String address);

	public List<Email> getEmailPerCity(String city);

	@SuppressWarnings("rawtypes")
	public ResponseEntity deletePerson(String firstName, String lastName);

	public Person updatePerson(String firstName, String lastName, Person PersonDetails);

}
