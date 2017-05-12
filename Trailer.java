public class Trailer extends Room {
	// Fields
	int numPlay = 0;
	// Constructor
	Trailer (int numPlayers) {
		numPlay = numPlayers;
		// add 1 for present
		for (int i=0; i<numPlayers; i++) {
			present[i]=1;
		}
	}
	// Methods
	public void Reset() {
		// change all player positions to the trailer
		for (int i=0; i<numPlay; i++) {
			present[i]=1;
		}
	}
}
