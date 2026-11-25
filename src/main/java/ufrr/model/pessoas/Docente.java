package ufrr.model.pessoas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ufrr.model.academico.Turma;
import ufrr.model.pessoas.Discente;


import ufrr.exception.TurmaInvalidaException;


public class Docente extends Pessoa {
    
    // atributos
    private String codigo;
    private String formacao;
    private String nivelAcademico;
    private final LocalDate dataAdmissao;
    
    // relacionamentos com outras classes
    private List<Turma> turmas = new ArrayList<>();
    private List<Discente> discentes = new ArrayList<>();

    
    public Docente(String nome, LocalDate dataNascimento, String email, String cpf,
        String codigo, String formacao, String nivelAcademico, LocalDate dataAdmissao) 
            
        throws DocenteInvalidoException {
        super(nome, dataNascimento, email, cpf);
                
        exigirTexto(codigo, "Codigo do docente");
        exigirTexto(formacao, "Formacao");
        exigirTexto(nivelAcademico, "Nivel Academico");
        validarDataAdmissao(dataAdmissao);
        
        this.codigo = codigo;
        this.formacao = formacao;
        this.nivelAcademico = nivelAcademico;
        // o atributo deve ser String ou nao tem problema continuar sendo localDate?
        this.dataAdmissao = dataAdmissao;
        
    }
    
    // metodos auxiliares de validacao
    private void exigirTexto(String atributo, String nome)
    throws DocenteInvalidoException{
        
        if(atributo == null || atributo.isBlank()){
            throw new DocenteInvalidoException(nome + " nao pode estar vazio(a)! ");
        }
    }
    
    private void validarDataAdmissao(LocalDate data){
        if(data == null){
            throw new DadoinvalidoException("Data de admissão não pode ser nula ");
        }
        if (dataAdmissao.isAfter(LocalDate.now())) {
            throw new DadoInvalidoException("Data de admissão não pode ser futura ");
        }
    }
    
    private static void exigirTurma(Turma turma, String nome) {
        if (turma == null) {
            throw new DadoInvalidoException(nome + " não pode ser nula");
        }
    }

    
    // getters
    public String getCodigo(){
        return this.codigo;
    }
    
    public String getFormacao(){
        return this.formacao;
    }
    
    public String getNivelAcademico(){
        return this.nivelAcademico;
    }
    
    public LocalDate getDataAdmissao(){
        return this.dataAdmissao;
    }
    
    
    // metodos da classe
    public void solicitarAtribuicaoTurma(Turma turma)
    throws TurmaInvalidaException {
        exigirTurma(turma, "Turma");
        
        if(!turmas.contains(turma)){
            turmas.add(turma);
        }
        
    };
    
    
    public Processo solicitarRemocaoTurma(Turma turma){}; 
    public Processo solicitarMudancaTurma(Turma turmaDestino, Turma turmaOrigem){};
    public void lancarNotas(Turma turma, List<Discente> discentes){};
    public void consolidarNotas(Turma turma){};
}
