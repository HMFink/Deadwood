import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class Board extends JFrame {

  class CustomMouseListener implements MouseListener {
    // Code for the different button clicks
    public void mouseClicked(MouseEvent e) {
      if (e.getSource() == bAct){
        System.out.println("Acting is Selected\n");
      } else if (e.getSource() == bRehearse){
        System.out.println("Rehearse is Selected\n");
      } else if (e.getSource() == bMove){
        System.out.println("Move is Selected\n");
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
    bMove = new JButton("MOVE");
    bMove.setBackground(Color.white);
    bMove.setBounds(icon.getIconWidth()+10,90,150, 20);
    bMove.addMouseListener(new CustomMouseListener());

    // Place the action buttons in the top layer
    bPane.add(bAct, new Integer(2));
    bPane.add(bRehearse, new Integer(2));
    bPane.add(bMove, new Integer(2));

  }

  public void addCard (String cardName, int x, int y) {
    cardlabel = new JLabel();
    // get name
    String card = cardName + ".png";
    ImageIcon cIcon =  new ImageIcon(cardName);
    cardlabel.setIcon(cIcon);
    cardlabel.setBounds(x,y,cIcon.getIconWidth(),cIcon.getIconHeight());
    cardlabel.setOpaque(true);
  }

  public void addPlayer (String color, String level, int x, int y) {
    playerlabel = new JLabel();
    String dieChoice = color + level + ".png";
    ImageIcon pIcon = new ImageIcon(dieChoice);
    playerlabel.setIcon(pIcon);
    playerlabel.setBounds(x,y,pIcon.getIconWidth(),pIcon.getIconHeight());
    bPane.add(playerlabel,new Integer(3));
  }


/////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
  // main method for testing purposes
  /*public static void main(String args[]){
    Board board = new Board();
    board.setVisible(true);
  }// end main*/
}// end Board class
