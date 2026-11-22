package ufrr.model.processos;

public interface ProcessoFactory {
    public enum TiposProcessos{
        MATRICULA,
        MUDANCATURMA,
        REMOCAO,
        TRANCAMENTO
    }
    public Processo criarProcesso(TiposProcessos tipo);
    
}
