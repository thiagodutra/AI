package br.edu.uepb.eightpuzzle.search;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import br.edu.uepb.eightpuzzle.vertex.Vertex;


public class IDDF extends Search {
	private final int LIMITE_PROFUNDIDADE = 100;

	private Stack<Vertex> stackStates;
	private Set<Vertex> visitedStates;
	private Vertex inicialState;

	public IDDF() {
		stackStates = new Stack<>();
		visitedStates = new HashSet<Vertex>();
	}

	public void setInicialState(Vertex inicialState) {
		this.inicialState = inicialState;
	}

//	public Vertex interactive() {
//		Vertex finaState = null;
//		stackStates.clear();
//		visitedStates.clear();
//
//		for (int i = 1; i <= LIMITE_PROFUNDIDADE; i++) {
//			estadoFinal = buscaEmProfundidadeIterativa(inicialState, i);
//			if (finalState != null)
//				return finalState;
//		}
//
//		return null;
//	}

	public Vertex interactiveDeepeningSearch(Vertex edge, int limite) {
		if (Vertex.isSolucao(edge)) {
			return edge;
		}
		stackStates.clear();
		visitedStates.clear();
		
		stackStates.add(edge);

		Vertex actualState = null;
		int totalExpansoes = 0;
		int totalNos = 0;

		while (!stackStates.isEmpty()) {
			edge = stackStates.pop();
			
			if (Vertex.isSolucao(edge)) {
				setTotalExpansions(totalExpansoes);
				setTotalNodes(totalNos);
				setTotalMemory(Runtime.getRuntime().totalMemory());

				return edge; // Achou a solução e sai do loop
			} else if (Vertex.getNivelNo(edge) < limite) {
				visitedStates.add(edge);
				
				totalExpansoes++;
				
				// GERANDO AS POSSIBILIDADES/ESTADOS
				actualState = Vertex.moveLeft(edge);
//				if (visitedStates.add(edge)) {
//					stackStates.push(actualState);
//					
//				}
				if (actualState != null && !stackStates.contains(actualState) && !visitedStates.contains(actualState)) {
					stackStates.push(actualState);
					
					totalNos++;
				}
				
				actualState = Vertex.moveRight(edge);
				if (actualState != null && !stackStates.contains(actualState) && !visitedStates.contains(actualState)) {
					stackStates.push(actualState);
					totalNos++;
				}
				
				actualState = Vertex.moveUp(edge);
				if (actualState != null && !stackStates.contains(actualState) && !visitedStates.contains(actualState)) {
					stackStates.push(actualState);
					totalNos++;
				}
				
				actualState = Vertex.moveDown(edge);
				if (actualState != null && !stackStates.contains(actualState) && !visitedStates.contains(actualState)) {
					stackStates.push(actualState);
					totalNos++;
				}
			}
		}
		return null;
	}

	public boolean solution(Vertex inicialState) {
		long timeInit = System.currentTimeMillis();

		Vertex estado = null;

		for (int i = 1; i <= LIMITE_PROFUNDIDADE; i++) {
			estado = interactiveDeepeningSearch(inicialState, i);
			if (estado != null) {
				setSolutionState(estado);
				setStepsList(getSolutionState());
				setTotalTime(System.currentTimeMillis() - timeInit);
				
				return true;
			}
		}

		return false;
	}
}