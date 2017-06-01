/***********************
 * Deadwood Game  *
 ***********************/


import java.util.*;
import javax.swing.JFrame;
import java.awt.*;

public class Deadwood{

	private static void toTrailer(Controller control, Board gameBoard, int numPlaying){
		int playerX = 991;
		int playerY = 270;
		// place all players in the trailer
		for (int i = 0; i < numPlaying; i++){
			String color = control.getPlayers().get(i).getColor();
			String level = Integer.toString(control.getPlayers().get(i).getLevel());
			gameBoard.setPlayer(color, level, playerX, playerY);
			playerY += 60;
		}
	}

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

/*
		// Enter number of players
		System.out.println("****************************************************************************");
    System.out.println("                                 DEADWOOD                                   ");
    System.out.println("****************************************************************************");
*/
		System.out.print("Please enter the number of players (2-3): ");
		numPlaying = in.nextInt();
		System.out.println();

		// Call controller
		Controller control = new Controller(numPlaying);

		// create and display board
		Board gameBoard = new Board();
		gameBoard.setVisible(true);

		// used to determine if the user input was valid or not
		boolean valid;

		// start the first day of the game
		control.startDay();
		ArrayList<Scene> scenes = control.getScenes();
		int sceneLen = scenes.size();

		// place a card on each scene room
		for (int i = 0; i < sceneLen; i++){
			String cardNum = scenes.get(i).getCard().getCardNum();
			int x = scenes.get(i).getX();
			int y = scenes.get(i).getY();
			gameBoard.addCard(cardNum, x, y);
		}

		toTrailer(control, gameBoard, numPlaying);
		int day = 1;

		boolean moved;
		boolean acted;
		boolean rehearsed;
		boolean worked;


