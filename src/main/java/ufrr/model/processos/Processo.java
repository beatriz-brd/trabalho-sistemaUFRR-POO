package ufrr.model.processos;

import ufrr.model.academico.Turma;
import ufrr.model.academico.Disciplina;
import java.time.LocalDate;

  /* Nota: a implementacao do metodo herdado de ProcessoFactory fica a cargo 
   das subclasses*/

public abstract class Processo implements ProcessoFactory {
   private String numeroProcesso;
   private String objetoProcesso;
   private String interessadoID;
   private Disciplina disciplinaDestino;
   private Turma turmaDestino;
   private LocalDate dataSolicitacao;
   private String status;
   
   //construtor
   public Processo(String numeroProcesso, String objetoProcesso,
           String interessadoID, Disciplina diciplinaDestino,
           Turma turmaDestino, LocalDate dataSolicitacao, String status) {
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

    public String getStatus() {
        return status;
    }

    
    //setters
    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public void setObjetoProcesso(String objetoProcesso) {
        this.objetoProcesso = objetoProcesso;
    }

    public void setInterressadoID(String interessadoID) {
        this.interessadoID = interessadoID;
    }

    public void setDiciplinaDestino(Disciplina disciplinaDestino) {
        this.disciplinaDestino = disciplinaDestino;
    }

    public void setTurmaDestino(Turma turmaDestino) {
        this.turmaDestino = turmaDestino;
    }

    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   
    //metodos
    /*diferenca para o uml: metodos arquivar, indeferir e deferir deixam de ser 
    abstratos e o metodo alterarStatus foi removido*/
   protected void arquivarProcesso(){
       setStatus("ARQUIVADO");
   };
   protected void indeferirProcesso(){
       setStatus("INDEFERIDO");
   };   
   protected void deferirProcesso(){
       setStatus("DEFERIDO");
   };
   
   public abstract void resolver();   
}
