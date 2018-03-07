package dfs;
/** DFS using Recursion **/
public class DFS_Recursive {

	public void dfs(Vertex vertex) {
		System.out.println(vertex + " ");

		for (Vertex v : vertex.getAdjList()) {
			if (!v.isVisited()) {
				v.setVisited(true);
				v.setPredecessor(vertex);
				dfs(v);
			}
		}
	}
}
