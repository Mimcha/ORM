package com.example.demo.controller;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // 1. Получение пользователей по городу
    @GetMapping("/by-city")
    public List<Person> getPersonsByCity(@RequestParam String city) {
        return personRepository.findAllByCity(city);
    }

}