public class CastingOffice extends Room {
	// Fields
	// Constructor
	CastingOffice () {
		// any important info
	}
	// Methods
	void Upgrade (Player p) {
		// variables
		int amt = 0; // assigned the value or credit/money depending on type
		boolean enough = false;
		// checks if enough credits/money
		enough = CheckMoneyCredit(amt);
		// upgrades player stats
		if (enough) {
			// do upgrade and return
		} else {
			// display error message and continue
		}
	}
	boolean CheckMoneyCredit (int amount) {
		return false;
	}
}
