package br.edu.uepb.eightpuzzle.search;



import java.util.LinkedList;
import java.util.List;

import br.edu.uepb.eightpuzzle.vertex.Vertex;



public class Controller {
	private BFS bfs;
	private DFS dfs;
	private IDDF dfsIterativo;
	private List<Vertex> solutionSteps;
	private Object instancia;

	/**
	 * Construtor que cria a instância de acordo com o tipo do objeto passado
	 * como parâmetro
	 * 
	 * @param obj
	 *            Objeto que representa o tipo da instancia
	 */
	public Controller(Object obj) {
		this.instancia = obj;

		if (obj instanceof BFS) {
			bfs = (BFS) obj;
		} else if (obj instanceof DFS) {
			dfs = (DFS) obj;
		} else if (obj instanceof IDDF) {
			dfsIterativo = (IDDF) obj;
		}
	}

	/**
	 * Realiza a busca da solução utilisando BFS - Busca em largura
	 * 
	 * @param estadoInicial
	 *            Estado que representa a cabeça da ávore
	 */
	public boolean solucao(Vertex estadoInicial) {
		boolean result = false;
		if (instancia instanceof BFS) {
			result = bfs.solucao(estadoInicial);
		} else if (instancia instanceof DFS) {
			result = dfs.solucao(estadoInicial);
		} else if (instancia instanceof IDDF) {
			dfsIterativo.setInicialState(estadoInicial);
			result = dfsIterativo.solution(estadoInicial);
		}

		return result;
	}

	/**
	 * Recupera o total de memória consumido pela JVM mna execução do programa
	 * 
	 * @return O total de memória em megabytes
	 */
	public int totalDeMemoria() {
		if (instancia instanceof BFS) {
			return (int) bfs.getTotalMemory() / 1000000;
		} else if (instancia instanceof DFS) {
			return (int) dfs.getTotalMemory() / 1000000;
		} else if (instancia instanceof IDDF) {
			return (int) dfsIterativo.getTotalMemory() / 1000000;
		}

		return 0;
	}

	/**
	 * Recupera o total de nós expandidos
	 * 
	 * @return O total de expansões
	 */
	public long totalDeExpansoes() {
		if (instancia instanceof BFS) {
			return bfs.getTotalExpansions();
		} else if (instancia instanceof DFS) {
			return dfs.getTotalExpansions();
		} else if (instancia instanceof IDDF) {
			return dfsIterativo.getTotalExpansions();
		}

		return 0;
	}

	/**
	 * Recupera o total de nós que foram criados
	 * 
	 * @return O total de nós
	 */
	public long totalDeNos() {
		if (instancia instanceof BFS) {
			return bfs.getTotalNodes();
		} else if (instancia instanceof DFS) {
			return dfs.getTotalNodes();
		} else if (instancia instanceof IDDF) {
			return dfsIterativo.getTotalNodes();
		}

		return 0;
	}

	/**
	 * Recupera o tempo total da execução em segundos
	 * 
	 * @return O tempo em segundos
	 */
	public double tempoTotal() {
		if (instancia instanceof BFS) {
			return (bfs.getTotalTime() * 0.001);
		} else if (instancia instanceof DFS) {
			return (dfs.getTotalTime() * 0.001);
		} else if (instancia instanceof IDDF) {
			return (dfsIterativo.getTotalTime() * 0.001);
		}

		return 0;
	}

	/**
	 * Recupera o total de passos até a solução. No caso da busca em largura
	 * será o melhor caminho No caso da busca em profundidade nem sempre resulta
	 * o melhor caminho
	 * 
	 * @return O total de passos
	 */
	public int totalDePassos() {
		if (instancia instanceof BFS) {
			return bfs.getTotalSteps();
		} else if (instancia instanceof DFS) {
			return dfs.getTotalSteps();
		} else if (instancia instanceof IDDF) {
			return dfsIterativo.getTotalSteps();
		}

		return 0;
	}

	/**
	 * Exibe o passo a passo da solução do problema
	 */
	public void exibeSolucao() {
		solutionSteps = new LinkedList<>();

		if (instancia instanceof BFS) {
			solutionSteps = bfs.getStepsList();
		} else if (instancia instanceof DFS) {
			solutionSteps = dfs.getStepsList();
		} else if (instancia instanceof IDDF) {
			solutionSteps = dfsIterativo.getStepsList();
		}

		if (solutionSteps != null) {
			for (Vertex state : solutionSteps) {
				if (state != null && state.getPredecessor() != null)
					System.out.println(state.getAction());
			}
		}
	}

	/**
	 * Imprime no console os estados. Util para testes
	 * 
	 * @param listDeEstados
	 *            A lista com os estados
	 * @param descricao
	 *            true para exibir a descrição do movimento
	 */
	public void printEstados(List<Vertex> listDeEstados, boolean descricao) {
		int count;
		int auxi = 0;

		for (Vertex estado : listDeEstados) {
			auxi++;
			count = 0;
			if (descricao)
				System.out.println(auxi + "° - " + estado.getAction());

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					System.out.print(estado.getPuzzle()[count] + " ");
					count++;
				}
				System.out.println();
			}
			System.out.println();
		}
	}

	/**
	 * Imprime no console os estados. Util para testes A diferença desse método
	 * para o printEstado é que não é necessário passar a lista como parâmetro.
	 * Se existir passos da solução será exibido.
	 * 
	 * @param descricao
	 *            true para exibir a descrição do movimento
	 */
	public void printEstadosSolucao(boolean descricao) {
		if (solutionSteps != null) {
			if (solutionSteps.isEmpty())
				exibeSolucao();
		}

		if (solutionSteps != null)
			printEstados(solutionSteps, descricao);
	}

}

