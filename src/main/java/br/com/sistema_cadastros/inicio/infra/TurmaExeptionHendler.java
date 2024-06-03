package br.com.sistema_cadastros.inicio.infra;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.sistema_cadastros.inicio.execeptions.TurmaNotFoudExecption;



@ControllerAdvice
public class TurmaExeptionHendler  extends  ResponseEntityExceptionHandler {

    @ExceptionHandler(TurmaNotFoudExecption.class)
    private ResponseEntity<RestErroMesseger> turmaerro(TurmaNotFoudExecption execption){
        RestErroMesseger messeger = new RestErroMesseger(HttpStatus.NOT_FOUND, execption.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messeger);
    }
   
}
    

