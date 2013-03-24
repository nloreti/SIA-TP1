package gridlock;

import heuristic.Heuristic;

import java.util.ArrayList;
import java.util.List;

import api.GPSProblem;
import api.GPSRule;
import api.GPSState;

public class GridLockProblem implements GPSProblem {

	protected Board initBoard;
	protected List<GPSRule> rules;
	protected Heuristic heuristic;

	public GridLockProblem(Board board, Heuristic heuristic) {
		this.initBoard = board;
		rules = new ArrayList<GPSRule>();
		initializeRules();
		this.heuristic = heuristic;
	}

	private void initializeRules() {
		Token[][] rawboard = initBoard.getRawBoard();
		for (int i = 0; i < initBoard.getSize(); i++) {
			for (int j = 0; j < initBoard.getSize(); j++) {
				if( rawboard[i][j].value == '.') {
					rules.add(new UpRule(rawboard[i][j]));
					rules.add(new DownRule(rawboard[i][j]));
					rules.add(new LeftRule(rawboard[i][j]));
					rules.add(new RightRule(rawboard[i][j]));
				}
			}
		}
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
		//TODO inverts half the rules
		int inversion_count = rules.size()/2;
		for (int i =0; i< inversion_count ; i++ ){
			int random = (int)(Math.random() * (inversion_count-1));
			GPSRule rule = rules.get(random);
			rules.remove(random);
			rules.add(rule);
		}
	}


}
