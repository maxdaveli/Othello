package ca.utoronto.utm.assignment1.othello;

/**
 * PlayerGreedy makes a move by considering all possible moves that the player
 * can make. Each move leaves the player with a total number of tokens.
 * getMove() returns the first move which maximizes the number of
 * tokens owned by this player. In case of a tie, between two moves,
 * (row1,column1) and (row2,column2) the one with the smallest row wins. In case
 * both moves have the same row, then the smaller column wins.
 * 
 * Example: Say moves (2,7) and (3,1) result in the maximum number of tokens for
 * this player. Then (2,7) is returned since 2 is the smaller row.
 * 
 * Example: Say moves (2,7) and (2,4) result in the maximum number of tokens for
 * this player. Then (2,4) is returned, since the rows are tied, but (2,4) has
 * the smaller column.
 * 
 * See the examples supplied in the assignment handout.
 * 
 * @author arnold
 *
 */

public class PlayerGreedy {
	private Othello othello;
	private char player;
	private OthelloBoard othelloBoard;

	/**
	 * Constructs a PlayerGreedy object for an instance of an Othello game and a player.
	 *
	 * @param othello the instance of the Othello game the player is playing in
	 * @param player the character representing the player ('X' or 'O')
	 */
	public PlayerGreedy(Othello othello, char player) {
		this.othello = othello;
		this.player = player;
	}

	/**
	 * Returns a copy of the current Othello board.
	 * This method is useful for simulating moves without altering the real game board.
	 *
	 * @return a copy of the current OthelloBoard
	 */
	public OthelloBoard getCopy() {
		return this.othello.board.copy();
	}

	/**
	 * Determines the best move for the greedy player by maximizing the token count. The method considers
	 * all the possible moves for the player, and evaluates each move's resulting tokens, and returns the
	 * move that results in the maximum of tokens. If multiple moves result in the same token count, then
	 * the move with the smallest row is chosen. If the rows are tied, then the move with the smallest
	 * column is chosen.
	 *
	 * @return the Move object representing the best move for this player, or null if no valid moves are found
	 */
	public Move getMove() {
		int max_tokens = -1;
		Move best = null;

		for (int row = 0; row < othello.DIMENSION; row ++) {
			for (int col = 0; col < othello.DIMENSION; col ++) {
				// create a copy of the current othello board by calling the copy method from OthelloBoard
				OthelloBoard boardCopy = getCopy();
				// checks if it's a valid move
				if (boardCopy.move(row, col, player)) {
					int tokens = boardCopy.getCount(player);
					// count the tokens after the move
					if (tokens > max_tokens) {
						max_tokens = tokens;
						best = new Move(row, col);
						// If this move gives more tokens, it's the new best move
					} else if (tokens == max_tokens) {
						// compare rows and columns
						if (row < best.getRow()) {
							best = new Move(row, col);
						} else if (row == best.getRow() && col < best.getCol()) {
							best = new Move(row, col);
						}
					}
				}
			}
		}
		return best;
	}
}
