// Card.java

public class Card{

   private String name;
   private boolean flipped;
   private int budget;
   private int roleCount;
   private Role roles[];

   // Card constructor
   public Card(String name, int roleCount, int budget){
      this.name = name;
      this.roleCount = roleCount;
      this.budget = budget;
      flipped = false;
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
