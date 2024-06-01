package br.com.sistema_cadastros.inicio.dto;

import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import br.com.sistema_cadastros.inicio.Enum.Genero;

//import lombok.Data;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlunoDTO(
        @NotBlank
        @NotNull
        String nome,
      //  @CPF
        String cpf,
        @NotBlank
        @NotNull
        int matricula,
        @Enumerated(EnumType.STRING) Genero genero,
        String curso,
        @NotBlank
        @NotNull
        String data

) {

}
