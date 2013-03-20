package engine;

import java.util.Iterator;
import java.util.LinkedList;

public class GridLockAStarEngine extends GPSEngine {

	@Override
	public void addNode(GPSNode node) {
		node.setH(problem.getHValue(node.getState()));
		node.setG(node.getParent().getG() + node.getCost());
		if (open.isEmpty()) {
			((LinkedList<GPSNode>) open).addFirst(node);
			return;
		}
		Iterator<GPSNode> it = open.iterator();
		int depth = node.getDepth();
		int index = 0;
		while (it.hasNext()) {
			GPSNode n = it.next();
			if (n.getDepth() == depth && node.getF() <= n.getF()) {
				// if nodes have the same depth, enqueue node
				// only if the h() value is less than this element
				((LinkedList<GPSNode>) open).add(index, node);
				return;
			} else if (n.getDepth() < depth) {
				// if nodes in queue have less depth than this node,
				// enqueue it before them
				((LinkedList<GPSNode>) open).add(index, node);
				return;
			}
			index++;
		}
		((LinkedList<GPSNode>) open).add(node);
	}

	@Override
	public String getStrategyName() {
		return "AStar Strategy";
	}

}
