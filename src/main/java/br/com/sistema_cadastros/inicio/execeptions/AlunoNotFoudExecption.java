package br.com.sistema_cadastros.inicio.execeptions;

public class AlunoNotFoudExecption extends RuntimeException{

    public AlunoNotFoudExecption(){
        super("Aluno não encontrado");
    }
    public AlunoNotFoudExecption(String msg){
        super(msg);
    }
}
