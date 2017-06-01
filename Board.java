import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.io.*;

public class Board extends JFrame {

  static String command = "";

  class CustomMouseListener implements MouseListener {

    // 1 for the different button clicks
    public void mouseClicked(MouseEvent e) {
      if (e.getSource() == bAct){
        command = "act";
        System.out.println("Acting is Selected\n");
      } else if (e.getSource() == bRehearse){
        command = "rehearse";
        System.out.println("Rehearse is Selected\n");
      } else if (e.getSource() == bMove){
        command = "move";
        System.out.println("Move is Selected\n");
      }
      else if (e.getSource() == bWork){
        command = "work";
      }
      else if (e.getSource() == bEnd){
        command = "end";
      }
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
  }

  // Private Attributes
  JLabel boardlabel;
  JLabel cardlabel;
  JLabel playerlabel;
  JLabel mLabel;
  // JButtons
  JButton bAct;
  JButton bRehearse;
  JButton bMove;
  JButton bWork;
  JButton bEnd;
  // JLayered Pane
  JLayeredPane bPane;

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
    mLabel.setBounds(icon.getIconWidth()+40,0,150,20);
    bPane.add(mLabel,new Integer(2));

    // Create Action buttons
    bAct = new JButton("ACT");
    bAct.setBackground(Color.white);
    bAct.setBounds(icon.getIconWidth()+10,30,150, 20);
    bAct.addMouseListener(new CustomMouseListener());

    bRehearse = new JButton("REHEARSE");
    bRehearse.setBackground(Color.white);
    bRehearse.setBounds(icon.getIconWidth()+10,60,150, 20);
    bRehearse.addMouseListener(new CustomMouseListener());

    bMove = new JButton("Move");
    bMove.setBackground(Color.white);
    bMove.setBounds(icon.getIconWidth()+10,90,150, 20);
    bMove.addMouseListener(new CustomMouseListener());

    bWork = new JButton("Work");
    bWork.setBackground(Color.white);
    bWork.setBounds(icon.getIconWidth()+10, 120,150, 20);
    bWork.addMouseListener(new CustomMouseListener());

    bEnd = new JButton("End Turn");
    bEnd.setBackground(Color.white);
    bEnd.setBounds(icon.getIconWidth()+10, 150,150, 20);
    bEnd.addMouseListener(new CustomMouseListener());

    // Place the action buttons in the top layer
    bPane.add(bAct, new Integer(2));
    bPane.add(bRehearse, new Integer(2));
    bPane.add(bMove, new Integer(2));
    bPane.add(bWork, new Integer(2));
    bPane.add(bEnd, new Integer(2));
  }


  // places a card on the board at the given x and y coordinates
  public void addCard (String cardName, int x, int y) {
    cardlabel = new JLabel();
    String card = cardName + ".png";
    ImageIcon cIcon =  new ImageIcon(card);
    cardlabel.setIcon(cIcon);
    cardlabel.setBounds(x,y,cIcon.getIconWidth(),cIcon.getIconHeight());
    bPane.add(cardlabel,new Integer(2));
    cardlabel.setOpaque(true);
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


  // creates a menu of the adjacent rooms when there are three options
  public String moveMenu(String room1, String room2, String room3){
    JPopupMenu menu = new JPopupMenu("Rooms you can move to");
    menu.add(room1);
    menu.add(room2);
    menu.add(room3);
    menu.show(bMove, bMove.getWidth(), bMove.getHeight());
    return room1;
  }


  // creates a menu of the adjacent rooms when there are four options
  public String moveMenu(String room1, String room2, String room3, String room4){
    JPopupMenu menu = new JPopupMenu("Rooms you can move to");
    menu.add(room1);
    menu.add(room2);
    menu.add(room3);
    menu.add(room4);
    menu.show(bMove, bMove.getWidth(), bMove.getHeight());
    return room1;
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
