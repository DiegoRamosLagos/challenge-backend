package com.example.challenge.rest.controller;

import com.example.challenge.rest.entity.Person;
import com.example.challenge.rest.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/challenge/person")
public class PersonController {
    @Autowired
    private IPersonService personService;

    @GetMapping()
    public ResponseEntity<?> getAll() {
        return new ResponseEntity(personService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{rut}")
    public ResponseEntity<?> getPersonDetails(@Valid @PathVariable String rut) {
        Person selected = null;
        Map<String, Object> response = new HashMap<>();
        try {
            selected = personService.getPersonByRut(rut);
        }catch (DataAccessException exception) {
            response.put("message", "Error on DB");
            response.put("error", exception.getMessage().concat(": ")
                    .concat(exception.getMostSpecificCause().getMessage()));
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (selected == null) {
            response.put("message", "The user is not exist");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(selected, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> savePerson(@Valid @RequestBody Person person, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result
                    .getFieldErrors()
                    .stream()
                    .map(fieldError -> "The field '" + fieldError.getField() + "' " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(personService.save(person), HttpStatus.CREATED);
    }

}
