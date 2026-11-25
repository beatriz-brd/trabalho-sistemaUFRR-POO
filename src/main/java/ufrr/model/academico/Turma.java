package ufrr.model.academico;

import java.time.LocalDate;
import java.util.List;

import ufrr.exception.TurmaInvalidaException;
import ufrr.exception.CodigoTurmaInvalidoException;
import ufrr.model.pessoas.Discente;
import ufrr.model.pessoas.Docente;

import java.util.ArrayList;
import java.util.Collections;

public class Turma {
    // Atributos
    private String codigo;
    private String nome;
    private int capacidadeAtual = 0;
    private int capacidadeMaxima;
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private STATUSTURMA status;

    // Enum status
    public enum STATUSTURMA {
        CADASTRADA,
        ATIVA,
        ARQUIVADA
    }

    // Associações/composições com outras classes
    private List<Discente> discentes = new ArrayList<>();
    private Docente docente;
    private Disciplina disciplina;
    private Departamento departamento;

    // -- Construtor --
    public Turma(String codigo, String nome, int capacidadeMaxima, String dataInicio, String dataFinal)
        throws CodigoTurmaInvalidoException, TurmaInvalidaException {
        checarCodigo(codigo);
        checarDadosTurma(nome, capacidadeMaxima, dataInicio, dataFinal);

        this.codigo = codigo;
        this.nome = nome;
        this.capacidadeMaxima = capacidadeMaxima;
        this.dataInicio = LocalDate.parse(dataInicio);
        this.dataFinal = LocalDate.parse(dataFinal);

        definirStatus();
    }

    // Métodos auxiliares e chamadores das exceções
    private LocalDate parsearDataObrigatoria(String dataTexto, String rotulo)
        throws TurmaInvalidaException {
        if (dataTexto == null || dataTexto.isBlank()) {
            throw new TurmaInvalidaException(
                "ERRO: A data de " + rotulo + " não pode estar vazia!\n"
            );
        }

        try {
            return LocalDate.parse(dataTexto);
        } catch (java.time.format.DateTimeParseException e) {
            throw new TurmaInvalidaException(
                "ERRO: A data de " + rotulo + " está em formato inválido (use AAAA-MM-DD)!\n"
            );
        }
    }

    private void checarCodigo(String codigo) 
        throws CodigoTurmaInvalidoException {
        if (codigo == null || codigo.isBlank()) {
            throw new CodigoTurmaInvalidoException(
                "ERRO: Código da turma não pode ser nulo!\n"
            );
        }
        
        if (codigo.length() != 7) {
            throw new CodigoTurmaInvalidoException(
                "ERRO: Código inserido com tamanho inválido, o tamanho deve ser de 7 dígitos!\n"
            );
        }

        if (!(codigo.substring(0, 1).equals("T"))) {
            throw new CodigoTurmaInvalidoException(
                "ERRO: O código deve iniciar com a letra 'T'!\n"
            );
        }

        for (int i = 1; i < codigo.length(); i++) {
            if (!Character.isDigit(codigo.charAt(i))) {
                throw new CodigoTurmaInvalidoException(
                    "ERRO: O código deve ter somente dígitos a partir da letra 'T'!\n"
                );
            }
        }
    }

    private void checarDadosTurma(String nome, int capacidadeMaxima, String DataInicio, String DataFinal)
        throws TurmaInvalidaException {
        if (nome == null || nome.equals("")) {
            throw new TurmaInvalidaException("ERRO: O nome da turma não pode estar vazio!\n");
        }

        if (capacidadeMaxima <= 0) {
            throw new TurmaInvalidaException(
                "ERRO: A capacidade máxma da turma não pode ser negativa ou nula!"
            );
        }

        LocalDate dateInicio = LocalDate.parse(DataInicio);
        LocalDate dateFinal = LocalDate.parse(DataFinal);

        if (dateInicio.isAfter(dateFinal)) {
            throw new TurmaInvalidaException(
                "ERRO: A data de término do período letivo não deve ser anterior a data de início!"
            );
        }
    }

    private void definirStatus() {
        if (this.dataInicio.isAfter(LocalDate.now())) {
            this.status = STATUSTURMA.CADASTRADA;
        } else if (this.dataFinal.isBefore(LocalDate.now())) {
            this.status = STATUSTURMA.ARQUIVADA;
        } else {
            this.status = STATUSTURMA.ATIVA;
        }
    }

    // -- Métodos --
    public void arquivarTurma() {
        this.status = STATUSTURMA.ARQUIVADA;
    }

