package br.com.sistema_cadastros.inicio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.sistema_cadastros.inicio.dto.AlunoDTO;
import br.com.sistema_cadastros.inicio.dto.TurmaDTO;
import br.com.sistema_cadastros.inicio.model.AlunoEntity;
import br.com.sistema_cadastros.inicio.model.ProfessorEntity;
import br.com.sistema_cadastros.inicio.model.TurmaEntity;
import br.com.sistema_cadastros.inicio.repostory.RepositorioAluno;
import br.com.sistema_cadastros.inicio.repostory.RepositorioProfessor;
import br.com.sistema_cadastros.inicio.repostory.RepositoryTurma;
import jakarta.transaction.Transactional;

@Service
public class TurmaService {
    @Autowired
    private RepositoryTurma repositoryTurma;
    @Autowired
    private RepositorioProfessor repositorioProfessor;

    @Autowired
    private RepositorioAluno repositorioAluno;


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
            List<AlunoEntity> alunos = turma.get().getLista_alunos();

           for(AlunoEntity aluno:alunos){
             aluno.setTurmas(null);
             repositorioAluno.save(aluno);
           }
           turma.get().setProfessorDisciplina(null);
            this.repositoryTurma.deleteById(id);
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
         // return turmaEntity;
         String aux = turmaEntity.getProfessorDisciplina().getNome();
         return "Do professor " + aux + " Atualizada";

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

 
   public String excluir(Long id) {
    Optional<AlunoEntity> alunoOptional = this.repositorioAluno.findById(id);
    
    if (alunoOptional.isPresent()) {
        AlunoEntity aluno = alunoOptional.get();
        List<TurmaEntity> turmas = aluno.getTurmas();
        
        // Remover o aluno de todas as turmas
        for (TurmaEntity turma : turmas) {
            turma.removeAluno(aluno); // Usar o novo método para remover o aluno da turma
            repositoryTurma.save(turma); // Atualizar a turma no repositório
        }
        
        // Agora que o aluno não está mais em nenhuma turma, podemos deletá-lo
        repositorioAluno.delete(aluno);

        return "Aluno deletado com sucesso!";
    } else {
        return "Aluno não localizado";
    }
}
  

public ResponseEntity<?> removerAluno(Long turmaId, Long alunoId) {
    Optional<TurmaEntity> turmaOptional = repositoryTurma.findById(turmaId);
    
    if (turmaOptional.isPresent()) {
        TurmaEntity turmaEntity = turmaOptional.get();
        Optional<AlunoEntity> alunoOptional = repositorioAluno.findById(alunoId);
        
        if (alunoOptional.isPresent()) {
            AlunoEntity aluno = alunoOptional.get();
            
            if (turmaEntity.getLista_alunos().contains(aluno)) {
                turmaEntity.removeAluno(aluno); // Remover aluno da turma
                repositoryTurma.save(turmaEntity); // Salvar a turma atualizada
                
                return ResponseEntity.status(HttpStatus.OK).body("Aluno removido da turma com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado na turma");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
        }
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não encontrada");
    }
  }

  public ResponseEntity<?> matricularAluno(Long id, Long idaluno) {
    Optional<TurmaEntity> optionalTurma = this.repositoryTurma.findById(id);
    Optional<AlunoEntity> optionalAluno = this.repositorioAluno.findById(idaluno);

    if (optionalTurma.isPresent() && optionalAluno.isPresent()) {
        TurmaEntity turmaEntity = optionalTurma.get();
        AlunoEntity alunoEntity = optionalAluno.get();

        // Verificar se o aluno já está matriculado na turma
        if (turmaEntity.getLista_alunos().contains(alunoEntity)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Aluno já está matriculado nesta turma");
        }

        turmaEntity.getLista_alunos().add(alunoEntity);
        alunoEntity.getTurmas().add(turmaEntity);
        repositoryTurma.save(turmaEntity);
        repositorioAluno.save(alunoEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body("Aluno matriculado com sucesso");
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma ou Aluno não encontrado");
    }
}


}
