package gridlock;

import utils.BoardUtils;
import api.GPSRule;
import api.GPSState;
import exception.NotAppliableException;

public class DownRule implements GPSRule {

	Token token;

	public DownRule(Token token) {
		this.token = token;
	}

	@Override
	public GPSState evalRule(GPSState state) throws NotAppliableException {
		GPSState ans = null;
		Board board = ((GridLockState) state).getBoard();
		Token[][] rawBoard = board.getRawBoard();

		Board temp;
		temp = checkDOWN(rawBoard);

		if (temp != null) {
			ans = new GridLockState(temp);
		}

		return ans;
	}

	public Board checkDOWN(Token[][] board) {

		Token[][] ans = new Token[Board.SIZE][Board.SIZE];
		BoardUtils.copyBoard(ans, board);
		BoardUtils.checkCorrect(ans);
		int size, tokenValue, distance, k;
		int i = token.getI();
		int j = token.getJ();
		if (i == Board.LAST_ROW) {
			return null;
		}
		Token downToken = board[i + 1][j];
		if (BoardUtils.isHorizontal(downToken.getValue())) {
			return null;
		} else if (BoardUtils.isVertical(downToken.getValue())) {
			size = BoardUtils.getVTokenSize(board, downToken.getValue(), i + 1,
					j);
			Token aux = ans[i + size][j];
			ans[i + size][j] = token;
			ans[i][j] = aux;
		} else {
			int h;
			for (h = i + 1; h < board.length; h++) {
				tokenValue = board[h][j].getValue();
				if (BoardUtils.isHorizontal(tokenValue)) {
					return null;
				}
				if (BoardUtils.isVertical(tokenValue)) {
					distance = h - i;
					size = BoardUtils.getVTokenSize(board, tokenValue, h, j);
					for (k = i; size > 0; k++, size--) {
						Token aux = ans[k + distance][j];
						ans[k + distance][j] = ans[k][j];
						ans[k][j] = aux;
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

}
