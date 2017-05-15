import java.util.*;

public class Dw{

   private static void userOptions(){
     System.out.println("----------------------------------------------------------------------------");
     System.out.println("Please enter one of the following: ");
     System.out.println();
     System.out.println("'move' = move to a room adjacent to your current position");
     System.out.println("'take role' = take a role in your current room");
     System.out.println("'act' = act out the role you are currently in");
     System.out.println("'upgrade' = upgrade your current level in exchange for money or credit");
     System.out.println("----------------------------------------------------------------------------");
   }

   public static void main(String[] args){

      System.out.println("****************************************************************************");
      System.out.println("                                 DEADWOOD                                   ");
      System.out.println("****************************************************************************");
      int day = 1;
      Scanner input = new Scanner(System.in);

      //prompt for number of Players
      System.out.print("Please enter the number of players (2-8): ");

      int numPlayers = input.nextInt();
      Controller controller = new Controller(numPlayers);
/*
      for (int i = 0; i < controller.getCards().size(); i++){
      System.out.println("Card name: " + controller.getCards().get(i).getName());
      System.out.println("Card budget: " + controller.getCards().get(i).getBudget());
      }
*/
      // used to determine if the user input was valid or not
      boolean valid;

      // start the first day of the game
      controller.startDay();

      while (day < 4){
        valid = false;
        userOptions();
        while (!valid){
          String command = input.nextLine();
          if (command.equals("move")){
             valid = true;

             if (move(command)){
               System.out.println("You have moved to " );
            }

          }
          else if (command.equals("take role")){
            System.out.println("took role!");
            valid = true;
          }
          else if (command.equals("act")){
            System.out.println("acted!");
            valid = true;
          }
          else if (command.equals("upgrade")){
            System.out.println("leveled up!");
            valid = true;
          }
          else{
            System.out.println("Invalid command! please enter one of the options listed above.");
          }
        }

      }
   }// end main

}// end class
