//Player.java

public class Player{

   private int idNumber;
   private int money;
   private int credit;
   private int level;
   private int rehearsals;
   private String room;
   private String role;

   // player contructor
   public Player(int num){
      idNumber = num;
      money = 0;
      credit = 0;
      level = 1;
      rehearsals = 0;
      room = "Trailer";
      role = null;
   }

////////////////////////////////////////////////////////////////////
// getters & setters
////////////////////////////////////////////////////////////////////

   // getter for player number
   public int getIdNum(){
      return idNumber;
   }

   // getter and setter for player's money
   public int getMoney(){
      return money;
   }

   public void changeMoney(int amount){
      money += amount;
   }

   // getter and setter for player credit
   public int getCredit(int amount){
      return credit;
   }
   
   public void addCredit(int amount){
      credit += amount;
   }

   //getter and setter for player level
   public int getLevel(){
      return level;
   }

   public void setLevel(int x){
      level = x;
   }

   // getter and setter for rehearsal count
   public int getRehearsals(){
      return rehearsals;
   }

   public void addRehearsal(){
      rehearsals += 1;
   }

   // clears the players rehearsal counters
   public void clearRehearsals(){
      rehearsals = 0;
   }

////////////////////////////////////////////////////////////////////
// Behavior methods
////////////////////////////////////////////////////////////////////

   public void act(){

   }// end act()

   public void rehearse(){

   }// end rehearse()

   public boolean move(String room){

      return true; // place holder for compilation purposes
   }// end move()

   public boolean takeRole(String role){

      return true; // place holder for compilation purposes
   }// end takeRole()

}// end Player class
