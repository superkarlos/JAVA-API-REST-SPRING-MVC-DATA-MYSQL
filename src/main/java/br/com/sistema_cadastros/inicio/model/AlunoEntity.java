package br.com.sistema_cadastros.inicio.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

//import org.springframework.cglib.reflect.MethodDelegate.Generator;

import br.com.sistema_cadastros.inicio.Enum.Genero;
import br.com.sistema_cadastros.inicio.dto.AlunoDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
@Table(name = "Alunos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
//comentoi
public class AlunoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String nome;
  private String cpf;
  private int matricula;
  private Genero genero;
  private String curso;
  private String data;

  private Boolean ativo = true;

  @ManyToMany(mappedBy = "lista_alunos", fetch = FetchType.LAZY)
  // @JsonManagedReference
  // @JsonBackReference
  @JsonIgnore
  private List<TurmaEntity> turmas;

  public AlunoEntity(AlunoDTO alunoDTO) {
  }
}
