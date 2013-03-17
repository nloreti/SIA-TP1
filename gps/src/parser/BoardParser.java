package parser;

import exception.BoardParsingException;
import gridlock.Board;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BoardParser {

	private static BoardParser parser = null;
	private static  File file;
	
	private BoardParser(File file){
		this.file = file;
	}
	
	public static BoardParser getInstance(File file){
		if (parser == null){
			parser = new BoardParser(file);
		}
		return parser;
	}
	
	public Board getBoard() throws BoardParsingException {
		Scanner scanner;
		int[][] board =  new int[6][6];
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			throw new BoardParsingException();
		}
		int i =0;
		while(scanner.hasNextLine()){
			String line = scanner.nextLine();
			String[] aux = line.split(" ");
			//TODO meter un magic number aca
			if (aux.length != 6 ){
				scanner.close();
				throw new BoardParsingException();
			}
			int j = 0;
			while (j < 6){
				board[i][j] = Integer.valueOf(aux[j].charAt(0));
				j++;
			}
			if(i > 5){
				scanner.close();
				throw new BoardParsingException();
			}
			i++;
		}
		scanner.close();
		return new Board(board);
	}

}
