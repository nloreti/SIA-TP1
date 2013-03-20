package gridlock;

import java.util.ArrayList;
import java.util.List;

import utils.BoardUtils;
import api.GPSRule;
import api.GPSState;
import exception.NotAppliableException;

public class UpRule implements GPSRule {

	@Override
	public List<GPSState> evalRule(GPSState state) throws NotAppliableException {
		List<GPSState> ans = new ArrayList<GPSState>();
		Board board = ((GridLockState)state).getBoard();
		int [][] rawBoard = board.getRawBoard();
		Board temp;
		
		for(int i = 0; i < board.getSize(); i++) {
			for(int j=0; j < board.getSize(); j++) {
				if(rawBoard[i][j] == '.'){
					temp = checkUP(rawBoard, i,j);
					if (temp != null) {
						ans.add(new GridLockState(temp));
					}
				}
			}
		}
		return ans;
	}
	
	
	private Board checkUP(int[][] board, int i, int j) {
		if ( i==0) {
			return null;
		}
		int[][] ans = new int[board.length][board.length];
		BoardUtils.copyBoard(ans,board);
		int upToken = board[i-1][j];
		int size,token,distance,k;
		//System.out.println("DOWN TOKEN: " + (char)downToken);
		if ( BoardUtils.isHorizontal(upToken) ) {
		//	System.out.println("ENTRO A HORIZONTAL");
			return null;
		}
		else if ( BoardUtils.isVertical(upToken) ) {
			distance = 1;
			size = getVTokenSize(board, upToken, i-1, j);
	//		System.out.println("SIZE: " + size);
			for ( k = i; size>0; k--, size--) {
				ans[k][j] = upToken;
			}
			for( ; distance > 0 && k >= 0; distance--, k--) {
				ans[k][j] = '.';
			}
		}else {
			int h;
			for(h = i-1; h >= 0; h--) {
				token = board[h][j];
				if (BoardUtils.isHorizontal(token)) {
					return null;
				}
				if( BoardUtils.isVertical(token)) {
		//			System.out.println("TOKEN if: " + (char)token);
					distance = i-h;
					size = getVTokenSize(board,token, h, j);
			//		System.out.println("SIZE " + size);
					for ( k = i; size>0; k--, size--) {
						ans[k][j] = token;
					}
					for( ; distance > 0 && k>=0; distance--, k--) {
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

	@Override
	public Integer getCost() {
		return 1;
	}

	@Override
	public String getName() {
		return "Se mueve arriba la pieza";
	}

	private int getVTokenSize(int[][] board, int token, int i, int j) {
		int size = 1;
		if ( i < 5 && board[i+1][j] == token) {
			for(int h=i+1;  h < board.length && board[h][j] == token; h++) {
				size++;
			}
		}else {
			for(int h=i-1;  h >= 0; h--) {
				if (board[h][j] != token) {
					break;
				}
				size++;
			}
		}
		return size;
	}

}
