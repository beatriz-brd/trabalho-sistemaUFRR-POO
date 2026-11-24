package ufrr.model.processos;

import java.time.LocalDate;
import ufrr.model.academico.Disciplina;
import ufrr.model.academico.Turma;

public class ProcessoMudancaTurma extends Processo {

    public ProcessoMudancaTurma(String numeroProcesso, String interessadoID,
            Disciplina disciplinaDestino, Turma turmaDestino,
            LocalDate dataSolicitacao) {
        
        if (turmaDestino == null) {
            throw new IllegalArgumentException("Mudança de turma exige a turma desejada");
        }
        super(numeroProcesso, "MUDANCA_TURMA", interessadoID,
                disciplinaDestino, turmaDestino, dataSolicitacao);
        
    }

    @Override
    public void resolver() {
        // Regra delegada ao service
    }
}
