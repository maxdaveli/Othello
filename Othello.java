package ca.utoronto.utm.assignment1.othello;

import java.util.Random;

/**
 * Capture an Othello game. This includes an OthelloBoard as well as knowledge
 * of how many moves have been made, whosTurn is next (OthelloBoard.P1 or
 * OthelloBoard.P2). It knows how to make a move using the board and can tell
 * you statistics about the game, such as how many tokens P1 has and how many
 * tokens P2 has. It knows who the winner of the game is, and when the game is
 * over.
 * 
 * See the following for a short, simple introduction.
 * https://www.youtube.com/watch?v=Ol3Id7xYsY4
 * 
 * @author arnold
 *
 */
public class Othello {
	public static final int DIMENSION = 8; // This is an 8x8 game
	private char whosTurn = OthelloBoard.P1; // P1 moves first!
	private int numMoves = 0;
	public OthelloBoard board = new OthelloBoard(DIMENSION);

	/**
	 * return P1,P2 or EMPTY depending on who moves next.
	 *
	 * @return P1, P2 or EMPTY
	 */
	public char getWhosTurn() {
		return whosTurn;
	}

	/**
	 * Attempt to make a move for P1 or P2 (depending on whos turn it is) at
	 * position row, col. A side effect of this method is modification of whos turn
	 * and the move count.z
	 *
	 * @param row
	 * @param col
	 * @return whether the move was successfully made.
	 */

	public boolean move(int row, int col)
	{
		if ((board.hasMove() == OthelloBoard.BOTH) || (board.hasMove() == OthelloBoard.P1 && whosTurn == OthelloBoard.P1) || (board.hasMove() == OthelloBoard.P2 && whosTurn == OthelloBoard.P2)) {
			if (whosTurn == OthelloBoard.P1) {
				if (board.move(row, col, OthelloBoard.P1)) {
					if (board.hasMove() == OthelloBoard.BOTH  || board.hasMove() == OthelloBoard.P2) {
						whosTurn = OthelloBoard.P2;
					}
					this.numMoves++;
					return true;
				}
			} else if (whosTurn == OthelloBoard.P2) {
				if (board.move(row, col, OthelloBoard.P2)) {
					if (board.hasMove() == OthelloBoard.BOTH  || board.hasMove() == OthelloBoard.P1) {
						whosTurn = OthelloBoard.P1;
					}
					this.numMoves++;
					return true;
				}
			}
		} else if (whosTurn == OthelloBoard.P1 && board.hasMove() != OthelloBoard.P1) {
				whosTurn = OthelloBoard.P2;
				return false;
		} else if (whosTurn == OthelloBoard.P2 && board.hasMove() != OthelloBoard.P2) {
				whosTurn = OthelloBoard.P1;
				return false;
		}
		return false;

	}

		/**
		 *
		 * @param player P1 or P2
		 * @return the number of tokens for player on the board
		 */
		public int getCount ( char player){
			if (player == OthelloBoard.P1) {
				return board.getCount(OthelloBoard.P1);
			} else if (player == OthelloBoard.P2) {
				return board.getCount(OthelloBoard.P2);
			}
			return 0;
		}

		/**
		 * Returns the winner of the game.
		 *
		 * @return P1, P2 or EMPTY for no winner, or the game is not finished.
		 */
		public char getWinner () {
			int player1 = getCount(OthelloBoard.P1);
			int player2 = getCount(OthelloBoard.P2);
			if (player1 > player2) {
				return OthelloBoard.P1;
			} else if (player2 > player1) {
				return OthelloBoard.P2;
			} else if (player1 == player2) {
				return OthelloBoard.EMPTY;
			} else
				return OthelloBoard.EMPTY;
		}

		/**
		 *
		 * @return whether the game is over (no player can move next)
		 */
		public boolean isGameOver () {
			if (board.hasMove() == OthelloBoard.EMPTY) {
				return true;
			} else
				return false;


		}

		/**
		 *
		 * @return a string representation of the board.
		 */
		public String getBoardString () {
			String s = "";
			s += "  ";
			for (int col = 0; col < board.dim; col++) {
				s += col + " ";
			}
			s += '\n';

			s += " +";
			for (int col = 0; col < board.dim; col++) {
				s += "-+";
			}
			s += '\n';

			for (int row = 0; row < board.dim; row++) {
				s += row + "|";
				for (int col = 0; col < board.dim; col++) {
					s += board.board[row][col] + "|";
				}
				s += row + "\n";

				s += " +";
				for (int col = 0; col < board.dim; col++) {
					s += "-+";
				}
				s += '\n';
			}
			s += "  ";
			for (int col = 0; col < board.dim; col++) {
				s += col + " ";
			}
			s += '\n';
			return s;
		}


		/**
		 * run this to test the current class. We play a completely random game. DO NOT
		 * MODIFY THIS!! See the assignment page for sample outputs from this.
		 *
		 * @param args
		 */
		public static void main (String[]args){

			Random rand = new Random();

			Othello o = new Othello();
			System.out.println(o.getBoardString());
			while (!o.isGameOver()) {
				int row = rand.nextInt(8);
				int col = rand.nextInt(8);

				if (o.move(row, col)) {
					System.out.println("makes move (" + row + "," + col + ")");
					System.out.println(o.getBoardString() + o.getWhosTurn() + " moves next");
				}
			}
		}
	}