package ca.utoronto.utm.assignment1.othello;
/**
 * Represents a move in the Othello game, consisting of a row and a column on the board.
 * This class gets the position of a move and provides methods to retrieve the row and the column.
 * Each move is identified by its row and column, where both values are integers corresponding to positions
 * on the Othello board. This class provides access to the moves row and column, and a way to
 * represent the move as a string.
 *
 *
 * @author Yichen
 *
 */
public class Move {
	private int row, col;


	/**
	 * Constructs a Move object with a given row and column
	 *
	 * @param row the row of the move on the board
	 * @param col the column of the move on the board
	 */
	public Move(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 * Returns the row of this move
	 *
	 * @return the row value as an integer
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Returns the column of this move
	 *
	 * @return the column value as an integer
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Returns a string representation of this move in the format (row, col)
	 *
	 * @return a string representation of the move
	 */
	public String toString() {
		return "(" + this.row + "," + this.col + ")";
	}
}
