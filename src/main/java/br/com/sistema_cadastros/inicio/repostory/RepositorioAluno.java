package br.com.sistema_cadastros.inicio.repostory;

import java.util.List;
import java.util.Optional;

//import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.sistema_cadastros.inicio.model.AlunoEntity;

@Repository
public interface RepositorioAluno extends JpaRepository<AlunoEntity, Long> {
   
   List<AlunoEntity> findByAtivoTrue();
   Optional<AlunoEntity> findByNome(String nome);
   
}