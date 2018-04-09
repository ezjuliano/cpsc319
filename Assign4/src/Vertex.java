
public class Vertex {
	final int key;	// name of the vertex
	int distance = Integer.MAX_VALUE;	// distance from a vertex
	Edge[] outgoingEdge;	// neighbouring edges
	WeightedEdge[] outgoingWeightedEdge;	// neighbouring edges with weights
	boolean visited;	// indicates whether or not the vertex has been visited
	
	Vertex(int key) {
		this.key = key;
		outgoingEdge = new Edge[1];
		outgoingWeightedEdge = new WeightedEdge[1];
		visited = false;
	}
	
	public void setDistance(int d) {distance = d;}
}
