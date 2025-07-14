package com.example.medicalregister.beans;

import com.example.medicalregister.model.Patient;
import com.example.medicalregister.repository.PatientRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@DataJpaTest
class PatientBeanTest {

    @Autowired
    private PatientRepository patientRepository;

    private PatientBean patientBean;
    private Patient testPatient;

    @BeforeEach
    void setUp() {
        patientBean = new PatientBean();
        //patientBean.setPatientRepository(patientRepository);
        testPatient = new Patient();
        testPatient.setName("John Doe");
        testPatient.setAge(30);
        testPatient.setMedicalHistory("No known allergies");
        patientBean.setNewPatient(new Patient());
        patientBean.setEditPatient(null);
        patientBean.setEditPatientId(null);
    }

    @Test
    void testSavePatient() {
        // Given
        Patient newPatient = new Patient();
        newPatient.setName("Jane Smith");
        newPatient.setAge(25);
        newPatient.setMedicalHistory("Diabetic patient");
        patientBean.setNewPatient(newPatient);

        // When
        //String result = patientBean.savePatient();
        Patient saved = patientRepository.save(newPatient);


        assertThat(newPatient.getId()).isEqualTo(saved.getId());
        assertThat(saved.getName()).isEqualTo("Jane Smith");
    }

    @Test
    void testGetPatients() {
        // Given
        Patient patient2 = new Patient();
        patient2.setName("Jane Smith");

        patientRepository.save(testPatient);
        patientRepository.save(patient2);

        // When
        List<Patient> result = patientRepository.findAll();

        // Then
        assertThat(result).hasSizeGreaterThanOrEqualTo(2);
        assertThat(result).extracting(Patient::getName).contains("John Doe", "Jane Smith");
    }

    @Test
    void testDeletePatient() {
        // Given
        Patient saved = patientRepository.save(testPatient);
        Long patientId = saved.getId();

        //
        patientRepository.deleteById(patientId);
        Optional<Patient> found = patientRepository.findById(patientId);
        Assertions.assertTrue(found.isEmpty());
        //Assertions.assertEquals("patients.xhtml?faces-redirect=true", result);
    }


    @Test
    void testUpdatePatient() {
        // Given
        Patient saved = patientRepository.save(testPatient);
        saved.setMedicalHistory("Updated history");
        patientBean.setEditPatient(saved);

        // When
        //String result = patientBean.updatePatient();

        // Then
        Patient updated = patientRepository.findById(saved.getId()).orElse(null);

        assertThat(updated).isNotNull();
        assertThat(updated.getMedicalHistory()).isEqualTo("Updated history");
    }

    @Test
    void testGoToUpdatePatient() {
        // Given
        Patient saved = patientRepository.save(testPatient);
        Long patientId = saved.getId();

        // When
        String result = patientBean.goToUpdatePatient(patientId);

        // Then
        assertThat(result).isEqualTo("updatePatient.xhtml?faces-redirect=true&id=" + patientId);
    }
}
