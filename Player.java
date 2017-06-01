//Player.java

import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import javax.swing.*;

public class Player{

   String name;
   private int idNumber;
   private int money;
   private int credit;
   private int level;
   private int rehearsals;
   private String currRoom;
   private Role role;
   private Scene scene;
   private String color;
   private JLabel image;

   // player contructor
   public Player(int num, String name){
      this.name = name;
      idNumber = num;
      money = 0;
      credit = 0;
      level = 1;
      rehearsals = 0;
      this.color = "";
      currRoom = "trailer";
      role = null;
      scene = null;
      image = null;
   }

////////////////////////////////////////////////////////////////////
// getters & setters
////////////////////////////////////////////////////////////////////

  public JLabel getImage(){
    return image;
  }

  public void setImage(JLabel image){
    this.image = image;
  }
  public void setColor(String color){
    this.color = color;
  }

  public String getColor(){
    return color;
  }

  public String getName(){
    return name;
  }

  public Role getRole() {
    return role;
  }

  public void changeRole(Role role){
    this.role = role;
  }

  public void clearRole(){
    role = null;
  }

  public String getCurrRoom(){
    return currRoom;
  }

  public void setCurrRoom(String newCurr){
    currRoom = newCurr;
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

   public void setScene(Scene newScene){
     this.scene = newScene;
   }

   public Scene getCurrScene(){
     return scene;
   }

///////////////////////////////////////////////////////////////////////
// Behavior methods
///////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////
//
// Returns: 0 if scene not finished, 1 if scene finished
///////////////////////////////////////////////////////////////////////
   public int act(){

     Random rand = new Random();
     int roll = rand.nextInt(6) + 1;
     // player's role is on the card
     if (role.mainOrExtra()){
       System.out.println("Scene budget:  " + scene.getCard().getBudget());
       System.out.println("Your roll + rehearsal counters (if applicable) = " + (roll + rehearsals));

       if ((roll + rehearsals) >= scene.getCard().getBudget()){
         System.out.println("Acting attempt succeed!");
         System.out.println("Shot counter removed from scene.");
         System.out.println("You have received two credits for your work!");
         credit += 2;
         scene.removeShotCounter();
         System.out.println("Remaining shot counters on scene: " + scene.getShotCount());
         if (scene.getShotCount() == 0){
           return 1;
         }
         else{
           return 0;
         }
       }
       else{
         System.out.println("Acting attempt failed!");
         return 0;
       }
     }
     // player's role is off the card
     else{
         System.out.println("Scene budget:  " + scene.getCard().getBudget());
         System.out.println("Your roll + rehearsal counters (if applicable) = " + (roll + rehearsals));
         if ((roll + rehearsals) >= scene.getCard().getBudget()){
           System.out.println("Acting attempt succeed!");
           System.out.println("Shot counter removed from scene.");
           System.out.println("You have received one dollar and one credit for your work!");
           money++;
           credit++;
           scene.removeShotCounter();
           System.out.println("Remaining shot counters on scene: " + scene.getShotCount());
           if (scene.getShotCount() == 0){
             return 1;
           }
           else{
             return 0;
           }
         }
         else{
           System.out.println("Acting attempt failed!");
           System.out.println("You receive one dollar anyway.");
           money++;
           return 0;
         }
     }
   }// end act()



   public boolean rehearse(){

     int budget = scene.getCard().getBudget();
     if ((level + rehearsals) >= budget){
       System.out.println("You cannot rehearse anymore for this role.");
       return false;
     }

     System.out.println("Rehearse successful! A +1 counter will be added to your rolls for the remainder of the scene.");
     rehearsals++;
     return true;
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
        if (adjRooms.get(i).equals(room)){
           currRoom = adjRooms.get(i);
           return true;
        }
     }
     return false;
   }// end move()

   public void upgrade(int upLevel, String payment){

     int cost = 0;
     // if player chose to pay with money
     if (payment.equalsIgnoreCase("money")){
       if (upLevel == 2){
         cost = 4;
       }
       else if (upLevel == 3){
         cost = 10;
       }
       else if (upLevel == 4){
         cost = 18;
       }
       else if (upLevel == 5){
         cost = 28;
       }
       else if (upLevel == 6){
         cost = 40;
       }
        if (money >= cost){
          level = upLevel;
          money -= cost;
          System.out.println("Your level has been upgraded!");
        }
        else{
          System.out.println("You don't have enough money to pay for the selected level.");
        }
     }
     else if (payment.equalsIgnoreCase("credit")){
       if (upLevel == 2){
         cost = 5;
       }
       else if (upLevel == 3){
         cost = 10;
       }
       else if (upLevel == 4){
         cost = 15;
       }
       else if (upLevel == 5){
         cost = 20;
       }
       else if (upLevel == 6){
         cost = 25;
       }

       if (credit >= cost){
         level = upLevel;
         credit -= cost;
         System.out.println("Your level has been upgraded!");
       }
       else{
         System.out.println("You don't have enough credits to pay for the selected level");
       }
     }
   }// end upgrade()

}// end Player class
