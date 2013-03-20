package gridlock;

import java.io.File;

import parser.BoardParser;
import engine.GPSEngine;
import engine.GridLockAStarEngine;
import engine.GridLockBFSEngine;
import engine.GridLockDFSEngine;
import engine.GridLockGreedyEngine;
import engine.GridLockIDFSEngine;
import engine.SearchStrategy;
import exception.BoardParsingException;

public class Solver {

	public static void main(String[] args) {
		// System.out.println(args[0].equalsIgnoreCase("IDFS"));
		if (args.length != 2) {
			System.out.println("Programa mal invocado");
			System.out
					.println("Intente nuevamente ingresando correctamente los parametros");
			return;
		}
		if (!(args[0].equalsIgnoreCase("DFS")
				|| args[0].equalsIgnoreCase("BFS")
				|| args[0].equalsIgnoreCase("IDFS")
				|| args[0].equalsIgnoreCase("GREEDY") || args[0]
					.equalsIgnoreCase("AStar"))) {
			System.out.println("Programa mal invocado");
			System.out
					.println("La estrategia no corresponde a una aceptada por el Programa - Inserte BFS | DFS | IDFS");
			return;
		}
		String path = args[1];
		String strategy_command = args[0];
		SearchStrategy strategy = null;
		// path = "files/board1.txt";
		// strategy_command = "IDFS";
		File board = new File(path);
		// File board = new File("files/board2.txt");
		GridLockProblem problem;
		try {
			problem = new GridLockProblem(BoardParser.getInstance(board)
					.getBoard());
			GPSEngine gps = null;
			if (strategy_command.compareTo("DFS") == 0) {
				gps = new GridLockDFSEngine();
				strategy = SearchStrategy.DFS;
			} else if (strategy_command.compareTo("BFS") == 0) {
				gps = new GridLockBFSEngine();
				strategy = SearchStrategy.BFS;
			} else if (strategy_command.compareTo("IDFS") == 0) {
				gps = new GridLockIDFSEngine();
				strategy = SearchStrategy.IDFS;
			} else if (strategy_command.compareTo("GREEDY") == 0) {
				gps = new GridLockGreedyEngine();
				strategy = SearchStrategy.GREEDY;
			} else if (strategy_command.compareTo("AStar") == 0) {
			    gps = new GridLockAStarEngine();
				strategy = SearchStrategy.AStar;
			}
			gps.engine(problem, strategy);
		} catch (BoardParsingException e) {
			e.printStackTrace();
		}
	}
}
