package br.com.sistema_cadastros.inicio.model;

import static jakarta.persistence.GenerationType.IDENTITY;

//import org.springframework.cglib.reflect.MethodDelegate.Generator;

import br.com.sistema_cadastros.inicio.Enum.Genero;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "alunos")
public class AlunoEntity {
    
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long id;
   private String nome;
   private String cpf;
   private int matricula;
   private Genero genero;
   private String curso;
   private  String data;

   

}
