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
			gameBoard.setPlayer(color, level, playerX, playerY, control.getPlayers().get(i));
			playerY += 60;
		}
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
		gameBoard.displayStats(control.getPlayers().get(currentPlayer-1));

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


		int pos = 0;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// start of turn
		while (day < 4){
			// check if all players have taken a turn this round
			if (currentPlayer > numPlaying){
				//System.out.println("currentPlayer reset to 1");gameBoard.clearCommand();
				currentPlayer = 1;
			}

			gameBoard.updateStats(control.getPlayers().get(currentPlayer-1));

			// set boolean values to false at the beginning of each player's turn
			moved = false;
			acted = false;
			rehearsed = false;
			worked = false;
			String command = "";

  		valid = false;
  		//userOptions();
			System.out.println(control.getPlayers().get(currentPlayer-1).getName() + "'s turn");


				while (!valid){

					gameBoard.clearCommand();
					command = gameBoard.getCommand();

					while(command.equals("")){
						System.out.print("");
						command = gameBoard.getCommand();
					}

					// player chooses to move
    			if (command.equals("move")){
						gameBoard.clearCommand();
						command = gameBoard.getCommand();
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
						if (control.getPlayers().get(currentPlayer-1).getRole() != null){
							System.out.println("You cannot move until your current scene finishes.");
							continue;
						}

							//retrieves the rooms adjacent to players current position
						ArrayList<String> adjRooms = new ArrayList<String>(control.getAdjacent(currentPlayer-1));

						boolean validRoom = false;
						if (adjRooms.size() == 3){
							gameBoard.moveMenu(adjRooms.get(0), adjRooms.get(1), adjRooms.get(2));
						}
						else if (adjRooms.size() == 4){
							gameBoard.moveMenu(adjRooms.get(0), adjRooms.get(1), adjRooms.get(2), adjRooms.get(3));
						}

						while(command.equals("")){
							System.out.print("");
							command = gameBoard.getCommand();
						}

						int roomNum = Integer.valueOf(command);
						gameBoard.clearCommand();


						String room = adjRooms.get(roomNum);

						while(!validRoom){
							// check if given room is valid
         			if (control.getPlayers().get(currentPlayer-1).move(room, adjRooms)) {
           				System.out.println("You have moved to " + control.getPlayers().get(currentPlayer-1).getCurrRoom());
									// change the players current scene to the requested scene
									control.getPlayers().get(currentPlayer-1).setScene(control.getScene(room));

									int playerX = 0;
									int playerY= 0;
									String playerLevel;
									String playerColor;
									int positions[] = {0, 50, 100};
									if (pos > 2){
										pos = 0;
									}

									if (control.getPlayers().get(currentPlayer-1).getCurrRoom().equals("trailer")){
										playerX = 991 + positions[pos];
										playerY = 270;
										playerLevel = Integer.toString(control.getPlayers().get(currentPlayer-1).getLevel());
										playerColor = control.getPlayers().get(currentPlayer-1).getColor();
										gameBoard.setPlayer(playerColor, playerLevel, playerX, playerY, control.getPlayers().get(currentPlayer-1));
										pos++;
									}
									else if (control.getPlayers().get(currentPlayer-1).getCurrRoom().equals("office")){
										playerX = 9 + positions[pos];
										playerY = 459;
										playerLevel = Integer.toString(control.getPlayers().get(currentPlayer-1).getLevel());
										playerColor = control.getPlayers().get(currentPlayer-1).getColor();
										gameBoard.setPlayer(playerColor, playerLevel, playerX, playerY, control.getPlayers().get(currentPlayer-1));
										pos++;
									}
									else{
										playerX = control.getPlayers().get(currentPlayer-1).getCurrScene().getX() + positions[pos];
										playerY = control.getPlayers().get(currentPlayer-1).getCurrScene().getY();
										playerLevel = Integer.toString(control.getPlayers().get(currentPlayer-1).getLevel());
										playerColor = control.getPlayers().get(currentPlayer-1).getColor();
										gameBoard.setPlayer(playerColor, playerLevel, playerX, playerY, control.getPlayers().get(currentPlayer-1));
										pos++;
									}
									validRoom = true;
									moved = true;
        			}
						}
						adjRooms.clear();
    			}

					// player chooses to take a role
					else if (command.equals("work")){
						gameBoard.clearCommand();
						command = gameBoard.getCommand();

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
						ArrayList<Role> allRoles = new ArrayList<Role>();
						for (int i = 0; i < offRoles.size(); i++){
							if (offRoles.get(i).getPlayerNum() == -1){
								System.out.println("-" + offRoles.get(i).getName() + ", role level: " + offRoles.get(i).getLevel());
								allRoles.add(offRoles.get(i));
							}
						}
						System.out.println();
						System.out.println("Available roles on the card:");
						Card currCard = control.getPlayers().get(currentPlayer-1).getCurrScene().getCard();
						ArrayList<Role> onRoles = currCard.getRoles();
						for (int i = 0; i < onRoles.size(); i++){
							if (onRoles.get(i).getPlayerNum() == -1){
								System.out.println("-" + currCard.getRoles().get(i).getName() + ", role level: " + currCard.getRoles().get(i).getLevel());
								allRoles.add(onRoles.get(i));
							}
						}
						System.out.println();

						// call the correct workMenu() method depending on the number of available roles
						int roleLen = allRoles.size();
						if (roleLen == 1){
							gameBoard.workMenu(allRoles.get(0).getName());
						}
						else if (roleLen == 2){
							gameBoard.workMenu(allRoles.get(0).getName(), allRoles.get(1).getName());
						}
						else if (roleLen == 3){
							gameBoard.workMenu(allRoles.get(0).getName(), allRoles.get(1).getName(), allRoles.get(2).getName());
						}
						else if (roleLen == 4){
							gameBoard.workMenu(allRoles.get(0).getName(), allRoles.get(1).getName(), allRoles.get(2).getName(), allRoles.get(3).getName());
						}
						else if (roleLen == 5){
							gameBoard.workMenu(allRoles.get(0).getName(), allRoles.get(1).getName(), allRoles.get(2).getName(), allRoles.get(3).getName(), allRoles.get(4).getName());
						}
						else if (roleLen == 6){
							gameBoard.workMenu(allRoles.get(0).getName(), allRoles.get(1).getName(), allRoles.get(2).getName(), allRoles.get(3).getName(), allRoles.get(4).getName(), allRoles.get(5).getName());
						}
						else if (roleLen == 7){
							gameBoard.workMenu(allRoles.get(0).getName(), allRoles.get(1).getName(), allRoles.get(2).getName(), allRoles.get(3).getName(), allRoles.get(4).getName(), allRoles.get(5).getName(), allRoles.get(6).getName());
						}

						//gameBoard.clearCommand();
						command = "";
						while(command.equals("")){
							System.out.print("");
							command = gameBoard.getCommand();
						}

						int roleNum = Integer.valueOf(command);
						gameBoard.clearCommand();
						command = gameBoard.getCommand();

						String role = allRoles.get(roleNum).getName();

						// check if the entered role is valid and the player is high enough level
       			if (control.canWork(role, currentPlayer-1)){
         				System.out.println("You are now working this role!");
								worked = true;
								int roleX;
								int roleY;
								Player p = control.getPlayers().get(currentPlayer-1);
								if (allRoles.get(roleNum).mainOrExtra()){
									roleX = allRoles.get(roleNum).getX() + p.getCurrScene().getX();
									roleY = allRoles.get(roleNum).getY() + p.getCurrScene().getY();
								}
								else{
									roleX = allRoles.get(roleNum).getX();
									roleY = allRoles.get(roleNum).getY();
								}
								String c = p.getColor();
								String l = Integer.toString(p.getLevel());
								gameBoard.setPlayer(c, l, roleX, roleY, p);
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
								gameBoard.updateStats(control.getPlayers().get(currentPlayer-1));
								control.decrementScene();
							}
							acted = true;
							gameBoard.updateStats(control.getPlayers().get(currentPlayer-1));
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
								gameBoard.updateStats(control.getPlayers().get(currentPlayer-1));
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
							gameBoard.updateStats(control.getPlayers().get(currentPlayer-1));

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
