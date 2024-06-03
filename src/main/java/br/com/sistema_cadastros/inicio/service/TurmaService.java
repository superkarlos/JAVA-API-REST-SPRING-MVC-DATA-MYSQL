package br.com.sistema_cadastros.inicio.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.sistema_cadastros.inicio.dto.AlunoDTO;
import br.com.sistema_cadastros.inicio.dto.TurmaDTO;
import br.com.sistema_cadastros.inicio.execeptions.AlunoNotFoudExecption;
import br.com.sistema_cadastros.inicio.execeptions.ProfessorNotFoudExecption;
import br.com.sistema_cadastros.inicio.execeptions.TurmaNotFoudExecption;
import br.com.sistema_cadastros.inicio.model.AlunoEntity;
import br.com.sistema_cadastros.inicio.model.ProfessorEntity;
import br.com.sistema_cadastros.inicio.model.TurmaEntity;
import br.com.sistema_cadastros.inicio.repostory.RepositorioAluno;
import br.com.sistema_cadastros.inicio.repostory.RepositorioProfessor;
import br.com.sistema_cadastros.inicio.repostory.RepositoryTurma;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class TurmaService {
    @Autowired
    private RepositoryTurma repositoryTurma;
    @Autowired
    private RepositorioProfessor repositorioProfessor;

    @Autowired
    private RepositorioAluno repositorioAluno;

    public TurmaEntity cadastrar( @Valid TurmaDTO turma) {
        if(turma == null){
            System.out.println("null");
          throw new TurmaNotFoudExecption("nome vazio");
         
        }
    TurmaEntity turmaEntity = new TurmaEntity();
    BeanUtils.copyProperties(turma, turmaEntity);
   
    // Verificação do professor
    if (turmaEntity.getProfessorDisciplina() != null) {
        Optional<ProfessorEntity> profOptional = this.repositorioProfessor.findById(turmaEntity.getProfessorDisciplina().getId());
        if (profOptional.isEmpty()) {
            throw new TurmaNotFoudExecption("O professor não existe no banco de dados! Cadastre no banco de dados!"); }
        turmaEntity.setProfessorDisciplina(profOptional.get());}
    // Verificação dos alunos
    if (turmaEntity.getLista_alunos() != null && !turmaEntity.getLista_alunos().isEmpty()) {
        List<AlunoEntity> alunosExistentes = new ArrayList<>();
        for (AlunoEntity aluno : turmaEntity.getLista_alunos()) {
            Optional<AlunoEntity> alunoOptional = this.repositorioAluno.findById(aluno.getId());
            if (alunoOptional.isEmpty()) {
                throw new AlunoNotFoudExecption("O aluno com ID " + aluno.getId() + " não existe no banco de dados!");
            }
            alunosExistentes.add(alunoOptional.get());
        }
        turmaEntity.setLista_alunos(alunosExistentes);
    }
    return this.repositoryTurma.save(turmaEntity);
}

    

    public List<TurmaEntity> Lista_tumas() {
        return this.repositoryTurma.findAll();
    }

    public Object Lista_tumas_id(Long id) {
        Optional<TurmaEntity> optional = this.repositoryTurma.findById(id);
        if (optional.isEmpty()) {
           throw new TurmaNotFoudExecption("turma de id : "+id+" Não Não foi cadastrada!");
        }
        TurmaEntity turmaEntity = optional.get();
        return turmaEntity;
      

    }

    public String assosiarprofessor(Long idprofessor, Long idTurma) throws Exception {
        Optional<ProfessorEntity> profOptional = this.repositorioProfessor.findById(idprofessor);
        if (profOptional.isEmpty()) {
            throw new TurmaNotFoudExecption("Professor não encontrado");
       //     throw new TurmaNotFoudExecption("turma de id : "+id+" Não Não foi cadastrada!");
        }
        Optional<TurmaEntity> turmaOptional = this.repositoryTurma.findById(idTurma);
        if (turmaOptional.isEmpty()) {
            throw new TurmaNotFoudExecption ("Turma não encontrada");
        }
        ProfessorEntity professorEntity = profOptional.get();
        TurmaEntity turma = turmaOptional.get();
        turma.setProfessorDisciplina(professorEntity);
        repositoryTurma.save(turma);
        return "professor salvo";
    }

    public String deletar(Long id) {
        Optional<TurmaEntity> turma = this.repositoryTurma.findById(id);
        if(turma.isEmpty()){
            throw new TurmaNotFoudExecption("Turma não encontrada");
        }
        if (turma.isPresent()) {
            List<AlunoEntity> alunos = turma.get().getLista_alunos();

            for (AlunoEntity aluno : alunos) {
                aluno.setTurmas(null);
                repositorioAluno.save(aluno);
            }
            turma.get().setProfessorDisciplina(null);
            this.repositoryTurma.deleteById(id);
            return "turma deletada" + id;

        } else {
            return "turma não encontrada";
        }
    }

///=============================================================================
public Object editar(Long id, TurmaDTO turmaDTO) {
    Optional<TurmaEntity> turma = this.repositoryTurma.findById(id);
    if (turma.isEmpty()) {
        throw new TurmaNotFoudExecption("Turma não encontrada");
    }
    TurmaEntity turmaEntity = turma.get();

    // Atualização do nome
    if (turmaDTO.nome() != null && !turmaDTO.nome().equals(turmaEntity.getNome())) {
        turmaEntity.setNome(turmaDTO.nome());
    }

    // Atualização do código
    if (turmaDTO.codigo() != null && !turmaDTO.codigo().equals(turmaEntity.getCodigo())) {
        turmaEntity.setCodigo(turmaDTO.codigo());
    }

    // Verificação e atualização do professor
    if (turmaDTO.professorDisciplina() != null) {
        Optional<ProfessorEntity> profOptional = this.repositorioProfessor.findById(turmaDTO.professorDisciplina().getId());
        if (profOptional.isEmpty()) {
            throw new TurmaNotFoudExecption("O professor não existe no banco de dados! Cadastre no banco de dados!");
        }
        turmaEntity.setProfessorDisciplina(profOptional.get());
    } else if (turmaDTO.professorDisciplina() == null) {
        turmaEntity.setProfessorDisciplina(null);
    }

    // Verificação e atualização dos alunos
    if (turmaDTO.lista_alunos() != null) {
        Set<Long> alunoIds = new HashSet<>();
        List<AlunoEntity> alunosExistentes = new ArrayList<>();

        for (AlunoEntity aluno : turmaDTO.lista_alunos()) {
            if (!alunoIds.add(aluno.getId())) {
                throw new AlunoNotFoudExecption("O aluno com ID " + aluno.getId() + " está duplicado na lista de alunos!");
            }

            Optional<AlunoEntity> alunoOptional = this.repositorioAluno.findById(aluno.getId());

            if (alunoOptional.isEmpty()) {
                throw new AlunoNotFoudExecption("O aluno com ID " + aluno.getId() + " não existe no banco de dados!");
            }

            alunosExistentes.add(alunoOptional.get());
        }

        turmaEntity.setLista_alunos(alunosExistentes);
    }

    return this.repositoryTurma.save(turmaEntity);
   // return "Turma atualizada com sucesso";
}

    
//================================================================================
    public Object exluirLogico(Long id) {
        Optional<TurmaEntity> Optional = this.repositoryTurma.findById(id);
        if(Optional.isEmpty()){
            throw new TurmaNotFoudExecption("Turma não encontrada");
        }
        if (Optional.isPresent()) {
            TurmaEntity Entity = Optional.get();
            Entity.setAtivo(false);
            return this.repositoryTurma.save(Entity);
        } else {
            return "Não deletado logicamente";
        }
    }
//================================================================================
    public List<TurmaEntity> lista_logica() {
        return this.repositoryTurma.findByAtivoTrue();

    }

    public String excluir(Long id) {
        Optional<AlunoEntity> alunoOptional = this.repositorioAluno.findById(id);
        if(alunoOptional .isEmpty()){
            throw new TurmaNotFoudExecption("Aluno não localizado");
        }
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
        if(turmaOptional.isEmpty()){
            throw new TurmaNotFoudExecption("Turma não encontrada");
        }
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
                   // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado na turma");
                    throw new TurmaNotFoudExecption("Aluno não encontrado na turma");
                }
            } else {
                throw new TurmaNotFoudExecption("Aluno não encontrado");
            }
        } else {
            throw new TurmaNotFoudExecption("Turma não encontrada");
        }
    }

    public ResponseEntity<?> matricularAluno(Long id, Long idaluno) {
        Optional<TurmaEntity> optionalTurma = this.repositoryTurma.findById(id);
        Optional<AlunoEntity> optionalAluno = this.repositorioAluno.findById(idaluno);
        if(optionalAluno.isEmpty()){
            throw new TurmaNotFoudExecption("Não é possivel cadastrar um aluno que não existe no banco de dados");
        }
        if(optionalTurma.isEmpty()){
            throw new TurmaNotFoudExecption("Turma inexistente");
        }

        if (optionalTurma.isPresent() && optionalAluno.isPresent()) {
            TurmaEntity turmaEntity = optionalTurma.get();
            AlunoEntity alunoEntity = optionalAluno.get();
            // Verificar se o aluno já está matriculado na turma
            if (turmaEntity.getLista_alunos().contains(alunoEntity)) {
                throw new AlunoNotFoudExecption("Aluno já cadastrado");
            }
            turmaEntity.getLista_alunos().add(alunoEntity);
            alunoEntity.getTurmas().add(turmaEntity);
            repositoryTurma.save(turmaEntity);
            repositorioAluno.save(alunoEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body("Aluno matriculado com sucesso");
        } else {
            throw new TurmaNotFoudExecption("Turma ou Aluno não encontrado");
        }
    }


    public String remover_professor(Long idturma, long idprofessor) {
        Optional<TurmaEntity> turma = this.repositoryTurma.findById(idturma);
        Optional<ProfessorEntity> optional = this.repositorioProfessor.findById(idprofessor);
        
        if(turma.isEmpty()){
            throw new TurmaNotFoudExecption("turma não encontrada");
        }
        if(optional.isEmpty()){
            throw new ProfessorNotFoudExecption("Professor não encontrado");
          }
        if (turma.isPresent()) {
            TurmaEntity novTurmaEntity = turma.get();
            if (novTurmaEntity.getProfessorDisciplina().getId() == idprofessor) {
                novTurmaEntity.setProfessorDisciplina(null);
                repositoryTurma.save(novTurmaEntity);
                return "Professor removido";
            } else {
                return "professor não encontrado";
            }
        } else {
            return "turma não encontrada";
        }
    }
  
 public Object professor_turmas(Long id){
    Optional<ProfessorEntity> professorOptional = repositorioProfessor.findById(id);
    if(professorOptional.isEmpty()){
        throw new ProfessorNotFoudExecption("Professor não encontrado");
    }
    
    ProfessorEntity professor = professorOptional.get();
    List<TurmaEntity> turmas = professor.getTurmas();
    
    List<String> nomesTurmas = turmas.stream().map(TurmaEntity::getNome).collect(Collectors.toList());
    
    return  "turmas do professor:"+ professor.getNome() +"\n"+nomesTurmas;
 }
 
 public Object aluno_turmas(Long id){
    Optional<AlunoEntity> alunoOptional = repositorioAluno.findById(id);
    if(alunoOptional.isEmpty()){
        throw new AlunoNotFoudExecption("Aluno não encontrado");
    }
    
    AlunoEntity aluno = alunoOptional.get();
    List<TurmaEntity> turmas = aluno.getTurmas();
    
    List<String> nomesTurmas = turmas.stream()
                                     .map(TurmaEntity::getNome)
                                     .collect(Collectors.toList());
    
    return  "Aluno: "+aluno.getNome()+": \n"+ nomesTurmas;
}
}