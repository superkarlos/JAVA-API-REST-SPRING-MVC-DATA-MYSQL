package br.com.sistema_cadastros.inicio.repostory;

import java.util.List;

//import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sistema_cadastros.inicio.model.ProfessorEntity;
import br.com.sistema_cadastros.inicio.model.TurmaEntity;

@Repository
public interface RepositorioProfessor extends JpaRepository<ProfessorEntity, Long> {

  List<ProfessorEntity> findByAtivoTrue();
  // ProfessorEntity findById (int chave);
  // List<ProfessorEntity> findByOrderNome();
  // List<TurmaEntity> findByprofessor(ProfessorEntity professor);

}
