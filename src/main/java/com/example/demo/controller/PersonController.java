package com.example.demo.controller;
import com.example.demo.PersonService;
import com.example.demo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    // CRUD операции
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        return ResponseEntity.ok(personService.createPerson(person));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Person>> getPersonById(@PathVariable Long id) {
        return ResponseEntity.ok(personService.getPersonById(id));
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        return ResponseEntity.ok(personService.updatePerson(id, person));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    // Дополнительные методы
    @GetMapping("/city/{city}")
    public ResponseEntity<List<Person>> findByCity(@PathVariable String city) {
        return ResponseEntity.ok(personService.findByCity(city));
    }

    @GetMapping("/younger-than/{age}")
    public ResponseEntity<List<Person>> findYoungerThan(@PathVariable int age) {
        return ResponseEntity.ok(personService.findYoungerThan(age));
    }

    @GetMapping("/name-surname")
    public ResponseEntity<Optional<Person>> findByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        return ResponseEntity.ok(personService.findByNameAndSurname(name, surname));
    }
}
