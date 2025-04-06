package com.daviderdos.person_registry.services;

import com.daviderdos.person_registry.dto.AddressDTO;
import com.daviderdos.person_registry.dto.ContactDetailDTO;
import com.daviderdos.person_registry.dto.PersonDTO;
import com.daviderdos.person_registry.models.Address;
import com.daviderdos.person_registry.models.ContactDetail;
import com.daviderdos.person_registry.models.Person;
import com.daviderdos.person_registry.repositories.PersonRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonDTO> getAllPersons() {
        return personRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public Person getPersonById(Long id) {
        return personRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Person not found."));
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
    
    private PersonDTO toDto(Person person) {
        PersonDTO dto = new PersonDTO();
        dto.id = person.getId();
        dto.firstName = person.getFirstName();
        dto.lastName = person.getLastName();
        dto.addresses = person.getAddresses().stream().map(address -> {
            AddressDTO a = new AddressDTO();
            a.type = address.getType();
            a.street = address.getStreet();
            a.city = address.getCity();
            a.postalCode = address.getPostalCode();
            a.contactDetails = address.getContactDetails().stream().map(cd -> {
                ContactDetailDTO c = new ContactDetailDTO();
                c.type = cd.getType();
                c.value = cd.getValue();
                return c;
            }).collect(Collectors.toList());
            return a;
        }).collect(Collectors.toList());
        return dto;
    }

    private Person fromDto(PersonDTO dto) {
        Person p = new Person();
        p.setId(dto.id);
        p.setFirstName(dto.firstName);
        p.setLastName(dto.lastName);
        if (dto.addresses != null) {
            List<Address> addresses = dto.addresses.stream().map(addrDto -> {
                Address a = new Address();
                a.setType(addrDto.type);
                a.setStreet(addrDto.street);
                a.setCity(addrDto.city);
                a.setPostalCode(addrDto.postalCode);
                a.setPerson(p);
                if (addrDto.contactDetails != null) {
                    List<ContactDetail> contacts = addrDto.contactDetails.stream().map(cdDto -> {
                        ContactDetail c = new ContactDetail();
                        c.setType(cdDto.type);
                        c.setValue(cdDto.value);
                        c.setAddress(a);
                        return c;
                    }).collect(Collectors.toList());
                    a.setContactDetails(contacts);
                }
                return a;
            }).collect(Collectors.toList());
            p.setAddresses(addresses);
        }
        return p;
    }
}
