package dados;

import java.time.LocalDate;

public class Motorista {
    private int idMotorista;
    private String nome;
    private String cnh;
    private String cpf;
    private LocalDate dtNascimento;

    public Motorista() {};

    public Motorista(String nome, String cnh, String cpf, LocalDate data_nascimento) {
        this.nome = nome;
        this.cnh = cnh;
        this.cpf = cpf;
        this.dtNascimento = data_nascimento;
    }

    public int getIdMotorista() {
        return idMotorista;
    }

    public void setIdMotorista(int idMotorista) {
        this.idMotorista = idMotorista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    @Override
    public String toString() {
        return "[ID " + this.idMotorista + "]" + "\nNome: " + this.nome + "\nCPF: " + this.cpf + "\nCNH: " +
                this.cnh + "\nDt Nascimento: " + this.dtNascimento;
    }
}
