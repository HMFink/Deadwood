import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class Scene {
	// Fields
	private String name;
	private ArrayList<Role> offCardRoles;
	private int shotCount;
	private int budget;
	private ArrayList<String> neighbors;
	private Card card;
	private boolean wrapped;
	private int x;
	private int y;
	private JLabel image;
	private JLabel cardBack;
	private ArrayList<Integer> shotList;
	private ArrayList<JLabel> shotImages;

	// Constructor
	Scene (String name, int shots, ArrayList<String> neighList, ArrayList<Role> offRoles, int x, int y, ArrayList<Integer> shotCounters){
		this.name = name;
		shotCount = shots;
		this.neighbors = new ArrayList<String>(neighList);
		//this.neighbors.addAll(neighList);
		offCardRoles = new ArrayList<Role>(offRoles);
		wrapped = false;
		this.x = x;
		this.y = y;
		image = null;
		this.shotList = new ArrayList<Integer>(shotCounters);
		shotImages = new ArrayList<JLabel>();
	}

///////////////////////////////////////////////////////////////////////
// Getters & setters
///////////////////////////////////////////////////////////////////////

public void addShotCounter(JLabel image){
	shotImages.add(image);
}

public ArrayList<JLabel> getShotImages(){
	return shotImages;
}

public ArrayList<Integer> getShotList(){
	return shotList;
}

public void setCardBack(JLabel image){
	this.cardBack = image;
}

public JLabel getCardBack(){
	return cardBack;
}
	public void setImage(JLabel image){
		this.image = image;
	}

	public JLabel getImage(){
		return image;
	}

	public String getName(){
		return name;
	}

	public ArrayList<Role> getOffCardRoles(){
		return offCardRoles;
	}

	public int getShotCount(){
		return shotCount;
	}

	public int getBudget(){
		return budget;
	}

	public ArrayList<String> getNeighbors(){
		return neighbors;
	}

	void ChangeCard(Card card) {
		// assigns a card to a scene
		this.card = card;
	}

	public Card getCard(){
		return card;
	}

	public void removeShotCounter(){
		shotCount--;
	}

	public void wrapScene(){
		wrapped = true;
	}

	public boolean getWrap(){
		return wrapped;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}
}
