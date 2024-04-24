package br.com.sistema_cadastros.inicio.repostory;
import java.util.List;

//import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistema_cadastros.inicio.model.ProfessorEntity;

public interface RepositorioProfessor extends JpaRepository <ProfessorEntity,Long> {


    List<ProfessorEntity> findAll();
    ProfessorEntity findById (int chave);
    //List<ProfessorEntity> findByOrderNome();

     
     
}
