package br.gov.cesarschool.poo.fidelidade.geral.negocio;

import java.util.Date;
import java.time.LocalDate;
import java.time.Period;
import br.gov.cesarschool.poo.fidelidade.geral.negocio.Sexo;
import br.gov.cesarschool.poo.fidelidade.geral.negocio.Endereco;

public class Cliente {
    private final String cpf;
    private String nomeCompleto;
    private Sexo sexo;
    private Date dataNascimento;
    private double renda;
    private Endereco endereco;
    
    public Cliente(String cpf, String nomeCompleto, Sexo sexo, Date dataNascimento, double renda, Endereco endereco) {
        this.cpf = cpf;
        this.nomeCompleto = nomeCompleto;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.renda = renda;
        this.endereco = endereco;
    }
    
    // getters e setters dos atributos privados
    
    public String getCpf() {
        return cpf;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public double getRenda() {
        return renda;
    }

    public void setRenda(double renda) {
        this.renda = renda;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public int obterIdade() {
        LocalDate hoje = LocalDate.now();
        LocalDate dataNasc = new java.sql.Date(this.dataNascimento.getTime()).toLocalDate();
        Period periodo = Period.between(dataNasc, hoje);
        return periodo.getYears();
    }
}