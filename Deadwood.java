/***********************
 * Deadwood Text Game  *
 ***********************/

public class Deadwood {

	private static void userOptions(){
     		System.out.println("----------------------------------------------------------------------------");
     		System.out.println("Please enter one of the following: ");
     		System.out.println();
		System.out.println("'who' = to see the current players information and any role they are working");
		System.out.println("'where' = to see your current room and any active scenes");
     		System.out.println("'move' = move to a room adjacent to your current position");
     		System.out.println("'work' = to take a role in your current room");
		System.out.println("'rehearse' = to rehearse your current role");
     		System.out.println("'act' = act out the role you are currently in");
     		System.out.println("'upgrade' = upgrade your current level in exchange for money or credit");
		System.out.println("'end' = to end your turn");
     		System.out.println("----------------------------------------------------------------------------");
	}
	
	public static void main(String[] args) {
		// Variables
		Scanner in = new Scanner(System.in);
		int numPlaying = 0;
		int currentPlayer = 0;
		// Enter number of players
		System.out.println("****************************************************************************");
      		System.out.println("                                 DEADWOOD                                   ");
      		System.out.println("****************************************************************************");
		System.out.print("Please enter the number of players (2-3): ");
		numPlaying = in.nextInt();
		System.out.println();
		// Call controller
		Controller control = startGame(numPlaying);
		// used to determine if the user input was valid or not
      		boolean valid;

      		// start the first day of the game
      		control.startDay();

      		while (day < 4){
        		valid = false;
        		userOptions();
        		while (!valid){
        			String command = in.next();
          			if (command.equals("move")){
					System.out.print("Enter the room you would like to move to: ");
					String room = in.next();
             				if (control.getPlayers().get(currentPlayer-1).move(room)) {
               					System.out.println("You have moved to " + room);
            				} else {
						System.out.println("Invalid room!");
					}
          			} else if (command.equals("work")){
					System.out.print("Enter the role you would like to take: ");
					String role = in.next();
             				if (control.getPlayers().get(currentPlayer-1).takeRole(role);) {
               					System.out.println("Role taken!");
            				} else {
						System.out.println("Invalid role!");
					}
          			} else if (command.equals("act")){
            				System.out.println("Acted!");
          			} else if (command.equals("rehearse")){
            				System.out.println("");
          			} else if (command.equals("upgrade")){
            				System.out.println("Leveled up!");
          			} else if (command.equals("who")){
            				System.out.println("");
          			} else if (command.equals("where")){
            				System.out.println("");
          			} else if (command.equals("end")){
            				System.out.println("Turn ended");
					valid = true;
          			} else {
            				System.out.println("Invalid command! please enter one of the options listed above.");
            			}
        		}
      		}
	}
/* NOTES *****************************************************
 * HELP: http://www.vogella.com/tutorials/SWT/article.html
 *************************************************************/
}
