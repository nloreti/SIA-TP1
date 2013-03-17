package gridlock;

import engine.GPSEngine;
import engine.GridLockDFSEngine;
import engine.SearchStrategy;

public class Solver {

	public static void main(String[] args) {
		int[][] matrix = new int[6][6];
		matrix[0][0] = '1';
		matrix[0][1] = '.';
		matrix[0][2] = '.';
		matrix[0][3] = 'a';
		matrix[0][4] = 'a';
		matrix[0][5] = 'a';
		matrix[1][0] = '1';
		matrix[1][1] = '.';
		matrix[1][2] = '.';
		matrix[1][3] = '2';
		matrix[1][4] = '.';
		matrix[1][5] = '4';
		matrix[2][0] = '0';
		matrix[2][1] = '0';
		matrix[2][2] = '.';
		matrix[2][3] = '2';
		matrix[2][4] = '3';
		matrix[2][5] = '4';
		matrix[3][0] = 'b';
		matrix[3][1] = 'b';
		matrix[3][2] = 'b';
		matrix[3][3] = '.';
		matrix[3][4] = '3';
		matrix[3][5] = '4';
		matrix[4][0] = '.';
		matrix[4][1] = '.';
		matrix[4][2] = '5';
		matrix[4][3] = '.';
		matrix[4][4] = 'c';
		matrix[4][5] = 'c';
		matrix[5][0] = 'd';
		matrix[5][1] = 'd';
		matrix[5][2] = '5';
		matrix[5][3] = 'e';
		matrix[5][4] = 'e';
		matrix[5][5] = '.';
		
		GridLockProblem problem = new GridLockProblem(new Board(matrix));
		GPSEngine gps = new GridLockDFSEngine();
		gps.engine(problem, SearchStrategy.DFS);
		//Board board = new Board(matrix);
		//board.printBoard();
		//board.getAllPosibleBoards();
		
	}
	
}
