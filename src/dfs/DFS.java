package dfs;

import java.util.Stack;

/** DFS Using stack **/

public class DFS {
	/** Attribute Stack of the type vertex**/
	private Stack<Vertex> stack;
	/**Constructor**/
	public DFS() {
		this.stack = new Stack<>();
	}
	/** Traverse the tree **/
	public void dfs(Vertex root) {
		stack.add(root);
		root.setVisited(true);
		/**While not visit all the vertexes **/
		while (!stack.isEmpty()) {
			Vertex actualVertex = stack.pop();
			System.out.println(actualVertex + " ");
			/** Iterates over the AdjList**/
			for (Vertex v : actualVertex.getAdjList()) {
				if (!v.isVisited()) {
					v.setVisited(true);
					v.setPredecessor(actualVertex);
					stack.push(v);
				}
			}
		}
	}
}
