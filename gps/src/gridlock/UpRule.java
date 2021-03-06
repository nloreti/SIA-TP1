package gridlock;

import utils.BoardUtils;
import api.GPSRule;
import api.GPSState;
import exception.NotAppliableException;

public class UpRule implements GPSRule {

	Token token;

	public UpRule(Token token) {
		this.token = token;
	}

	@Override
	public GPSState evalRule(GPSState state) throws NotAppliableException {
		GPSState ans = null;
		Board board = ((GridLockState) state).getBoard();
		Token[][] rawBoard = board.getRawBoard();
		Board temp;
		temp = checkUP(rawBoard);
		if (temp != null) {
			ans = new GridLockState(temp);
		}

		return ans;
	}

	public Board checkUP(Token[][] board) {
		Token[][] ans = new Token[BoardUtils.size][BoardUtils.size];
		BoardUtils.copyBoard(ans, board);
		BoardUtils.checkCorrect(ans);
		int size, tokenValue, distance, k;
		int i = token.getI();
		int j = token.getJ();
		if (i == 0) {
			return null;
		}
		Token upToken = board[i - 1][j];
		if (BoardUtils.isHorizontal(upToken.getValue())) {
			return null;
		} else if (BoardUtils.isVertical(upToken.getValue())) {

			size = BoardUtils
					.getVTokenSize(board, upToken.getValue(), i - 1, j);
			Token aux = ans[i - size][j];
			ans[i - size][j] = token;
			ans[i][j] = aux;
		} else {
			int h;
			for (h = i - 1; h >= 0; h--) {
				tokenValue = board[h][j].getValue();
				if (BoardUtils.isHorizontal(tokenValue)) {
					return null;
				}
				if (BoardUtils.isVertical(tokenValue)) {
					distance = i - h;
					size = BoardUtils.getVTokenSize(board, tokenValue, h, j);
					for (k = i; size > 0; k--, size--) {
						Token aux = ans[k - distance][j];
						ans[k - distance][j] = ans[k][j];
						ans[k][j] = aux;
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

	@Override
	public Integer getCost() {
		return 1;
	}

	@Override
	public String getName() {
		return "Se mueve arriba la pieza";
	}

}
