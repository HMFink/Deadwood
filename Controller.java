// Controller.java

public class Controller{

   private int day = 0;
   private int sceneCount = 0;
   private int playerCount = 0;

   // Controller constructor
   public Controller(int num){
      day = 0;
      sceneCount = 10;
      playerCount = num;
      createCards();
      createPlayers();
      createRooms();
   }
   
   private void createPlayers () {
	   for (int i=0; i<playerCount; i++) {
		   
	   }
   }
   
   private void createCards () {
	   
   }
   
   private void createRooms () {
	   // create all rooms
	   CastingOffice office = new CastingOffice(playerCount);
	   Trailer start = new Trailer(playerCount);
	   Board gameBoard = new Board();
   }

   public void decrementScene(){
      sceneCount--;
   }

   public void startDay () {
	   //Reset();
   }

   public void startTurn () {}

   public void endTurn () {}

   public void endDay () {}

   public void endGame () {}

   public void calcWinner () {}
   
}// end Controller class
