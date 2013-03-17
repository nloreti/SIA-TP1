package gridlock;

public class Block {

	private int id;
	private int pos_x;
	private int pos_y;
	private int lenght;
	private Orientation orientation;
	
	public Block(int id, int pos_x, int pos_y, int lenght,
			Orientation orientation) {
		super();
		this.id = id;
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.lenght = lenght;
		this.orientation = orientation;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPos_x() {
		return pos_x;
	}
	public void setPos_x(int pos_x) {
		this.pos_x = pos_x;
	}
	public int getPos_y() {
		return pos_y;
	}
	public void setPos_y(int pos_y) {
		this.pos_y = pos_y;
	}
	public int getLenght() {
		return lenght;
	}
	public void setLenght(int lenght) {
		this.lenght = lenght;
	}
	public Orientation getOrientation() {
		return orientation;
	}
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
	
	
}
