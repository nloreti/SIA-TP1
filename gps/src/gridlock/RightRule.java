package gridlock;

import java.util.ArrayList;
import java.util.List;

import api.GPSRule;
import api.GPSState;
import exception.NotAppliableException;

public class RightRule implements GPSRule {

	@Override
	public Integer getCost() {
		return 1;
	}

	@Override
	public String getName() {
		return "Se mueve arriba la pieza";
	}

	@Override
	public List<GPSState> evalRule(GPSState state) throws NotAppliableException {
		List<GPSState> ans = new ArrayList<GPSState>();
		Board board = ((GridLockState)state).getBoard();
		int [][] rawBoard = board.getRawBoard();
		
		for(int i = 0; i < board.getSize(); i++) {
			for(int j=0; j < board.getSize(); j++) {
				if(rawBoard[i][j] == '.'){
					Board temp;
					temp = checkRIGHT(rawBoard, i,j);
					if (temp != null) {
						ans.add(new GridLockState(temp));
					}
				}
			}
		}
		return ans;
	}
	
	
	private Board checkRIGHT(int[][] board, int i, int j) {
		
	//	System.out.println("x:" + i + "y: " + j);
		if ( j==5 ) {
			return null;
		}
		int[][] ans = new int[board.length][board.length];
		copyBoard(ans,board);
		int rightToken = board[i][j+1];
		int size,token,distance,k;
	//	System.out.println("RIGTH TOKEN: " + (char)rightToken);
		if ( isVertical(rightToken) ) {
	//		System.out.println("ENTRO A VERTICAL");
			return null;
		}
		else if ( isHorizontal(rightToken) ) {
			distance = 1;
			size = getHTokenSize(board,rightToken, i, j+1);
		//	System.out.println("SIZE: " + size);
			for ( k = j; size>0; k++, size--) {
				ans[i][k] = rightToken;
			}
			for( ; distance > 0 && k < board.length; distance--, k++) {
				ans[i][k] = '.';
			}
		}else {
			int h;
			for(h = j+1; h < board.length; h++) {
				token = board[i][h];
				if (isVertical(token)) {
					return null;
				}
				if( isHorizontal(token)) {
					distance = h-j;
					size = getHTokenSize(board,token, i, h);
					for ( k = j; size>0; k++, size--) {
						ans[i][k] = token;
					}
					for( ; distance > 0 && k < board.length; distance--, k++) {
						ans[i][k] = '.';
					}
					break;
				}
			}
			if(h==0) {
				return null;
			}
		}
		
		return new Board(ans);
	}
	public boolean isHorizontal(int token) {
		if( (token >= 'a' && token <= 'z') || token == '0') {
			return true;
		}
		return false;
	}
	
	public boolean isVertical(int token) {
		if(token >= '1' && token <= '9') {
			return true;
		}
		return false;
	}
	
	private void copyBoard(int[][] tempBoard, int[][] board2) {
		
		for(int i = 0; i < board2.length ; i++) {
			for(int j = 0; j <board2.length; j++) {
				tempBoard[i][j] = board2[i][j];
			}
		}
		
	}

	private int getHTokenSize(int[][] board, int token, int i, int j) {
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

}
