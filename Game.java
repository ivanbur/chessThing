package chessThing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game implements ActionListener, MouseListener
{
	public static final int ROWS = 8;
	public static final int COLUMNS = 8;
	public static final int TILE_SIZE = 64;
	public static final int WINDOW_WIDTH = COLUMNS * TILE_SIZE + 6;
	public static final int WINDOW_HEIGHT = ROWS * TILE_SIZE + 26;

	public static Tile[][] tiles = new Tile[ROWS][COLUMNS];
	public static JLabel[][] tileLabels = new JLabel[ROWS][COLUMNS];
	public static Piece[] pieces = new Piece[32];
	public static JLabel[] pieceLabels = new JLabel[pieces.length];

	public static int playerColor = 0; // 0: white, 1: black

	public static JFrame frame = new JFrame();

	private JButton playButton = new JButton();

	public static void main(String[] args)
	{
		new Game();
	}

	public Game()
	{
		setUpFrame();
		setUpMainMenu();

		//setUpGameBoard();
	}

	private void setUpFrame()
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.setTitle("Chess Thing");
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setFocusable(true);

		frame.setVisible(true);
		frame.addMouseListener(this);
	}

	private void setUpMainMenu()
	{
		playButton.setSize(TILE_SIZE, TILE_SIZE);
		playButton.setLocation((WINDOW_WIDTH / 2) - (playButton.getWidth() / 2),
					(WINDOW_HEIGHT / 2) - (playButton.getHeight() / 2));
		playButton.setText("PLAY");
		playButton.setActionCommand("pressed play");
		playButton.addActionListener(this);

		frame.add(playButton);
	}

	private void getRidOfMainMenu()
	{
		playButton.setVisible(false);
	}

	private void setUpGameBoard()
	{
		// pawns
		for (int p = 0; p < 16; p++)
		{
			if (p < 8)
			{
				pieces[p] = new Piece(p * TILE_SIZE, (ROWS - 2) * TILE_SIZE, ROWS - 2, p, 0);
			}
			else
			{
				pieces[p] = new Piece(p * TILE_SIZE, TILE_SIZE, 1, p, 6);
			}
		}

		// bishops
		pieces[16] = new Piece(2 * TILE_SIZE, (ROWS - 1) * TILE_SIZE, ROWS - 1, 2, 1);
		pieces[17] = new Piece(5 * TILE_SIZE, (ROWS - 1) * TILE_SIZE, ROWS - 1, 5, 1);
		pieces[18] = new Piece(2 * TILE_SIZE, 0, 0, 2, 7);
		pieces[19] = new Piece(5 * TILE_SIZE, 0, 0, 5, 7);

		// knights
		pieces[20] = new Piece(TILE_SIZE, (ROWS - 1) * TILE_SIZE, ROWS - 1, 1, 2);
		pieces[21] = new Piece(6 * TILE_SIZE, (ROWS - 1) * TILE_SIZE, ROWS - 1, 6, 2);
		pieces[22] = new Piece(TILE_SIZE, 0, 0, 1, 8);
		pieces[23] = new Piece(6 * TILE_SIZE, 0, 0, 6, 8);

		// rooks
		pieces[24] = new Piece(0, (ROWS - 1) * TILE_SIZE, ROWS - 1, 0, 3);
		pieces[25] = new Piece(7 * TILE_SIZE, (ROWS - 1) * TILE_SIZE, ROWS - 1, 7, 3);
		pieces[26] = new Piece(0, 0, 0, 0, 9);
		pieces[27] = new Piece(7 * TILE_SIZE, 0, 0, 7, 9);

		// queens
		pieces[28] = new Piece(4, (ROWS - 1) * TILE_SIZE, ROWS - 1, 4, 4);
		pieces[29] = new Piece(4, 0, 0, 4, 10);

		// kings
		pieces[30] = new Piece(5, (ROWS - 1) * TILE_SIZE, ROWS - 1, 5, 5);
		pieces[31] = new Piece(5, 0, 0, 5, 11);

		// labels
		for (int i = 0; i < pieces.length; i++)
		{
			ImageIcon pieceImage = new ImageIcon(getClass().getResource("piece" + pieces[i].getValue() + ".png"));
			pieceLabels[i] = new JLabel(pieceImage);
			pieceLabels[i].setLocation(pieces[i].getX(), pieces[i].getY());
			pieceLabels[i].setSize(TILE_SIZE, TILE_SIZE);
			pieceLabels[i].setVisible(true);

			frame.add(pieceLabels[i]);
		}

		// tiles
		for (int r = 0; r < ROWS; r++)
		{
			for (int c = 0; c < COLUMNS; c++)
			{
				tiles[r][c] = new Tile(c * TILE_SIZE, r * TILE_SIZE, r, c, (r + c) % 2);

				tileLabels[r][c] = new JLabel();

				tileLabels[r][c].setSize(TILE_SIZE, TILE_SIZE);
				tileLabels[r][c].setLocation(tiles[r][c].getX(), tiles[r][c].getY());
				tileLabels[r][c].setOpaque(true);

				if (tiles[r][c].getColor() == 0)
					tileLabels[r][c].setBackground(Color.WHITE);
				else if (tiles[r][c].getColor() == 1)
					tileLabels[r][c].setBackground(Color.BLACK);

				tileLabels[r][c].setVisible(true);

				frame.add(tileLabels[r][c]);
			}
		}

		frame.repaint();
	}

	public void highlightPiece(Piece p)
	{
		ArrayList<int[]> possiblePositions = p.getPossiblePositions();
//		p.setHighlighted(true);
		
		possiblePositions.forEach((i) -> tiles[i[0]][i[1]].setHighlighted(true));
		
		p.setHighlighted(true);
	}

	public void unHighlightTiles()
	{
		for (Tile[] tArray : tiles)
		{
			for (Tile t : tArray)
			{
				if (t.isHighlighted())
				{
					t.setHighlighted(false);
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (!playButton.isVisible())
		{
			int mouseRow = -1;
			int mouseColumn = -1;

			for (int r = 0; r < tiles.length; r++)
			{
				for (int c = 0; c < tiles[r].length; c++)
				{
					if (c != tiles[r].length - 1)
					{
						if (e.getX() > tiles[r][c].getX() && e.getX() < tiles[r][c + 1].getX())
						{
							mouseColumn = tiles[r][c].getColumn();
						}
					}
					else
					{
						if (mouseColumn == -1)
						{
							mouseColumn = COLUMNS - 1;
						}
					}

					if (r != tiles.length - 1)
					{
						if (e.getY() > tiles[r][c].getY() && e.getY() < tiles[r + 1][c].getY())
						{
							mouseRow = tiles[r][c].getRow();
						}
					}
					else
					{
						if (mouseRow == -1)
						{
							mouseRow = ROWS - 1;
						}
					}

					if (mouseRow != -1 && mouseColumn != -1)
					{
						for (Piece p : pieces)
						{
							if (p.getColumn() == mouseColumn && p.getRow() == mouseRow && p.getValue() / 6 == playerColor)
							{
								if (!p.isHighlighted())
								{
									highlightPiece(p);
									break;
								}

								if (p.isHighlighted())
								{
									unHighlightTiles();
									p.setHighlighted(false);
									break;
								}
							}

						}

						for (Tile[] tArray : tiles)
						{
							for (Tile t : tArray)
							{
								if (t.isHighlighted() && t.getColumn() == mouseColumn && t.getRow() == mouseRow)
								{
									for (int i = 0; i < pieces.length; i++) {
										if (pieces[i].isHighlighted()) {
											pieces[i].move(mouseRow, mouseColumn);
										}
									}
									
									unHighlightTiles();
								}
							}
						}

						break;
					}
				}
			}

			System.out.println("Mouse Row: " + mouseRow + ", Mouse Column: " + mouseColumn);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String message = e.getActionCommand();

		if (message.equals("pressed play"))
		{
			getRidOfMainMenu();
			setUpGameBoard();
		}
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}
}
