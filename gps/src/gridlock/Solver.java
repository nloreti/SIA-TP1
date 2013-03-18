package gridlock;

import java.io.File;

import parser.BoardParser;
import engine.GPSEngine;
import engine.GridLockBFSEngine;
import engine.GridLockDFSEngine;
import engine.SearchStrategy;
import exception.BoardParsingException;

public class Solver {


	public static void main(String[] args) {
		File board = new File("files/board2.txt");
		//TODO poner aca direccion relativa!
//		File board = new File("/Users/joseignaciosantiagogalindo/Documents/ITBA/SIA/tp1/SIA-TP1/gps/src/files/board1.txt");
		GridLockProblem problem;
		try {
			problem = new GridLockProblem(BoardParser.getInstance(board).getBoard());
			GPSEngine gps = new GridLockDFSEngine();
//			GPSEngine gps = new GridLockBFSEngine();
			gps.engine(problem, SearchStrategy.BFS);
			//Board board = new Board(matrix);
			//board.printBoard();
			//board.getAllPosibleBoards();
		} catch (BoardParsingException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
