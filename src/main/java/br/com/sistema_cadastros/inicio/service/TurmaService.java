package br.com.sistema_cadastros.inicio.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistema_cadastros.inicio.dto.TurmaDTO;
import br.com.sistema_cadastros.inicio.model.TurmaEntity;
import br.com.sistema_cadastros.inicio.repostory.RepositorioProfessor;
import br.com.sistema_cadastros.inicio.repostory.RepositoryTurma;
import jakarta.transaction.Transactional;

@Service
public class TurmaService {
    @Autowired
    private RepositoryTurma repositoryTurma;
    @Autowired
    private RepositorioProfessor repositorioProfessor;

    @Transactional
    public TurmaEntity cadastrar(TurmaDTO turma){
        TurmaEntity turmaEntity = new TurmaEntity();
        BeanUtils.copyProperties(turma, turmaEntity);
        return this.repositoryTurma.save(turmaEntity);
    }
    @Transactional
    public List<TurmaEntity> Lista_tumas(){
        return this.repositoryTurma.findAll();
    }
}
