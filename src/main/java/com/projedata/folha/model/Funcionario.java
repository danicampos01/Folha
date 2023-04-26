package com.projedata.folha.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;


public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public BigDecimal getSalarioMinimo() {
        return salario.divide(BigDecimal.valueOf(1212), 2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "Nome: " + getNome() + ", Data de Nascimento: " + getDataNascimentoFormatada() +
                ", Salário: " + salario.toString().replace(".", ",") + ", Função: " + funcao;
    }
}
