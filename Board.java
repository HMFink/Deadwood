import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;

public class Board extends JFrame {
  private JLabel boardlabel;
  private JLabel cardlabel;
  private JLabel playerLabel;
  private JLabel mLabel;

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
    mLabel.setBounds(icon.getIconWidth()+40,0,100,20);
    bPane.add(mLabel,new Integer(2));
/*
    // Create Action buttons
    bAct = new JButton("ACT");
    bAct.setBackground(Color.white);
    bAct.setBounds(icon.getIconWidth()+10, 30,100, 20);
    bAct.addMouseListener(new boardMouseListener());
    bRehearse = new JButton("REHEARSE");
    bRehearse.setBackground(Color.white);
    bRehearse.setBounds(icon.getIconWidth()+10,60,100, 20);
    bRehearse.addMouseListener(new boardMouseListener());
    bMove = new JButton("MOVE");
    bMove.setBackground(Color.white);
    bMove.setBounds(icon.getIconWidth()+10,90,100, 20);
    bMove.addMouseListener(new boardMouseListener());

    // Place the action buttons in the top layer
    bPane.add(bAct, new Integer(2));
    bPane.add(bRehearse, new Integer(2));
    bPane.add(bMove, new Integer(2));
*/

  }


/////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
  // main method for testing purposes
  public static void main(String args[]){
    Board board = new Board();
    board.setVisible(true);
  }// end main
}// end Board class
