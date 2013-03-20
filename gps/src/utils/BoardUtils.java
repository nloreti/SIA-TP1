package utils;

public class BoardUtils {

	public static int getHTokenSize(int[][] board, int token, int i, int j) {
		int size = 1;
		if ( j < 5 && board[i][j+1] == token) {
			for(int h=j+1;  h < board.length && board[i][h] == token; h++) {
				size++;
			}
		}else {
			for(int h=j-1;  h >= 0 && board[i][h] == token; h--) {
				size++;
			}
		}
		return size;
	}

	public static int getVTokenSize(int[][] board, int token, int i, int j) {
		int size = 1;
		if ( i < 5 && board[i+1][j] == token) {
			for(int h=i+1;  h < board.length && board[h][j] == token; h++) {
				size++;
			}
		}else {
			for(int h=i-1;  h > 0 && board[h][j] == token; h--) {
				size++;
			}
		}
		return size;
	}
	
	public static boolean isHorizontal(int token) {
		if( (token >= 'a' && token <= 'z') || token == '0') {
			return true;
		}
		return false;
	}
	
	public static boolean isVertical(int token) {
		if(token >= '1' && token <= '9') {
			return true;
		}
		return false;
	}
	
	public static void copyBoard(int[][] tempBoard, int[][] board2) {
		for(int i = 0; i < board2.length ; i++) {
			for(int j = 0; j <board2.length; j++) {
				tempBoard[i][j] = board2[i][j];
			}
		}
	}
	
}
