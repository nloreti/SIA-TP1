package engine;

import api.GPSProblem;

public class GridLockBFSEngine extends GPSEngine {

	public GridLockBFSEngine() {
		super();
	}

	@Override
	public void engine(GPSProblem problem, SearchStrategy strategy) {
		super.engine(problem, strategy);
	}

	@Override
	public void addNode(GPSNode node) {
		open.add(node);
	}

	@Override
	public String getStrategyName() {
		return "BFS";
	}
}
