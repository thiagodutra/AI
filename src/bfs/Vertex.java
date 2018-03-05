package bfs;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

	private int data;
	private boolean visited;
	private List<Vertex> neighbourList;
	
	public Vertex(int data) {
		this.data = data;
		this.neighbourList = new ArrayList<>();
		
	}
	
}
