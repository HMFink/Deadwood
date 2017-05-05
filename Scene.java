import java.util.ArrayList;

public class Scene extends Room {
	// Fields
	ArrayList<Role> roleOnCard;
	ArrayList<Role> roleOffCard;
	int shotCount;
	int budget;
	// Constructor
	Scene (int shots) {
		shotCount = shots;
		roleOffCard = new ArrayList<Role>();
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
