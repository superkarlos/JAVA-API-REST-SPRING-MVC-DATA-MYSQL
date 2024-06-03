package br.com.sistema_cadastros.inicio.controller;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistema_cadastros.inicio.dto.AlunoDTO;
import br.com.sistema_cadastros.inicio.dto.TurmaDTO;
import br.com.sistema_cadastros.inicio.model.AlunoEntity;
import br.com.sistema_cadastros.inicio.model.TurmaEntity;
import br.com.sistema_cadastros.inicio.repostory.RepositorioAluno;
import br.com.sistema_cadastros.inicio.repostory.RepositorioProfessor;
import br.com.sistema_cadastros.inicio.repostory.RepositoryTurma;
import br.com.sistema_cadastros.inicio.service.TurmaService;
import jakarta.persistence.PreUpdate;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Turma")
public class TurmaController {
    @Autowired
    private RepositoryTurma repositoryTurma;
    @Autowired
    private RepositorioProfessor repositorioProfessor;

    @Autowired
    private RepositorioAluno repositorioAluno;

    @Autowired
    private TurmaService service;

    @GetMapping("/listar")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.Lista_tumas());
    }
   
    @GetMapping("/professor/{id}")
    public ResponseEntity<?> getAll_professpr(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body( service.professor_turmas(id));
        
    }
    @GetMapping("/aluno/{id}")
    public ResponseEntity<?> getAll_aluno(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body( service.aluno_turmas(id));
        
    }
    @GetMapping("/listar_logica")
    public ResponseEntity<?> getAllLogic() {
        return ResponseEntity.status(HttpStatus.OK).body(service.lista_logica());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.Lista_tumas_id(id));
    }

    @PostMapping("/post")
    public ResponseEntity<?> postTurma(@Valid @RequestBody TurmaDTO turmaDTO) {
        try {
            TurmaEntity turma = service.cadastrar(turmaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(turma);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
        }
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<?> putTurma(@PathVariable(value = "id") Long id, @RequestBody TurmaDTO turmaDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.editar(id, turmaDTO));
    }
    
    @PreUpdate
    @Transactional
    @PutMapping("/{idturma}/AssociarProfessor/{idprofessor}")
    public ResponseEntity associar(@PathVariable(value = "idturma") Long idT,
            @PathVariable(value = "idprofessor") Long idP) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.assosiarprofessor(idP, idT));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> dell(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(service.deletar(id));
    }

    @DeleteMapping("/desativer/{id}")
    public ResponseEntity<?> getByIdLogic(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.exluirLogico(id));
    }

    @DeleteMapping("/{idturma}/removerProfessor/{idprofessor}")
    public ResponseEntity deletarProfessor(@PathVariable(value = "idturma") Long idT,
            @PathVariable(value = "idprofessor") Long idP) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.remover_professor(idT, idP));
    }

    @DeleteMapping("/{turmaId}/removerAluno/{alunoId}")
    public ResponseEntity<?> removerAlunoDaTurma(@PathVariable Long turmaId, @PathVariable Long alunoId) {
        return service.removerAluno(turmaId, alunoId);
    }

    // @Transactional

    @PreUpdate
    @Transactional
    @PutMapping("/{id}/matricularAluno/{idaluno}")
    public ResponseEntity<?> matricularAluno(@PathVariable Long id, @PathVariable Long idaluno) {
        return service.matricularAluno(id, idaluno);
    }

}
