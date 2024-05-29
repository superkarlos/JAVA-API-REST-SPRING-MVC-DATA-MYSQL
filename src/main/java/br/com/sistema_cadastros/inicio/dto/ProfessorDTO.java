package br.com.sistema_cadastros.inicio.dto;

import java.util.List;
import org.hibernate.validator.constraints.br.CPF;

import br.com.sistema_cadastros.inicio.Enum.Genero;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record ProfessorDTO(
    String nome,
  //  @CPF
    String cpf,
    int matricula,
    @Enumerated(EnumType.STRING)
    Genero genero,
    String departamento,
    String data,
    float salario
 //  List disciplina


) {
    
}
