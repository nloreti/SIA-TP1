package gridlock;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import parser.BoardParser;
import api.GPSRule;
import exception.BoardParsingException;

public class Test {

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
//		String strategy_command = args[0];
//		SearchStrategy strategy = null;
		File board = new File(path);
//		GridLockProblem problem;
		List<GPSRule> rules = new ArrayList<GPSRule>();
		
		try {
			Board board1 = BoardParser.getInstance(board).getBoard();
			board1.printBoard();
			Token[][] rawboard = board1.getRawBoard();
			for (int i = 0; i < board1.getSize(); i++) {
				for (int j = 0; j < board1.getSize(); j++) {
					if( rawboard[i][j].value == '.') {
					//	rules.add(new UpRule(rawboard[i][j]));
					//	rules.add(new DownRule(rawboard[i][j]));
					//	rules.add(new LeftRule(rawboard[i][j]));
						rules.add(new RightRule(rawboard[i][j]));
					}
				}
			}
	//	System.out.println("Rules size " + rules.size());
		for (GPSRule rule : rules) {
		//	System.out.println("ORIGINAL");
	//		board1.printBoard();
			Board test = ((RightRule)rule).checkRIGHT(board1.getRawBoard());
			if (test != null) {
				test.printBoard();
			}
		}
		} catch (BoardParsingException e) {
			e.printStackTrace();
		}
	}
	
}
