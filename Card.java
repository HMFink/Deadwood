// Card.java

import java.util.*;

public class Card{

   private String name;
   private boolean flipped;
   private int budget;
   private ArrayList<Role> roles = new ArrayList<Role>();

   // constructor for single role cards
   public Card(String name, int budget, Role role0){
      this.name = name;
      this.budget = budget;
      flipped = false;

      // add the given role to the arraylist of roles for the card
      roles.add(role0);
   }

   // constructor for double role cards
   public Card(String name, int budget, Role role0, Role role1){
      this.name = name;
      this.budget = budget;
      flipped = false;

      // add the given roles to the arraylist of roles for the card
      roles.add(role0);
      roles.add(role1);
   }

   // constructor for triple role cards
   public Card(String name, int budget, Role role0, Role role1, Role role2){
      this.name = name;
      this.budget = budget;
      flipped = false;

      // add the given roles to the arraylist of roles for the card
      roles.add(role0);
      roles.add(role1);
      roles.add(role2);
   }

////////////////////////////////////////////////////////////////////
// getters & setters
////////////////////////////////////////////////////////////////////

   // returns true if the card has been flipped, otherwise false
   public boolean isFlipped(){
      return flipped;
   }

   public int getBudget(){
      return budget;
   }

   public String getName(){
      return name;
   }

   public void flip(){
      flipped = true;
   }

}// end Card class
