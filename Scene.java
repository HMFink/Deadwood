import java.util.ArrayList;

public class Scene {
	// Fields
	String name;
	ArrayList<Role> offCardRoles;
	int shotCount;
	int budget;
	ArrayList<String> neighbors;
	Card card;
	boolean wrapped;
	int x;
	int y;

	// Constructor
	Scene (String name, int shots, ArrayList<String> neighList, ArrayList<Role> offRoles, int x, int y){
		this.name = name;
		shotCount = shots;
		this.neighbors = new ArrayList<String>(neighList);
		//this.neighbors.addAll(neighList);
		offCardRoles = new ArrayList<Role>(offRoles);
		wrapped = false;
		this.x = x;
		this.y = y;
	}

///////////////////////////////////////////////////////////////////////
// Getters & setters
///////////////////////////////////////////////////////////////////////

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
