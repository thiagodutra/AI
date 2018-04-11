package br.edu.uepb.eightpuzzle.search;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import br.edu.uepb.eightpuzzle.vertex.Vertex;

/**
 * 
 * @author Thiago O. Dutra
 *
 */
public class Search {
	private long totalExpansions;
	private long totalNodes;
	private long totalMemory;
	private long totalTime;
	private int totalSteps;
	private Vertex solutionState;
	private List<Vertex> stepsList;

	public Search() {
	}

	public long getTotalExpansions() {
		return totalExpansions;
	}

	public void setTotalExpansions(long totalExpansions) {
		this.totalExpansions = totalExpansions;
	}

	public long getTotalNodes() {
		return totalNodes;
	}

	public void setTotalNodes(long totalNodes) {
		this.totalNodes = totalNodes;
	}

	public long getTotalMemory() {
		return totalMemory;
	}

	public void setTotalMemory(long totalMemory) {
		this.totalMemory = totalMemory;
	}

	public long getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
	}

	public int getTotalSteps() {
		return totalSteps;
	}

	public void setTotalSteps(int totalSteps) {
		this.totalSteps = totalSteps;
	}

	public Vertex getSolutionState() {
		return solutionState;
	}

	public void setSolutionState(Vertex solutionState) {
		this.solutionState = solutionState;
	}
	
	public List<Vertex> getStepsList() {
		return stepsList;
	}
	public void setStepsList(Vertex state) {
		stepsList = new LinkedList<>();
		List<Vertex> result = getStepsList(solutionState);
		if (result != null)
			setTotalSteps(result.size());
		else
			setTotalSteps(0);
		this.stepsList = result;
	}
	
	public void setListDePassos(List<Vertex> stepsList) {
		this.stepsList = stepsList;
	}
	
	private List<Vertex> getStepsList(Vertex solution) {
		if (solution.getPredecessor() != null) {
			stepsList.add(solution);
			
			return getStepsList(solution.getPredecessor());
		}

		Collections.reverse(stepsList);
		return stepsList;
	}
}
