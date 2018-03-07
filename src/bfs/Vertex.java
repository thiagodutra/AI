package bfs;

import java.util.ArrayList;
import java.util.List;
	/**Vertex Class**/
public class Vertex {
	
	
	/**Attributes: data 
	 * 			  visited boolean - stores if it was visited or not
	 * 			  List<vertex> stores the vertex neighbors **/
	private int data;
	private boolean visited;
	private List<Vertex> neighborList;
	
	/**Constructor: Already and data and creates a neighbour's list**/
	public Vertex(int data) {
		this.data = data;
		this.neighborList = new ArrayList<>();
		
	}
	/**Adds a Vertex as a neighbor **/
	public void addNeighbor(Vertex vertex) {
		this.neighborList.add(vertex);
	}
	
	/** Getters and Setters**/
	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public List<Vertex> getNeighborList() {
		return neighborList;
	}

	public void setNeighborList(List<Vertex> neighborList) {
		this.neighborList = neighborList;
	}
	
	/** Rewriting the toString method**/
	@Override
	public String toString() {
		return ""+this.data;
	}
	
	
	
}
