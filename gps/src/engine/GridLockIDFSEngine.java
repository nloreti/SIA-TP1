package engine;

import gridlock.GridLockProblem;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import api.GPSProblem;

public class GridLockIDFSEngine extends GPSEngine {

	private final static int DEFAULT_DEPTH = 50;
	private final static int DEPTH_JUMP = 100;
	private int currentMaxDepth;

	@Override
	public void engine(GPSProblem problem, SearchStrategy strategy) {
		((GridLockProblem) problem).invertRules();
		currentMaxDepth = DEFAULT_DEPTH;
		super.engine(problem, strategy);
	}

	@Override
	public void addNode(GPSNode node) {
			((LinkedList<GPSNode>) open).addFirst(node);
	}

	@Override
	protected boolean explode(GPSNode node) {
		if (node.getDepth() >= currentMaxDepth) {
			//si ya llegué a la profundidad deseada tengo que resetar todo
			// y empezar de nuevo
			open.clear();
			closed.clear();
			open.add(new GPSNode(problem.getInitState(), 0, 0));
			currentMaxDepth += DEPTH_JUMP;
			System.out.println("current depth: " + currentMaxDepth );
			return true;
		}
		return super.explode(node);
	}

	@Override
	public String getStrategyName() {
		return "Iterative DFS";
	}

}
