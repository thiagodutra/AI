package br.edu.uepb.eightpuzzle.vertex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Vertex implements Cloneable{
	
	private byte[] puzzle;
	private int blank;
	private String action;
	private Vertex predecessor;
	
	public Vertex (byte[] puzzle, int blank ) {
		this(puzzle, blank, "",  null);
	}
	
	public Vertex(byte[] puzzle, int blank, String action, Vertex predecessor) {
		this.puzzle = puzzle;
		this.blank = blank;
		this.action = action;
		this.predecessor = predecessor;
	}
	
	/**
	 * Getters Setters
	 * 
	 */
	public byte[] getPuzzle() {
		return puzzle;
	}

	public void setPuzzle(byte[] puzzle) {
		this.puzzle = puzzle;
	}

	public int getBlank() {
		return blank;
	}

	public void setBlank(int blank) {
		this.blank = blank;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Vertex getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(Vertex predecessor) {
		this.predecessor = predecessor;
	}

	public static Vertex moveUp(Vertex state) {
		// Verifica se o movimento é possível
		if (state.blank >= (3 * 3 - 3)) {
			return null;
		}
		// Cria um novo objeto estado
		Vertex newState = null;
		try {
			newState = state.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int indexMove = newState.getBlank() + 3;

		// Seta a descrição do movimento
		newState.setAction("Mover o " + newState.getPuzzle()[indexMove] + " para CIMA");
		// Faz a troca dos elementos o array representando o movimento
		newState.swap(newState.getBlank(), indexMove);

		// Atualiza o objeto com o novo index do campo vazio e o pai do estado
		newState.setBlank(indexMove);
		newState.setPredecessor(state);

		return newState;
	}
	
	public static Vertex moveDown(Vertex state) {
		// Verifica se o movimento é possível
		if (state.blank < 3) {
			return null;
		}
		// Cria um novo objeto estado
		Vertex newState = null;
		try {
			newState = state.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int indexMove = newState.getBlank() - 3;

		// Seta a descrição do movimento
		newState.setAction("Mover o " + newState.getPuzzle()[indexMove] + " para BAIXO");
		// Faz a troca dos elementos o array representando o movimento
		newState.swap(newState.getBlank(), indexMove);

		// Atualiza o objeto com o novo index do campo vazio e o pai do estado
		newState.setBlank(indexMove);
		newState.setPredecessor(state);

		return newState;
	}
	
	public static Vertex moveLeft(Vertex state) {
		// Verifica se o movimento é possível
		if (state.blank % 3 == (3 - 1)) {
			return null;
		}
		// Cria um novo objeto estado
		Vertex newState = null;
		try {
			newState = state.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int indexMove = newState.getBlank() + 1;

		// Seta a descrição do movimento
		newState.setAction("Mover o " + newState.getPuzzle()[indexMove] + " para ESQUERDA");
		// Faz a troca dos elementos o array representando o movimento
		newState.swap(newState.getBlank(), indexMove);

		// Atualiza o objeto com o novo index do campo vazio e o pai do estado
		newState.setBlank(indexMove);
		newState.setPredecessor(state);

		return newState;
	}
	
	public static Vertex moveRight(Vertex state) {
		// Verifica se o movimento é possível
		if (state.blank % 3 == 0) {
			return null;
		}
		// Cria um novo objeto estado
		Vertex newState = null;
		try {
			newState = state.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int indexMove = newState.getBlank() - 1;

		// Seta a descrição do movimento
		newState.setAction("Mover o " + newState.getPuzzle()[indexMove] + " para DIREITA");
		// Faz a troca dos elementos o array representando o movimento
		newState.swap(newState.getBlank(), indexMove);

		// Atualiza o objeto com o novo index do campo vazio e o pai do estado
		newState.setBlank(indexMove);
		newState.setPredecessor(state);

		return newState;
	}
	
	private void swap(int indexTo, int indexMove) {
		puzzle[indexTo] = puzzle[indexMove];
		puzzle[indexMove] = 0;
	}
	public static boolean isSolucao(Vertex state) {
		boolean result = false;

		result = Arrays.equals(state.getPuzzle(), new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 0 });
	
		return result;
	}

	public static int getNivelNo(Vertex state) {
		int count = 0;

		Vertex actual = state;
		while (actual.getPredecessor() != null) {
			actual = actual.getPredecessor();
			count++;
		}

		return count;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + blank;
		result = prime * result + ((predecessor == null) ? 0 : predecessor.hashCode());
		result = prime * result + Arrays.hashCode(puzzle);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (blank != other.blank)
			return false;
		if (predecessor == null) {
			if (other.predecessor != null)
				return false;
		} else if (!predecessor.equals(other.predecessor))
			return false;
		if (!Arrays.equals(puzzle, other.puzzle))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Estado [conteudo=" + Arrays.toString(puzzle) + ", idxCampoVazio=" + blank + ", descAcao="
				+ action + "]";
	}

	@Override
	protected Vertex clone() throws CloneNotSupportedException {
		Vertex estado = (Vertex) super.clone();
		estado.puzzle = puzzle.clone();
		return estado;
	}
	
	
}

