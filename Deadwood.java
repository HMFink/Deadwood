/***********************
 * Deadwood Text Game  *
 ***********************/


import java.util.*;

public class Deadwood{


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
		int currentPlayer = 1;
		// Enter number of players
		System.out.println("****************************************************************************");
    System.out.println("                                 DEADWOOD                                   ");
    System.out.println("****************************************************************************");
		System.out.print("Please enter the number of players (2-3): ");
		numPlaying = in.nextInt();
		System.out.println();
		// Call controller
		Controller control = new Controller(numPlaying);
		//control.startGame(numPlaying);
		// used to determine if the user input was valid or not
		boolean valid;

		// start the first day of the game
		control.startDay();
		int day = 1;

/*
			System.out.println("card names:");
			for (int i = 0; i < control.getCards().size(); i++){
				System.out.println(control.getCards().get(i).getName());
			}
*/
  		while (day < 4){
				// check if all players have taken a turn this round
				if (currentPlayer > numPlaying){
					//System.out.println("currentPlayer reset to 1");
					currentPlayer = 1;
				}

    		valid = false;
    		userOptions();
				System.out.println(control.getPlayers().get(currentPlayer-1).getName() + "'s turn");

				while (!valid){
    			String command = in.next();

						// player chooses to move
      			if (command.equals("move")){
 							//retrieves the rooms adjacent to players current position
							ArrayList<String> adjRooms = new ArrayList<String>(control.getAdjacent(currentPlayer-1));
							System.out.println("Rooms you can currently move to:");
							// displays adjacent rooms to player
							//System.out.println("size of adjRoom ArrayList: " + adjRooms.size());
							for (int i = 0; i < adjRooms.size(); i++){
								System.out.println(adjRooms.get(i));
							}
							// player will be prompted for a valid room to move to until
							// this is true
							boolean validRoom = false;
							//prompt player for room to move to
							System.out.println("Enter the room you would like to move to: ");

							while(!validRoom){
								//String room = "";
								String room = in.next();
								// check if given room is valid
           			if (control.getPlayers().get(currentPlayer-1).move(room, adjRooms)) {
             				System.out.println("You have moved to " + control.getPlayers().get(currentPlayer-1).getCurrRoom());
										validRoom = true;
          			}
								else {
										System.out.println("Invalid room! Please enter one of the rooms listed above");
								}
							}
							adjRooms.clear();

      			}
						else if (command.equals("work")){
							System.out.print("Enter the role you would like to take: ");
							String role = in.next();
							if (in.hasNext()) {
								role += in.nextLine();
							}
         			if (control.getPlayers().get(currentPlayer-1).takeRole(role)){
           				System.out.println("Role taken!");
        			}
							else {
								System.out.println("Invalid role!");
							}
      			} else if (command.equals("act")){
        				if (control.getPlayers().get(currentPlayer-1).act()) {
									System.out.println("Acted!");
								} else {
									System.out.println("Fail!");
								}
      			} else if (command.equals("rehearse")){
								if (control.getPlayers().get(currentPlayer-1).rehearse()) {
									System.out.println("Rehearsed!");
								} else {
									System.out.println("Fail!");
								}
      			} else if (command.equals("upgrade")){
								if (control.getPlayers().get(currentPlayer-1).upgrade()) {
									System.out.println("Leveled up!");
								} else {
									System.out.println("Fail!");
								}
      			} else if (command.equals("who")){
        				System.out.println("Player " + currentPlayer + ": " + control.getPlayers().get(currentPlayer-1).getName());
								System.out.println("Current Money: " + control.getPlayers().get(currentPlayer-1).getMoney());
								System.out.println("Current Credits: " + control.getPlayers().get(currentPlayer-1).getCredit());
								System.out.println("Current Level: " + control.getPlayers().get(currentPlayer-1).getLevel());
								System.out.println("Current Room: " + control.getPlayers().get(currentPlayer-1).getCurrRoom());
								System.out.println("Current Role: " + control.getPlayers().get(currentPlayer-1).getRole());
      			} else if (command.equals("where")){
        				System.out.println("Current Room: " + control.getPlayers().get(currentPlayer-1).getCurrRoom());
								String sceneName = control.getPlayers().get(currentPlayer-1).getCurrRoom();
								System.out.println("Shooting: " + control.getCard(sceneName));
      			} else if (command.equals("end")){
        				System.out.println("Turn ended");
								valid = true;
								currentPlayer++;
      			}
    		}
  		}
	}
/* NOTES *****************************************************
 * HELP: http://www.vogella.com/tutorials/SWT/article.html
 *************************************************************/
}
