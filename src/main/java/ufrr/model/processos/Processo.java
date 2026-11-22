package ufrr.model.processos;

import ufrr.model.academico.Turma;
import ufrr.model.academico.Disciplina;
import java.time.LocalDate;

import ufrr.exception.StatusProcessoInvalido;
import ufrr.exception.TipoProcessoInvalido;

  /* Nota: a implementacao do metodo herdado de ProcessoFactory fica a cargo 
   das subclasses*/

public abstract class Processo implements ProcessoFactory {
   private String numeroProcesso;
   private String objetoProcesso;
   private String interessadoID;
   private Disciplina disciplinaDestino;
   private Turma turmaDestino;
   private LocalDate dataSolicitacao;
   private StatusProcesso status;
   
   //enum para evitar erros de escrita
   public enum StatusProcesso{
        ABERTO,
        DEFERIDO,
        INDEFERIDO,
        ARQUIVADO
    }
   
   //construtor
   public Processo(String numeroProcesso, String objetoProcesso,
           String interessadoID, Disciplina diciplinaDestino,
           Turma turmaDestino, LocalDate dataSolicitacao, StatusProcesso status) {
        this.numeroProcesso = numeroProcesso;
        this.objetoProcesso = objetoProcesso;
        this.interessadoID = interessadoID;
        this.disciplinaDestino = diciplinaDestino;
        this.turmaDestino = turmaDestino;
        this.dataSolicitacao = dataSolicitacao;
        this.status = status;
    }

   //getters
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

    
    //setters
    protected void setStatusProcesso(StatusProcesso status){
        this.status = status;
    }
    
    //metodos de validacao
    public void validarStatusProcesso(StatusProcesso status){
        private static final Set<StatusProcesso> StatusValidos = EnumSet.of(
            StatusProcesso.ABERTO,
            StatusProcesso.DEFERIDO,
            StatusProcesso.INDEFERIDO,
            StatusProcesso.ARQUIVADO
        );

        if(!status.equals(null) || status == null){
            throw new StatusProcessoInvalido(
            "ERRO: O status do processo nao pode ser nulo!");
        }
        if else(!StatusValidos.contains(status)){
            throw new StatusProcessoInvalido(
            "ERRO: O Status do processo nao e valido: " + status);
        }
    }
    
    //usado na criacao de processos
    public void validarTipoProcesso(TiposProcessos tipo){
        private static final Set<TiposProcessos> TiposProcessosValidos = EnumSet.of(
            TiposProcessos.MATRICULA,
            TiposProcessos.MUDANDCATURMA,
            TiposProcessos.REMOCAO,
            TiposProcessos.TRANCAMENTO
        );

        if(!tipo.equals(null) || tipo == null){
            throw new TipoProcessoInvalido(
            "ERRO: O tipo do processo nao pode ser nulo!");
        }
        if else(!TipoProcessoInvalido.contains(tipo)){
            throw new StatusProcessoInvalido(
            "ERRO: O tipo do processo nao e valido: " + status);
        }
    }
    

    //metodos
    /*diferenca para o uml: metodos arquivar, indeferir e deferir deixam de ser 
    abstratos e o metodo alterarStatus foi removido*/
   protected void arquivarProcesso(){
       setStatusProcesso(StatusProcesso.ARQUIVADO);
   };
   protected void indeferirProcesso(){
       setStatusProcesso(StatusProcesso.INDEFERIDO);
   };   
   protected void deferirProcesso(){
       setStatusProcesso(StatusProcesso.DEFERIDO);
   };
   
   public void alterarStatus(StatusProcesso status){
       
   }
   
   public abstract void resolver();   
}
