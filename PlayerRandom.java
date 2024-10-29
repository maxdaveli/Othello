package ca.utoronto.utm.assignment1.othello;

import java.util.ArrayList;
import java.util.Random;

/**
 * PlayerRandom makes a move by first determining all possible moves that this
 * player can make, putting them in an ArrayList, and then randomly choosing one
 * of them.
 * 
 * @author arnold
 *
 */
public class PlayerRandom {
	private Othello othello;
	private char player;
	private Random rand = new Random();

	/**
	 * Constructs a PlayerRandom object for the specific Othello game and player.
	 *
	 * @param othello the Othello game that this player is in.
	 * @param player the character representing the player ('X' or 'O')
	 */
	public PlayerRandom(Othello othello, char player) {
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
	 * Determines all possible moves for the random player, and store them in an ArrayList, and randomly
	 * selects one move to return. The method checks each position on the board to see if a player can make
	 * a valid move, and simulates the move on the copied board, and adds the valid move to a list. If the
	 * list contains valid moves, one is randomly selected and returned. If no valid move exists, then it returns
	 * null.
	 * @return the randomly selected Move object, or null if no valid moves are available
	 */
	public Move getMove() {
		ArrayList<Move> all_possible_moves = new ArrayList<>();

		for (int row = 0; row < othello.DIMENSION; row++) {
			for (int col = 0; col < othello.DIMENSION; col++) {
				OthelloBoard boardCopy = getCopy();
				if (boardCopy.move(row, col, this.player)) {
					Move new_move = new Move(row, col);
					all_possible_moves.add(new_move);
				}
			}
		}

		if (!all_possible_moves.isEmpty()) {
			int index = rand.nextInt(all_possible_moves.size());
			return all_possible_moves.get(index);
		}

		return null;
	}
}
