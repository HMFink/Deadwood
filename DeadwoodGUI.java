/***********************
 * Deadwood GUI Game   *
 ***********************/

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Deadwood {
	private static class Closer extends WindowAdapter {
	  public void windowClosing (WindowEvent e) {
	    System.exit(0);
	  }
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Board board = new Board();

		frame.setTitle("Deadwood");
		frame.setPreferredSize(new Dimension(600,600));
		frame.setResizable(false);
		frame.addWindowListener(new Closer());

		frame.add(board);

		frame.pack();
		frame.setVisible(true);
	}
}
