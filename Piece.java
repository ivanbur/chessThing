package chessThing;

import java.util.ArrayList;

public class Piece
{
	private int x, y, row, column, value;
	private boolean highlighted = false;

	public Piece(int x, int y, int row, int column, int value)
	{
		this.x = x;
		this.y = y;
		this.row = row;
		this.column = column;
		this.value = value;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public int getRow()
	{
		return row;
	}

	public int getColumn()
	{
		return column;
	}

	public boolean isHighlighted()
	{
		return highlighted;
	}

	public void setHighlighted(boolean set)
	{
		highlighted = set;
	}

	public void move(int row, int column)
	{
		this.row = row;
		this.column = column;
	}

	public ArrayList<int[]> getPossiblePositions()
	{
		ArrayList<int[]> possiblePositions = new ArrayList<int[]>();

		if (value == 0)
		{

		}

		int[] testing = {0, 1};

		possiblePositions.add(testing);

		return possiblePositions;
	}

	// value:
	// 0:  white pawn
	// 1:  white bishop
	// 2:  white knight
	// 3:  white rook
	// 4:  white queen
	// 5:  white king
	// 6:  black pawn
	// 7:  black bishop
	// 8:  black knight
	// 9:  black rook
	// 10: black queen
	// 11: black king
	public int getValue()
	{
		return value;
	}

	public int getColor()
	{
		return value;
	}

	public void draw()
	{

	}
}
