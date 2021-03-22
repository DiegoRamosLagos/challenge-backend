package com.example.challenge.rest.service.impl;

import com.example.challenge.rest.entity.Person;
import com.example.challenge.rest.repository.PersonRepository;
import com.example.challenge.rest.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements IPersonService {
    @Autowired
    private PersonRepository personRepository;
    @Override
    public Person getPersonByRut(String rut) {
        return personRepository.findByRut(rut);
    }

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }
}
