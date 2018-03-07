package iddfs;

import java.util.Stack;

public class Iddfs {

	private Node targetNode;
	private boolean isTargetFound;

	public Iddfs(Node targetNode) {
		this.targetNode = targetNode;
	}

	public void deepeningSearch(Node startNode) {
		/** Starting with 0, cause the first node is the root **/
		int depth = 0;
		/** While target is not found, continue to search **/
		while (!isTargetFound) {
			System.out.println();
			dfs(startNode, depth);
			depth++;
		}
	}

	/** Using a stack instead of recursion, because is better to comprehend **/
	private void dfs(Node startNode, int depth) {
		Stack<Node> stack = new Stack<>();
		startNode.setDepth(0);
		stack.push(startNode);
		/** While the stack is not Empty **/
		while (!stack.isEmpty()) {
			Node actualNode = stack.pop();
			System.out.println(actualNode + " ");
			/**
			 * Tests if the actualNode is the Node we're looking for then sets isTargetFound
			 * to true in need to exit the while structure in deepeningSearch method
			 **/
			if (actualNode.getName().equals(this.targetNode.getName())) {
				System.out.println("\nNode has been found...");
				this.isTargetFound = true;
				return;
			}
			/**Compare the depths and if it's true continues the search**/
			if (actualNode.getDepth() >= depth) {
				continue;
			}
			/**Increments the depth and stacks the node**/
			for (Node node : actualNode.getAdjList()) {
				node.setDepth(actualNode.getDepth() + 1);
				stack.push(node);

			}

		}

	}
}
