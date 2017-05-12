public class CastingOffice extends Room {
	// Fields
	final int money[] = {4, 10, 18, 28, 40};
	final int credit[] = {5, 10, 15, 20, 25};
	// Constructor
	CastingOffice (int numPlayers) {
		// add 0 for absent
		for (int i=0; i<numPlayers; i++) {
			present[i]=0;
		}
	}
	// Methods
	void Upgrade (Player p, int level, int type) {
		// variables
		int amt = 0;
		boolean enough = false;
		// check if present
		int num = p.getIdNum();
		if (present[num]==1) {
			// determine credits or money
			if (type == 0) { // 0 is money
				amt = p.getMoney();
			} else { // anything non-zero is credits
				amt = p.getCredit();
			}
			// checks if enough credits/money
			enough = CheckMoneyCredit(amt, level, type);
			// upgrades player stats
			if (enough) {
				// do upgrade and return
				p.setLevel(level);
			} else {
				// display error message and continue
				//dispMessage("Not enough credits/money. Try again with other type.");
			}
		} else {
			// display error message and continue
			//dispMessage("Not in the casting office!");
		}
	}
	boolean CheckMoneyCredit (int amount, int level, int type) {
		// determine amount required
		int required = 0;
		if (type == 0) { // 0 is money
			required = money[level-2];
		} else { // anything non-zero is credits
			required = credit[level-2];
		}
		// determine if amount given is enough
		if (amount < required) {
			return true;
		} else {
			return false;
		}
	}
}
