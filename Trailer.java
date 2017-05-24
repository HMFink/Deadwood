import java.util.ArrayList;

public class Trailer {

	// Fields
	private String name;
	private ArrayList<String> neighbors;

	// Constructor
	public Trailer () {

		name = "trailer";
		neighbors = new ArrayList<String>();
		neighbors.add("Main Street");
		neighbors.add("Saloon");
		neighbors.add("Hotel");
	}

	// Methods
	public ArrayList<String> getNeighbors(){
		return neighbors;
	}

}
