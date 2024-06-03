package br.com.sistema_cadastros.inicio.execeptions;

public class TurmaNotFoudExecption extends RuntimeException {
    public TurmaNotFoudExecption(){
        super("Turma não encontrada");
    }
    public TurmaNotFoudExecption(String msg){
        super(msg);
    }
    
}
