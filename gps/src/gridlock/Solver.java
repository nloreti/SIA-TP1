package gridlock;

import heuristic.Heuristic;
import heuristic.HeuristicBlock;
import heuristic.HeuristicEnhanced;

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
		//Chequeo que sean 3 los argumentos
		if (args.length != 3) {
			System.out.println("Programa mal invocado");
			System.out
					.println("Intente nuevamente ingresando correctamente los parametros");
			System.out.println("Estrategia-a-Utilizar ruta-al-tablero heuristica");
			return;
		}
		
		//Parseo la estrategia
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
		
		//Parseo la Heuristica
		Heuristic heuristic = null;
		if ( args[2] == "h1") {
			heuristic = new HeuristicBlock();
		}else if (args[2] == "h2") {
			heuristic = new HeuristicEnhanced();
		}
		
		String path = args[1];
		String strategy_command = args[0];
		SearchStrategy strategy = null;
		File board = new File(path);
		GridLockProblem problem;

		try {
			problem = new GridLockProblem(BoardParser.getInstance(board)
					.getBoard(), heuristic);
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
