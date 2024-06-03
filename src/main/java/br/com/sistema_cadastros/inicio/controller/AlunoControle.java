package br.com.sistema_cadastros.inicio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistema_cadastros.inicio.dto.AlunoDTO;
import br.com.sistema_cadastros.inicio.model.AlunoEntity;
import br.com.sistema_cadastros.inicio.service.AlunoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Alunos")
public class AlunoControle {

  @Autowired
  private AlunoService service;

  @GetMapping("/lista")
  public ResponseEntity<List<AlunoEntity>> getAll() {
    // retorna a lista de alunos
    // System.out.println(service.lista_alunos());
    return ResponseEntity.status(HttpStatus.OK).body(service.lista_alunos());
  }

  @GetMapping("/lista_logica")
  public ResponseEntity<List<AlunoEntity>> getAll_logic() {
    return ResponseEntity.status(HttpStatus.OK).body(service.lista_alunos_logica());
  }

  // inserir via json
  @PostMapping("/post")
  public ResponseEntity<AlunoEntity> inserir(@Valid @RequestBody AlunoDTO alunoDTO) {

    return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.inserir(alunoDTO));
  }

  // retorna um unico id
  @GetMapping("lista/{id}")
  public ResponseEntity<Object> getOne(@PathVariable(value = "id") Long id) {

   // return ResponseEntity.status(HttpStatus.OK).body(service.lista_id(id));
   return ResponseEntity.ok(service.lista_id(id));
  }

  @PutMapping("/post/{id}")
  public ResponseEntity<Object> putOne(@Valid @PathVariable(value = "id") Long id, @RequestBody AlunoDTO alunodto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.atualizar(id, alunodto));

  }

  @DeleteMapping("deletar/{id}")
  public ResponseEntity<Object> delepeOne(@PathVariable(value = "id") Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(service.excluir(id));

  }

  @DeleteMapping("deletarLogico/{id}")
  public ResponseEntity<Object> delepe_logicoOne(@PathVariable(value = "id") Long id) {

    return ResponseEntity.status(HttpStatus.OK).body(this.service.exluirLogico(id));

  }

}
