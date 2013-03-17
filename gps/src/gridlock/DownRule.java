package gridlock;

import java.util.ArrayList;
import java.util.List;

import api.GPSRule;
import api.GPSState;
import exception.NotAppliableException;

public class DownRule implements GPSRule {

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
					temp = checkDOWN(rawBoard, i,j);
					if (temp != null) {
						ans.add(new GridLockState(temp));
					}
				}
			}
		}
		return ans;
	}
	
	
	private Board checkDOWN(int[][] board, int i, int j) {
		if ( i==5 ) {
			return null;
		}
		int[][] ans = new int[board.length][board.length];
		copyBoard(ans,board);
		int downToken = board[i+1][j];
		int size,token,distance,k;
		//System.out.println("DOWN TOKEN: " + (char)downToken);
		if ( isHorizontal(downToken) ) {
		//	System.out.println("ENTRO A HORIZONTAL");
			return null;
		}
		else if ( isVertical(downToken) ) {
			distance = 1;
			size = getVTokenSize(board, downToken, i+1, j);
	//		System.out.println("SIZE: " + size);
			for ( k = i; size>0; k++, size--) {
				ans[k][j] = downToken;
			}
			for( ; distance > 0 && k < board.length; distance--, k++) {
				ans[k][j] = '.';
			}
		}else {
			int h;
			for(h = i+1; h < board.length; h++) {
				token = board[h][j];
				if (isHorizontal(token)) {
					return null;
				}
				if( isVertical(token)) {
		//			System.out.println("TOKEN if: " + (char)token);
					distance = h-i;
					size = getVTokenSize(board,token, h, j);
			//		System.out.println("SIZE " + size);
					for ( k = i; size>0; k++, size--) {
						ans[k][j] = token;
					}
					for( ; distance > 0 && k<board.length; distance--, k++) {
						ans[k][j] = '.';
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

	private int getVTokenSize(int[][] board, int token, int i, int j) {
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

}
