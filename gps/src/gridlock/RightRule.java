package gridlock;

import utils.BoardUtils;
import api.GPSRule;
import api.GPSState;
import exception.NotAppliableException;

public class RightRule implements GPSRule {

	Token token;

	public RightRule(Token token) {
		this.token = token;
	}

	@Override
	public GPSState evalRule(GPSState state) throws NotAppliableException {
		GPSState ans = null;
		Board board = ((GridLockState) state).getBoard();
		Token[][] rawBoard = board.getRawBoard();
		Board temp;
		temp = checkRIGHT(rawBoard);
		if (temp != null) {
			ans = new GridLockState(temp);
		}

		return ans;
	}

	public Board checkRIGHT(Token[][] board) {
		int size, tokenValue, distance, k;
		int i = token.getI();
		int j = token.getJ();

		if (j == 5) {
			return null;
		}
		Token[][] ans = new Token[6][6];
		BoardUtils.copyBoard(ans, board);
		Token rightToken = board[i][j+1];
		if (BoardUtils.isVertical(rightToken.getValue())) {
			return null;
		} else if (BoardUtils.isHorizontal(rightToken.getValue())) {
	//		System.out.println("Entro al corto - " + i + " " + j);
			size = BoardUtils.getHTokenSize(board, rightToken.getValue(), i, j+1);
			Token aux = ans[i][j+size];
			ans[i][j+size] = token;
			ans[i][j] = aux;
		} else {
		//	System.out.println("Entro al largo - " + i + " " + j);
			int h;
			for (h = j + 1; h < board.length; h++) {
				tokenValue = board[i][h].getValue();
				if (BoardUtils.isVertical(tokenValue)) {
					return null;
				}
				if (BoardUtils.isHorizontal(tokenValue)) {
			//		System.out.println("H: " + h + " token: " + (char)tokenValue);
					distance = h-j;
					size = getHTokenSize(board, tokenValue, i, h);
				//	System.out.println("Size " + size + " - " + "distance: " + distance);
					for (k = j; size > 0; k++, size--) {
					//	System.out.println("k: " + k + " k+size:" + (k+distance) + " j:" + j);
						Token aux = ans[i][k+distance];
						ans[i][k+distance] = ans[i][k];
						ans[i][k] = aux;
					}
					break;
				}
			}
			if (h == 6) {
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

	private int getHTokenSize(Token[][] board, int token, int i, int j) {
		int size = 1;
		if (j < 5 && board[i][j + 1].getValue() == token) {
			for (int h = j + 1; h < board.length && board[i][h].getValue() == token; h++) {
				size++;
			}
		} else {
			for (int h = j - 1; h >= 0 && board[i][h].getValue() == token; h--) {
				size++;
			}
		}
		return size;
	}

}
