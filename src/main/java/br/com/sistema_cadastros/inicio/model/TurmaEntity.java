package br.com.sistema_cadastros.inicio.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="Turmas")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class TurmaEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "professor_id",nullable = true)
    @JsonBackReference
    private ProfessorEntity professorDisciplina;
   

    public TurmaEntity(String nome,String codigo, ProfessorEntity professorDisciplina){
        this.codigo= codigo;
        this.nome =nome;
        this.professorDisciplina = professorDisciplina;
    }

    public ProfessorEntity getProfessorDisciplina() {
        return professorDisciplina;
    }

    public void setProfessorDisciplina(ProfessorEntity professorDisciplina) {
        this.professorDisciplina = professorDisciplina;
    }

}
