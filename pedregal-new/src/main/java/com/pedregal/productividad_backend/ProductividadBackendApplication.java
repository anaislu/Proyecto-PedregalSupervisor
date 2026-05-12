package com.pedregal.productividad_backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class ProductividadBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductividadBackendApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
	// 2. Arrancador automático de Angular
    @Bean
    public CommandLineRunner startAngular() {
        return args -> {
            try {
                
				String angularPath = "C:\\Users\\ASUS\\OneDrive\\Documentos\\GitHub\\Proyecto-PedregalSupervisor\\pedregal-new"; // Asumiendo que Angular está en la raíz del proyecto
                ProcessBuilder processBuilder;
				System.out.println("Iniciando servidor Angular...");
                String os = System.getProperty("os.name").toLowerCase();

                if (os.contains("win")) {
                    // Windows
                    processBuilder = new ProcessBuilder("cmd", "/c", "cd " + angularPath + " && ng serve");
                } else {
                    // Linux/Mac
                    processBuilder = new ProcessBuilder("bash", "-c", "cd " + angularPath + " && ng serve");
                }

                processBuilder.directory(new File(angularPath));
                processBuilder.inheritIO();
                processBuilder.start();
				Process process = processBuilder.start();
                
                System.out.println("Servidor Angular iniciado en http://localhost:4200");
                

                System.out.println("--- Proceso de Angular lanzado correctamente ---");
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error crítico al iniciar Angular: " + e.getMessage());
            }
        };
    }

}
