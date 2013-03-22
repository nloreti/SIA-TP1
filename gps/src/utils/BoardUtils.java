package utils;

import gridlock.Token;

public class BoardUtils {

	public static int size = 6;

	public static int getHTokenSize(Token[][] board, int token, int i, int j) {
		int size = 1;
		if (j < 5 && board[i][j + 1].getValue() == token) {
			for (int h = j + 1; h < BoardUtils.size
					&& board[i][h].getValue() == token; h++) {
				size++;
			}
		} else {
			for (int h = j - 1; h >= 0 && board[i][h].getValue() == token; h--) {
				size++;
			}
		}
		return size;
	}

	public static int getVTokenSize(Token[][] board, int token, int i, int j) {
		int[][] rawBoard = getIntBoard(board);
		return getVTokenSize(rawBoard, token, i, j);
	}

	public static int getVTokenSize(int[][] board, int token, int i, int j) {
		int size = 1;
		if (i < 5 && board[i + 1][j] == token) {
			for (int h = i + 1; h < BoardUtils.size && board[h][j] == token; h++) {
				size++;
			}
		} else {
			for (int h = i - 1; h >= 0 && board[h][j] == token; h--) {
				size++;
			}
		}
		return size;
	}

	public static boolean isHorizontal(int token) {
		if ((token >= 'a' && token <= 'z') || token == '0') {
			return true;
		}
		return false;
	}

	public static boolean isVertical(int token) {
		if (token >= '1' && token <= '9') {
			return true;
		}
		return false;
	}

	public static void copyBoard(int[][] tempBoard, int[][] board) {
		for (int i = 0; i < BoardUtils.size; i++) {
			for (int j = 0; j < BoardUtils.size; j++) {
				tempBoard[i][j] = board[i][j];
			}
		}
	}

	public static int[][] getIntBoard(Token[][] board) {
		int[][] ans = new int[BoardUtils.size][BoardUtils.size];
		for (int i = 0; i < BoardUtils.size; i++) {
			for (int j = 0; j < BoardUtils.size; j++) {
				ans[i][j] = board[i][j].getValue();
			}
		}
		return ans;

	}

	public static void copyBoard(Token[][] ans, Token[][] board) {
		for (int i = 0; i < BoardUtils.size; i++) {
			for (int j = 0; j < BoardUtils.size; j++) {
				ans[i][j] = board[i][j];
			}
		}

	}

	public static void checkCorrect(Token[][] board) {
		for (int x = 0; x < BoardUtils.size; x++) {
			for (int y = 0; y < BoardUtils.size; y++) {
				board[x][y].setI(x);
				board[x][y].setJ(y);
			}
		}

	}
}
