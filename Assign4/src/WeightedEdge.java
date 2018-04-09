
public class WeightedEdge extends Edge {
	
	int weight;	// edge weight

	WeightedEdge(Vertex from, Vertex to, int weight) {
		super(from, to);
		this.weight = weight;
	}

}
