package chessThing;

import java.awt.Color;

import javax.swing.JFrame;

public class Tile
{
	private int x, y, row, column, value;
	private boolean highlighted = false;

	public Tile(int x, int y, int row, int column, int value)
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

	public void setHighlighted(boolean set)
	{
		highlighted = set;

		if (set)
		{
			Game.tileLabels[row][column].setBackground(Color.YELLOW);
		}
		else if (value == 0)
		{
			Game.tileLabels[row][column].setBackground(Color.WHITE);
		}
		else
		{
			Game.tileLabels[row][column].setBackground(Color.BLACK);
		}
	}

	public boolean isHighlighted()
	{
		return highlighted;
	}

	// value is 0 if white and 1 is black
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

	public String toString()
	{
		if (value == 0)
		{
			return "Color: white, Row: " + row + ", Column: " + column + ", X: " + x + ", Y: " + y;
		}
		else if (value == 1)
		{
			return "Color: black, Row: " + row + ", Column: " + column + ", X: " + x + ", Y: " + y;
		}

		return "";
	}
}
