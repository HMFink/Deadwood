public class Role {
	// Fields
	int playerNum;
	int roleLevel;
	String roleName;
	String line;
	boolean mainOrExtra; // true if main role false if extra

	// Constructor
	Role (int level, boolean onCard, String name, String line) {
		roleLevel = level;
		mainOrExtra = onCard;
		roleName = name;
		this.line = line;
		playerNum = -1;
	}

	// Methods
	public void addPlayer (int num){
		this.playerNum = num;
	}

	public int getPlayerNum(){
		return playerNum;
	}

	public String getName(){
		return roleName;
	}

	public int getLevel(){
		return roleLevel;
	}

	public boolean mainOrExtra () {
		return mainOrExtra;
	}

}