  		while (day < 4){
				// check if all players have taken a turn this round
				if (currentPlayer > numPlaying){
					//System.out.println("currentPlayer reset to 1");gameBoard.clearCommand();
					currentPlayer = 1;
				}

				// set boolean values to false at the beginning of each player's turn
				moved = false;
				acted = false;
				rehearsed = false;
				worked = false;
				String command = "";

    		valid = false;
    		userOptions();
				System.out.println(control.getPlayers().get(currentPlayer-1).getName() + "'s turn");

				while (!valid){

					gameBoard.clearCommand();
					command = gameBoard.getCommand();

					while(command.equals("")){
						System.out.print("");
						command = gameBoard.getCommand();
					}
/*
					String command = in.next();
*/
					System.out.println("command = " + command);

					// player chooses to move
    			if (command.equals("move")){
						gameBoard.clearCommand();
						// check if player has already move during the current turn
						if (moved){
							System.out.println("You may only move once per turn.");
							continue;
						}
						// check if player has acted during current turn
						if (acted){
							System.out.println("You may not act and move in the same turn.");
							continue;
						}
						if (rehearsed){
							System.out.println("You may not rehearse and move in the same turn.");
							continue;
						}



							//retrieves the rooms adjacent to players current position
						ArrayList<String> adjRooms = new ArrayList<String>(control.getAdjacent(currentPlayer-1));
/*
						System.out.println();
						System.out.println("Rooms you can currently move to:");

						// displays adjacent rooms to player
						//System.out.println("size of adjRoom ArrayList: " + adjRooms.size());

						for (int i = 0; i < adjRooms.size(); i++){
							System.out.println("-" + adjRooms.get(i));
						}
						// player will be prompted for a valid room to move to until
						// this is true
						//prompt player for room to move to
						System.out.println("Enter the room you would like to move to: ");
*/
						boolean validRoom = false;
						String room = gameBoard.moveMenu(adjRooms.get(0), adjRooms.get(1), adjRooms.get(2));


						while(!validRoom){
							//String room = "";
							//String room = in.nextLine();
							// check if given room is valid
         			if (control.getPlayers().get(currentPlayer-1).move(room, adjRooms)) {
           				System.out.println("You have moved to " + control.getPlayers().get(currentPlayer-1).getCurrRoom());
									// change the players current scene to the requested scene
									control.getPlayers().get(currentPlayer-1).setScene(control.getScene(room));

									int playerX = 0;
									int playerY= 0;
									if (control.getPlayers().get(currentPlayer-1).getCurrRoom().equals("trailer")){
										//enter trailer coordinates
									}
									playerX = control.getPlayers().get(currentPlayer-1).getCurrScene().getX();
									playerY = control.getPlayers().get(currentPlayer-1).getCurrScene().getY();
									String playerLevel = Integer.toString(control.getPlayers().get(currentPlayer-1).getLevel());
									String playercolor = control.getPlayers().get(currentPlayer-1).getColor();
									gameBoard.setPlayer(playercolor, playerLevel, playerX, playerY);
									validRoom = true;
									moved = true;
        			}
							/*
							else {
									System.out.println("Invalid room! Please enter one of the rooms listed above");
							}
							*/
						}
						adjRooms.clear();
    			}

					// player chooses to take a role
					else if (command.equals("work")){
						//check if the player is in the trailer or the casting office
						if (control.getPlayers().get(currentPlayer-1).getCurrRoom().equals("trailer") ||
								control.getPlayers().get(currentPlayer-1).getCurrRoom().equals("office")){
									System.out.println("There are no roles to work in this room.");
									continue;
								}
						// check if player is already working a role
						if (control.getPlayers().get(currentPlayer-1).getRole() != null){
							System.out.println("You must finish working your current role before you can take another.");
							continue;
						}

						if (control.getPlayers().get(currentPlayer-1).getCurrScene().getWrap()){
							System.out.println("This scene has already done being shot.");
						}
						System.out.println();
						// display available roles in current scene
						System.out.println("Available roles off the card:");
						Scene currScene = control.getPlayers().get(currentPlayer-1).getCurrScene();
						ArrayList<Role> offRoles = currScene.getOffCardRoles();
						for (int i = 0; i < offRoles.size(); i++){
							if (offRoles.get(i).getPlayerNum() == -1){
								System.out.println("-" + offRoles.get(i).getName() + ", role level: " + offRoles.get(i).getLevel());
							}
						}
						System.out.println();
						System.out.println("Available roles on the card:");
						Card currCard = control.getPlayers().get(currentPlayer-1).getCurrScene().getCard();
						for (int i = 0; i < currCard.getRoles().size(); i++){
							if (currCard.getRoles().get(i).getPlayerNum() == -1){
								System.out.println("-" + currCard.getRoles().get(i).getName() + ", role level: " + currCard.getRoles().get(i).getLevel());
							}
						}
						System.out.println();
						// prompt player for the desired role to work
						System.out.println("Enter the role you would like to take: ");
						String role = in.next();

						if (in.hasNext()) {
							role += in.nextLine();
						}
						// check if the entered role is valid and the player is high enough level
       			if (control.canWork(role, currentPlayer-1)){
         				System.out.println("You are now working this role!");
								worked = true;
      			}

						else {
							System.out.println("Invalid Role.");
							continue;
						}

						// player chooses to act
    			} else if (command.equals("act")){
							// check if player is currently working a role
							if (control.getPlayers().get(currentPlayer-1).getRole() == null){
								System.out.println("You are not currently working a role.");
								continue;
							}
							// check if player has moved during the current turn
							if (moved){
								System.out.println("You may not move and act in the same turn.");
								continue;
							}
							// check if the player has already acted during current Turn
							if (acted){
								System.out.println("You may only act once per turn.");
								continue;
							}
							if (rehearsed){
								System.out.println("You may not rehearse and act in the same turn.");
								continue;
							}
							// act
							if (control.getPlayers().get(currentPlayer-1).act() == 1){
								System.out.println("That's a wrap! The scene is over.");
								control.payout(currentPlayer-1);
								control.decrementScene();
							}
							acted = true;
							continue;

							// player chooses to rehearse
    			} else if (command.equals("rehearse")){
							if (rehearsed){
								System.out.println("You may only rehearse once per turn");
								continue;
							}
							if (acted){
								System.out.println("You may not act and rehearse in the same turn.");
								continue;
							}
							if (worked){
								System.out.println("You must wait until your next turn to rehearse.");
								continue;
							}

							if (control.getPlayers().get(currentPlayer-1).rehearse()){
								rehearsed = true;
							}

						// player chooseses to upgrade
    			} else if (command.equals("upgrade")){
							if (!control.getPlayers().get(currentPlayer-1).getCurrRoom().equals("office")){
								System.out.println("You must be in the casting office to upgrade your level");
								continue;
							}

							control.displayLevels();
							System.out.println();
							System.out.println("Your money: " + control.getPlayers().get(currentPlayer-1).getMoney());
							System.out.println("Your credits: " + control.getPlayers().get(currentPlayer-1).getCredit());
							System.out.println("Which level would you like to upgrade to?");
							int upLevel = in.nextInt();
							System.out.println("Would you like to pay with money or credit?");
							String payment = in.next();

							control.getPlayers().get(currentPlayer-1).upgrade(upLevel, payment);

    			} else if (command.equals("who")){
							System.out.println();
      				System.out.println("Player " + currentPlayer + ": " + control.getPlayers().get(currentPlayer-1).getName());
							System.out.println("Money: " + control.getPlayers().get(currentPlayer-1).getMoney());
							System.out.println("Credits: " + control.getPlayers().get(currentPlayer-1).getCredit());
							System.out.println("Level: " + control.getPlayers().get(currentPlayer-1).getLevel());
							System.out.println("Current Room: " + control.getPlayers().get(currentPlayer-1).getCurrRoom());
							Role tempRole = control.getPlayers().get(currentPlayer-1).getRole();
							if (tempRole != null){
								System.out.println("Current Role: " + control.getPlayers().get(currentPlayer-1).getRole().getName());
								System.out.println("Rehearsal counters: " + control.getPlayers().get(currentPlayer-1).getRehearsals());
							}
							else{
								System.out.println("Current role: none");
							}

    			} else if (command.equals("where")){
							System.out.println();
      				System.out.println("Current Room: " + control.getPlayers().get(currentPlayer-1).getCurrRoom());
							String sceneName = control.getPlayers().get(currentPlayer-1).getCurrRoom();
							if (control.getPlayers().get(currentPlayer-1).getCurrRoom().equals("trailer") ||
									control.getPlayers().get(currentPlayer-1).getCurrRoom().equals("office")){
										continue;
									}
							System.out.println();
							System.out.println("Shooting: " + control.getCard(sceneName));
							System.out.println();
							System.out.println("Available roles off the card:");
							Scene currScene = control.getPlayers().get(currentPlayer-1).getCurrScene();
							ArrayList<Role> offRoles = currScene.getOffCardRoles();
							for (int i = 0; i < offRoles.size(); i++){
								if (offRoles.get(i).getPlayerNum() == -1){
									System.out.println("-" + offRoles.get(i).getName() + ", role level: " + offRoles.get(i).getLevel());
								}
							}
							System.out.println();
							System.out.println("Available roles on the card:");
							Card currCard = control.getPlayers().get(currentPlayer-1).getCurrScene().getCard();
							for (int i = 0; i < currCard.getRoles().size(); i++){
								if (currCard.getRoles().get(i).getPlayerNum() == -1){
									System.out.println("-" + currCard.getRoles().get(i).getName() + ", role level: " + currCard.getRoles().get(i).getLevel());
								}
							}
							System.out.println();
							System.out.println("Shot counters on scene: " + currScene.getShotCount());

    			} else if (command.equals("end")){
      				System.out.println("Turn ended");
							valid = true;
							currentPlayer++;
    			}
  		}
		}
	}// end main
}// end class
