package br.com.sistema_cadastros.inicio.dto;

import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import br.com.sistema_cadastros.inicio.Enum.Genero;

//import lombok.Data;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


public record AlunoDTO(

    String nome,
   //@CPF
    String cpf,
    int matricula,
    @Enumerated(EnumType.STRING)
    Genero genero,
    String curso,
    String data
  

) {
    
}
