package heuristic;

import gridlock.Board;
import gridlock.GridLockState;
import api.GPSState;

public class HeuristicEnhanced extends Heuristic {

	@Override
	public int getH(GPSState state) {
		Board board = ((GridLockState)state).getBoard();
		return board.getAproxCost2Exit();
	}

}
