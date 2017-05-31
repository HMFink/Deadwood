public class Role {
	// Fields
	int playerNum;
	int roleLevel;
	String roleName;
	int x;
	int y;
	String line;
	boolean mainOrExtra; // true if main role false if extra

	// Constructor
	Role (int level, boolean onCard, String name, int x, int y, String line) {
		roleLevel = level;
		mainOrExtra = onCard;
		roleName = name;
		this.line = line;
		playerNum = -1;
		this.x  = x;
		this.y = y;
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

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

}
