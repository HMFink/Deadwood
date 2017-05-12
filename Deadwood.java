/***********************
 * Deadwood Text Game  *
 ***********************/

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Deadwood {

	public static void main(String[] args) {
		// Start the game
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.setText("Deadwood");
		// 2-3 players and 3 days
		Button twoPlay = new Button(shell, SWT.PUSH);
		twoPlay.setText("2");
		twoPlay.addSelectionListener(new SelectionAdapter() {
		    @Override
		    public void widgetSelected(SelectionEvent e) {
		        Controller game = new Controller(2);
		    }
		});
		Button threePlay = new Button(shell, SWT.PUSH);
		threePlay.setText("3");
		threePlay.addSelectionListener(new SelectionAdapter() {
		    @Override
		    public void widgetSelected(SelectionEvent e) {
		    	Controller game = new Controller(3);
		    }
		});
		// 4 players and 4 days
		Button fourPlay = new Button(shell, SWT.PUSH);
		fourPlay.setText("4");
		fourPlay.addSelectionListener(new SelectionAdapter() {
		    @Override
		    public void widgetSelected(SelectionEvent e) {
		    	Controller game = new Controller(4);
		    }
		});
		// 5 players and 4 days (2 credit bonus)
		Button fivePlay = new Button(shell, SWT.PUSH);
		fivePlay.setText("5");
		fivePlay.addSelectionListener(new SelectionAdapter() {
		    @Override
		    public void widgetSelected(SelectionEvent e) {
		    	Controller game = new Controller(5);
		    }
		});
		// 6 players and 4 days (4 credit bonus)
		Button sixPlay = new Button(shell, SWT.PUSH);
		sixPlay.setText("6");
		sixPlay.addSelectionListener(new SelectionAdapter() {
		    @Override
		    public void widgetSelected(SelectionEvent e) {
		    	Controller game = new Controller(6);
		    }
		});
		// 7-8 players and 4 days (start at rank 2)
		Button sevenPlay = new Button(shell, SWT.PUSH);
		sevenPlay.setText("7");
		sevenPlay.addSelectionListener(new SelectionAdapter() {
		    @Override
		    public void widgetSelected(SelectionEvent e) {
		    	Controller game = new Controller(7);
		    }
		});
		Button eightPlay = new Button(shell, SWT.PUSH);
		eightPlay.setText("8");
		eightPlay.addSelectionListener(new SelectionAdapter() {
		    @Override
		    public void widgetSelected(SelectionEvent e) {
		    	Controller game = new Controller(8);
		    }
		});
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

}
