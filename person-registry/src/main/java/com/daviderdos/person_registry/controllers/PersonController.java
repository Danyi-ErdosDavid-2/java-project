package com.daviderdos.person_registry.controllers;

import com.daviderdos.person_registry.dto.PersonDTO;
import com.daviderdos.person_registry.services.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/")
    public List<PersonDTO> getAll() {
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public PersonDTO getById(@PathVariable Long id) {
        return personService.getPersonById(id);
    }

    @PostMapping
    public PersonDTO create(@RequestBody PersonDTO dto) {
        return personService.savePerson(dto);
    }

    @PutMapping("/{id}")
    public PersonDTO update(@PathVariable Long id, @RequestBody PersonDTO dto) {
        dto.id = id;
        return personService.savePerson(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        personService.deletePerson(id);
    }
}
