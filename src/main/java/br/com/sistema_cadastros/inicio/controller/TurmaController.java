package br.com.sistema_cadastros.inicio.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<?> getall(){
        return ResponseEntity.status(HttpStatus.OK).body(service.Lista_tumas());
    }
    @PostMapping("/post")
    public ResponseEntity<?>postTurma(@RequestBody TurmaDTO turmaDTO){
        return ResponseEntity.status(HttpStatus.OK).body(service.cadastrar(turmaDTO));
    }
    
    
}
