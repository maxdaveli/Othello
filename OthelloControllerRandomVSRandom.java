package ca.utoronto.utm.assignment1.othello;

/**
 * Determine whether the first player or second player has the advantage when
 * both are playing a Random Strategy.
 * 
 * Do this by creating two players which use a random strategy and have them
 * play each other for 10000 games. What is your conclusion, does the first or
 * second player have some advantage, at least for a random strategy? 
 * State the null hypothesis H0, the alternate hypothesis Ha and 
 * about which your experimental results support. Place your short report in
 * randomVsRandomReport.txt.
 * 
 * @author arnold
 *
 */
public class OthelloControllerRandomVSRandom {
	/**
	 * Run main to execute the simulation and print out the two line results.
	 * Output looks like 
	 * Probability P1 wins=.75 
	 * Probability P2 wins=.20
	 * @param args
	 */
	public static void main(String[] args) {

		int p1wins = 0, p2wins = 0, numGames = 10000;

		for (int i = 0; i < numGames; i ++) {
			Othello othello = new Othello();
			PlayerRandom player1 = new PlayerRandom(othello, OthelloBoard.P1);
			PlayerRandom player2 = new PlayerRandom(othello, OthelloBoard.P2);

			while (!othello.isGameOver()) {

				Move move = null;
				char whosTurn = othello.getWhosTurn();

				if (whosTurn == OthelloBoard.P1) {
					move = player1.getMove();
				} else if (whosTurn == OthelloBoard.P2) {
					move = player2.getMove(); }

				if (move != null) {
					othello.move(move.getRow(), move.getCol());
				} else {
					othello.move(-1, -1);
				}
			}
			System.out.println(i);
			char gamewinner = othello.getWinner();
			if (gamewinner == OthelloBoard.P1) {
				p1wins++;
			} else if (gamewinner == OthelloBoard.P2) {
				p2wins++;
			}
		}
		System.out.println("Probability P1 wins=" + (float) p1wins / numGames);
		System.out.println("Probability P2 wins=" + (float) p2wins / numGames);
	}
}
