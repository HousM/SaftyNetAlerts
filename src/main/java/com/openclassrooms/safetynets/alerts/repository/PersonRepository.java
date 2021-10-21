package com.openclassrooms.safetynets.alerts.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynets.alerts.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

	List<Person> findPersonByFirstNameAndLastName(String firstName, String lastName);

	List<Person> findPersonByAddress(String address, Sort sort);

	List<Person> findPersonByCity(String city);

	Person findPersonById(int id);

}
