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
    public ResponseEntity<?> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.Lista_tumas());
    }
    @GetMapping("/listar_logica")
    public ResponseEntity<?> getAllLogic(){
        return ResponseEntity.status(HttpStatus.OK).body(service.lista_logica());
    }
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> getById(@PathVariable (value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.Lista_tumas_id(id));
    }
   
    @PostMapping("/post")
    public ResponseEntity<?>postTurma(@RequestBody TurmaDTO turmaDTO){
        return ResponseEntity.status(HttpStatus.OK).body(service.cadastrar(turmaDTO));
    }
    @PutMapping("/post/{id}")
    public ResponseEntity<?>putTurma(@PathVariable (value = "id") Long id,@RequestBody TurmaDTO turmaDTO){
        return ResponseEntity.status(HttpStatus.OK).body(service.editar(id,turmaDTO));
    }
    @GetMapping("/{idturma}/AssociarProfessor/{idprofessor}")
    public ResponseEntity associar(@PathVariable (value = "idturma") Long idT,@PathVariable (value = "idprofessor") Long idP)throws Exception{   
        return ResponseEntity.status(HttpStatus.OK).body(service.assosiarprofessor(idP, idT));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> dell(@PathVariable (value = "id") Long id){
        return ResponseEntity.ok(service.deletar(id));
    }
    @DeleteMapping("/desativer/{id}")
    public ResponseEntity<?> getByIdLogic(@PathVariable (value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.exluirLogico(id));
    }
   
   // @Transactional

   @PreUpdate
   @Transactional
   @GetMapping("/{id}/matricularAluno/{idaluno}")
public ResponseEntity<?> matricularAluno(@PathVariable Long id, @PathVariable Long idaluno) {
    Optional<TurmaEntity> optionalTurma = this.repositoryTurma.findById(id);
    Optional<AlunoEntity> optionaAluno = this.repositorioAluno.findById(idaluno);

    if (optionalTurma.isPresent() && optionaAluno.isPresent()) {
        TurmaEntity turmaEntity = optionalTurma.get();
        AlunoEntity alunoEntity = optionaAluno.get();
        
        // Imprimindo apenas os IDs para evitar recursão
        System.out.println("Turma ID: " + turmaEntity.getId());
        System.out.println("Aluno ID: " + alunoEntity.getId());

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
