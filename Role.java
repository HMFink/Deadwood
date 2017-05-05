public class Role {
	// Fields
	int playerNum;
	int roleLevel;
	boolean mainOrExtra; // true if main role false if extra
	// Constructor
	Role (int level, boolean onCard) {
		roleLevel = level;
		mainOrExtra = onCard;
	}
	// Methods
	void AddPlayer (int num, int level) {
		if (level >= roleLevel) {
			playerNum = num;
		} else {
			// display error message and continue
		}
	}
	boolean MainOrExtra () {
		return mainOrExtra;
	}
}
