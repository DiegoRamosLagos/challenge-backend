package com.example.challenge.rest.service;

import com.example.challenge.rest.entity.Person;

import java.util.List;

public interface IPersonService {
    Person getPersonByRut(String rut);
    List<Person> getAll();
    Person save(Person person);
}
