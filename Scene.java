import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Scene {
	// Fields
	String name;
	ArrayList<Role> offCardRoles;
	int shotCount;
	int budget;
	ArrayList<String> neighbors;
	Card card;
	Random rand = new Random();

	// Constructor
	Scene (String name, int shots, int numPlayers, ArrayList<String> neighList, ArrayList<Role> offCardRoles){
		this.name = name;
		shotCount = shots;
		this.neighbors = new ArrayList<String>(neighList);
		//this.neighbors.addAll(neighList);
		this.offCardRoles = offCardRoles;
		int present[] = new int[numPlayers];
		// add 0 for absent
		for (int i=0; i<numPlayers; i++) {
			present[i]=0;
		}
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
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
		// Methods
	public boolean ActRoll (int playerNum, int rehearseCount) {
		int roll = rand.nextInt(6)+1; //returns 0-5 plus 1 for 1-6
		roll += rehearseCount; //to account for rehearsing bonus
		if (roll >= budget) {
			shotCount--;
			if (shotCount) {
				EndRoll();
			}
			return true;
		}
		return false;
	}

	private void EndRoll () {
		// variables
		ArrayList<Integer> rolls = new ArrayList<Integer>();
		// check if someone is working an on-card role
		ArrayList<Roles> playerList = card.getRoles();
		for (int j=0; j<playerList.size(); j++) {
			if (playerList.get(j).getNum()!=-1) {
				// roll and organize from highest to lowest
				for (int i=0; i<budget; i++) {
					rolls.add(rand.nextInt(6)+1);
				}
				Collections.sort(rolls);
				Collections.reverse(rolls);
				// handles assigning of rewards
				AssignReward(rolls, playerList.size());
				break;
			}
		}
	}

	private void AssignReward (ArrayList<Integer> diceRolls, ArrayList<Roles> roles) {
		Controller control = getController();
		ArrayList<Player> players = control.getPlayers();
		int numRoles = roles.size();
		// increments each players money on the card
		for (int i=0; i<numRoles; i++) {
			if (roles.get(i)!=-1) { // player is on this role
				players.get(roles.get(i)).changeMoney(diceRolls.get(i));
			}
		}
		// increments each players money off the card
		numRoles = offCardRoles.size();
		for (int i=0; i<numRoles; i++) {
			if (offCardRoles.get(i)!=-1) { // player is on this role
				players.get(offCardRoles.get(i)).changeMoney(offCardRoles.get(i).getLevel());
			}
		}
	}

}
