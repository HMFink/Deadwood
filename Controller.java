// Controller.java

import java.util.*;
import java.io.IOException;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.awt.*;


public class Controller {

   private int day = 1;
   private int sceneCount = 0;
   private int playerCount;
   private static ArrayList<Card> cards;
   private static ArrayList<Scene> scenes;
   Trailer trailer;
   CastingOffice office;
   private static ArrayList<Player> players;
   private static int currentCard;

   // Controller constructor
   public Controller(int numPlayers){
      day = 0;
      sceneCount = 10;
      playerCount = numPlayers;
      cards = new ArrayList<Card>();
      scenes = new ArrayList<Scene>();
      players = new ArrayList<Player>();
      createCards();
      currentCard = 0;
      Collections.shuffle(cards);
      createPlayers(numPlayers);
      createRooms();
   }

///////////////////////////////////////////////////////////////////////
// Function Name: createPlayers()
// Behavior: Prompts users for player names and creates players. Players
// are then stored in the players arraylist
///////////////////////////////////////////////////////////////////////
   private void createPlayers(int numPlayers){
      // Variables
      Scanner in = new Scanner(System.in);
      String name;
      // Get player names
      for (int i=1; i<=numPlayers; i++){
          System.out.print("Enter player " + i + "'s name: ");
	        name = in.next();
          Player temp = new Player(i, name);
	        players.add(temp);
	        System.out.println();
      }
   }// end cratePlayers


///////////////////////////////////////////////////////////////////////
// Function name: decrementScene()
// Behavior: decrements the number scenes currently left on the board
///////////////////////////////////////////////////////////////////////
   public void decrementScene(){
      sceneCount--;
   }

///////////////////////////////////////////////////////////////////////
// Function name: getPlayer()
// Behavior: returns the players ArrayList
///////////////////////////////////////////////////////////////////////
   public ArrayList<Player> getPlayers(){
     return players;
   }// end getPlayers()


///////////////////////////////////////////////////////////////////////
//
///////////////////////////////////////////////////////////////////////
   public void startDay () {
      for (int i=0; i<playerCount; i++) {
        players.get(i).setCurrRoom("trailer");
      }
      for (int i = 0; i < 10; i++){
        scenes.get(i).ChangeCard(cards.get(currentCard));
        currentCard++;
      }
   }

///////////////////////////////////////////////////////////////////////
//
///////////////////////////////////////////////////////////////////////
   public void endDay () {
     day++;
     if (day==3) {
       calcWinner();
     } else {
       startDay();
     }
   }

///////////////////////////////////////////////////////////////////////
//
///////////////////////////////////////////////////////////////////////
   private void calcWinner () {
     int sumValue = 0;
     int maxSum = 0;
     int winner = -1;
     for (int i=0; i<=playerCount; i++) {
       sumValue = players.get(i).getMoney();
       sumValue += players.get(i).getCredit();
       sumValue += 5*(players.get(i).getLevel());
       if (sumValue > maxSum) {
         maxSum = sumValue;
         winner = i;
       }
     }
     System.out.println("The winner is " + players.get(winner).getName());
   }

///////////////////////////////////////////////////////////////////////
//
///////////////////////////////////////////////////////////////////////
   public String getCard(String scene) {
     for (int i = 0; i < scenes.size(); i++){
       String sceneName = scenes.get(i).getName();
      // System.out.println("sceneName = " + sceneName);
        if (sceneName.equals(scene)){
          return scenes.get(i).getCard().getName();
        }
     }
     return null;
   }

///////////////////////////////////////////////////////////////////////
//
///////////////////////////////////////////////////////////////////////
public Scene getScene(String scene){
   for (int i = 0; i < scenes.size(); i++){
     String sceneName = scenes.get(i).getName();
    // System.out.println("sceneName = " + sceneName);
      if (sceneName.equals(scene)){
        return scenes.get(i);
      }
   }
   return null;
 }

///////////////////////////////////////////////////////////////////////
//
///////////////////////////////////////////////////////////////////////
   public ArrayList<Card> getCards(){
      return cards;
   }

///////////////////////////////////////////////////////////////////////
//
///////////////////////////////////////////////////////////////////////
   public ArrayList<Scene> getScenes(){
     return scenes;
   }

///////////////////////////////////////////////////////////////////////
// Function name: getAdjacent()
// Parameters: String currRoom
// Returns ArrayList<String>
// Behavior: returns an arraylist of all the rooms that are ajacent to
// to the given room.
///////////////////////////////////////////////////////////////////////
   public ArrayList<String> getAdjacent(int currPlayer){

     String currRoom = players.get(currPlayer).getCurrRoom();
     System.out.println("current room = " + currRoom);

      if (currRoom.equals("trailer")){
         //System.out.println("returning trailer neighbors");
         return trailer.getNeighbors();
      }
      else if (currRoom.equals("office")){
         return office.getNeighbors();
      }
      else{
         for (int i = 0; i < scenes.size(); i++){
           String sceneName = scenes.get(i).getName();
          // System.out.println("sceneName = " + sceneName);
            if (sceneName.equals(currRoom)){
              //System.out.println("size of neighbors list = " + scenes.get(i).getNeighbors().size());
              return scenes.get(i).getNeighbors();
            }
         }
      }
      return null;
   }

