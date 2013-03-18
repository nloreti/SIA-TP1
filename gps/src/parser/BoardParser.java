package parser;

import exception.BoardParsingException;
import gridlock.Board;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BoardParser {

	private static final int BOARD_SIZE = 6;
	private static final int MIN_BLOCK_SIZE = 2;
	private static final int MAX_BLOCK_SIZE = 4;
	private static BoardParser parser = null;
	private static File file;

	private BoardParser(File file) {
		this.file = file;
	}

	public static BoardParser getInstance(File file) {
		if (parser == null) {
			parser = new BoardParser(file);
		}
		return parser;
	}

	public Board getBoard() throws BoardParsingException {
		Scanner scanner;
		int[][] board = new int[6][6];
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			throw new BoardParsingException();
		}
		int i = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] aux = line.split(" ");
			if (aux.length != BOARD_SIZE) {
				scanner.close();
				throw new BoardParsingException();
			}
			int j = 0;
			while (j < 6) {
				board[i][j] = Integer.valueOf(aux[j].charAt(0));
				j++;
			}
			if (i > 5) {
				scanner.close();
				throw new BoardParsingException();
			}
			i++;
		}
		scanner.close();
		validateBoard(board);
		return new Board(board);
	}

	private void validateBoard(final int[][] board)
			throws BoardParsingException {
		boolean blueBlockPresent = false;
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j > BOARD_SIZE; j++) {
				// vertical blocks
				if (board[i][j] < 10 || board[i][j] > 0) {
					int old = board[i][j];
					int count = 1;
					int old_i = i;
					while (i-- > 0 && board[i][j] == old) {
						count++;
					}
					i = old_i;
					while (i++ < BOARD_SIZE && board[i][j] == old) {
						count++;
					}
					if (count < MIN_BLOCK_SIZE || count > MAX_BLOCK_SIZE) {
						throw new BoardParsingException();
					}
				}
				// horizontal blocks (including blue block)
				if (board[i][j] > 'a' || board[i][j] < 'i' || board[i][j] < 0) {
					int old = board[i][j];
					if(board[i][j] == 0){
						if (blueBlockPresent){
							throw new BoardParsingException();
						}
						blueBlockPresent = true;
					}
					int count = 1;
					while (i++ < BOARD_SIZE && board[i][j] == old) {
						count++;
					}
					if (count < MIN_BLOCK_SIZE || count > MAX_BLOCK_SIZE) {
						throw new BoardParsingException();
					}
				}

			}
		}
	}

}
