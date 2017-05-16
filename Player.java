//Player.java

import java.util.ArrayList;
public class Player{

  private String name;
   private int idNumber;
   private int money;
   private int credit;
   private int level;
   private int rehearsals;
   private String currRoom;
   private String role;

   // player contructor
   public Player(int num, String name){
      this.name = name;
      idNumber = num;
      money = 0;
      credit = 0;
      level = 1;
      rehearsals = 0;
      currRoom = "trailer";
      role = null;
   }

////////////////////////////////////////////////////////////////////
// getters & setters
////////////////////////////////////////////////////////////////////

  public String getName(){
    return name;
  }

  public String getCurrRoom(){
    return currRoom;
  }

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
   public int getCredit(){
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

///////////////////////////////////////////////////////////////////////
// Behavior methods
///////////////////////////////////////////////////////////////////////

   public void act(){

   }// end act()

   public void rehearse(){

   }// end rehearse()

///////////////////////////////////////////////////////////////////////
// Function name: move()
// Parameter: String rooms
// Returns: boolean
// Behavior: returns true if the the given room is adjacent to the players
// current room. Also sets player's current room to the given room.
///////////////////////////////////////////////////////////////////////
   public boolean move(String room, ArrayList<String> adjRooms){
     //ArrayList<String> adjRooms = controller.getAdjacent();
     for (int i = 0; i < adjRooms.size(); i++){
        if (room.equals(adjRooms.get(i))){
           currRoom = adjRooms.get(i);
           return true;
        }
     }
     return false;
   }// end move()

   public boolean takeRole(String role){

      return true; // place holder for compilation purposes
   }// end takeRole()

}// end Player class