   public void displayLevels(){
     System.out.println("Level  Money  Credit");
     System.out.println("  2      4       5");
     System.out.println("  3     10      10");
     System.out.println("  4     18      15");
     System.out.println("  5     28      20");
     System.out.println("  6     40      25");
   }

///////////////////////////////////////////////////////////////////////
// Function name: canWork()
// Behavior: returns true if the given role is in the same scene as the
// given player
///////////////////////////////////////////////////////////////////////
   public boolean canWork(String role, int player){
     boolean in = false;
     boolean level = false;
     Role newRole = null;
      String sceneName = players.get(player).getCurrRoom();
      for (int i = 0; i < scenes.size(); i++){
        if (sceneName.equals(scenes.get(i).getName())){
           int len = scenes.get(i).getOffCardRoles().size();
           for (int j = 0; j < len; j++){
             if (role.equalsIgnoreCase(scenes.get(i).getOffCardRoles().get(j).getName())){
               //System.out.println("role is on scene!");
               in = true;
               newRole = scenes.get(i).getOffCardRoles().get(j);
               if (players.get(player).getLevel() >= scenes.get(i).getOffCardRoles().get(j).getLevel()){
                 level = true;
               }
             }
           }
           for (int k = 0; k < scenes.get(i).getCard().getRoles().size(); k++){
              //System.out.println("on card role = " + scenes.get(i).getCard().getRoles().get(k).getName());
              if (role.equalsIgnoreCase(scenes.get(i).getCard().getRoles().get(k).getName())){
                 //System.out.println("role is on scene!");
                 in = true;
                 newRole = scenes.get(i).getCard().getRoles().get(k);
                 if (players.get(player).getLevel() >= scenes.get(i).getCard().getRoles().get(k).getLevel()){
                   level = true;
                 }
              }
           }
        }
      }

      if (in && level){
        if (newRole.getPlayerNum() != -1){
          System.out.println("Another player is currently working this role.");
          return false;
        }
        players.get(player).changeRole(newRole);
        newRole.addPlayer(players.get(player).getIdNum());

        return true;
      }
      else{
        return false;
      }
  }// end canWork()

///////////////////////////////////////////////////////////////////////
//
///////////////////////////////////////////////////////////////////////
public void payout(int currPlayer){


  boolean onCard = false;
  // scene to payout
  Scene currScene = players.get(currPlayer).getCurrScene();
  currScene.wrapScene();
  // check if at least one player was acting on the cardList
  for (int i = 0; i < currScene.getCard().getRoles().size(); i++){
    if (currScene.getCard().getRoles().get(i).getPlayerNum() != -1){
      onCard = true;
    }
  }

  // payout with on-card bonuses
  if (onCard){
    Random rand = new Random();
    int budget = currScene.getCard().getBudget();
    int endRolls[] = new int[budget];

    for (int i = 0; i < budget; i++){
      endRolls[i] = rand.nextInt(6) + 1;
    }
    // sort the rolls from lowest to highest
    Arrays.sort(endRolls);
    int numCardRoles = currScene.getCard().getRoles().size();
    int bonuses[] = new int[numCardRoles];
    int j = 0;
    // assign dice rolls to on-card roles for bonus amounts
    for (int i = budget-1; i >= 0 ; i--){
      if (j == numCardRoles-1){
        j = 0;
      }
      bonuses[j] += endRolls[i];
      j++;
    }

    // pay bonuses to players with on card roles
    for (int i = 0; i < numCardRoles; i++){
      int player = currScene.getCard().getRoles().get(i).getPlayerNum();
      if (player != -1){
        player -= 1;
        players.get(player).changeMoney(bonuses[i]);
        System.out.println(players.get(player).getName() + " received $" + bonuses[i] + " as a scene wrap bonus.");
        players.get(player).clearRole();
        players.get(player).clearRehearsals();
      }
    }

    int numExtraRoles = currScene.getOffCardRoles().size();

    for (int i = 0; i < numExtraRoles; i++){
      int player = currScene.getOffCardRoles().get(i).getPlayerNum();
      if (player != -1){
        player -= 1;
        players.get(player).changeMoney(currScene.getOffCardRoles().get(i).getLevel());
        System.out.println(players.get(player).getName() + " received $" + currScene.getOffCardRoles().get(i).getLevel() + " as a scene wrap bonus.");
        players.get(player).clearRole();
        players.get(player).clearRehearsals();
      }
    }
  }
  else{
    int numOffCardRoles = currScene.getOffCardRoles().size();
    for (int i = 0; i < numOffCardRoles; i++){
      int player = currScene.getOffCardRoles().get(i).getPlayerNum();
      if (player != -1){
        player -= 1;
        players.get(player).clearRole();
      }
    }
    System.out.println("No actors on the card, so no bonuses this time!");
  }
}// end payout()

///////////////////////////////////////////////////////////////////////
//
///////////////////////////////////////////////////////////////////////
   public static void createCards(){

      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      try{
         DocumentBuilder builder = factory.newDocumentBuilder();
         Document doc  = builder.parse("cards.xml");

         // creates a NodeList of every element in the .xml file
         NodeList cardList = doc.getElementsByTagName("*");

         int cardCount = 0;
         String name = "";
         String budget = "";
         String sceneNum = "";
         String sceneLine = "";

         ArrayList<Role> roles = new ArrayList<Role>();
         String roleName = "";
         String roleLevel = "";
         String roleLine = "";

         boolean create = false;

         int createCount = 0;

         // iterates through the elements of the .xml file and creates
         // card object using the xml tags as references
         for (int i = 0; i < cardList.getLength(); i++){

            Node cardNode = cardList.item(i);
            Element card = (Element) cardNode;

            // card section
            if (card.getTagName().equals("card")){

               if (create){
                  int budg = Integer.valueOf(budget);
                  int num = Integer.valueOf(sceneNum);
                  Card current = new Card(name, budg, num, sceneLine, roles);
                  cards.add(current);
                  roles.clear();
                  createCount++;
               }
               name = card.getAttribute("name");
               budget = card.getAttribute("budget");
               create = true;

            }

            //scene section
            else if (card.getTagName().equals("scene")){
               cardNode = cardList.item(i);
               card = (Element) cardNode;
               sceneNum = card.getAttribute("number");
               sceneLine = card.getTextContent();
            }

            // parts sections
            else if (card.getTagName().equals("part")){
               roleName = card.getAttribute("name");
               roleLevel = card.getAttribute("level");
               roleLine = card.getTextContent();

               int level = Integer.valueOf(roleLevel);

               // build a Role ArrayList to pass to the card
               Role temp = new Role(level, true, roleName, roleLine);
               roles.add(temp);

            }
         }

         int budg = Integer.valueOf(budget);
         int num = Integer.valueOf(sceneNum);
         Card current = new Card(name, budg, num, sceneLine, roles);
         cards.add(current);
         roles.clear();
         createCount++;


      }
      catch(ParserConfigurationException ex){
         ex.printStackTrace();
      }
      catch(SAXException ex){
         ex.printStackTrace();
      }
      catch(IOException ex){
         ex.printStackTrace();
      }

   }

///////////////////////////////////////////////////////////////////////
//
///////////////////////////////////////////////////////////////////////
   private void createRooms(){
	   // create all rooms
     this.office = new CastingOffice();
     this.trailer = new Trailer();


      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      try{
         DocumentBuilder builder = factory.newDocumentBuilder();
         Document doc = builder.parse("board.xml");

         NodeList roomList = doc.getElementsByTagName("*");

         String sceneName = "";
         int shotCount = 0;
         ArrayList<Role> offCardRoles = new ArrayList<Role>();
         ArrayList<String> neighbors = new ArrayList<String>();

         String roleName = "";
         String roleLevel = "";
         String roleLine = "";

         boolean create = false;

         int createCount = 0;


         for (int i = 0; i < roomList.getLength(); i++){

            Node roomNode = roomList.item(i);
            Element room = (Element) roomNode;

            // name of scene
            if (room.getTagName().equals("set")){
               if (create == true){
                  Scene current = new Scene(sceneName, shotCount, neighbors, offCardRoles);
                  scenes.add(current);
                  shotCount = 0;
                  offCardRoles.clear();
                  neighbors.clear();
                  createCount++;
               }
               sceneName = room.getAttribute("name");
               create = true;
            }
            else if (room.getTagName().equals("neighbor")){
               String neighbor = room.getAttribute("name");
               neighbors.add(neighbor);
            }
            else if (room.getTagName().equals("take")){
               String tempCount = room.getAttribute("number");
               int currCount = Integer.valueOf(tempCount);
               if (currCount > shotCount){
                  shotCount = currCount;
               }
            }
            else if (room.getTagName().equals("part")){
               roleName = room.getAttribute("name");
               roleLevel = room.getAttribute("level");
               int level = Integer.valueOf(roleLevel);
               roleLine = room.getTextContent();
               Role role = new Role(level, false, roleName, roleLine);
               offCardRoles.add(role);
            }

         }

         neighbors.clear();
         neighbors.add("Main Street");
         neighbors.add("General Store");
         neighbors.add("Bank");
         neighbors.add("trailer");
         Scene current = new Scene(sceneName, shotCount, neighbors, offCardRoles);
         scenes.add(current);
         offCardRoles.clear();
         neighbors.clear();
         createCount++;

      }
      catch(ParserConfigurationException ex){
         ex.printStackTrace();
      }
      catch(SAXException ex){
         ex.printStackTrace();
      }
      catch(IOException ex){
         ex.printStackTrace();
      }
   }// end createRooms()


}// end Controller class
