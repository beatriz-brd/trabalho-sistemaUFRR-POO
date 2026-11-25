package ufrr.model.pessoas;

import exception.PessoaInvalidaException;
import java.time.LocalDate;

public abstract class Pessoa {
    // Atributos
    protected String nome;
    protected LocalDate dataNascimento;
    protected String email;
    protected String cpf;

    // Constantes
    public static final LocalDate DATA_MINIMA_NASCIMENTO = LocalDate.of(1900, 1, 1);

    // -- Métodos para @override --
    protected void alterarDados(String nome, String dataNascimento, String email, String cpf)
        throws PessoaInvalidaException{
        // Se algum parâmetro estiver vazio, não altera
        if (nome != null && !nome.isBlank()) {
            setNome(nome);
        }

        if (dataNascimento != null && !dataNascimento.isBlank()) {
            setDataNascimento(dataNascimento);
        }

        if (email != null && !email.isBlank()) {
            setEmail(email);
        }

        if (cpf != null && !cpf.isBlank()) {
            setCpf(cpf);
        }
    }

    // -- Construtor --
    Pessoa(String nome, String dataNascimento, String email, String cpf) 
        throws PessoaInvalidaException {
        // Verifica nome nulo ou vazio
        if (nome == null || nome.isBlank()) {
            throw new PessoaInvalidaException(
                "ERRO: O nome da pessoa não pode ser vazio!\n"
            );
        }

        // Verifica a data de nascimento,email e cpf
        validarDataDeNascimento(dataNascimento);
        validarEmail(email);
        validarCpf(cpf);

        this.nome = nome;
        this.dataNascimento = LocalDate.parse(dataNascimento);
        this.email = email;
        this.cpf = cpf;
    }

    // -- Getters e setters --
    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setNome(String nome) throws PessoaInvalidaException {
        if (nome == null || nome.isBlank()) {
            throw new PessoaInvalidaException(
                "ERRO: O nome da pessoa não pode ser vazio!\n"
            );
        }
        
        this.nome = nome;
    }

    public void setDataNascimento(String dataNascimento) throws PessoaInvalidaException {
        validarDataDeNascimento(dataNascimento);
        this.dataNascimento = LocalDate.parse(dataNascimento);
    }

    public void setEmail(String email) throws PessoaInvalidaException {
        validarEmail(email);
        this.email = email;
    }

    public void setCpf(String cpf) throws PessoaInvalidaException {
        validarCpf(cpf);
        this.cpf = cpf;
    }

    // -- Métodos auxiliares e chamadores das exceções --
    private void validarDataDeNascimento(String DataNascimento)
        throws PessoaInvalidaException {
        if (DATA_MINIMA_NASCIMENTO.isAfter(LocalDate.parse(DataNascimento))
            || LocalDate.parse(DataNascimento).isAfter(LocalDate.now())) {
            throw new PessoaInvalidaException(
                "ERRO: A data de nascimento deve estar entre 01/01/1900 e hoje!"
            );
        }
    }

    private void validarEmail(String email)
        throws PessoaInvalidaException {
        if (email == null) {
            throw new PessoaInvalidaException("ERRO: O email não pode ser nulo!\n");
        }

        if (!(email.length() > 6 && email.length() < 254)) {
            throw new PessoaInvalidaException(
                "ERRO: O email deve ter extensão de 6 a 254 caracteres!\n"
            );
        }

        if (!(email.contains("@"))) {
            throw new PessoaInvalidaException(
                "ERRO: O email deve ter o caractere '@' !\n"
            );
        }

        if (!(email.contains(".com") || email.contains(".net")
        || email.contains(".br") || email.contains(".gov"))) {
            throw new PessoaInvalidaException(
                "ERRO: O email deve possuir um domínio válido (como .com; .net; .br e .gov)\n"
            );
        }
    }

    private void validarCpf(String cpf)
        throws PessoaInvalidaException {
        if (cpf == null) {
            throw new PessoaInvalidaException("ERRO: O CPF não pode ser nulo!\n");
        }

        if (cpf.length() != 11) {
            throw new PessoaInvalidaException(
                "ERRO: O CPF deve possuir 11 dígitos!\n"
            );
        }

        for (int i = 0; i < cpf.length(); i++) {
            if (!Character.isDigit(cpf.charAt(i))) {
                throw new PessoaInvalidaException(
                    "ERRO: O CPF deve possuir somente dígitos!\n"
                );
            }
        }
    }
}