    public void alterarDados(String nome, int capacidadeMaxima, String dataInicio, String dataFinal) 
        throws TurmaInvalidaException {
        LocalDate novoInicio = parsearDataObrigatoria(dataInicio, "início");
        LocalDate novoFim = parsearDataObrigatoria(dataFinal, "término");

        if (novoInicio.isAfter(novoFim)) {
            throw new TurmaInvalidaException(
                "ERRO: A data de término do período letivo não deve ser anterior à data de início!\n"
            );
        }

        setNome(nome);

        setCapacidadeMaxima(capacidadeMaxima);

        this.dataInicio = novoInicio;
        this.dataFinal = novoFim;

        definirStatus();
    }

    // -- Getters e Setters --
    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getCapacidadeAtual() {
        return capacidadeAtual;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public STATUSTURMA getStatus() {
        return status;
    }

    public List<Discente> getDiscentes() {
        return Collections.unmodifiableList(discentes);
    }

    public Docente getDocente() {
        return docente;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setCodigo(String codigo)
        throws CodigoTurmaInvalidoException {
        checarCodigo(codigo);
        this.codigo = codigo;
    }

    private void setNome(String nome)
        throws TurmaInvalidaException {
        if (nome == null || nome.isBlank()) {
            throw new TurmaInvalidaException("ERRO: O nome da turma não pode estar vazio!\n");
        }
        this.nome = nome;
    }

    private void setCapacidadeMaxima(int capacidadeMaxima)
        throws TurmaInvalidaException {
        if (capacidadeMaxima <= 0) {
            throw new TurmaInvalidaException(
                "ERRO: A capacidade máxima deve ser positiva!\n"
            );
        }

        if (capacidadeMaxima < this.capacidadeAtual) {
            throw new TurmaInvalidaException(
                "ERRO: A capacidade máxima não pode ser menor que a capacidade atual!\n"
            );
        }
        this.capacidadeMaxima = capacidadeMaxima;
    }

    /* private void setDataFinal(String dataFinal)
        throws TurmaInvalidaException {
        if (dataFinal == null || dataFinal.isBlank()) {
            throw new TurmaInvalidaException("ERRO: A data de término não pode estar vazia!\n");
        }

        LocalDate novaDataFinal = LocalDate.parse(dataFinal);

        if (this.dataInicio != null && this.dataInicio.isAfter(novaDataFinal)) {
            throw new TurmaInvalidaException(
                "ERRO: A data de término não pode ser anterior à data de início!\n"
            );
        }

        this.dataFinal = novaDataFinal;
        definirStatus();
    }

    private void setDataInicio(String dataInicio)
        throws TurmaInvalidaException {
        if (dataInicio == null || dataInicio.isBlank()) {
            throw new TurmaInvalidaException("ERRO: A data de início não pode estar vazia!\n");
        }

        LocalDate novaDataInicio = LocalDate.parse(dataInicio);
        if (this.dataFinal != null && novaDataInicio.isAfter(this.dataFinal)) {
            throw new TurmaInvalidaException(
                "ERRO: A data de início não pode ser posterior à data de término!\n"
            );
        }
        
        this.dataInicio = novaDataInicio;
        definirStatus();
    }
    */

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    private void atualizarCapacidadeAtual()
        throws TurmaInvalidaException {
        if (discentes == null) {
            throw new TurmaInvalidaException(
                "ERRO: A lista de discentes não pode ser nula!\n"
            );
        }

        if (discentes.size() > this.capacidadeMaxima) {
            throw new TurmaInvalidaException(
                "ERRO: A quantidade de discentes excede a capacidade máxima da turma!\n"
            );
        }

        this.capacidadeAtual = discentes.size();
    }

    // -- Listas --
    public void adicionarDiscente(Discente discente)
        throws TurmaInvalidaException {
        if (discente == null) {
            throw new TurmaInvalidaException("ERRO: O discente não pode ser nulo!\n");
        }

        if (this.capacidadeAtual >= this.capacidadeMaxima) {
            throw new TurmaInvalidaException("ERRO: A turma já atingiu a capacidade máxima!\n");
        }
        if (!discentes.contains(discente)) {
            discentes.add(discente);
            atualizarCapacidadeAtual();
        } else {
            throw new TurmaInvalidaException("AVISO: Discente já cadastrado na turma!\n");
        }
    }

    public boolean removerDiscente(Discente discente)
        throws TurmaInvalidaException {
        boolean removido = discentes.remove(discente);
        if (removido) {
            atualizarCapacidadeAtual();
        }
        return removido;
    }
}
