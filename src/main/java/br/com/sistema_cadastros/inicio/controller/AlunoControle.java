package br.com.sistema_cadastros.inicio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistema_cadastros.inicio.model.AlunoEntity;
import br.com.sistema_cadastros.inicio.repostory.RepositorioAluno;

@RestController
@RequestMapping("/Alunos")
public class AlunoControle {

    @Autowired
    private RepositorioAluno repositorioAluno;


    @GetMapping("/inicio")
    public String inico(){
        return "pagina inicial alunos";
    }
    
    @GetMapping("/{id}")
    public String id( @PathVariable String id){
       // int novo = Integer.parseInt(id);
        return " id do aluno é "+ (id);
    }
    

    @PostMapping("/post_aluno")
    public AlunoEntity aluno( @RequestBody AlunoEntity aluno){
        System.err.println( "Nome:"+aluno.getNome());
        System.out.println("ID :"+aluno.getCpf());
        System.out.println( " Genero :"+ aluno.getGenero());
        return aluno;
    }
}
