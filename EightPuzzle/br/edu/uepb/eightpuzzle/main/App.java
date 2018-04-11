package br.edu.uepb.eightpuzzle.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.edu.uepb.eightpuzzle.search.BFS;
import br.edu.uepb.eightpuzzle.search.Controller;
import br.edu.uepb.eightpuzzle.search.DFS;
import br.edu.uepb.eightpuzzle.search.IDDF;
import br.edu.uepb.eightpuzzle.search.Search;
import br.edu.uepb.eightpuzzle.vertex.Vertex;

public class App {
	private static Random random = new Random();

	public App() {
		// TODO Auto-generated constructor stub
	}

//	private static Vertex criaTabuleiro() {
//		int tamanho = 9;
//		int sorteadoDaVez = 0;
//		List<Integer> sorteados = new ArrayList<>();
//		byte[] board = new byte[tamanho];
//
//		int campoVazio = random.nextInt(tamanho);
//
//		for (int i = 0; i < tamanho; i++) {
//			if (i == campoVazio) {
//				board[i] = 0;
//			} else {
//				do {
//					sorteadoDaVez = random.nextInt(tamanho - 1) + 1;
//				} while (sorteados.contains(sorteadoDaVez));
//				board[i] = (byte) sorteadoDaVez;
//				sorteados.add(sorteadoDaVez);
//			}
//		}
//
//		return new Vertex(board, campoVazio);
//	}

	public static void main(String[] args) {
		byte[] conteudo1 = new byte[] { 1, 0, 3, 4, 2, 5, 7, 8, 6 }; // 03 mov
		byte[] conteudo2 = new byte[] { 1, 2, 0, 4, 5, 3, 6, 7, 8 }; // 14 mov
		byte[] conteudo3 = new byte[] { 2, 7, 5, 4, 0, 3, 1, 6, 8 }; // 22 mov
		byte[] conteudo4 = new byte[] { 8, 7, 6, 5, 4, 3, 2, 1, 0 }; // 30 mov
		byte[] conteudo5 = new byte[] { 4, 1, 2, 7, 6, 3, 5, 8, 0 }; // 10 mov
		byte[] conteudo6 = new byte[] { 7, 5, 2, 0, 3, 8, 1, 6, 4 }; // 23 mov
		byte[] conteudo7 = new byte[] { 8, 1, 2, 0, 4, 3, 7, 6, 5 }; // No
		byte[] conteudo8 = new byte[] { 4, 2, 5, 1, 8, 3, 0, 7, 6 }; // 18 move
		byte[] conteudo9 = new byte[] { 0, 7, 4, 2, 6, 1, 5, 3, 8 }; // 22 move
		byte[] conteudo10 = new byte[] { 2, 4, 8, 0, 5, 6, 3, 7, 1 }; // 22 move

//		Vertex estadoInicial = criaTabuleiro();
		 Vertex estadoInicial = new Vertex(conteudo1, 1);
		// Vertex estadoInicial = new Vertex(conteudo2, 2);
		// Vertex estadoInicial = new Vertex(conteudo3, 4);
		// Vertex estadoInicial = new Vertex(conteudo4, 8);
		// Vertex estadoInicial = new Vertex(conteudo5, 8);
//		 Vertex estadoInicial = new Vertex(conteudo6, 3);
		// Vertex estadoInicial = new Vertex(conteudo7, 3);
		// Vertex estadoInicial = new Vertex(conteudo8, 6);
		// Vertex estadoInicial = new Vertex(conteudo9, 0);
		// Vertex estadoInicial = new Vertex(conteudo10, 3);
		System.out.println(estadoInicial);

		Search bfs = new BFS();
		Search dfs = new DFS();
		Search iddf = new IDDF(); // Iterativo

		Controller manager = new Controller(iddf);

		if (manager.solucao(estadoInicial)) {
			manager.exibeSolucao();
			System.out.println("TEMP " + manager.tempoTotal() + "s");
			System.out.println("TOTAL DE PASSOS: " + manager.totalDePassos());
			System.out.println("TOTAL EXPANSOES: " + manager.totalDeExpansoes());
			System.out.println("TOTAL DE NOS: " + manager.totalDeNos());
			System.out.println("TOTAL MEMORIA: " + manager.totalDeMemoria());
		} else {
			System.out.println(manager.getClass().getClassLoader() + " Não encontrou a solução");
		}
	}
}
