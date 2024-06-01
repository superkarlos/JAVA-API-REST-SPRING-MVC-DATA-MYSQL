package br.com.sistema_cadastros.inicio.controller;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.Service;
import org.springframework.beans.BeanUtils;
//import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistema_cadastros.inicio.dto.ProfessorDTO;
import br.com.sistema_cadastros.inicio.model.AlunoEntity;
//import br.com.sistema_cadastros.inicio.dto.ProfessorDTO;
import br.com.sistema_cadastros.inicio.model.ProfessorEntity;
import br.com.sistema_cadastros.inicio.model.ProfessorEntity;
import br.com.sistema_cadastros.inicio.repostory.RepositorioProfessor;
import br.com.sistema_cadastros.inicio.service.ProfessorService;
import ch.qos.logback.core.joran.util.beans.BeanUtil;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Professor")
public class ProfessorController {

  @Autowired
  private ProfessorService service;

  @GetMapping("lista")
  public ResponseEntity<List<ProfessorEntity>> getAll() {

    return ResponseEntity.status(HttpStatus.OK).body(service.lista());
  }

  @GetMapping("/lista_logica")
  public ResponseEntity<List<ProfessorEntity>> getAll_logic() {

    return ResponseEntity.status(HttpStatus.OK).body(service.listaLogica());

  }

  @GetMapping("lista/{id}")
  public ResponseEntity<Object> getUnioo(@PathVariable(value = "id") Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(service.listaID(id));
  }

  @PostMapping("/post")
  public ResponseEntity<?> post(@Valid @RequestBody ProfessorDTO professorDTO) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastro(professorDTO));
  }

  @PutMapping("post/{id}")
  public ResponseEntity<Object> putUnico(@PathVariable(value = "id") Long id, @RequestBody ProfessorDTO professordto) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(service.atualizar(id, professordto));
  }

  // @GetMapping("deletar/{id}")
  @DeleteMapping("deletar/{id}")
  public ResponseEntity<Object> delepeOne(@PathVariable(value = "id") Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(service.deletar(id));
  }

  @DeleteMapping("deletarLogico/{id}")
  public ResponseEntity<Object> delepe_logicoOne(@PathVariable(value = "id") Long id) {
    return service.desativar(id);
  }

  @GetMapping("Turma/entrar/{id}")
  public ResponseEntity<String> entraEntity(@PathVariable(value = "id") Long id) {

    return ResponseEntity.status(HttpStatus.OK).body(service.entrar(id));

  }

}