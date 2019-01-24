package chessThing;

public class Tile {
	private int x, y, row, column, value;
	boolean highlighted = false;
	
	public Tile(int x, int y, int row, int column, int value) {
		this.x = x;
		this.y = y;
		this.row = row;
		this.column = column;
		this.value = value;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void setHighlighted(boolean set) {
		highlighted = set;
	}
	
	public boolean getHighlighted() {
		return highlighted;
	}
	
	
	// value is 0 if the tile is white and 1 if the tile is black
	public int getValue() {
		return value;
	}
	
	public int getColor() {
		return value;
	}
}
