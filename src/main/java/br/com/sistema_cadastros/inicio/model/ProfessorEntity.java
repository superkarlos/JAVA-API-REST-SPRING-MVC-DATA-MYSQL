package br.com.sistema_cadastros.inicio.model;

import static jakarta.persistence.GenerationType.IDENTITY;

import br.com.sistema_cadastros.inicio.Enum.Genero;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="Professor")
public class ProfessorEntity {
    
   @Id
   @GeneratedValue(strategy =GenerationType.AUTO)
   private long id;
   private String nome;
   private String cpf;
   private int matricula;
   private Genero genero;

   private String departamento;
   private  String data;

   private float salario;
   private String disciplina;

}
