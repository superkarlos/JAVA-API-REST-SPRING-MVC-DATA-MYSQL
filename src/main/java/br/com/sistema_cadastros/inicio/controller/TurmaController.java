package br.com.sistema_cadastros.inicio.controller;


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

import br.com.sistema_cadastros.inicio.dto.TurmaDTO;
import br.com.sistema_cadastros.inicio.model.TurmaEntity;
import br.com.sistema_cadastros.inicio.service.TurmaService;

@RestController
@RequestMapping("/Turma")
public class TurmaController {

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
    @GetMapping("/{idturma}/assosicar/{idprofessor}")
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
}
