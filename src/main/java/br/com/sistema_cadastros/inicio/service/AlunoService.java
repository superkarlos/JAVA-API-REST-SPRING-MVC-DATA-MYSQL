package br.com.sistema_cadastros.inicio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistema_cadastros.inicio.dto.AlunoDTO;
import br.com.sistema_cadastros.inicio.model.AlunoEntity;
import br.com.sistema_cadastros.inicio.model.TurmaEntity;
import br.com.sistema_cadastros.inicio.repostory.RepositorioAluno;
import br.com.sistema_cadastros.inicio.repostory.RepositoryTurma;

@Service
public class AlunoService {

    @Autowired
    private RepositorioAluno repository;

    @Autowired
    private RepositoryTurma repositoryTurma;

    private AlunoEntity mescar(AlunoEntity alunoEntity, AlunoDTO alunodto) {

        if (alunodto.nome() != null && (alunoEntity.getNome() != alunodto.nome())) {
            alunoEntity.setNome(alunodto.nome());
        }

        if (alunodto.cpf() != null && (alunoEntity.getCpf() != alunodto.cpf())) {
            alunoEntity.setCpf(alunodto.cpf());
        }
        if (alunodto.matricula() != 0 && (alunoEntity.getMatricula() != alunodto.matricula())) {
            alunoEntity.setMatricula(alunodto.matricula());
        }

        if (alunodto.genero() != null && (alunoEntity.getGenero() != alunodto.genero())) {
            alunoEntity.setGenero(alunodto.genero());
        }
        if (alunodto.curso() != null && (alunoEntity.getCurso() != alunodto.curso())) {
            alunoEntity.setCurso(alunodto.curso());
        }
        if (alunodto.data() != null && (alunoEntity.getData() != alunodto.data())) {
            alunoEntity.setData(alunodto.data());
        }
        return alunoEntity;

    }

    public List<AlunoEntity> lista_alunos() {
        return this.repository.findAll();
    }

    public List<AlunoEntity> lista_alunos_logica() {
        return this.repository.findByAtivoTrue();
    }

    public AlunoEntity inserir(AlunoDTO alunoDTO) {
        AlunoEntity alunoEntity = new AlunoEntity();
        BeanUtils.copyProperties(alunoDTO, alunoEntity);
        return this.repository.save(alunoEntity);
    }

    public Object lista_id(Long id) {
        Optional<AlunoEntity> aluno_Optional = this.repository.findById(id);

        if (aluno_Optional.isPresent()) {
            System.out.println("Nome" + aluno_Optional.get().getNome());
            return aluno_Optional.get();

        } else {
            return "Não localizado";
        }
    }

    public Object atualizar(Long id, AlunoDTO alunodto) {
        Optional<AlunoEntity> aluno_Optional = this.repository.findById(id);
        AlunoEntity alunoEntity = aluno_Optional.get();

        if (aluno_Optional.isPresent()) {
            mescar(alunoEntity, alunodto);
            return this.repository.save(alunoEntity);

        } else {
            return "Aluno não encontrado";
        }
    }

    public String excluir(Long id) {
        Optional<AlunoEntity> alunoOptional = this.repository.findById(id);

        if (alunoOptional.isPresent()) {
            AlunoEntity aluno = alunoOptional.get();
            List<TurmaEntity> turmas = aluno.getTurmas();

            // Remover o aluno de todas as turmas
            for (TurmaEntity turma : turmas) {
                turma.getLista_alunos().remove(aluno); // Assumindo que TurmaEntity tem um método getAlunos()
                repositoryTurma.save(turma); // Atualizar a turma no repositório
            }

            // Agora que o aluno não está mais em nenhuma turma, podemos deletá-lo
            repository.delete(aluno);

            return "Aluno deletado com sucesso!";
        } else {
            return "Aluno não localizado";
        }
    }

    public Object exluirLogico(Long id) {
        Optional<AlunoEntity> aluno_Optional = this.repository.findById(id);

        if (aluno_Optional.isPresent()) {
            AlunoEntity alunoEntity = aluno_Optional.get();
            alunoEntity.setAtivo(false);
            return this.repository.save(alunoEntity);
        } else {
            return "Não deletado logicamente";
        }
    }
}
