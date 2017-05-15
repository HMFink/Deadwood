// Board.java

public class Board{
   // Fields
   
   // Board constructor
   public Board(){
	   run();
   }

   // update the Board display
   public void update(String keyword, int x, int y){
      if (keyword.equalsIgnoreCase("position")) {
         // when a players position needs to be updated
         System.out.println("Players new position is, (" + x + ", " + y + ")");
      } else if (keyword.equalsIgnoreCase("level")) {
         // when a players level is upgraded
         System.out.println("Players old level was " + x + " and new level is " + y + ")");
      } else if (keyword.equalsIgnoreCase("takeRole")) {
         // when a player takes a role
         // get role name
         String name = "Hannah";//getName();
         System.out.println("Players new role is, " + name);
      } else if (keyword.equalsIgnoreCase("endScene")) {
         // when a scene ends and all players are moved off of the roles
         System.out.println("All players in ended scene have moved to, (" + x + ", " + y + ")");
      } else {
         // keyword not recognized display error and try again
         System.out.println("System error: Keyword not recognized. Please try again.");
      }
   }

   // display a message to the user(s) on the Board
   public void dispMessage(String message){
      // print message
      System.out.println(message);
   }
   
   public void run () {
	  
   }
}// end Board class

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
