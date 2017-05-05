// Controller.java

public class Controller{

   private int day;
   private int sceneCount;
   private int playerCount;

   // Controller constructor
   public Controller(int num){
      day = 1;
      sceneCount = 1;
      playerCount = num;
   }

   public void decrementScene(){
      sceneCount--;
   }

   public void startDay(){}

   public void startTurn(){}

   public void endTurn(){}

   public void endDay(){}

   public void endGame(){}

   public void calcWinner(){}
   
}// end Controller class
