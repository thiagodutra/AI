package dfs;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
	
	private String name;
	private List<Vertex> adjList;
	private Vertex predecessor;
	private boolean visited;
	
	public Vertex (String name) {
		this.name = name;
		this.adjList = new ArrayList<Vertex>();
	}
	/**Adds neighbors to the list of adjacency**/
	public void addNeighbor(Vertex vertex) {
		this.adjList.add(vertex);
	}

	
	/**Getters and Setters**/
	public Vertex getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(Vertex predecessor) {
		this.predecessor = predecessor;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public List<Vertex> getAdjList() {
		return adjList;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	
	
}
