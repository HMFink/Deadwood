// Board.java

public class Board{

   // Board constructor
   public Board(){}

   // update the Board display
   public void update(String keyword, int x, int y){
      if (strcmp(keyword, position)==0) {
         // when a players position needs to be updated
         System.out.println("Players new position is, (%d, %d)", x, y);
      } else if (strcmp(keyword, level)==0) {
         // when a players level is upgraded
         System.out.println("Players old level was %d and new level is %d)", x, y);
      } else if (strcmp(keyword, takeRole)==0) {
         // when a player takes a role
         // get role name
         String name = getName();
         System.out.println("Players new role is, %s", name);
      } else if (strcmp(keyword, endScene)==0) {
         // when a scene ends and all players are moved off of the roles
         System.out.println("All players in ended scene have moved to, (%d, %d)", x, y);
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
}// end Board class
