package ufrr.model.processos;

import ufrr.model.academico.Turma;
import java.time.LocalDate;

public abstract class Processo {
   private String numeroProcesso;
   private String objetoProcesso;
   private String interressadoID;
   private Disciplina diciplinaDestino;
   private Turma turmaDestino;
   private LocalDate dataSolicitacao;
   private String status;
   
   protected abstract void arquivarProcesso();
   protected abstract void indeferirProcesso();   
   protected abstract void deferirProcesso();
   public abstract void resolver();
   protected abstract void alterarStatus();
   
}
