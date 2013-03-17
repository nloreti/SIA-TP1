package engine;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class GridLockDFSEngine extends GPSEngine {


	private Set<GPSNode> visited = new HashSet<GPSNode>();

	public GridLockDFSEngine() {
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
	public void addNode(GPSNode node) {
		if (visited.contains(node)) {
			return;
		}
		((LinkedList<GPSNode>) open).addFirst(node);
		visited.add(node);
	}
	
	@Override
	public String getStrategyName() {
		return "DFS";
	}
}
