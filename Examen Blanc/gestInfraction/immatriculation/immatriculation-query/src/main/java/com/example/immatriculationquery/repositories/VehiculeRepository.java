package com.example.immatriculationquery.repositories;

import com.example.immatriculationquery.entities.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehiculeRepository extends JpaRepository<Vehicule, String> {
    List<Vehicule> findByProprietaireIdEquals(String id);
}
