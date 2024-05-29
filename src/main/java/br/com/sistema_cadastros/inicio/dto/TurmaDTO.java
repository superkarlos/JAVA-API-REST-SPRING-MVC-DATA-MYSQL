package br.com.sistema_cadastros.inicio.dto;

import br.com.sistema_cadastros.inicio.model.AlunoEntity;
import br.com.sistema_cadastros.inicio.model.ProfessorEntity;

public record TurmaDTO(
    String nome,
    String codigo,
    ProfessorEntity professorDisciplina
) {
} 
