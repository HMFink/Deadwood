// Card.java

import java.util.*;

public class Card{

   private String name;
   private int budget;
   private String cardNum;
   private String description;
   private ArrayList<Role> roles;
   private boolean flipped;


   // constructor
   public Card(String name, int budget, String cardNum, String description, ArrayList<Role> cardRoles){
      this.name = name;
      this.budget = budget;
      this.cardNum = cardNum;
      this.description = description;
      flipped = false;
      roles = new ArrayList<Role>(cardRoles);
   }

////////////////////////////////////////////////////////////////////
// getters & setters
////////////////////////////////////////////////////////////////////

   // returns true if the card has been flipped, otherwise false
   public boolean isFlipped(){
      return flipped;
   }

   public String getName(){
      return name;
   }

   public int getBudget(){
      return budget;
   }

   public String getCardNum(){
      return cardNum;
   }

   public String getDescription(){
      return description;
   }

   public ArrayList<Role> getRoles(){
      return roles;
   }
   public void flip(){
      flipped = true;
   }

}// end Card class
