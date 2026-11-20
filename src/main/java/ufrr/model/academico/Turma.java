package ufrr.model.academico;

import java.time.LocalDate;
import java.util.List;

import exception.DiscenteInvalidoException;
import exception.MatriculaInvalidaException;
import exception.TurmaInvalidaException;
import model.pessoas.Discente;
import model.pessoas.Docente;

import java.util.ArrayList;

public class Turma {
    // Atributos
    String codigo;
    String nome;
    int capacidadeAtual = 0;
    int capacidadeMaxima;
    LocalDate dataInicio;
    LocalDate dataFinal;
    String status;

    // Associações/composições com outras classes
    List<Discente> discentes = new ArrayList<>();
    Docente docente;
    Disciplina disciplina;
    Departamento departamento;

    // -- Construtor --
    public Turma(String codigo, String nome, int capacidadeMaxima, String dataInicio, String dataFinal) {
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
    private void checarCodigo(String codigo) 
        throws CodigoTurmaInvalidoException {
        if (codigo.length() != 7) {
            throw new CodigoTurmaInvalidaException(
                "ERRO: Código inserido com tamanho inválido, o tamanho deve ser 7 dígitos!\n"
            );
        }

        if (!(codigo.substring(0, 1).equals("T"))) {
            throw new CodigoTurmaInvalidaException(
                "ERRO: O código deve iniciar com a letra 'T'!\n"
            );
        }

        for (int i = 1; i < codigo.length(); i++) {
            if (!Character.isDigit(codigo.charAt(i))) {
                throw new CodigoTurmaInvalidaException(
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
            this.status = "Cadastrada";
        } else if (this.dataFinal.isBefore(LocalDate.now())) {
            this.status = "Arquivada";
        } else {
            this.status = "Ativa";
        }
    }

    // -- Métodos --
    public void arquivarTurma() {
        this.status = "Arquivada";
    }

    public alterarDados() {}
}
