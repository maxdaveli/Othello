package ca.utoronto.utm.assignment1.othello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Represents a human player in the Othello game.
 * This class a human playing the game to input their moves in the console, and it reads the player's input
 * for the row and column of the board where they want to place their piece, and it checks if their input
 * is valid. The human player is represented by their assigned piece, either 'X' or 'O', and it interacts
 * with an instance of the Othello game to make the corresponding moves. If the player enters an invalid move,
 * the program will prompt them to try again, until the player enters a valid move.
 *
 *
 * @author Yichen
 *
 */
public class PlayerHuman {
	
	private static final String INVALID_INPUT_MESSAGE = "Invalid number, please enter 1-8";
	private static final String IO_ERROR_MESSAGE = "I/O Error";
	private static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

	private Othello othello;
	private char player;

	/**
	 * Constructs a PlayerHuman object with given parameters othello and a player
	 *
	 * @param othello the Othello game instance this player is playing
	 * @param player the character representing the player either 'X' or 'O'
	 */
	public PlayerHuman(Othello othello, char player) {
		
		this.othello = othello;
		this.player = player;
	}

	/**
	 * Asks the player to enter their move (row and column) and returns it as a move object
	 *
	 * @return the Move object representing the player's chosen move
	 */
	public Move getMove() {
		
		int row = getMove("row: ");
		int col = getMove("col: ");
		return new Move(row, col);
	}

	/**
	 * Asks the player to input a number for the row or the column and ensures its a valid move.
	 * It keeps asking until a valid input between 1 and 8 is provided.
	 *
	 * @param message the message to prompt the player either row or col
	 * @return the validated number for the move or -1 if an error occurs
	 */
	private int getMove(String message) {
		
		int move, lower = 0, upper = 7;
		while (true) {
			try {
				System.out.print(message);
				String line = PlayerHuman.stdin.readLine();
				move = Integer.parseInt(line);
				if (lower <= move && move <= upper) {
					return move;
				} else {
					System.out.println(INVALID_INPUT_MESSAGE);
				}
			} catch (IOException e) {
				System.out.println(INVALID_INPUT_MESSAGE);
				break;
			} catch (NumberFormatException e) {
				System.out.println(INVALID_INPUT_MESSAGE);
			}
		}
		return -1;
	}
}
