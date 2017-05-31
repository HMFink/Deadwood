import java.util.ArrayList;

public class Trailer {

	// Fields
	private String name;
	private ArrayList<String> neighbors;
	int x;
	int y;

	// Constructor
	public Trailer () {

		name = "trailer";
		neighbors = new ArrayList<String>();
		neighbors.add("Main Street");
		neighbors.add("Saloon");
		neighbors.add("Hotel");
		x = 991;
		y = 237;
	}

	// Methods
	public ArrayList<String> getNeighbors(){
		return neighbors;
	}

}
