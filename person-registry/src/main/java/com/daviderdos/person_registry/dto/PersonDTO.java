package com.daviderdos.person_registry.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    public Long id;
    public String firstName;
    public String lastName;
    public List<AddressDTO> addresses;
    
    public PersonDTO(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
