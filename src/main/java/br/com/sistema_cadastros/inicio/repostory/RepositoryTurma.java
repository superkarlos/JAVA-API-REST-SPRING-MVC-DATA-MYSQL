package br.com.sistema_cadastros.inicio.repostory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sistema_cadastros.inicio.model.AlunoEntity;
import br.com.sistema_cadastros.inicio.model.TurmaEntity;
@Repository
public interface RepositoryTurma extends JpaRepository<TurmaEntity,Long> {
    List<TurmaEntity> findByAtivoTrue();

    void save(Optional<TurmaEntity> turma);
    
}
