package com.projedata.folha.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.projedata.folha.model.Funcionario;
import com.projedata.folha.model.Pessoa;
import com.projedata.folha.util.ExceptionGeradas;
import com.projedata.folha.util.Util;


public class FuncionarioService {

	List<Funcionario> funcionarios;

	public FuncionarioService() {
		funcionarios = new ArrayList<>();
	}

	public void imprimirQuantidadeSalarioPorFuncionario(BigDecimal salarioBase) {
		System.out.println("\nQuantidade de salários mínimos ganhos por cada funcionário:");

		for (Funcionario f : funcionarios) {
			BigDecimal salarioMinimo = salarioBase;
			BigDecimal salarioDividido = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
			String resultado = salarioDividido.compareTo(BigDecimal.ONE) < 0 ? " menos de um"
					: salarioDividido.toString();
			System.out.println(f.getNome() + " - " + resultado.toString().replace('.', ',') + " salário(s) mínimo (s)");
		}

		// funcionarios.forEach(funcionario -> System.out.println(funcionario.getNome()
		// + ": "
		// + funcionario.getSalarioMinimo().setScale(2,
		// RoundingMode.HALF_UP).toString().replace(".", ",")));
	}

	public void imprimirSomaTotalSalario() {
		System.out.println("\nTotal dos salários dos funcionários: " + Util.formatarDecimal(
				funcionarios.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add)));
	}

	public void imprimirOrdenarOrdemAlfabetica() {
		List<String> nomesFuncionarios = new ArrayList<>();
		for (Funcionario funcionario : funcionarios) {
			nomesFuncionarios.add(funcionario.getNome());
		}
		Collections.sort(nomesFuncionarios);

		System.out.println("\nLista de Funcionários em ordem alfabética:");
		for (String nome : nomesFuncionarios) {
			System.out.println(nome);
		}
	}

	public void obterMaiorIdade() {
		System.out.println("\nFuncionário com a maior idade:");
		Funcionario funcionarioMaisVelho = funcionarios.stream().max(Comparator.comparingInt(Pessoa::getIdade))
				.orElse(null);
		if (funcionarioMaisVelho != null) {
			System.out
					.println("Nome: " + funcionarioMaisVelho.getNome() + ", Idade: " + funcionarioMaisVelho.getIdade());
		}
	}

	public void imprimirAgrupadoPorFuncao() {
		System.out.println("\nFuncionários agrupados por função:");
		for (Map.Entry<String, List<Funcionario>> entry : AgruparPorFuncao().entrySet()) {
			String funcao = entry.getKey();
			List<Funcionario> listaFuncionarios = entry.getValue();
			System.out.println("Função: " + funcao);

			// listaFuncionarios.forEach(System.out::println);
			for (Funcionario funcionario : listaFuncionarios) {
				System.out.println("Nome: " + funcionario.getNome() + " | Data de Nascimento: "
						+ funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
						+ " | Salário: R$ " + Util.formatarDecimal(funcionario.getSalario()) + " | Função: "
						+ funcionario.getFuncao());
			}

			System.out.println();
		}
	}

	public Map<String, List<Funcionario>> AgruparPorFuncao() {
		Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();

		for (Funcionario funcionario : funcionarios) {
			String funcao = funcionario.getFuncao();

			if (!funcionariosPorFuncao.containsKey(funcao)) {
				funcionariosPorFuncao.put(funcao, new ArrayList<>());
			}

			funcionariosPorFuncao.get(funcao).add(funcionario);
		}

		return funcionariosPorFuncao;
	}

	public void aumentarSalarios(double percentualAumento) {
		for (Funcionario funcionario : funcionarios) {
			BigDecimal salarioAtual = funcionario.getSalario();
			BigDecimal aumento = salarioAtual.multiply(BigDecimal.valueOf(percentualAumento / 100.0));
			funcionario.setSalario(salarioAtual.add(aumento));
		}

		System.out.println("\nNovos salários");
		listarTodos();

	}

	public void listarTodos() {
		System.out.println("\nLista de Funcionários:");
		for (Funcionario funcionario : funcionarios) {
			System.out.println("Nome: " + funcionario.getNome() + " | Data de Nascimento: "
					+ funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
					+ " | Salário: R$ " + Util.formatarDecimal(funcionario.getSalario()) + " | Função: "
					+ funcionario.getFuncao());
		}
	}

	public void remover(String nome) {
		// implentado apaenas aqui nesse metodo, obviamente todos o eventos precisam ser
		// tratados e devidamentes testados com teste automatizadosÏ
		try {

			if (nome.equals(null)) {
				throw new ExceptionGeradas("Nome não foi informado.");
			}

			funcionarios.removeIf(funcionario -> funcionario.getNome().equals(nome));
			System.out.println("\nFuncionario removido:" + nome);
		} catch (ExceptionGeradas e) {
			System.out.println("Erro: " + e.getMessage());
		}

	}

	public void inserir() {

		funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), BigDecimal.valueOf(2009.44), "Operador"));

		funcionarios.add(new Funcionario("João", LocalDate.of(1990, 05, 12), BigDecimal.valueOf(2284.38), "Operador"));

		funcionarios
				.add(new Funcionario("Caio", LocalDate.of(1961, 05, 02), BigDecimal.valueOf(9836.14), "Coordenador"));

		funcionarios
				.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), BigDecimal.valueOf(19119.88), "Diretor"));

		funcionarios.add(
				new Funcionario("Alice", LocalDate.of(1995, 01, 05), BigDecimal.valueOf(2234.68), "Recepcionista"));

		funcionarios
				.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), BigDecimal.valueOf(1582.72), "Operador"));

		funcionarios
				.add(new Funcionario("Arthur", LocalDate.of(1993, 03, 31), BigDecimal.valueOf(1582.72), "Operador"));

		funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 07, 8), BigDecimal.valueOf(3017.45), "Gerente"));

		funcionarios.add(
				new Funcionario("Heloisa", LocalDate.of(2003, 05, 24), BigDecimal.valueOf(1606.85), "Eletricista"));

		funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 02), BigDecimal.valueOf(2799.93), "Gerente"));
		System.out.println("\nQuantidade de funcionarios inseridos: " + funcionarios.size());
	}

	// 3.8 – Imprimir os funcionários que fazem aniversário em uma lista de meses
	// especificados
	public void imprimirAniversariantes(List<Integer> meses) {
		System.out.println("\nAniversariantes dos meses " + meses + ":");
		for (Funcionario funcionario : funcionarios) {
			int mesAniversario = funcionario.getDataNascimento().getMonthValue();
			if (meses.contains(mesAniversario)) {
				System.out.println(funcionario.getNome());
			}
		}
	}
	
	
	

}
