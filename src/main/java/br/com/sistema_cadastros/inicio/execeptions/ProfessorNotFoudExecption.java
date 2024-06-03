package br.com.sistema_cadastros.inicio.execeptions;

public class ProfessorNotFoudExecption  extends RuntimeException{
    
    public ProfessorNotFoudExecption (){
        super("Professor não encontrado");
    }

    public ProfessorNotFoudExecption (String msg){
        super(msg);
    }
}
