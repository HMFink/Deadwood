// Controller.java

import java.util.*;
import java.io.IOException;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;


public class Controller{

   private Controller controller = new Controller();
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
   private Controller () {}
	
   public getController (int numPlayers) {
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
      	createRooms(numPlayers);
   	return 
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
   }// getPlayers()

///////////////////////////////////////////////////////////////////////
// Function name: startGame()
// Parameters: int numPlaying
// Returns: Controller
///////////////////////////////////////////////////////////////////////
   public void startGame (int numPlaying) {
	   //playerCount = numPlaying;
   	 //Controller game = new Controller();
	   //Board gameBoard = new Board();
     //return game;
   }


   public void startDay () {
      for (int i=0; i<playerCount; i++) {
        players.get(i).setCurrRoom("trailer");
      }
      for (int i = 0; i < 10; i++){
        scenes.get(i).ChangeCard(cards.get(currentCard));
        currentCard++;
      }
   }

   public void endDay () {
     day++;
     if (day==3) {
       calcWinner();
     } else {
       startDay();
     }
   }

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

   public ArrayList<Card> getCards(){
      return cards;
   }

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

/*
               System.out.println("------------------------------------------");
               System.out.println("Name of card: " + name);
               System.out.println("Budget of card: " + budget);
               cardCount++;
*/
            }

            //scene section
            else if (card.getTagName().equals("scene")){
               cardNode = cardList.item(i);
               card = (Element) cardNode;
               sceneNum = card.getAttribute("number");
               sceneLine = card.getTextContent();
/*
               System.out.println("scene number: " + sceneNum);
               System.out.println("scene description: " + sceneLine);
*/
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
/*
               System.out.println("role name: " + roleName);
               System.out.println("role level: " + roleLevel);
               System.out.println("role line: " + roleLine);
*/
            }
         }

         int budg = Integer.valueOf(budget);
         int num = Integer.valueOf(sceneNum);
         Card current = new Card(name, budg, num, sceneLine, roles);
         cards.add(current);
         roles.clear();
         createCount++;

         //System.out.println("total cards created = " + createCount);
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


   private void createRooms(int numPlayers){
	   // create all rooms
     this.office = new CastingOffice(numPlayers);
     this.trailer = new Trailer(numPlayers);

	  // Board gameBoard = new Board();

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


/*
               System.out.println("------------------------------------------");
               System.out.println("Name of card: " + name);
               System.out.println("Budget of card: " + budget);
               cardCount++;
*/
         for (int i = 0; i < roomList.getLength(); i++){

            Node roomNode = roomList.item(i);
            Element room = (Element) roomNode;

            // name of scene
            if (room.getTagName().equals("set")){
               if (create == true){
                 System.out.println("size of neighbors list before = " + neighbors.size());
                  Scene current = new Scene(sceneName, shotCount, numPlayers, neighbors, offCardRoles);
                  scenes.add(current);
                  offCardRoles.clear();
                  neighbors.clear();
                  createCount++;
                  System.out.println("size of neighbors list after = " + neighbors.size());
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
         System.out.println("size of neighbors = " + neighbors.size());
         Scene current = new Scene(sceneName, shotCount, numPlayers, neighbors, offCardRoles);
         scenes.add(current);
         offCardRoles.clear();
         neighbors.clear();
         createCount++;
         //System.out.println("number of scenes created: " + createCount);
/*
         for (int i = 0; i < scenes.size(); i++){
           System.out.println("scene name = " + scenes.get(i).getName());
           for (int j = 0; j < scenes.get(i).getNeighbors().size(); j++){
             System.out.println("neighbor: " + scenes.get(i).getNeighbors().get(j));
           }
         }
*/
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
