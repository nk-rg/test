package com.dico.test.empleado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.dico.test.empleado", "com.dico.test.util"})
public class EmpleadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpleadoApplication.class, args);
	}

}
