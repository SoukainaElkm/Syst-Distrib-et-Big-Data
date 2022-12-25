package com.example.infractionquery.repositories;

import com.example.infractionquery.entities.Infraction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InfractionRepository extends JpaRepository<Infraction,String> {
    public List<Infraction> findByMatriculeEquals(String matricule);
}
