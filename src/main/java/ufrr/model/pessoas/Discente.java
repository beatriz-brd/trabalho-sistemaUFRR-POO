package ufrr.model.pessoas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import exception.DiscenteInvalidoException;
import exception.PessoaInvalidaException;
import model.academico.Disciplina;
import model.academico.Matricula;
import model.academico.Turma;
import model.processos.Processo;

public class Discente extends Pessoa {
    // Atributos
    private String matriculaID;
    private String curso;
    private double IRA = 0.0;
    private LocalDate dataIngresso;

    // Associações/composições com outras classes
    private List<Turma> turmas = new ArrayList<>();
    private List<Matricula> matriculas = new ArrayList<>();

    // Constante de auxílio
    public static final LocalDate DATA_MINIMA_INGRESSO = LocalDate.of(1988, 1, 1);

    // -- Construtor --
    public Discente(String nome, String dataNascimento, String email, String cpf,
        String matriculaID, String curso, double IRA, String dataIngresso)
        throws PessoaInvalidaException, DiscenteInvalidoException  {
        super(nome, dataNascimento, email, cpf);

        checarMatricula(matriculaID);
        checarDadosDiscente(curso, IRA, dataIngresso, dataNascimento);

        this.matriculaID = matriculaID;
        this.curso = curso;
        this.IRA = IRA;
        this.dataIngresso = LocalDate.parse(dataIngresso);
    }

    // -- Sobrescritas e sobrecargas --
    // Sobrecarga de alterarDados() de Pessoa
    public void alterarDados(String nome, String dataNascimento, String email, String cpf,
        String matriculaID, String curso, Double ira, String dataIngresso)
        throws PessoaInvalidaException, DiscenteInvalidoException {
        super.alterarDados(nome, dataNascimento, email, cpf);

        if (matriculaID != null && !matriculaID.isBlank()) {
            setMatriculaID(matriculaID);
        }

        if (curso != null && !curso.isBlank()) {
            setCurso(curso);
        }

        if (ira != null) {
            setIRA(ira);
        }

        if (dataIngresso != null && !dataIngresso.isBlank()) {
            setDataIngresso(dataIngresso);
        }
    }

    // -- Métodos auxiliares e chamadores das exceções --
    private void checarMatricula(String matricula) 
        throws DiscenteInvalidoException {
        if (matricula.length() != 10) {
            throw new DiscenteInvalidoException(
                "ERRO: Matrícula inserida com tamanho inválido, o tamanho deve ser 10 dígitos!\n"
            );
        }

        for (int i = 0; i < matricula.length(); i++) {
            if (!Character.isDigit(matricula.charAt(i))) {
                throw new DiscenteInvalidoException(
                    "ERRO: Matrícula inserida deve ser composta somente de dígitos!\n"
                );
            }
        }
    }

    private void checarDadosDiscente(String curso, double IRA, String dataIngresso, String DataNascimento)
        throws DiscenteInvalidoException {
        if (curso == null || curso.isBlank()) {
            throw new DiscenteInvalidoException("ERRO: Curso não pode estar vazio!\n");
        }

        if (!(IRA >= 0.0 && IRA <= 10.0)) {
            throw new DiscenteInvalidoException("ERRO: IRA deve estar entre 0 e 10!");
        }
    }

    // -- Métodos (Em progresso)--
    public Processo solicitarMatriculaDisciplina(Turma turma) {}
    public Processo trancarDisciplina(Turma turma) {}
    public Processo solicitarMudançaDisciplina(Turma turma) {}
    public Processo solicitarAdiantamentoDisciplinas(Turma turma) {}
    public double calcularIRA(List<Matricula> matriculas) {}
    public List<Disciplina> consultarDisciplinas() {}

    // -- Getters e Setters --
    public String getMatriculaID() {
        return matriculaID;
    }

    public String getCurso() {
        return curso;
    }

    public double getIRA() {
        return IRA;
    }

    public LocalDate getDataIngresso() {
        return dataIngresso;
    }

    public void setMatriculaID(String matriculaID)
        throws DiscenteInvalidoException {
        checarMatricula(matriculaID);
        this.matriculaID = matriculaID;
    }

    public void setCurso(String curso)
        throws DiscenteInvalidoException {
        if (curso == null || curso.isBlank()) {
            throw new DiscenteInvalidoException("ERRO: Curso não pode estar vazio!\n");
        }
        this.curso = curso;
    }

    public void setIRA(double IRA)
        throws DiscenteInvalidoException {
        if (IRA < 0.0 || IRA > 10.0) {
            throw new DiscenteInvalidoException("ERRO: IRA deve estar entre 0 e 10!");
        }
        this.IRA = IRA;
    }

    public void setDataIngresso(String dataIngresso)
        throws PessoaInvalidaException, DiscenteInvalidoException {
        if (dataIngresso == null || dataIngresso.isBlank()) {
            throw new DiscenteInvalidoException("ERRO: Data de ingresso não pode ser vazia!\n");
        }

        if (DATA_MINIMA_INGRESSO.isAfter(LocalDate.parse(dataIngresso))
            || LocalDate.parse(dataIngresso).isAfter(LocalDate.now())) {
            throw new DiscenteInvalidoException(
                "ERRO: A data de ingresso deve estar entre 01/01/1988 e hoje!"
            );
        }

        this.dataIngresso = LocalDate.parse(dataIngresso);
    }

    // -- Listas de associações/composições--
    public void adicionarTurma(Turma turma)
        throws DiscenteInvalidoException {
        if (turma == null) {
            throw new DiscenteInvalidoException("ERRO: A turma não pode ser nula!\n");
        }
        if (!turmas.contains(turma)) {
            turmas.add(turma);
        }
    }

    public boolean removerTurma(Turma turma) {
        return turmas.remove(turma);
    }

    public void adicionarMatricula(Matricula matricula)
        throws DiscenteInvalidoException {
        if (matricula == null) {
            throw new DiscenteInvalidoException("ERRO: A matrícula não pode ser nula!\n");
        }
        if (!matriculas.contains(matricula)) {
            matriculas.add(matricula);
        }
    }

    public boolean removerMatricula(Matricula matricula) {
        return matriculas.remove(matricula);
    }

    public List<Turma> getTurmas() {
        return Collections.unmodifiableList(turmas);
    }

    public List<Matricula> getMatriculas() {
        return Collections.unmodifiableList(matriculas);
    }
}
