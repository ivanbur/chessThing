package chessThing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Game implements MouseListener {

	public final static int TILE_SIZE = 64;

	public final static int ROWS = 8;
	public final static int COLUMNS = 8;

	public final static int WINDOW_WIDTH = ROWS * TILE_SIZE;
	public final static int WINDOW_HEIGHT = COLUMNS * TILE_SIZE;

	public static Tile[][] tiles = new Tile[ROWS][COLUMNS];
	public static Piece[] pieces = new Piece[32];

	public static int playerColor = 0; // 0: white, 1: black

	public static void main(String[] args) {
		new Game();
	}

	public Game() {
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLUMNS; c++) {
				tiles[r][c] = new Tile(c * TILE_SIZE, r * TILE_SIZE, r, c, (r + c) % 2);
			}
		}

		for (Tile[] i : tiles) {
			for (Tile x : i) {
				System.out.println(x);
			}
		}
	}

	public void highlightPiece(Piece p) {

	}

	public void unHighlightTiles() {
		for (Tile[] tArray : tiles) {
			for (Tile t : tArray) {
				if (t.getHighlighted()) {
					t.setHighlighted(false);
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int mouseRow = -1;
		int mouseColumn = -1;
		boolean pieceHighlighted = false;
		Piece thePiece = new Piece(0, 0, -1, -1, 0);

		for (int r = 0; r < tiles.length; r++) {
			for (int c = 0; c < tiles[r].length; c++) {
				if (c != tiles[r].length - 1) {
					if (e.getX() > tiles[r][c].getX() && e.getX() < tiles[r][c + 1].getX()) {
						mouseColumn = tiles[r][c].getColumn();
					}
				} else {
					if (mouseColumn == -1) {
						mouseColumn = COLUMNS - 1;
					}
				}

				if (r != tiles.length - 1) {
					if (e.getX() > tiles[r][c].getY() && e.getX() < tiles[r + 1][c].getY()) {
						mouseRow = tiles[r][c].getRow();
					}
				} else {
					if (mouseRow == -1) {
						mouseRow = ROWS - 1;
					}
				}

				if (mouseRow != -1 && mouseColumn != -1) {
					for (Piece p : pieces) {
						if (p.getColumn() == mouseColumn && p.getRow() == mouseRow && p.getValue() / 6 == playerColor) {
							if (!pieceHighlighted) {
								highlightPiece(p);
								pieceHighlighted = true;
								thePiece = p;
								break;
							}

							if (pieceHighlighted) {
								unHighlightTiles();
								pieceHighlighted = false;
								thePiece = new Piece(0, 0, -1, -1, 0);
								break;
							}
						}

					}

					for (Tile[] tArray : tiles) {
						for (Tile t : tArray) {
							if (t.getHighlighted() && t.getColumn() == mouseColumn && t.getRow() == mouseRow) {
								unHighlightTiles();
								thePiece.move(mouseRow, mouseColumn);
							}
						}
					}

					break;
				}
			}
		}

		System.out.println("Mouse Row: " + mouseRow + ", Mouse Column: " + mouseColumn);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
