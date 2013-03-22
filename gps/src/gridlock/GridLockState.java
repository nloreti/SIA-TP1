package gridlock;

import api.GPSState;

public class GridLockState implements GPSState {

	private Board board;

	public GridLockState(Board board) {
		super();
		this.board = board;
	}

	public Board getBoard() {
		return board;
	}

	@Override
	public int hashCode() {
		return board.hashCode();
	}

	@Override
	public String toString() {
		return board.toString();
	}

	@Override
	public boolean isGoalState() {
		return board.isResolved();
	}

	@Override
	public boolean compare(GPSState state) {
		return board.equals(((GridLockState) state).getBoard());
	}

}
