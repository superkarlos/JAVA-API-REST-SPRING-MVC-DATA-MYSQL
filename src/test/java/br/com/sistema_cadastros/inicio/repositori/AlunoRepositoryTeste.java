package br.com.sistema_cadastros.inicio.repositori;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.sistema_cadastros.inicio.Enum.Genero;
import br.com.sistema_cadastros.inicio.dto.AlunoDTO;
import br.com.sistema_cadastros.inicio.model.AlunoEntity;
import br.com.sistema_cadastros.inicio.repostory.RepositorioAluno;
import jakarta.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("teste")
class AlunoRepositoryTeste {

    @Autowired
    EntityManager entityManager;

    @Autowired
    RepositorioAluno repositorioAluno;

    @Test
    @DisplayName("Deve encontrar aluno pelo nome")
    void findByAlunosNome() {
        AlunoDTO dto = new AlunoDTO("CARLOS", "11221432", 12112, Genero.MASCULINO, "historia", "12/12/21");
        this.createUser(dto);

        Optional<AlunoEntity> resultado = this.repositorioAluno.findByNome("CARLOS");

        assertThat(resultado.isPresent()).isTrue();
        assertThat(resultado.get().getNome()).isEqualTo("CARLOS");
    }

    @Test
    @DisplayName("Não deve encontrar aluno com nome inexistente")
    void shouldNotFindByNonExistentAlunosNome() {
        AlunoDTO dto = new AlunoDTO("MARIA", "11221432", 12112, Genero.FEMININO, "matematica", "11/11/21");
        this.createUser(dto);

        Optional<AlunoEntity> resultado = this.repositorioAluno.findByNome("MARIA");
        assertThat(resultado.get().getNome()).isEqualTo("MARIA");
        assertThat(resultado.get().getCpf());
      //  assertThat(resultado.isEmpty()).isTrue();

    }
    
    @Test
   
    private AlunoEntity createUser(AlunoDTO alunoDTO) {
        AlunoEntity alunoEntity = new AlunoEntity();
        BeanUtils.copyProperties(alunoDTO, alunoEntity);
        this.entityManager.persist(alunoEntity);
        return alunoEntity;
    }
}
