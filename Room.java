public class Room {
	// Fields
	int present[];
	// NO Constructor
	// Methods
	boolean CheckPlayerPresence(int playerNum) {
		if (present[playerNum-1]==1) {
			return true;
		}
		return false;
	}
	
	public void ChangePresence (int playerNum, int present) {
		this.present[playerNum-1] = present;
	}
}
