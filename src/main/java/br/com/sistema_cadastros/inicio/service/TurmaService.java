package br.com.sistema_cadastros.inicio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.sistema_cadastros.inicio.dto.TurmaDTO;
import br.com.sistema_cadastros.inicio.model.ProfessorEntity;
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


    public TurmaEntity cadastrar(TurmaDTO turma){
        TurmaEntity turmaEntity = new TurmaEntity();
        BeanUtils.copyProperties(turma, turmaEntity);
        return this.repositoryTurma.save(turmaEntity);
    }
 
    public List<TurmaEntity> Lista_tumas(){
        return this.repositoryTurma.findAll();
    }
    public String assosiarprofessor(Long idprofessor,Long idTurma)throws Exception{
        Optional<ProfessorEntity> profOptional = this.repositorioProfessor.findById(idprofessor);
        if (profOptional.isEmpty()){
            return ("Professor não encontrado"); 
        }
        Optional<TurmaEntity> turmaOptional = this.repositoryTurma.findById(idTurma);
        if (turmaOptional.isEmpty()){
            return ("Turma não encontrada");
       }
        ProfessorEntity professorEntity = profOptional.get();
        TurmaEntity turma =turmaOptional.get();
        turma.setProfessorDisciplina(professorEntity);
        repositoryTurma.save(turma);
        return "professor salvo";
    }

    public String deletar(Long id){
        Optional <TurmaEntity> turma = this.repositoryTurma.findById(id);
        if(turma.isPresent()){
            this.repositoryTurma.deleteById(id);
          // System.out.println(turma);
            return "turma deletada" + id;
            
        }else{
            return "turma não encontrada";
        }
    }

    public Object  editar(Long id,TurmaDTO turmaDTO){
        Optional <TurmaEntity> turma = this.repositoryTurma.findById(id);
        if(turma.isPresent()){
           TurmaEntity turmaEntity = new TurmaEntity();
           BeanUtils.copyProperties(turma, turmaEntity);
           if(turmaDTO.nome()== null){
            turmaEntity.setNome(turma.get().getNome());
           }
           if(turmaDTO.codigo()== null){
            turmaEntity.setNome(turma.get().getCodigo());
           }
           if(turmaDTO.professorDisciplina()== null){
            turmaEntity.setProfessorDisciplina(turma.get().getProfessorDisciplina());
           }
          return turmaEntity;

        }else{
            return "turma não encontrada";
        }
        
    }
}


