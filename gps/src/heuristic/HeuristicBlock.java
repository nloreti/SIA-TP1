package heuristic;

import gridlock.Board;
import gridlock.GridLockState;
import api.GPSState;

public class HeuristicBlock extends Heuristic{

	@Override
	public int getH(GPSState state) {
		Board board = ((GridLockState)state).getBoard();
		return board.getBlock2Exit();
	}

	
}
