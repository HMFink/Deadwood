// Board.java

public class Board{
   // Fields
   
   // Board constructor
   public Board(){
      // board created
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
}// end Board class
