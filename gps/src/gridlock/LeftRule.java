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

		Token[][] ans = new Token[Board.SIZE][Board.SIZE];
		BoardUtils.copyBoard(ans, board);
		BoardUtils.checkCorrect(ans);

		int size, tokenValue, distance, k;
		int i = token.getI();
		int j = token.getJ();

		if (j == 0) {
			return null;
		}

		Token leftToken = board[i][j - 1];
		if (BoardUtils.isVertical(leftToken.getValue())) {
			return null;
		} else if (BoardUtils.isHorizontal(leftToken.getValue())) {
			size = BoardUtils.getHTokenSize(board, leftToken.getValue(), i,
					j - 1);
			Token aux = ans[i][j - size];
			ans[i][j - size] = token;
			ans[i][j] = aux;
		} else {
			int h;
			for (h = j - 1; h >= 0; h--) {
				tokenValue = board[i][h].getValue();
				if (BoardUtils.isVertical(tokenValue)) {
					return null;
				}
				if (BoardUtils.isHorizontal(tokenValue)) {
					distance = j - h;
					size = BoardUtils.getHTokenSize(board, tokenValue, i, h);
					for (k = j; size > 0; k--, size--) {
						Token aux = ans[i][k - distance];
						ans[i][k - distance] = ans[i][k];
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

}
