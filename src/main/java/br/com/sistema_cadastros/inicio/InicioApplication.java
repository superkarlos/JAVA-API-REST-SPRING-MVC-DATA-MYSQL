package br.com.sistema_cadastros.inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication

public class InicioApplication {


	public static void main(String[] args) {
		SpringApplication.run(InicioApplication.class, args);
	}

}
