package com.example.medicalregister.repository;
import com.example.medicalregister.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {}
