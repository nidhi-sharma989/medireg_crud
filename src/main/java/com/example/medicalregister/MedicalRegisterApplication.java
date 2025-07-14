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

//     @Bean
//     public ServletRegistrationBean<FacesServlet> facesServlet() {
//         ServletRegistrationBean<FacesServlet> servlet = new ServletRegistrationBean<>(new FacesServlet(), "*.xhtml");
//         servlet.setName("FacesServlet");
//         servlet.setLoadOnStartup(1); // Ensure servlet
//         return servlet;
//     }

//     @Bean
//     public ServletContextInitializer servletContextInitializer() {
//         return servletContext -> {
//             servletContext.setInitParameter("jakarta.faces.FACELETS_SKIP_COMMENTS", "true");
//             servletContext.setInitParameter("jakarta.faces.PROJECT_STAGE", "Development");
//             // Register Weld CDI listener
//             servletContext.addListener(org.jboss.weld.environment.servlet.Listener.class);
//         };
//     }
}