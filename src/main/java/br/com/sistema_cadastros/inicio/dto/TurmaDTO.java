package br.com.sistema_cadastros.inicio.dto;

import java.util.List;

import br.com.sistema_cadastros.inicio.model.AlunoEntity;
import br.com.sistema_cadastros.inicio.model.ProfessorEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TurmaDTO(
        Long id,
   // @NotBlank(message = "nome não deve ser null")
 // @NotNull(message = "nome não deve ser null")
        String nome,
 //   @NotBlank @NotNull 
        String codigo,
        ProfessorEntity professorDisciplina,
        List<AlunoEntity> lista_alunos

) {
}
