package gridlock;

import heuristic.Heuristic;
import heuristic.HeuristicBlock;

import java.util.ArrayList;
import java.util.List;

import api.GPSProblem;
import api.GPSRule;
import api.GPSState;

public class GridLockProblem implements GPSProblem {

	protected Board initBoard;
	protected List<GPSRule> rules;
	protected Heuristic heuristic = new HeuristicBlock();

	public GridLockProblem(Board board) {
		this.initBoard = board;
		rules = new ArrayList<GPSRule>();
		initializeRules();
//		sortRules();
	}
	
//	private void sortRules() {
//		//TODO: POR AHORA NADA;
//	}

	private void initializeRules() {
		rules.add(new UpRule());
		rules.add(new DownRule());
		rules.add(new LeftRule());
		rules.add(new RightRule());
	}

	@Override
	public GPSState getInitState() {
		return new GridLockState(this.initBoard);
	}

	@Override
	public GPSState getGoalState() {
		throw new IllegalStateException();
	}

	@Override
	public List<GPSRule> getRules() {
		return this.rules;
	}

	@Override
	public Integer getHValue(GPSState state) {
		return heuristic.getH(state);
	}

	public void invertRules() {
		int min = 0;
		int max = 3;
		int random = min + (int)(Math.random() * ((max - min) + 1));
		GPSRule rule = rules.get(random);
		rules.remove(random);
		rules.add(rule);
	}


}
