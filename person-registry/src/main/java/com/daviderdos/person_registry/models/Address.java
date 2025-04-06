package com.daviderdos.person_registry.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "addresses", uniqueConstraints = @UniqueConstraint(columnNames = {"person_id", "type"}))
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    private String type;
    private String street;
    private String city;
    private String postalCode;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContactDetail> contactDetails = new ArrayList<>();

    @PrePersist
    @PreUpdate
    private void validateAddressType() {
        if (person == null || type == null) return;
        long count = person.getAddresses().stream()
            .filter(a -> a != this && type.equals(a.getType()))
            .count();
        if (count > 0) {
            throw new IllegalStateException("A személynek már van ilyen típusú címe: " + type);
        }
    }
    
}
