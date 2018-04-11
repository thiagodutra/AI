package br.edu.uepb.eightpuzzle.search;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import br.edu.uepb.eightpuzzle.vertex.Vertex;


public class BFS extends Search {
	private Queue<Vertex> stateQueue;
	private Set<Vertex> visitedStates;

	public BFS() {
		stateQueue = new LinkedList<>();
		visitedStates = new HashSet<>();
	}

	public Vertex buscaEmLargura(Vertex edge){
		if (Vertex.isSolucao(edge)) {
			return edge;
		}

		stateQueue.add(edge);

		Vertex actualState = null;
		int totalExpansions = 0;
		int totalNodes = 0;
		
		while (!stateQueue.isEmpty()) {
			edge = stateQueue.poll();

			if (Vertex.isSolucao(edge)) {
				setTotalExpansions(totalExpansions);
				setTotalNodes(totalNodes);
				setTotalMemory(Runtime.getRuntime().totalMemory());

				return edge;
			}
			visitedStates.add(edge);
			totalExpansions++;

			actualState = Vertex.moveLeft(edge);
			if (actualState != null && !visitedStates.contains(actualState)) {
				stateQueue.add(actualState);
				totalNodes++;
			}

			actualState = Vertex.moveRight(edge);
			if (actualState != null && !visitedStates.contains(actualState)) {
				stateQueue.add(actualState);
				totalNodes++;
			}

			actualState = Vertex.moveUp(edge);
			if (actualState != null && !visitedStates.contains(actualState)) {
				stateQueue.add(actualState);
				totalNodes++;
			}

			actualState = Vertex.moveDown(edge);
			if (actualState != null && !visitedStates.contains(actualState)) {
				stateQueue.add(actualState);
				totalNodes++;
			}

		}

		return null;
	}

	public boolean solucao(Vertex inicialState) {
		long timeInit = System.currentTimeMillis();
		setSolutionState(buscaEmLargura(inicialState));
		setTotalTime(System.currentTimeMillis() - timeInit);
		if (getSolutionState() != null) {
			setStepsList(getSolutionState());

			return true;
		}

		return false;
	}

}
