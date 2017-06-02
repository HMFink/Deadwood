import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Board extends JFrame {

  static String command = "";

  class CustomMouseListener implements MouseListener {

    // 1 for the different button clicks
    public void mouseClicked(MouseEvent e) {
      // cases for player actions
      if (e.getSource() == bAct){
        command = "act";
      } else if (e.getSource() == bRehearse){
        command = "rehearse";
      } else if (e.getSource() == bMove){
        command = "move";
      }
      else if (e.getSource() == bWork){
        command = "work";
      }
      else if (e.getSource() == bEnd){
        command = "end";
      }
      else if (e.getSource() == bUpgrade){
        command = "upgrade";
      }
    }



    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
  }


  class MoveListener implements ActionListener{

    public void actionPerformed(ActionEvent e){
      // cases for room selection
      if (e.getSource() == room0){
        command = "0";
      }
      else if (e.getSource() == room1){
        command = "1";
      }
      else if (e.getSource() == room2){
        command = "2";
      }
      else if (e.getSource() == room3){
        command = "3";
      }
      else if (e.getSource() == role0){
        command = "0";
      }
      else if (e.getSource() == role1){
        command = "1";
      }
      else if (e.getSource() == role2){
        command = "2";
      }
      else if (e.getSource() == role3){
        command = "3";
      }
      else if (e.getSource() == role4){
        command = "4";
      }
      else if (e.getSource() == role5){
        command = "5";
      }
      else if (e.getSource() == role6){
        command = "6";
      }
    }
  }

  class LevelListener implements ActionListener{

    public void actionPerformed(ActionEvent e){
      // cases for upgrading level
      if (e.getSource() == level2){
        command = "2";
      }
      else if (e.getSource() == level2){
        command = "3";
      }
      else if (e.getSource() == level4){
        command = "4";
      }
      else if (e.getSource() == level5){
        command = "5";
      }
      else if (e.getSource() == level6){
        command = "6";
      }
      else if (e.getSource() == money){
        command = "money";
      }
      else if (e.getSource() == credit){
        command = "credit";
      }
    }

  }

  // Private Attributes
  JLabel boardlabel;
  JLabel cardlabel;
  static JLabel cardBack;
  JLabel playerlabel;
  JLabel mLabel;
  static JLabel currPlayerLabel;
  static JLabel levelLabel;
  static JLabel moneyLabel;
  static JLabel creditLabel;
  static JLabel rehearsalLabel;
  static JLabel shotLabel;

  // JButtons
  JButton bAct;
  JButton bRehearse;
  JButton bMove;
  JButton bWork;
  JButton bEnd;
  JButton bUpgrade;

  // JLayered Pane
  JLayeredPane bPane;

  // JmenuItems for adjacent rooms when moving
  static JMenuItem room0;
  static JMenuItem room1;
  static JMenuItem room2;
  static JMenuItem room3;

  // JMenuItems for available roles
  static JMenuItem role0;
  static JMenuItem role1;
  static JMenuItem role2;
  static JMenuItem role3;
  static JMenuItem role4;
  static JMenuItem role5;
  static JMenuItem role6;
  static JMenuItem level2;
  static JMenuItem level3;
  static JMenuItem level4;
  static JMenuItem level5;
  static JMenuItem level6;
  static JMenuItem money;
  static JMenuItem credit;


  public Board() {
    // set the title of the JFrame
    super("Deadwood");

    // Set the exit option for the JFrame
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    // create the JLayeredPane to hold the display, cards, role dice and buttons
    bPane = getLayeredPane();

    // create the Deadwood boardLabel
    boardlabel = new JLabel();
    ImageIcon icon = new ImageIcon("board.jpg");
    boardlabel.setIcon(icon);
    boardlabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());

    // Add the board to the lower layer
    bPane.add(boardlabel, new Integer(0));

    // set the size of the GUI
    setSize(icon.getIconWidth() +200, icon.getIconHeight());

    // Create the Menu for action buttons
    mLabel = new JLabel("MENU");
    mLabel.setBounds(icon.getIconWidth()+60,0,150,20);
    bPane.add(mLabel,new Integer(2));

    // Create Action buttons
    bAct = new JButton("ACT");
    bAct.setBackground(Color.cyan);
    bAct.setBounds(icon.getIconWidth()+10,30,150, 20);
    bAct.addMouseListener(new CustomMouseListener());

    bRehearse = new JButton("REHEARSE");
    bRehearse.setBackground(Color.cyan);
    bRehearse.setBounds(icon.getIconWidth()+10,70,150, 20);
    bRehearse.addMouseListener(new CustomMouseListener());

    bMove = new JButton("Move");
    bMove.setBackground(Color.cyan);
    bMove.setBounds(icon.getIconWidth()+10,110,150, 20);
    bMove.addMouseListener(new CustomMouseListener());

    bWork = new JButton("Work");
    bWork.setBackground(Color.cyan);
    bWork.setBounds(icon.getIconWidth()+10, 150,150, 20);
    bWork.addMouseListener(new CustomMouseListener());

    bUpgrade = new JButton("Upgrade level");
    bUpgrade.setBackground(Color.cyan);
    bUpgrade.setBounds(icon.getIconWidth()+10, 190, 150, 20);
    bUpgrade.addMouseListener(new CustomMouseListener());

    bEnd = new JButton("End Turn");
    bEnd.setBackground(Color.cyan);
    bEnd.setBounds(icon.getIconWidth()+10, 230, 150, 20);
    bEnd.addMouseListener(new CustomMouseListener());


    // Place the action buttons in the top layer
    bPane.add(bAct, new Integer(2));
    bPane.add(bRehearse, new Integer(2));
    bPane.add(bMove, new Integer(2));
    bPane.add(bWork, new Integer(2));
    bPane.add(bEnd, new Integer(2));
    bPane.add(bUpgrade, new Integer(2));

    //display for current player stats

  }


  public void removeCard(Scene scene){
    scene.getImage().setVisible(false);
  }

  public void flipCard(Scene scene){

    scene.getCardBack().setVisible(false);
  }



  // places a card on the board at the given x and y coordinates
  public void addCard (String cardName, int x, int y, Scene scene) {
    cardlabel = new JLabel();
    String card = cardName + ".png";
    ImageIcon cIcon =  new ImageIcon(card);
    cardlabel.setIcon(cIcon);
    cardlabel.setBounds(x,y,cIcon.getIconWidth(),cIcon.getIconHeight());
    bPane.add(cardlabel,new Integer(2));
    cardlabel.setOpaque(true);
    scene.setImage(cardlabel);

    cardBack = new JLabel();
    ImageIcon cbIcon = new ImageIcon("cardBack.png");
    cardBack.setIcon(cbIcon);
    cardBack.setBounds(x, y, 205, 115);
    bPane.add(cardBack, new Integer(3));
    cardBack.setOpaque(true);
    scene.setCardBack(cardBack);

  }

  public void setShotCounters(ArrayList<Scene> scenes){

    for (int i = 0; i < scenes.size(); i++){
      ArrayList<Integer> shotList = scenes.get(i).getShotList();
      for (int j = 0; j < shotList.size(); j+=2){
        shotLabel = new JLabel();
        ImageIcon sIcon = new ImageIcon("shot_counter.png");
        shotLabel.setIcon(sIcon);
        shotLabel.setBounds(shotList.get(j)+5, shotList.get(j+1)+5, sIcon.getIconWidth(), sIcon.getIconHeight());
        bPane.add(shotLabel, new Integer(2));
        shotLabel.setOpaque(true);
        scenes.get(i).addShotCounter(shotLabel);
      }
    }
  }

  public void removeShotCounter(Scene scene){
    scene.getShotImages().get(0).setVisible(false);
    scene.getShotImages().remove(0);
  }

  // moves a player to the given x and y coordinates
  public void setPlayer (String color, String level, int x, int y, Player player) {
    if (player.getImage() != null){
      player.getImage().setIcon(null);
    }
    playerlabel = new JLabel();
    String dieChoice = color + level + ".png";
    ImageIcon pIcon = new ImageIcon(dieChoice);
    playerlabel.setIcon(pIcon);
    playerlabel.setBounds(x,y,pIcon.getIconWidth(),pIcon.getIconHeight());
    bPane.add(playerlabel,new Integer(3));
    player.setImage(playerlabel);
  }

  // display and update player stats on sidebar
  public void displayStats(Player player){

    currPlayerLabel = new JLabel("Player: " + player.getName());
    currPlayerLabel.setBounds(1208, 230, 150, 150);
    bPane.add(currPlayerLabel, new Integer(2));

    levelLabel = new JLabel("Level: " + player.getLevel());
    levelLabel.setBounds(1208, 265, 150, 150);
    bPane.add(levelLabel, new Integer(2));

    moneyLabel = new JLabel("Money: " + player.getMoney());
    moneyLabel.setBounds(1208, 305, 150, 150);
    bPane.add(moneyLabel, new Integer(2));

    creditLabel = new JLabel("Credit: " + player.getCredit());
    creditLabel.setBounds(1208, 345, 150, 150);
    bPane.add(creditLabel, new Integer(2));

    rehearsalLabel = new JLabel("Rehearsals: " + player.getRehearsals());
    rehearsalLabel.setBounds(1208, 385, 150, 150);
    bPane.add(rehearsalLabel, new Integer(2));
  }


  public void updateStats(Player player){

    bPane.remove(currPlayerLabel);
    currPlayerLabel = new JLabel("Player: " + player.getName());
    currPlayerLabel.setBounds(1208, 230, 150, 150);
    bPane.add(currPlayerLabel, new Integer(2));

    bPane.remove(levelLabel);
    levelLabel = new JLabel("Level: " + player.getLevel());
    levelLabel.setBounds(1208, 265, 150, 150);
    bPane.add(levelLabel, new Integer(2));

    bPane.remove(moneyLabel);
    moneyLabel = new JLabel("Money: " + player.getMoney());
    moneyLabel.setBounds(1208, 305, 150, 150);
    bPane.add(moneyLabel, new Integer(2));

    bPane.remove(creditLabel);
    creditLabel = new JLabel("Credit: " + player.getCredit());
    creditLabel.setBounds(1208, 345, 150, 150);
    bPane.add(creditLabel, new Integer(2));

    bPane.remove(rehearsalLabel);
    rehearsalLabel = new JLabel("Rehearsals: " + player.getRehearsals());
    rehearsalLabel.setBounds(1208, 385, 150, 150);
    bPane.add(rehearsalLabel, new Integer(2));
  }


  // creates a menu of the adjacent rooms when there are three options
  public void moveMenu(String r1, String r2, String r3){

    JPopupMenu menu = new JPopupMenu("Rooms you can move to");

    room0 = new JMenuItem(r1);
    room1 = new JMenuItem(r2);
    room2 = new JMenuItem(r3);
    menu.add(room0);
    menu.add(room1);
    menu.add(room2);
    room0.addActionListener(new MoveListener());
    room1.addActionListener(new MoveListener());
    room2.addActionListener(new MoveListener());
    menu.show(bMove, bMove.getWidth(), bMove.getHeight());
  }


  // creates a menu of the adjacent rooms when there are four options
  public void moveMenu(String r1, String r2, String r3, String r4){

    JPopupMenu menu = new JPopupMenu("Rooms you can move to");

    room0 = new JMenuItem(r1);
    room1 = new JMenuItem(r2);
    room2 = new JMenuItem(r3);
    room3 = new JMenuItem(r4);
    menu.add(room0);
    menu.add(room1);
    menu.add(room2);
    menu.add(room3);
    room0.addActionListener(new MoveListener());
    room1.addActionListener(new MoveListener());
    room2.addActionListener(new MoveListener());
    menu.show(bMove, bMove.getWidth(), bMove.getHeight());
  }

  // creates a menu of available roles for 1 option
  public void workMenu(String r1){

    JPopupMenu menu = new JPopupMenu("Available roles");
    role0 = new JMenuItem(r1);
    menu.add(role0);
    role0.addActionListener(new MoveListener());
    menu.show(bWork, bWork.getWidth(), bWork.getHeight());
  }

  // creates a menu of available roles for 2 options
  public void workMenu(String r1, String r2){

    JPopupMenu menu = new JPopupMenu("Available roles");
    role0 = new JMenuItem(r1);
    role1 = new JMenuItem(r2);
    menu.add(role0);
    menu.add(role1);
    role0.addActionListener(new MoveListener());
    role1.addActionListener(new MoveListener());
    menu.show(bWork, bWork.getWidth(), bWork.getHeight());
  }

  // creates a menu of available roles for 3 options
  public void workMenu(String r1, String r2, String r3){

    JPopupMenu menu = new JPopupMenu("Available roles");
    role0 = new JMenuItem(r1);
    role1 = new JMenuItem(r2);
    role2 = new JMenuItem(r3);
    menu.add(role0);
    menu.add(role1);
    menu.add(role2);
    role0.addActionListener(new MoveListener());
    role1.addActionListener(new MoveListener());
    role2.addActionListener(new MoveListener());
    menu.show(bWork, bWork.getWidth(), bWork.getHeight());
  }

  // creates a menu of available roles for 4 options
  public void workMenu(String r1, String r2, String r3, String r4){

    JPopupMenu menu = new JPopupMenu("Available roles");
    role0 = new JMenuItem(r1);
    role1 = new JMenuItem(r2);
    role2 = new JMenuItem(r3);
    role3 = new JMenuItem(r4);
    menu.add(role0);
    menu.add(role1);
    menu.add(role2);
    menu.add(role3);
    role0.addActionListener(new MoveListener());
    role1.addActionListener(new MoveListener());
    role2.addActionListener(new MoveListener());
    role3.addActionListener(new MoveListener());
    menu.show(bWork, bWork.getWidth(), bWork.getHeight());
  }

  // creates a menu of available roles for 5 options
  public void workMenu(String r1, String r2, String r3, String r4, String r5){

    JPopupMenu menu = new JPopupMenu("Available roles");
    role0 = new JMenuItem(r1);
    role1 = new JMenuItem(r2);
    role2 = new JMenuItem(r3);
    role3 = new JMenuItem(r4);
    role4 = new JMenuItem(r5);
    menu.add(role0);
    menu.add(role1);
    menu.add(role2);
    menu.add(role3);
    menu.add(role4);
    role0.addActionListener(new MoveListener());
    role1.addActionListener(new MoveListener());
    role2.addActionListener(new MoveListener());
    role3.addActionListener(new MoveListener());
    role4.addActionListener(new MoveListener());
    menu.show(bWork, bWork.getWidth(), bWork.getHeight());
  }

  // creates a menu of available roles for 6 options
  public void workMenu(String r1, String r2, String r3, String r4, String r5, String r6){

    JPopupMenu menu = new JPopupMenu("Available roles");
    role0 = new JMenuItem(r1);
    role1 = new JMenuItem(r2);
    role2 = new JMenuItem(r3);
    role3 = new JMenuItem(r4);
    role4 = new JMenuItem(r5);
    role5 = new JMenuItem(r6);
    menu.add(role0);
    menu.add(role1);
    menu.add(role2);
    menu.add(role3);
    menu.add(role4);
    menu.add(role5);
    role0.addActionListener(new MoveListener());
    role1.addActionListener(new MoveListener());
    role2.addActionListener(new MoveListener());
    role3.addActionListener(new MoveListener());
    role4.addActionListener(new MoveListener());
    role5.addActionListener(new MoveListener());
    menu.show(bWork, bWork.getWidth(), bWork.getHeight());
  }

  // creates a menu of available roles for 7 options
  public void workMenu(String r1, String r2, String r3, String r4, String r5, String r6, String r7){

    JPopupMenu menu = new JPopupMenu("Available roles");
    role0 = new JMenuItem(r1);
    role1 = new JMenuItem(r2);
    role2 = new JMenuItem(r3);
    role3 = new JMenuItem(r4);
    role4 = new JMenuItem(r5);
    role5 = new JMenuItem(r6);
    role6 = new JMenuItem(r7);
    menu.add(role0);
    menu.add(role1);
    menu.add(role2);
    menu.add(role3);
    menu.add(role4);
    menu.add(role5);
    menu.add(role6);
    role0.addActionListener(new MoveListener());
    role1.addActionListener(new MoveListener());
    role2.addActionListener(new MoveListener());
    role3.addActionListener(new MoveListener());
    role4.addActionListener(new MoveListener());
    role5.addActionListener(new MoveListener());
    role6.addActionListener(new MoveListener());
    menu.show(bWork, bWork.getWidth(), bWork.getHeight());
  }

  public void upgradeMenu(){

    JPopupMenu menu = new JPopupMenu("levels");
    level2 = new JMenuItem("level 2");
    level3 = new JMenuItem("level 3");
    level4 = new JMenuItem("level 4");
    level5 = new JMenuItem("level 5");
    level6 = new JMenuItem("level 6");
    menu.add(level2);
    menu.add(level3);
    menu.add(level4);
    menu.add(level5);
    menu.add(level6);
    level2.addActionListener(new LevelListener());
    level3.addActionListener(new LevelListener());
    level4.addActionListener(new LevelListener());
    level5.addActionListener(new LevelListener());
    level6.addActionListener(new LevelListener());
    menu.show(bUpgrade, bUpgrade.getWidth(), bUpgrade.getHeight());
  }

  public void paymentMenu(){

    JPopupMenu payment = new JPopupMenu();
    money = new JMenuItem("Money");
    credit = new JMenuItem("Credit");
    payment.add(money);
    payment.add(credit);
    money.addActionListener(new LevelListener());
    credit.addActionListener(new LevelListener());
    payment.show(bUpgrade, bUpgrade.getWidth(), bUpgrade.getHeight());
  }



  public String getCommand(){
    //String temp = command;
    //command = "";
    return command;
  }

  public void clearCommand(){
    command = "";
  }


/////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
  // main method for testing purposes
  /*public static void main(String args[]){
    Board board = new Board();
    board.setVisible(true);
  }// end main*/
}// end Board class
