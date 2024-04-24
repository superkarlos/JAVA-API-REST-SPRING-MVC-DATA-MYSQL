package br.com.sistema_cadastros.inicio.controller;
import java.util.List;
//import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistema_cadastros.inicio.model.ProfessorEntity;
import br.com.sistema_cadastros.inicio.repostory.RepositorioProfessor;


@RestController
@RequestMapping("/Professor")
public class ProfessorController {

    @Autowired
    private RepositorioProfessor repositorioProfessor;

    @GetMapping("")
    public String home(){
        return "Homeb ";
    }

    @GetMapping("/inicio")
    public String inicio(){
        return "Pagina inicial professores";
    }
    @GetMapping("/{id}")
    public String id( @PathVariable String id){
       // int novo = Integer.parseInt(id);
        return " id do professor  é "+ (id);
    }
    

    @PostMapping("/cadastrar")
    public ProfessorEntity cadastaProfessorEntity( @RequestBody ProfessorEntity p){
       // System.out.println(p.getNome());
       // System.out.println(p);
        return repositorioProfessor.save(p);
    }

   // @GetMapping("/ordemNome")
   // public List<ProfessorEntity> ordemNome(){
      //return repositorioProfessor.findByOrderNome();
   // }

    @GetMapping( "/contador")
    public long contador(){
        return repositorioProfessor.count();
    }

    @GetMapping("/listar")
    public List<ProfessorEntity> lista_professor(){
        return repositorioProfessor.findAll();
    }

    @GetMapping("/codigo/{id}")
    public ProfessorEntity selet_cod(@PathVariable int id){
        return repositorioProfessor.findById(id);
    }


    @PutMapping("/editar")
    public ProfessorEntity editar(@RequestBody ProfessorEntity p)
    {
        return repositorioProfessor.save(p);

    }

    @DeleteMapping("remover/{id}")
    public void remover(@PathVariable int id){

        ProfessorEntity p = selet_cod(id);
        repositorioProfessor.delete(p);

    }
}
