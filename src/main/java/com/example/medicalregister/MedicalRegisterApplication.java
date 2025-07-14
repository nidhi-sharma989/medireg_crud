package com.example.medicalregister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MedicalRegisterApplication {

    private static final Logger logger = LoggerFactory.getLogger(MedicalRegisterApplication.class);

    public static void main(String[] args) {
        logger.info("Starting Medical Register Application...");
        SpringApplication.run(MedicalRegisterApplication.class, args);
        logger.info("Medical Register Application started successfully");
    }


}