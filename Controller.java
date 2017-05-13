// Controller.java

import java.util.*;

public class Controller{

   private int day = 0;
   private int sceneCount = 0;
   private int playerCount = 0;
   private Arraylist<Card> cards;

   // Controller constructor
   public Controller(int num){
      day = 0;
      sceneCount = 10;
      playerCount = num;
      createCards();
      createPlayers();
      createRooms();
   }

   private void createPlayers () {
	   for (int i=0; i<playerCount; i++) {

	   }
   }

   private void createRooms () {
	   // create all rooms
	   CastingOffice office = new CastingOffice(playerCount);
	   Trailer start = new Trailer(playerCount);
	   Board gameBoard = new Board();
   }

   public void decrementScene(){
      sceneCount--;
   }

   public void startDay () {
	   //Reset();
   }

   public void startTurn () {}

   public void endTurn () {}

   public void endDay () {}

   public void endGame () {}

   public void calcWinner () {}


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

/*             System.out.println("scene number: " + sceneNum);
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
         System.out.println("total cards created = " + createCount);
         return cards;
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

}// end Controller class
