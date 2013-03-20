package gridlock;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import utils.BoardUtils;

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
	
	public Map<Integer,Integer> getBlocks() {
		return blocks;
	}
	
	public void saveBlocks() {
		int size;
		for(int i = 0; i < this.size; i++) {
			for(int j=0; j < this.size; j++) {
					int token = board[i][j];
					if( !blocks.containsKey(token)) {
						if (BoardUtils.isVertical(token)) {
							size = BoardUtils.getVTokenSize(board, token, i,j);
						}else {
							size = BoardUtils.getHTokenSize(board, token, i,j);
						}
						blocks.put(token, size);
					}
			}
		}
	}
	
	public int getSize() {
		return this.size;
	}
	
	public int[][] getRawBoard(){
		return this.board;
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
		for(int i=0; i<board.length; i++) {
			if( board[i][5] == '0') {
				return true;
			}
		}
		return false;
	}
	
	public Position getBlueBlock() {
		return blueBlock;
	}

	public void setBlueBlock(Position blueBlock) {
		this.blueBlock = blueBlock;
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

	@Override
	public String toString() {
		String ans = "Board\n";
		for(int i = 0; i < size; i++) {
			for(int j=0; j < size; j++) {
				ans += ((char)(board[i][j]) + " ");
				
			}
			
			ans += "\n";
		}
		return ans;
	}
	
	public int getBlock2Exit() {
		int blocks = 0;
		for(int j=5;board[2][j] != '0';j--) {
			
			int token = board[2][j];
		//	printBoard();
		//	System.out.println("T: " + (char)token + " I:" + blueBlock.getX() + " J:" + j);
			if ( token != '.' && token != 0) {
				blocks++;
			}
		}
		///System.out.println("Blocks " + blocks);
		return blocks;
	}
	
	

}
