package iddfs;

import java.util.ArrayList;
import java.util.List;

public class Node {
	/**Attributes**/
	private String name;
	private int depth = 0;
	private List<Node> adjList;
	
	/**Constructor**/
	public Node (String name) {
		this.name = name;
		this.adjList = new ArrayList<>();
	}
	
	/**Add adjacent nodes**/
	public void addNeighbor (Node node) {
		this.adjList.add(node);
	}
	/**toString**/
	
	@Override
	public String toString () {
		return this.name;
	}
	
	/**Getters Setters**/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public List<Node> getAdjList() {
		return adjList;
	}
	
	
	

}
