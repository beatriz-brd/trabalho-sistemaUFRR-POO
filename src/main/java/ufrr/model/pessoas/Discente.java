package ufrr.model.pessoas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import exception.DiscenteInvalidoException;
import exception.MatriculaInvalidaException;
import model.academico.Disciplina;
import model.academico.Matricula;
import model.academico.Turma;

public class Discente {
    // Atributos
    private String matriculaID;
    private String curso;
    private double IRA = 0.0;
    private LocalDate dataIngresso;
    private LocalDate dataNascimento;

    // Associações/composições com outras classes
    private List<Turma> turmas = new ArrayList<>();
    private List<Matricula> matriculas = new ArrayList<>();
    
    // Constantes de auxílio
    public static final LocalDate DATA_MINIMA_INGRESSO = LocalDate.of(1988, 1, 1);
    public static final LocalDate DATA_MINIMA_NASCIMENTO = LocalDate.of(1900, 1, 1);

    // -- Construtor --
    public Discente(String matriculaID, String curso, double IRA, String DataIngresso, String DataNascimento) {
        checarMatricula(matriculaID);
        checarDadosDiscente(curso, IRA, DataIngresso, DataNascimento);

        this.matriculaID = matriculaID;
        this.curso = curso;
        this.IRA = IRA;
        this.dataIngresso = LocalDate.parse(DataIngresso);
        this.dataNascimento = LocalDate.parse(DataNascimento);
    }

    // Métodos auxiliares e chamadores das exceções
    private void checarMatricula(String matricula) 
        throws MatriculaInvalidaException {
        if (matricula.length() != 10) {
            throw new MatriculaInvalidaException(
                "ERRO: Matrícula inserida com tamanho inválido, o tamanho deve ser 10 dígitos!\n"
            );
        }

        for (int i = 0; i < matricula.length(); i++) {
            if (!Character.isDigit(matricula.charAt(i))) {
                throw new MatriculaInvalidaException(
                    "ERRO: Matrícula inserida deve ser composta somente de dígitos!\n"
                );
            }
        }
    }

    private void checarDadosDiscente(String curso, double IRA, String DataIngresso, String DataNascimento)
        throws DiscenteInvalidoException {
        if (curso == null || curso.equals("")) {
            throw new DiscenteInvalidoException("ERRO: Curso não pode estar vazio!\n");
        }

        if (!(IRA >= 0.0 && IRA <= 10.0)) {
            throw new DiscenteInvalidoException("ERRO: IRA deve estar entre 0 e 10!");
        }

        if (DATA_MINIMA_INGRESSO.isAfter(LocalDate.parse(DataIngresso))) {
            throw new DiscenteInvalidoException("ERRO: A data de ingresso deve ser posterior a 01/01/1988!");
        }

        if (DATA_MINIMA_NASCIMENTO.isAfter(LocalDate.parse(DataNascimento))) {
            throw new DiscenteInvalidoException("ERRO: A data de nascimento deve ser posterior a 01/01/1900!");
        }
    }

    // -- Métodos --
    public Processo solicitarMatriculaDisciplina(Turma turma) {}
    public Processo trancarDisciplina(Turma turma) {}
    public Processo solicitarMudançaDisciplina(Turma turma) {}
    public Processo solicitarAdiantamentoDisciplinas(Turma turma) {}
    public double calcularIRA(List<Matricula> matriculas) {}
    public List<Disciplina> consultarDisciplinas() {}

}
