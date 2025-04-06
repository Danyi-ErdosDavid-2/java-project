package com.daviderdos.person_registry.dto;

import java.util.List;

public class AddressDTO {
    public String type;
    public String street;
    public String city;
    public String postalCode;
    public List<ContactDetailDTO> contactDetails;
}
