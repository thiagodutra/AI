package bfs;

import java.util.LinkedList;
import java.util.Queue;

/**This Algorithm searches a graph 'horizontally' first**/
public class BredthFirstSearch {
	
	public void bfs( Vertex root) {
		/**Creates a queue of linked lists**/
		Queue<Vertex> queue = new LinkedList<>();
		/**Updates de root as visited and adds it to the queue**/
		root.setVisited(true);
		queue.add(root);
		/**Walk through the entire queue**/
		while (!queue.isEmpty()) {
			/**Updates the actual vertex, removes it from queue then print it**/
			Vertex actualVertex = queue.remove();
			System.out.println(actualVertex+"-");
			/**Iterates through the actual vertex neighbor's list**/
			for (Vertex v : actualVertex.getNeighborList()) {
				/**Sets Vertex v as visited and adds it to the queue**/
				if (!v.isVisited()) {
					v.setVisited(true);
					queue.add(v);
				}
				
			}
			
		}
	}

}
