package br.com.sistema_cadastros.inicio.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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
    private Boolean ativo = true;
    



    @ManyToOne
    @JoinColumn(name = "professor_id",nullable = true)
    @JsonBackReference
   // @JsonIgnore
   
    private ProfessorEntity professorDisciplina;
   

}
