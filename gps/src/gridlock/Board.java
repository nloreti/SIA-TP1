package gridlock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
	
	private Position blueBlock;
	//TODO: EL TAM ES COMUN A TODOS LOS TABLEROS DEL NIVEL, ESTO HAY QUE SACARLO PARA FUERA
	private int size = 6;
	private int[][] board; 
	//TODO: LOS TAMANIOS SON COMUNES A TODOS LOS TABLEROS DEL NIVEL, ESTO HAY QUE SACARLO PARA FUERA
	Map<Integer,Integer> blocks = new HashMap<Integer,Integer>();
	
	public Board(int[][] board) {
		this.board = board;
		blueBlock = this.getBlueBlockPosition();
		saveBlocks();
	}
	
	public void saveBlocks() {
		int size;
		for(int i = 0; i < this.size; i++) {
			for(int j=0; j < this.size; j++) {
					int token = board[i][j];
					if( !blocks.containsKey(token)) {
						if (isVertical(token)) {
							size = getVTokenSize(token, i,j);
						}else {
							size = getHTokenSize(token, i,j);
						}
						blocks.put(token, size);
					}
			}
		}
	}
	
	private int getHTokenSize(int token, int i, int j) {
		int size = 1;
		if ( j < 5 && board[i][j+1] == token) {
			for(int h=j+1;  h < this.size && board[i][h] == token; h++) {
				size++;
			}
		}else {
			for(int h=j-1;  h >= 0 && board[i][h] == token; h--) {
				size++;
			}
		}
		return size;
	}

	private int getVTokenSize(int token, int i, int j) {
		int size = 1;
		if ( i < 5 && board[i+1][j] == token) {
			for(int h=i+1;  h < this.size && board[h][j] == token; h++) {
				size++;
			}
		}else {
			for(int h=i-1;  h > 0 && board[h][j] == token; h--) {
				size++;
			}
		}
		return size;
	}

	public List<Board> getAllPosibleBoards(){
		List<Board> ans = new ArrayList<Board>();
		for(int i = 0; i < this.size; i++) {
			for(int j=0; j < this.size; j++) {
				if(board[i][j] == '.'){
					Board temp;
					temp = checkUP(i,j);
					if (temp != null) {
						ans.add(temp);
					}
					temp = checkDOWN(i,j);
					if (temp != null) {
						ans.add(temp);
					}
					temp = checkLEFT(i,j);
					if (temp != null) {
						ans.add(temp);
					}
					temp = checkRIGHT(i,j);
					if (temp != null) {
						ans.add(temp);
					}
							
				}
			}
		}
		for (Board board : ans) {
			board.printBoard();
		}
		return ans;
	}

	private Board checkRIGHT(int i, int j) {
		
	//	System.out.println("x:" + i + "y: " + j);
		if ( j==5 ) {
			return null;
		}
		int[][] ans = new int[this.size][this.size];
		copyBoard(ans,this.board);
		int rightToken = board[i][j+1];
		int size,token,distance,k;
	//	System.out.println("RIGTH TOKEN: " + (char)rightToken);
		if ( isVertical(rightToken) ) {
	//		System.out.println("ENTRO A VERTICAL");
			return null;
		}
		else if ( isHorizontal(rightToken) ) {
			distance = 1;
			size = getHTokenSize(rightToken, i, j+1);
		//	System.out.println("SIZE: " + size);
			for ( k = j; size>0; k++, size--) {
				ans[i][k] = rightToken;
			}
			for( ; distance > 0 && k < this.size; distance--, k++) {
				ans[i][k] = '.';
			}
		}else {
			int h;
			for(h = j+1; h < this.size; h++) {
				token = board[i][h];
				if (isVertical(token)) {
					return null;
				}
				if( isHorizontal(token)) {
					distance = h-j;
					size = getHTokenSize(token, i, h);
					for ( k = j; size>0; k++, size--) {
						ans[i][k] = token;
					}
					for( ; distance > 0 && k < this.size; distance--, k++) {
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

	private	Board checkLEFT(int i, int j) {

		if ( j==0 ) {
			return null;
		}
		int[][] ans = new int[this.size][this.size];
		copyBoard(ans,this.board);
		int leftToken = board[i][j-1];
		int size,token,distance,k;
		//System.out.println("LEFT TOKEN: " + (char)leftToken);
		if ( isVertical(leftToken) ) {
		//	System.out.println("ENTRO A VERTICAL");
			return null;
		}
		else if ( isHorizontal(leftToken) ) {
			distance = 1;
			size = getHTokenSize(leftToken, i, j-1);
		//	System.out.println("SIZE: " + size);
			for ( k = j; size>0; k--, size--) {
				ans[i][k] = leftToken;
			}
			for( ; distance > 0 && k >= 0; distance--, k--) {
				ans[i][k] = '.';
			}
		}else {
			int h;
			for(h = j-1; h > 0; h--) {
				token = board[i][h];
				if (isVertical(token)) {
					return null;
				}
				if( isHorizontal(token)) {
					distance = j-h;
					size = getHTokenSize(token, i, h);
					for ( k = j; size>0; k--, size--) {
						ans[i][k] = token;
					}
					for( ; distance > 0; distance--, k--) {
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

	private void copyBoard(int[][] tempBoard, int[][] board2) {
		
		for(int i = 0; i < board2.length ; i++) {
			for(int j = 0; j <board2.length; j++) {
				tempBoard[i][j] = board2[i][j];
			}
		}
		
	}

	private Board checkDOWN(int i, int j) {
		if ( i==5 ) {
			return null;
		}
		int[][] ans = new int[this.size][this.size];
		copyBoard(ans,this.board);
		int downToken = board[i+1][j];
		int size,token,distance,k;
		//System.out.println("DOWN TOKEN: " + (char)downToken);
		if ( isHorizontal(downToken) ) {
		//	System.out.println("ENTRO A HORIZONTAL");
			return null;
		}
		else if ( isVertical(downToken) ) {
			distance = 1;
			size = getVTokenSize(downToken, i+1, j);
	//		System.out.println("SIZE: " + size);
			for ( k = i; size>0; k++, size--) {
				ans[k][j] = downToken;
			}
			for( ; distance > 0 && k < this.size; distance--, k++) {
				ans[k][j] = '.';
			}
		}else {
			int h;
			for(h = i+1; h < this.size; h++) {
				token = board[h][j];
				if (isHorizontal(token)) {
					return null;
				}
				if( isVertical(token)) {
		//			System.out.println("TOKEN if: " + (char)token);
					distance = h-i;
					size = getVTokenSize(token, h, j);
			//		System.out.println("SIZE " + size);
					for ( k = i; size>0; k++, size--) {
						ans[k][j] = token;
					}
					for( ; distance > 0 && k<this.size; distance--, k++) {
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

	private Board checkUP(int i, int j) {
		if ( i==0 ) {
			return null;
		}
		int[][] ans = new int[this.size][this.size];
		copyBoard(ans,this.board);
		int upToken = board[i-1][j];
		int size,token,distance,k;
	//	System.out.println("UP TOKEN: " + (char)upToken);
		if ( isHorizontal(upToken) ) {
	//		System.out.println("ENTRO A HORIZONTAL");
			return null;
		}
		else if ( isVertical(upToken) ) {
			distance = 1;
			size = getVTokenSize(upToken, i-1, j);
	//		System.out.println("SIZE: " + size);
			for ( k = i; size>0; k--, size--) {
				ans[k][j] = upToken;
			}
			for( ; distance > 0 && k > 0; distance--, k--) {
				ans[k][j] = '.';
			}
		}else {
			int h;
			for(h = i-1; h > 0; h--) {
				token = board[h][j];
				if (isHorizontal(token)) {
					return null;
				}
				if( isVertical(token)) {
		//			System.out.println("TOKEN if: " + (char)token);
					distance = i-h;
					size = getVTokenSize(token, h, j);
		//			System.out.println("SIZE " + size);
					for ( k = i; size>0; k--, size--) {
						ans[k][j] = token;
					}
					for( ; distance > 0 && k>0; distance--, k--) {
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

	public Position getBlueBlockPosition() {
		Position ans = null;
		for(int i = 0; i < size; i++) {
			for(int j=0; j < size; j++) {
				if(board[i][j] == '0'){
					ans = new Position(i, j);
					break;
				}
			}
		}
		return ans;
	}
	
	public boolean isResolved() {
		int pos_x = blueBlock.getX();
		boolean ans = true;
		for(int j = 0; j< size; j++) {
			if(board[pos_x][j] != '.'){
				ans = false;
				break;
			}
		}
		return ans;
	}
	
	public Position getBlueBlock() {
		return blueBlock;
	}

	public void setBlueBlock(Position blueBlock) {
		this.blueBlock = blueBlock;
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
	
	public void printBoard() {
		System.out.println("Printing Board");
		for(int i = 0; i < size; i++) {
			for(int j=0; j < size; j++) {
				System.out.print((char)(board[i][j]) + " ");
				
			}
			
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		int[][] matrix = new int[6][6];
		matrix[0][0] = '1';
		matrix[0][1] = '.';
		matrix[0][2] = '.';
		matrix[0][3] = 'a';
		matrix[0][4] = 'a';
		matrix[0][5] = 'a';
		matrix[1][0] = '1';
		matrix[1][1] = '.';
		matrix[1][2] = '.';
		matrix[1][3] = '2';
		matrix[1][4] = '.';
		matrix[1][5] = '4';
		matrix[2][0] = '0';
		matrix[2][1] = '0';
		matrix[2][2] = '.';
		matrix[2][3] = '2';
		matrix[2][4] = '3';
		matrix[2][5] = '4';
		matrix[3][0] = 'b';
		matrix[3][1] = 'b';
		matrix[3][2] = 'b';
		matrix[3][3] = '.';
		matrix[3][4] = '3';
		matrix[3][5] = '4';
		matrix[4][0] = '.';
		matrix[4][1] = '.';
		matrix[4][2] = '5';
		matrix[4][3] = '.';
		matrix[4][4] = 'c';
		matrix[4][5] = 'c';
		matrix[5][0] = 'd';
		matrix[5][1] = 'd';
		matrix[5][2] = '5';
		matrix[5][3] = 'e';
		matrix[5][4] = 'e';
		matrix[5][5] = '.';
		
		Board board = new Board(matrix);
		board.printBoard();
		board.getAllPosibleBoards();
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(board);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (!Arrays.deepEquals(board, other.board))
			return false;
		return true;
	}
	
	

}
