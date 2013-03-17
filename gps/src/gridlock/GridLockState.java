package gridlock;

import api.GPSState;

public class GridLockState implements GPSState{

	private Board board;

		public GridLockState(Board borad) {
		super();
		this.board = borad;
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
		//board.printBoard();
	//	System.out.println(board.isResolved());
		return board.isResolved();
	}
	
	@Override
	public boolean compare(GPSState state) {
		return board.equals(((GridLockState)state).getBoard());
	}

}
