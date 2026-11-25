package ufrr.model.avaliacoes;

import java.time.LocalDate;

import ufrr.exception.DadoInvalidoException;

public class Nota {
    // atributos
    private String id;
    private String nome;
    private double valor;
    private int peso;
    
    // local date mesmo ou string e depois parse()?
    private LocalDate dataLancamento;
    
    // construtor
    public Nota(String id, String nome, double valor, int peso, LocalDate dataLancamento)
    throws NotaInvalidaException{
        exigirTexto(id, "ID");
        exigirTexto(nome, "Nome");
        
        validarNota(valor, peso, dataLancamento);
        
        this.id = id;
        this.nome = nome; 
        this.valor = valor;
        this.peso = peso;
        this.dataLancamento = dataLancamento;
    }
    
    // metodos auxiliares de validacao
    public void exigirTexto(String atributo, String nome)
        throws NotaInvalidaException{
        if(atributo == null || atributo.isBlank()){
            throw new NotaInvalidaException(nome + " nao pode estar vazio ou ser nulo! ");
    }
        
    public void validarNota(double valor, int peso, LocalDate dataLancamento) throws NotaInvalidaException{

        if(valor < 0 || valor > 10.0){
            throw new NotaInvalidaException("O valor da nota nao pode ser negativo e nao pode passar de 10.0! ");
        }

        if(peso < 0){
            throw new NotaInvalidaException("O peso nao pode ser negativo! ");
        }        
        
        if(dataLancamento == null){
            throw new NotaInvalidaException("A data de lancamento nao pode ser nula! ");

        }
    }
    
    // getters
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public int getPeso() {
        return peso;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    // setters
    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
    
    // altera o objeto inteiro e sua relacao com outras classes ou apenas valor e peso? 
    public void alterarNota(){  };
    
}
