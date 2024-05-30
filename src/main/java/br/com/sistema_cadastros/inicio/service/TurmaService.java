package br.com.sistema_cadastros.inicio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.sistema_cadastros.inicio.dto.TurmaDTO;
import br.com.sistema_cadastros.inicio.model.AlunoEntity;
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
    public Object Lista_tumas_id(Long id){
        Optional <TurmaEntity> optional = this.repositoryTurma.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }else{
            return "Turma Não encontrada";
        }
        
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

           TurmaEntity turmaEntity = turma.get();
         
          if((turmaDTO.nome() != turmaEntity.getNome()) &&  (turmaDTO.nome() != null) ){
            turmaEntity.setNome(turmaDTO.nome());
          }
          if((turmaDTO.codigo() != turmaEntity.getCodigo()) &&  (turmaDTO.codigo() != null) ){
            turmaEntity.setCodigo(turmaDTO.codigo()) ;
          }
          if((turmaDTO.professorDisciplina() != turmaEntity.getProfessorDisciplina()) ){
            turmaEntity.setProfessorDisciplina(turmaDTO.professorDisciplina());
          }
           this.repositoryTurma.save(turmaEntity);
          return turmaEntity;

        }else{
            return "turma não encontrada";
        }
        
    }


    public Object exluirLogico(Long id){
        Optional<TurmaEntity> Optional = this.repositoryTurma.findById(id);

       if(Optional.isPresent()){
          TurmaEntity Entity = Optional.get();
          Entity.setAtivo(false);
          return this.repositoryTurma.save(Entity);
       }
       else{
           return "Não deletado logicamente";
       }
    }

    public List<TurmaEntity> lista_logica(){
        return this.repositoryTurma.findByAtivoTrue();

}

}
