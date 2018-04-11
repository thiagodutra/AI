package br.edu.uepb.eightpuzzle.search;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import br.edu.uepb.eightpuzzle.vertex.Vertex;

public class DFS extends Search {
	private Stack<Vertex> stateStack;
	private Set<Vertex> visitedStates;

	public DFS() {
		stateStack = new Stack<>();
		visitedStates = new HashSet<>();
	}

	public Vertex buscaEmProfundidade(Vertex edge) {
		if (Vertex.isSolucao(edge)) {
			return edge;
		}

		stateStack.add(edge);

		Vertex estadoDaVez = null;
		int totalExpansoes = 0;
		int totalNos = 0;
		
		while (totalExpansoes < 600000) {
			edge = stateStack.pop();

			if (Vertex.isSolucao(edge)) {
				setTotalExpansions(totalExpansoes);
				setTotalNodes(totalNos);
				setTotalMemory(Runtime.getRuntime().totalMemory());

				return edge;
			}
			
			totalExpansoes++;
			visitedStates.add(edge);

			estadoDaVez = Vertex.moveLeft(edge);
			if (estadoDaVez != null && !visitedStates.contains(estadoDaVez)) {
				stateStack.push(estadoDaVez);
				totalNos++;
			}

			estadoDaVez = Vertex.moveRight(edge);
			if (estadoDaVez != null && !visitedStates.contains(estadoDaVez)) {
				stateStack.push(estadoDaVez);
				totalNos++;
			}

			estadoDaVez = Vertex.moveUp(edge);
			if (estadoDaVez != null && !visitedStates.contains(estadoDaVez)) {
				stateStack.push(estadoDaVez);
				totalNos++;
			}

			estadoDaVez = Vertex.moveDown(edge);
			if (estadoDaVez != null && !visitedStates.contains(estadoDaVez)) {
				stateStack.push(estadoDaVez);
				totalNos++;
			}
		}

		return null;
	}

	public boolean solucao(Vertex inicialState) {
		long timeInit = System.currentTimeMillis();
		setSolutionState(buscaEmProfundidade(inicialState));
		setTotalTime(System.currentTimeMillis() - timeInit);

		if (getSolutionState() != null) {
			setListDePassos(getPassosSolucao());

			return true;
		}

		return false;
	}

	public List<Vertex> getPassosSolucao() {
		Vertex atual = getSolutionState();
		List<Vertex> result = new ArrayList<>();

		result.add(atual);
		while (atual.getPredecessor() != null) {
			atual = atual.getPredecessor();
			if (atual.getPredecessor() != null)
				result.add(atual);
		}
		setTotalSteps(result.size());

		Collections.reverse(result);

		return result;
	}
}

