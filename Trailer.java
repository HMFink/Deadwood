import java.util.ArrayList;

public class Trailer extends Room {

	// Fields
	private String name;
	private int present[];
	private int numPlay;
	private ArrayList<String> neighbors;

	// Constructor
	Trailer (int numPlayers) {

		name = "trailer";
		neighbors = new ArrayList<String>();
		neighbors.add("Main Street");
		neighbors.add("Saloon");
		neighbors.add("Hotel");

		numPlay = numPlayers;
		present = new int[numPlayers];
		// add 1 for present
		for (int i=0; i<numPlayers; i++) {
			present[i]=1;
		}
	}
	// Methods
	public ArrayList<String> getNeighbors(){
		return neighbors;
	}
}
