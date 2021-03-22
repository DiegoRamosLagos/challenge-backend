package com.example.challenge.rest.repository;

import com.example.challenge.rest.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByRut(String rut);
}
