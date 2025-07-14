package com.example.medicalregister.controller;

import com.example.medicalregister.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MedicalRegisterController {
    @Autowired
    PatientRepository patientRepository;

    @GetMapping("/")
    public RedirectView home() {
        return new RedirectView("/index.xhtml");
    }

}
