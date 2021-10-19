package com.openclassrooms.safetynets.alerts.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynets.alerts.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

}
