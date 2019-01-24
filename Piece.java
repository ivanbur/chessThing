package chessThing;

public class Piece {
	private int x, y, row, column, value;
	
	public Piece(int x, int y, int row, int column, int value) {
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
	
	public void move(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	// value:
	// 0: pawn
	// 1: bishop
	// 2: knight
	// 3: rook
	// 4: queen
	// 5: king
	public int getValue() {
		return value;
	}
	
	public int getPiece() {
		return value;
	}
}
