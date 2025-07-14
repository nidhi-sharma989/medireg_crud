package com.example.medicalregister.beans;

import com.example.medicalregister.model.Patient;
import com.example.medicalregister.repository.PatientRepository;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

@Named("patientBean")
@ViewScoped
public class PatientBean implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(PatientBean.class);

    private Patient newPatient = new Patient();
    private Patient editPatient;
    private Long editPatientId;

   @Autowired
    private PatientRepository patientRepository;

    public Patient getNewPatient() {
        return newPatient;
    }

    public void setNewPatient(Patient newPatient) {
        this.newPatient = newPatient;
    }

    public Patient getEditPatient() {
        return editPatient;
    }

    public void setEditPatient(Patient editPatient) {
        this.editPatient = editPatient;
    }

    public Long getEditPatientId() {
        return editPatientId;
    }

    public void setEditPatientId(Long editPatientId) {
        this.editPatientId = editPatientId;
    }

    public String savePatient() {
        logger.info("Attempting to save new patient: {}", newPatient.getName());
        try {
            patientRepository.save(newPatient);
            logger.info("Successfully saved patient with ID: {} and name: {}", newPatient.getId(), newPatient.getName());
            newPatient = new Patient(); // Reset form
            return "patients.xhtml?faces-redirect=true";
        } catch (Exception e) {
            logger.error("Error saving patient: {}", newPatient.getName(), e);
            throw e;
        }
    }

    public List<Patient> getPatients() {
        logger.debug("Retrieving all patients from database");
        List<Patient> patients = patientRepository.findAll();
        logger.debug("Retrieved {} patients", patients.size());
        return patients;
    }

    public String deletePatient(Long id) {
        logger.info("Attempting to delete patient with ID: {}", id);
        try {
            patientRepository.deleteById(id);
            logger.info("Successfully deleted patient with ID: {}", id);
            return "patients.xhtml?faces-redirect=true";
        } catch (Exception e) {
            logger.error("Error deleting patient with ID: {}", id, e);
            throw e;
        }
    }

    public void loadPatient(Long id) {
        logger.debug("Loading patient with ID: {}", id);
        this.editPatient = patientRepository.findById(id).orElse(null);
        if (this.editPatient != null) {
            logger.debug("Successfully loaded patient: {}", this.editPatient.getName());
        } else {
            logger.warn("Patient with ID {} not found", id);
        }
    }

    public String updatePatient() {
        logger.info("Attempting to update patient with ID: {} and name: {}", editPatient.getId(), editPatient.getName());
        try {
            patientRepository.save(editPatient);
            logger.info("Successfully updated patient with ID: {}", editPatient.getId());
            return "patients.xhtml?faces-redirect=true";
        } catch (Exception e) {
            logger.error("Error updating patient with ID: {}", editPatient.getId(), e);
            throw e;
        }
    }

    public String goToUpdatePatient(Long id) {
        logger.debug("Navigating to update page for patient ID: {}", id);
        return "updatePatient.xhtml?faces-redirect=true&id=" + id;
    }
}
