package com.example.incidentmanage.repository;

import com.example.incidentmanage.entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface IncidentRepository extends JpaRepository<Incident, Long> {

}
