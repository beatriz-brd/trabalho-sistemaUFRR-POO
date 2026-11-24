package ufrr.model.processos;

import java.time.LocalDate;
import ufrr.model.academico.Disciplina;
import ufrr.model.academico.Turma;


public class ProcessoRemocao extends Processo{
    private String justificativa;
    
    public ProcessoRemocao(String numeroProcesso, String objetoProcesso,
           String interessadoID, Disciplina disciplinaDestino,
           Turma turmaDestino, LocalDate dataSolicitacao, String justificativa){
        
        super(numeroProcesso, "REMOCAO", interessadoID,
                disciplinaDestino, turmaDestino, dataSolicitacao);
        
        if(justificativa == null || justificativa.isBlank()){
            throw new IllegalArgumentException("Justificativa nao pode estar vazia! ");
        }
        
    }
    
    // getters
    public String getJustificativa(){
        return justificativa;
    };
    

    @Override
    public void resolver() {}
}
