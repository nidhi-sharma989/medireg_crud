package com.example.medicalregister.service;
import com.example.medicalregister.model.Patient;
import com.example.medicalregister.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository repo;

    public List<Patient> findAll() { return repo.findAll(); }
    public Patient findById(Long id) { return repo.findById(id).orElse(null); }
    public void save(Patient patient) { repo.save(patient); }
    public void delete(Long id) { repo.deleteById(id); }
}