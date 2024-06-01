package br.com.sistema_cadastros.inicio.dto;

import java.util.List;
import org.hibernate.validator.constraints.br.CPF;

import br.com.sistema_cadastros.inicio.Enum.Genero;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfessorDTO(
    @NotBlank @NotNull String nome,
    // @CPF
    String cpf,
    int matricula,
    @Enumerated(EnumType.STRING) Genero genero,
    String departamento,
    @NotBlank
    @NotNull
    String data,
    @NotBlank
    @NotNull
    float salario
// List disciplina

) {

}
