package br.com.sistema_cadastros.inicio.infra;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestErroMesseger {
    private HttpStatus status;
    private String messeger;
}
