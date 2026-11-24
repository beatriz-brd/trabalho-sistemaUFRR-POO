package ufrr.model.processos;

import ufrr.model.academico.Turma;
import ufrr.model.academico.Disciplina;
import java.time.LocalDate;

import ufrr.exception.StatusProcessoInvalido;


public abstract class Processo {
   private final String numeroProcesso;
   private final String objetoProcesso;
   private final String interessadoID;
   private Disciplina disciplinaDestino;
   private Turma turmaDestino;
   private final LocalDate dataSolicitacao;
   private StatusProcesso status;
   
   //enum para evitar erros de escrita
   public enum StatusProcesso{
        ABERTO,
        DEFERIDO,
        INDEFERIDO,
        ARQUIVADO
    }
   
   //ajustado construtor e adicionada verificacao de parametros (declarado metodo auxiliar exigirTexto)
   //construtor
   public Processo(String numeroProcesso, String objetoProcesso,
           String interessadoID, Disciplina disciplinaDestino,
           Turma turmaDestino, LocalDate dataSolicitacao) {
       
       exigirTexto(numeroProcesso, "numeroProcesso");
       exigirTexto(objetoProcesso, "objetoProcesso");
       exigirTexto(interessadoID, "interessadoID");
       
        this.numeroProcesso = numeroProcesso;
        this.objetoProcesso = objetoProcesso;
        this.interessadoID = interessadoID;
        this.disciplinaDestino = disciplinaDestino;
        this.turmaDestino = turmaDestino;
        this.dataSolicitacao = dataSolicitacao;
        this.status = StatusProcesso.ABERTO;
    }
   
   // metodo auxiliar
    private static void exigirTexto(String valor, String nome) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(nome + " não pode ser nulo ou vazio");
        }
    }
    
   // getters
    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public String getObjetoProcesso() {
        return objetoProcesso;
    }

    public String getInteressadoID() {
        return interessadoID;
    }

    public Disciplina getDisciplinaDestino() {
        return disciplinaDestino;
    }

    public Turma getTurmaDestino() {
        return turmaDestino;
    }

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

    public StatusProcesso getStatus() {
        return status;
    }
    
    
    // metodos
    /* diferenca para o uml: status é enum, e alterar status é publico com validacao */
    
    public void alterarStatus(StatusProcesso novo) 
       throws StatusProcessoInvalido{
        if (status == null) {
            throw new StatusProcessoInvalido("ERRO: O status do processo não pode ser nulo!");
        }       
       if(status != StatusProcesso.ABERTO){
           throw new StatusProcessoInvalido("Processo: " + numeroProcesso + " ja esta " + status + " e nao pode mudar!");
       }
       
       this.status = novo;
   }
    
   protected void arquivarProcesso() throws StatusProcessoInvalido {
        alterarStatus(StatusProcesso.ARQUIVADO);
   };
   
   protected void indeferirProcesso() throws StatusProcessoInvalido {
        alterarStatus(StatusProcesso.INDEFERIDO);
   };   
   
   protected void deferirProcesso() throws StatusProcessoInvalido {
        alterarStatus(StatusProcesso.DEFERIDO);
   };
   
   public abstract void resolver();   
}
