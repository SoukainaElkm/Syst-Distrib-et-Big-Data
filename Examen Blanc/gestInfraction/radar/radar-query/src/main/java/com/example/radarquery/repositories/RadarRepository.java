package com.example.radarquery.repositories;

import com.example.radarquery.entities.Radar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RadarRepository extends JpaRepository<Radar,String> {
}
