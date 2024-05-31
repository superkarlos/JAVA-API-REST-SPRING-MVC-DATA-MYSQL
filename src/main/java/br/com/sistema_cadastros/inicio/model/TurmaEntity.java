package br.com.sistema_cadastros.inicio.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
    public void removeAluno(AlunoEntity aluno) {
      this.lista_alunos.remove(aluno);
      aluno.getTurmas().remove(this); // Também remover a turma da lista de turmas do aluno
  }



    @ManyToOne
    @JoinColumn(name = "professor_id",nullable = true)
    @JsonBackReference
   // @JsonIgnore
    private ProfessorEntity professorDisciplina;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name= "Turmas_alunos",
    joinColumns = @JoinColumn(name= "disciplica_fk"),
    inverseJoinColumns = @JoinColumn(name= "Aluno_fk"))
  //  @JsonManagedReference
    //  @JsonBackReference
   // @JsonIgnore
  //  private Set<AlunoEntity> lista_alunos = new HashSet<>();
    private List<AlunoEntity> lista_alunos ;
   

}
