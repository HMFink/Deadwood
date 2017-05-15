import java.util.ArrayList;

public class Scene extends Room {
	// Fields
	String name;
	ArrayList<Role> roleOnCard;
	ArrayList<Role> roleOffCard;
	int shotCount;
	int budget;
	ArrayList<String> neigbors;
	Card card;

	// Constructor
	Scene (String name, int shots, int numPlayers, ArrayList<String> neighbors, ArrayList<Role> offCardRoles){
		this.name = name;
		shotCount = shots;
		roleOffCard = offCardRoles;
		int present[] = new int[numPlayers];
		// add 0 for absent
		for (int i=0; i<numPlayers; i++) {
			present[i]=0;
		}
	}
	// Methods
	boolean ActRoll (int playerNum, int rehearseCount) {
		return false;
	}
	void EndRoll () {
		// variables
		ArrayList<Integer> rolls = new ArrayList<Integer>();
		// roll and organize from highest to lowest
		// handles assigning of rewards
		AssignReward(rolls);
	}
	void AssignReward (ArrayList<Integer> diceRolls) {
		// increments each players money
	}

	void ChangeCard(Card card) {
		// assigns a card to a scene
		this.card = card;
	}

	public Card getCard(){
		return card;
	}
}
