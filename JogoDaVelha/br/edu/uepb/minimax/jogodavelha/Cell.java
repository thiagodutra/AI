package br.edu.uepb.minimax.jogodavelha;

public class Cell {
	private int x;
	private int y;
	
	//-1(bad move), 0(neutral), +1(good move)
	private int minimaxHeuristicsValue;
	
	//Constructor
	public Cell (int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	
	//toString Method
	@Override
	public String toString() {
		return "("+this.x+","+this.y+")";
	}
	
	//Getters Settters
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getMinimaxHeuristicsValue() {
		return minimaxHeuristicsValue;
	}

	public void setMinimaxHeuristicsValue(int minimaxHeuristics) {
		this.minimaxHeuristicsValue = minimaxHeuristics;
	}

	
	
}
