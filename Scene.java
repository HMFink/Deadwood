import java.util.ArrayList;

public class Scene extends Room {
	// Fields
	ArrayList<Role> roleOnCard;
	ArrayList<Role> roleOffCard;
	int shotCount;
	int budget;
	// Constructor
	Scene (int shots, int numPlayers) {
		shotCount = shots;
		roleOffCard = new ArrayList<Role>();
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
	void ChangeCard(Role[] roles, int money) {
		// changes on card roles and budget
		budget = money;
	}
}
