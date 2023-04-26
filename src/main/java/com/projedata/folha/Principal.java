package com.projedata.folha;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.projedata.folha.service.FuncionarioService;

@SpringBootApplication
public class Principal {

	public static void main(String[] args) {
		SpringApplication.run(Principal.class, args);

		FuncionarioService funcionarioService = new FuncionarioService();

		// 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela.
		funcionarioService.inserir();

		// 3.2 – Remover o funcionário “João” da lista.
		funcionarioService.remover("João");
		
		// 3.3 – Imprimir todos os funcionários com todas suas informações
		funcionarioService.listarTodos();

		// 3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista
		// de funcionários com novo valor.
		funcionarioService.aumentarSalarios(10);

		// 3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função”
		// e o valor a “lista de funcionários”.
		funcionarioService.AgruparPorFuncao();

		// 3.6 – Imprimir os funcionários, agrupados por função.
		funcionarioService.imprimirAgrupadoPorFuncao();

		// 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
		List<Integer> mesesAniversario = new ArrayList<>(Arrays.asList(10, 12));
		funcionarioService.imprimirAniversariantes(mesesAniversario);

		// 3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e
		// idade.
		funcionarioService.obterMaiorIdade();

		// 3.10 – Imprimir a lista de funcionários por ordem alfabética.

		funcionarioService.imprimirOrdenarOrdemAlfabetica();

		// 3.11 – Imprimir o total dos salários dos funcionários.
		funcionarioService.imprimirSomaTotalSalario();

		// 3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando
		// que o salário mínimo é R$1212.00.
		funcionarioService.imprimirQuantidadeSalarioPorFuncionario( new BigDecimal(1212.00));
	}

}
