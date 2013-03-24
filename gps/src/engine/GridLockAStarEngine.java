package engine;

import java.util.Comparator;
import java.util.PriorityQueue;

public class GridLockAStarEngine extends GPSEngine {

	
	private static final int DEFAULT_QUEUE_SIZE = 100;
	
	public GridLockAStarEngine(){
		open = new PriorityQueue<GPSNode>(DEFAULT_QUEUE_SIZE, new Comparator<GPSNode>() {
            @Override
            public int compare(GPSNode n1, GPSNode n2) {
                    if (n1.getF() != n2.getF()) {
                            return n1.getF()  - n2.getF();
                    }
                    return n1.getH()  - n2.getH();
            }
    });
		
	}
	
	@Override
	public void addNode(GPSNode node) {
		node.setH(problem.getHValue(node.getState()));
		open.add(node);
	}

	@Override
	public String getStrategyName() {
		return "AStar Strategy";
	}
	
	@Override
	protected GPSNode getHead(){
		return ((PriorityQueue<GPSNode>) open).remove();

	}

}
