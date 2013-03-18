package engine;

import java.util.HashSet;
import java.util.Set;

import api.GPSProblem;

public class GridLockBFSEngine extends GPSEngine {

	private Set<GPSNode> visited = new HashSet<GPSNode>();

	public GridLockBFSEngine() {
		super();
	}

	//	@Override
//	public void engine(GPSProblem problem) {
//		((BuildingProblem) problem).invertRules();
//		visited.clear();
//		super.engine(problem);
//	}
//	
	@Override
	public void engine(GPSProblem problem, SearchStrategy strategy) {
		visited.clear();
		super.engine(problem,strategy);
	}
	
	@Override
	public void addNode(GPSNode node) {
		if (!visited.contains(node)) {
			open.add(node);
			visited.add(node);
		}
	}
	
	@Override
	public String getStrategyName() {
		return "BFS";
	}
}
