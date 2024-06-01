package br.com.sistema_cadastros.inicio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.sistema_cadastros.inicio.dto.ProfessorDTO;
import br.com.sistema_cadastros.inicio.model.ProfessorEntity;
import br.com.sistema_cadastros.inicio.model.TurmaEntity;
import br.com.sistema_cadastros.inicio.repostory.RepositorioProfessor;
import br.com.sistema_cadastros.inicio.repostory.RepositoryTurma;

@Service
public class ProfessorService {

    @Autowired
    private RepositorioProfessor repository;

    @Autowired
    private RepositoryTurma repositoryTurma;

    public ProfessorEntity mescar(ProfessorEntity professorEntity, ProfessorDTO professordto) {
        if (professordto.nome() != null && (professorEntity.getNome() != professordto.nome())) {
            professorEntity.setNome(professordto.nome());
            if (professordto.cpf() != null && (professorEntity.getCpf() != professordto.cpf())) {
                professorEntity.setCpf(professordto.cpf());
            }
            if (professordto.matricula() != 0 && (professorEntity.getMatricula() != professordto.matricula())) {
                professorEntity.setMatricula(professordto.matricula());
            }
            if (professordto.genero() != null && (professorEntity.getGenero() != professordto.genero())) {
                professorEntity.setGenero(professordto.genero());
            }
            if (professordto.departamento() != null
                    && (professorEntity.getDepartamento() != professordto.departamento())) {
                professorEntity.setDepartamento(professordto.departamento());
            }
            if (professordto.data() != null && (professorEntity.getData() != professordto.data())) {
                professorEntity.setData(professordto.data());
            }
            if (professordto.salario() != 0 && (professorEntity.getSalario() != professordto.salario())) {
                professorEntity.setSalario(professordto.salario());
            }

        }
        return professorEntity;
    }

    public List<ProfessorEntity> lista() {
        return this.repository.findAll();
    }

    public List<ProfessorEntity> listaLogica() {
        return this.repository.findByAtivoTrue();
    }

    public Object listaID(Long id) {

        Optional<ProfessorEntity> optional = this.repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return "PROFESSOR NÂO LOCALIZADO";
        }
    }

    public ProfessorEntity cadastro(ProfessorDTO professorDTO) {
        ProfessorEntity professorEntity = new ProfessorEntity();
        BeanUtils.copyProperties(professorDTO, professorEntity);
        return this.repository.save(professorEntity);
    }

    public Object atualizar(Long id, ProfessorDTO professordto) {
        Optional<ProfessorEntity> professorEntity = this.repository.findById(id);

        if (professorEntity.isPresent()) {
            ProfessorEntity professor = professorEntity.get();
            mescar(professor, professordto);
            return this.repository.save(professor);
        } else {
            return "Não localizado";
        }

    }

    public String deletar(Long id) {
        Optional<ProfessorEntity> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            // Remover a associação de todas as turmas
            ProfessorEntity professor = optional.get();
            List<TurmaEntity> turmas = professor.getTurmas();

            for (TurmaEntity turma : turmas) {
                turma.setProfessorDisciplina(null);
                repositoryTurma.save(turma);
            }

            // Agora pode deletar o professor
            repository.delete(professor);
            return "Deletado";
        } else {
            return "Não localizado";
        }
    }

    public ResponseEntity desativar(Long id) {
        Optional<ProfessorEntity> Optional = this.repository.findById(id);

        if (Optional.isPresent()) {

            ProfessorEntity professorEntity = Optional.get();
            professorEntity.setAtivo(false);
            return ResponseEntity.status(HttpStatus.CREATED).body(this.repository.save(professorEntity));
            // return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com
            // sucesso!");

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não deletado logicamente");
        }

    }

    public String entrar(Long id) {

        Optional<TurmaEntity> turmaOptional = this.repositoryTurma.findById(id);

        if (turmaOptional.isEmpty()) {
            return ("Turma não encontrada");
        } else {
            return "id";
        }

    }
}
