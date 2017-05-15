/***********************
 * Deadwood Text Game  *
 ***********************/

public class Deadwood {

	public static void main(String[] args) {
		// Variables
		Scanner in = new Scanner(System.in);
		int numPlaying = 0;
		// Enter number of players
		System.out.print("Please enter the number of players (2-8): ");
		numPlaying = in.nextInt();
		// Call controller
		startGame(numPlaying);
	}

}
