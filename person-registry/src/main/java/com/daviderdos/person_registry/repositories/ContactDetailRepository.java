package com.daviderdos.person_registry.repositories;

import com.daviderdos.person_registry.models.ContactDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDetailRepository extends JpaRepository<ContactDetail, Long> {}
