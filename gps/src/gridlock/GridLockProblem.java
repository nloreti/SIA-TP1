package gridlock;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import api.GPSProblem;
import api.GPSRule;
import api.GPSState;

public class GridLockProblem implements GPSProblem {

	protected Board initBoard;
	protected List<GPSRule> rules;

	public GridLockProblem(Board board) {
		this.initBoard = board;
		rules = new ArrayList<GPSRule>();
		initializeRules();
		sortRules();
	}
	
	private void sortRules() {
		//TODO: POR AHORA NADA;
	}

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
		throw new IllegalStateException();
	}

	public void invertRules() {
//		List<GPSRule> inverted = new LinkedList<GPSRule>();
//		for(GPSRule r: rules) {
//			inverted.add(r);
//		}
//		rules = inverted;
		GPSRule rule = rules.get(0);
		rules.remove(0);
		rules.add(rule);
		
//		rule = rules.get(0);
//		rules.remove(0);
//		rules.add(rule);
		
	}


}
