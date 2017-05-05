/***********************
 * Deadwood Text Game  *
 ***********************/

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class DeadwoodText {

	public static void main(String[] args) {
		// test of display
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.setText("Please enter the number of players");
		new Button(shell, SWT.RADIO);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
		
		// skeleton of the actual program
	}

}

/* NOTES *****************************************************************************************************
 * Command				Action
 * ***********************************************************************************************************
 * who					The software identifies the current player and any parts that the player is working.
 * Where				The software describes the current player’s room and any active scenes.
 * move (room)			The current player moves to the indicated room.
 * work (part)			The current player takes the indicated role.
 * upgrade ($ level) 	Upgrade the current player to the indicated level
 * upgrade (cr level)	Upgrade the current player to the indicated level.
 * Rehearse 			The current player rehearses
 * act 					The current player performs in its current role.
 * end					End the current player’s turn 
 * 
 * HELP: http://www.vogella.com/tutorials/SWT/article.html
 *************************************************************************************************************/