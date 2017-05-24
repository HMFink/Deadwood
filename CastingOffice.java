import java.util.ArrayList;

public class CastingOffice {

	// Fields
	private String name;
	final int money[] = {4, 10, 18, 28, 40};
	final int credit[] = {5, 10, 15, 20, 25};
	private ArrayList<String> neighbors;

	// Constructor
	CastingOffice () {

		name = "office";
		neighbors = new ArrayList<String>();
		neighbors.add("Train Station");
		neighbors.add("Ranch");
		neighbors.add("Secret Hideout");
	}
	// Methods
	public ArrayList<String> getNeighbors(){
		return neighbors;
	}


	void Upgrade (Player p, int level, int type) {
		// variables
		int amt = 0;
		boolean enough = false;
		// check if present
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
