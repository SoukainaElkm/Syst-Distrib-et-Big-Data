package com.example.immatriculationquery.repositories;

import com.example.immatriculationquery.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, String> {
}
