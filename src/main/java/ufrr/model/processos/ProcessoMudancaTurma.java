package ufrr.model.processos;

import java.time.LocalDate;
import ufrr.model.academico.Disciplina;
import ufrr.model.academico.Turma;

public class ProcessoMudancaTurma extends Processo {
    private Turma turmaOrigem;
    
    public ProcessoMudancaTurma(String numeroProcesso, String interessadoID,
            Disciplina disciplinaDestino, Turma turmaDestino,
            LocalDate dataSolicitacao, Turma turmaOrigem) {
        
        /* Por regra o super deve ser a primeira linha do construtor
          fazendo primeiro as validacoes do contrutor pai e depois da classe filha*/
         super(numeroProcesso, "MUDANCA_TURMA", interessadoID,
                disciplinaDestino, turmaDestino, dataSolicitacao);
         
        // se o parametro for inicializado com null na classe pai e jogado um erro
        if(turmaDestino == null){
            throw new IllegalArgumentException("Mudança de turma exige a turma desejada");
        }
        
        if(turmaOrigem == null){
            throw new IllegalArgumentException("Mudança de turma exige a turma de Origem");
        } 
            
       
        
        this.turmaOrigem = turmaOrigem;
        
    }
        
    @Override
    public void resolver() {
        // Regra delegada ao service
    }
}
