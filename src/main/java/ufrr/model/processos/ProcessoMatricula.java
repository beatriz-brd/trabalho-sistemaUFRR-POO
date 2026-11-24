package ufrr.model.processos;

import java.time.LocalDate;

import ufrr.model.academico.Turma;
import ufrr.model.academico.Disciplina;

// heranca
public class ProcessoMatricula extends Processo {
    private String periodoLetivo;
    private int prioridade;
    
    //construtor
    // ajustado parametros passados ao construtor pai
    public ProcessoMatricula(String numeroProcesso, String interessadoID,
           Disciplina disciplinaDestino, Turma turmaDestino, LocalDate dataSolicitacao, String periodoLetivo){
        
        //parametros passados ao construtor da classe pai
        super(numeroProcesso, "MATRICULA", interessadoID, disciplinaDestino, 
                turmaDestino, dataSolicitacao);
        
        this.periodoLetivo = periodoLetivo;
        this.prioridade = 0;
    }
    
    // getters
    public String getPeriodoLetivo() {   
        return periodoLetivo;
        
    }
   
    public int getPrioridade() {    
        return prioridade;
    }
    
    // setters
    public void setPeriodoLetivo(String periodoLetivo) {
        this.periodoLetivo = periodoLetivo;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
    

    // metodos
    // calcular prioridade fica a cargo de services
    
    @Override
    public void resolver() {
        
    }

    
}
