package engine;

import java.util.Iterator;
import java.util.LinkedList;

public class GridLockGreedyEngine extends GPSEngine {

	@Override
	public void addNode(GPSNode node) {
		node.setH(problem.getHValue(node.getState()));
		if (open.isEmpty()) {
			((LinkedList<GPSNode>) open).addFirst(node);
			return;
		}
		Iterator<GPSNode> it = open.iterator();
		int depth = node.getDepth();
		int index = 0;
		while (it.hasNext()) {
			GPSNode n = it.next();
			if (n.getDepth() == depth && node.getH() <= n.getH()) {
				((LinkedList<GPSNode>) open).add(index, node);
				return;
			} else if (n.getDepth() < depth) {
				((LinkedList<GPSNode>) open).add(index, node);
				return;
			}
			index++;
		}
		((LinkedList<GPSNode>) open).add(node);
	}

	@Override
	public String getStrategyName() {
		return "Greedy Strategy";
	}

}
