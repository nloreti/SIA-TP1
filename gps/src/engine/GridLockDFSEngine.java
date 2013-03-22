package engine;

import java.util.LinkedList;

public class GridLockDFSEngine extends GPSEngine {

	public GridLockDFSEngine() {
		super();
	}

	@Override
	public void addNode(GPSNode node) {
		((LinkedList<GPSNode>) open).addFirst(node);
	}

	@Override
	public String getStrategyName() {
		return "DFS";
	}
}
