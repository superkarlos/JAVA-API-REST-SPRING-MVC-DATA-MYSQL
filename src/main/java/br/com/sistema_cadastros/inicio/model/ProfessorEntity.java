package br.com.sistema_cadastros.inicio.model;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.sistema_cadastros.inicio.Enum.Genero;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Professor")
public class ProfessorEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id = 0;
  private String nome;
  private String cpf;
  private int matricula;
  private Genero genero;
  private String departamento;
  private String data;
  private float salario;

  private Boolean ativo = true;

  @OneToMany(mappedBy = "professorDisciplina", fetch = FetchType.EAGER)
  // @JsonManagedReference
  // @JsonBackReference
  @JsonIgnore
  private List<TurmaEntity> turmas;

}
