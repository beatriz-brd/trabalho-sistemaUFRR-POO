package ufrr.model.processos;

import java.time.LocalDate;
import ufrr.model.academico.Disciplina;
import ufrr.model.academico.Turma;

import ufrr.exception.TurmaInvalidaException;


public class ProcessoTrancamento extends Processo{
    private final String motivoTrancamento;
    private boolean trancamentoValido = false;

    public ProcessoTrancamento(String numeroProcesso, String interessadoID,
        Disciplina disciplinaDestino, Turma turmaDestino,
        LocalDate dataSolicitacao, String motivoTrancamento) {

        super(numeroProcesso, "TRANCAMENTO", interessadoID,
                disciplinaDestino, turmaDestino, dataSolicitacao);

        if(turmaDestino == null){
            throw new TurmaInvalidaException("Trancamento exige a turma ser trancada! ");
        }
        if(motivoTrancamento == null){
            throw new IllegalArgumentException("O Processo de trancamento precisa de um motivo! ");
        }
        
        
        this.motivoTrancamento = motivoTrancamento;
        this.trancamentoValido = true;
        
    }
    
    // getters
    public String getMotivoTrancamento() {
        return motivoTrancamento;
    }

    public boolean isTrancamentoValido() {
        return trancamentoValido;
    }
    
    
    @Override
    public void resolver(){};

}
