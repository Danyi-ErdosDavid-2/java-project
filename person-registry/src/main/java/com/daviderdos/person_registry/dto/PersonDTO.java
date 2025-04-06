package com.daviderdos.person_registry.dto;

import java.util.List;

public class PersonDTO {
    public Long id;
    public String firstName;
    public String lastName;
    public List<AddressDTO> addresses;
}
