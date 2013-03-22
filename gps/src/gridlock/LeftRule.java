package gridlock;

import utils.BoardUtils;
import api.GPSRule;
import api.GPSState;
import exception.NotAppliableException;

public class LeftRule implements GPSRule {

	Token token;

	public LeftRule(Token token) {
		this.token = token;
	}

	@Override
	public Integer getCost() {
		return 1;
	}

	@Override
	public String getName() {
		return "Se mueve arriba la pieza";
	}

	@Override
	public GPSState evalRule(GPSState state) throws NotAppliableException {
	//	System.out.println("LeftRule - Token: " + (char)token.getValue() + " I:" + token.getI() + " J:" + token.getJ());	
		GPSState ans = null;
		Board board = ((GridLockState) state).getBoard();
		Token[][] rawBoard = board.getRawBoard();
		Board temp;
		temp = checkLEFT(rawBoard);
		if (temp != null) {
			ans = new GridLockState(temp);
		}

		return ans;
	}
	
	
	public Board checkLEFT(Token[][] board) {
		
		Token[][] ans = new Token[6][6];
		BoardUtils.copyBoard(ans, board);
		
		//TODO para que está esto?
		for(int x=0; x < Board.SIZE ;x++) {
			for(int y=0; y < Board.SIZE;y++) {
				ans[x][y].setI(x);
				ans[x][y].setJ(y);
			}
		}

		int size, tokenValue, distance, k;
		int i = token.getI();
		int j = token.getJ();

		if (j == 0) {
			return null;
		}

		Token leftToken = board[i][j-1];
		if (BoardUtils.isVertical(leftToken.getValue())) {
			return null;
		} else if (BoardUtils.isHorizontal(leftToken.getValue())) {
	//		System.out.println("Entro al corto - " + i + " " + j);
			size = BoardUtils.getHTokenSize(board, leftToken.getValue(), i, j-1);
			Token aux = ans[i][j-size];
			ans[i][j-size] = token;
			ans[i][j] = aux;
		} else {
		//	System.out.println("Entro al largo - " + i + " " + j);
			int h;
			for (h = j - 1; h >= 0; h--) {
				tokenValue = board[i][h].getValue();
				if (BoardUtils.isVertical(tokenValue)) {
					return null;
				}
				if (BoardUtils.isHorizontal(tokenValue)) {
			//		System.out.println("H: " + h + " token: " + (char)tokenValue);
					distance = j-h;
					size = BoardUtils.getHTokenSize(board, tokenValue, i, h);
				//	System.out.println("Size " + size + " - " + "distance: " + distance);
					for (k = j; size > 0; k--, size--) {
					//	System.out.println("k: " + k + " k+size:" + (k+distance) + " j:" + j);
						Token aux = ans[i][k-distance];
						ans[i][k-distance] = ans[i][k];
						ans[i][k] = aux;
					}
					break;
				}
			}
			if (h == -1) {
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


}
