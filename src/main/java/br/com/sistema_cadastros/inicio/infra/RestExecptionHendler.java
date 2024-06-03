package br.com.sistema_cadastros.inicio.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.sistema_cadastros.inicio.execeptions.AlunoNotFoudExecption;
import br.com.sistema_cadastros.inicio.execeptions.ProfessorNotFoudExecption;
import br.com.sistema_cadastros.inicio.execeptions.TurmaNotFoudExecption;

@ControllerAdvice
public class RestExecptionHendler extends ResponseEntityExceptionHandler {

 //   private
     @ExceptionHandler(ProfessorNotFoudExecption.class)
     private ResponseEntity <RestErroMesseger> ProfessorNotFound(ProfessorNotFoudExecption execption){
        RestErroMesseger menMesseger = new RestErroMesseger(HttpStatus.NOT_FOUND,execption.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(menMesseger);
     }
    
    @ExceptionHandler(AlunoNotFoudExecption.class)
     private ResponseEntity<RestErroMesseger> AlunoNotFoudExecption( AlunoNotFoudExecption execption){
        RestErroMesseger mensagem = new RestErroMesseger(HttpStatus.NOT_FOUND, execption.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
    }
    
}
