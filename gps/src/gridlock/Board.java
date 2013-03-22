package gridlock;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import utils.BoardUtils;

public class Board {

	final  static int SIZE = 6;
	final  static int LAST_COL = 5;
	final  static int EXIT_ROW = 2;
	private Position blueBlock;
	private Token[][] board;
	Map<Integer, Integer> blocks = new HashMap<Integer, Integer>();

	public Board(int[][] board) {
		this.board = initBoard(board);
		blueBlock = this.getBlueBlockPosition();
		saveBlocks();
	}
	
	public Board(Token[][] board) {
		this.board = board;
		blueBlock = this.getBlueBlockPosition();
		saveBlocks();
	}

	public Token[][] initBoard(int [][] board) {
		Token[][] ans = new Token[SIZE][SIZE];
		for (int i = 0; i < this.SIZE; i++) {
			for (int j = 0; j < this.SIZE; j++) {
				ans[i][j] = new Token (board[i][j], i, j);
			}
		}
		return ans;
	}
	
	public Map<Integer, Integer> getBlocks() {
		return blocks;
	}

	public void saveBlocks() {
		int size;
		for (int i = 0; i < this.SIZE; i++) {
			for (int j = 0; j < this.SIZE; j++) {
				int token = board[i][j].getValue();
				if (!blocks.containsKey(token)) {
					if (BoardUtils.isVertical(token)) {
						size = BoardUtils.getVTokenSize(board, token, i, j);
					} else {
						size = BoardUtils.getHTokenSize(board, token, i, j);
					}
					blocks.put(token, size);
				}
			}
		}
	}

	public int getSize() {
		return this.SIZE;
	}

	public Token[][] getRawBoard() {
		return this.board;
	}

	public Position getBlueBlockPosition() {
		Position ans = null;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (board[i][j].getValue() == '0') {
					ans = new Position(i, j);
					break;
				}
			}
		}
		return ans;
	}

	public boolean isResolved() {
	//	this.printBoard();
		for (int i = 0; i < board.length; i++) {
			if (board[i][LAST_COL].getValue() == '0') {
				return true;
			}
		}
		return false;
	}

	public Position getBlueBlock() {
		return blueBlock;
	}

	public void setBlueBlock(Position blueBlock) {
		this.blueBlock = blueBlock;
	}

	public void printBoard() {
		System.out.println("Printing Board");
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.print((char) (board[i][j].getValue()) + " ");

			}

			System.out.println();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(board);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (!Arrays.deepEquals(board, other.board))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String ans = "Board\n";
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				ans += ((char) (board[i][j].getValue()) + " ");

			}

			ans += "\n";
		}
		return ans;
	}

	public int getBlock2Exit() {
		int blocks = 0;
		for (int j = LAST_COL; board[EXIT_ROW][j].getValue() != '0'; j--) {
			int token = board[EXIT_ROW][j].getValue();
			// printBoard();
			// System.out.println("T: " + (char)token + " I:" + blueBlock.getX()
			// + " J:" + j);
			if (token != '.' && token != 0) {
				blocks++;
			}
		}
		// /System.out.println("Blocks " + blocks);
		return blocks;
	}

	//TODO CHINITO WORKING ON THIS
	public int getAproxCost2Exit() {
		int cost = 0;
		for (int j = LAST_COL; board[EXIT_ROW][j].getValue() != '0'; j--) {
			int token = board[EXIT_ROW][j].getValue();
			if (token != '.' && token != '0') {
				int lenght = blocks.get(token);
				cost += canBeFree(lenght, 2, j);
			}
		}
		return cost;
	}

	private int canBeFree(int lenght, int i, int j) {
		boolean isFree = false;
		for (int h = j; h > 0; h--) {
			if (board[i][j].getValue() == '.') {
				isFree = true;
				break;
			}
		}
		if (isFree == false) {
			for (int k = j; k < this.SIZE; k++) {
				if (board[i][j].getValue() == '.' ) {
					isFree = true;
					break;
				}
			}
		}
		return isFree == true? 2:1;

	}

}
