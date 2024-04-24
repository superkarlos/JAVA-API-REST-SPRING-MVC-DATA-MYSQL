package br.com.sistema_cadastros.inicio.Enum;

public enum Genero {
    Masculino("Masculino"),
    Feminino("Feminino"),
    Nenhum("Nenhum");

    private String genero;
    
    private Genero( String genero){
        this.genero=genero;
    }
   
}
