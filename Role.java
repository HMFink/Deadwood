public class Role {
	// Fields
	int playerNum;
	int roleLevel;
	String roleName;
	boolean mainOrExtra; // true if main role false if extra
	// Constructor
	Role (int level, boolean onCard, String name) {
		roleLevel = level;
		mainOrExtra = onCard;
		roleName = name;
	}
	// Methods
	public void AddPlayer (int num, int level) {
		if (level >= roleLevel) {
			playerNum = num;
		} else {
			// display error message and continue
		}
	}
	public boolean MainOrExtra () {
		return mainOrExtra;
	}
	
	public String getName () {
		return roleName;
	}
}
