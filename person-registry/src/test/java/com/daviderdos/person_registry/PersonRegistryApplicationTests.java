package com.daviderdos.person_registry;

import com.daviderdos.person_registry.dto.PersonDTO;
import com.daviderdos.person_registry.services.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.http.MediaType;


@SpringBootTest
@AutoConfigureMockMvc
class PersonRegistryApplicationTests {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    // GET All Person
    @Test
    public void testGetAllPersons() throws Exception {
        List<PersonDTO> persons = Arrays.asList(new PersonDTO(1L, "John", "Doe"));
        
        Mockito.when(personService.getAllPersons())
            .thenReturn(persons);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/persons/"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].firstName").value("John"))
            .andExpect(jsonPath("$[0].lastName").value("Doe"));
    }
    
    // GET Person
    @Test
    public void testGetPersonById() throws Exception {
        PersonDTO person = new PersonDTO(1L, "John", "Doe");
        
        Mockito.when(personService.getPersonById(1L))
            .thenReturn(person);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/persons/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.firstName").value("John"))
            .andExpect(jsonPath("$.lastName").value("Doe"));
    }
    
    // CREATE Person
    @Test
    public void testCreatePerson() throws Exception {
        PersonDTO personDTO = new PersonDTO(null, "Jane", "Doe");
        PersonDTO createdPerson = new PersonDTO(1L, "Jane", "Doe");
        
        Mockito.when(personService.savePerson(Mockito.any(PersonDTO.class)))
            .thenReturn(createdPerson);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/persons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(personDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.firstName").value("Jane"))
            .andExpect(jsonPath("$.lastName").value("Doe"));
    }
    
    // UPDATE Person
    @Test
    public void testUpdatePerson() throws Exception {
        PersonDTO existingPersonDTO = new PersonDTO(1L, "John", "Doe");
        PersonDTO updatedPersonDTO = new PersonDTO(1L, "Jane", "Doe");

        Mockito.when(personService.savePerson(Mockito.any(PersonDTO.class)))
            .thenReturn(updatedPersonDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/persons/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(updatedPersonDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.firstName").value("Jane"))
            .andExpect(jsonPath("$.lastName").value("Doe"));
    }
    
    // DELETE Person
    @Test
    public void testDeletePerson() throws Exception {
        Long personId = 1L;
        Mockito.doNothing().when(personService).deletePerson(personId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/persons/{id}", personId)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }
}
