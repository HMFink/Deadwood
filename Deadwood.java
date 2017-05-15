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
		System.out.println();
		// Call controller
		startGame(numPlaying);
	}
/* NOTES *****************************************************************************************************
 * Command				Action
 * ***********************************************************************************************************
 * who					The software identifies the current player and any parts that the player is working.
 * Where				The software describes the current player’s room and any active scenes.
 * move (room)			The current player moves to the indicated room.
 * work (part)			The current player takes the indicated role.
 * upgrade ($ level) 	Upgrade the current player to the indicated level
 * upgrade (cr level)	Upgrade the current player to the indicated level.
 * Rehearse 			The current player rehearses
 * act 					The current player performs in its current role.
 * end					End the current player’s turn 
 * 
 * HELP: http://www.vogella.com/tutorials/SWT/article.html
 *************************************************************************************************************/
}
