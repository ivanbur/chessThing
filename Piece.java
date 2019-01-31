package chessThing;

import java.util.ArrayList;

public class Piece {
	private int x, y, row, column, value;
	private boolean highlighted = false;

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

	public boolean isHighlighted() {
		return highlighted;
	}

	public void setHighlighted(boolean set) {
		highlighted = set;
	}

	public void move(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public ArrayList<int[]> getPossiblePositions() {
		ArrayList<int[]> possiblePositions = new ArrayList<int[]>();

		if (value % 6 == 0) {
			boolean pieceInFront = false;

			for (int i = 0; i < Game.pieces.length; i++) {
				if (Game.pieces[i].getRow() == row - 1 && Game.pieces[i].getValue() / 6 != value / 6) {

					if (Game.pieces[i].getColumn() == column + 1) {
						possiblePositions.add(new int[] { row - 1, column + 1 });
					} else if (Game.pieces[i].getColumn() == column - 1) {
						possiblePositions.add(new int[] { row - 1, column - 1 });
					} else if (Game.pieces[i].getColumn() == column) {
						pieceInFront = true;
					}
				}
			}

			if (!pieceInFront) {
				possiblePositions.add(new int[] { row - 1, column });
			}
		} else if (value % 6 == 1) {
			// for (int r = row; r < Game.ROWS; r++) {
			// for (int c = column; c <)
			// }
		} else if (value % 6 == 2) {

		} else if (value % 6 == 3) {

		} else if (value % 6 == 4) {
			for (int deltaR = -1; deltaR <= 1; deltaR++) {
				for (int deltaC = -1; deltaC <= 1; deltaC++) {
					goThroughTiles(Game.ROWS - 1, deltaR, deltaC, possiblePositions);
				}
			}
		} else if (value % 6 == 5) {
			
		}

		int[] testing = { 0, 1 };

		possiblePositions.add(testing);

		return possiblePositions;
	}

	private void goThroughTiles(int maxTimes, int deltaR, int deltaC, ArrayList<int[]> possiblePositions) {
		for (int i = 1; i < maxTimes; i++) {
			if (!isTileOccupied(row + i * deltaR, column + i * deltaC)) {
				possiblePositions.add(new int[] { row + i * deltaR, column + i * deltaC });
			} else {
				break;
			}
		}
	}

	private boolean isTileOccupied(int r, int c) {
		boolean tileOccupied = false;

		if (r >= Game.ROWS || r < 0 || c >= Game.COLUMNS || c < 0) {
			return false;
		}

		for (int i = 0; i < Game.pieces.length; i++) {
			if (Game.pieces[i].getRow() == r && Game.pieces[i].getColumn() == c) {
				tileOccupied = true;
			}
		}

		return tileOccupied;
	}

	// value:
	// 0: white pawn
	// 1: white bishop
	// 2: white knight
	// 3: white rook
	// 4: white queen
	// 5: white king
	// 6: black pawn
	// 7: black bishop
	// 8: black knight
	// 9: black rook
	// 10: black queen
	// 11: black king
	public int getValue() {
		return value;
	}

	public int getColor() {
		return value;
	}

	public void draw() {

	}
}
