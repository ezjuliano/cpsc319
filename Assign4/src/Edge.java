
public class Edge {
	Vertex prev;	// source
	Vertex next;	// destination
	
	Edge(Vertex from, Vertex to) {
		prev = from;
		next = to;
	}
	
	public Vertex getNeighbour(int index) {
		if(prev.key == index)
			return next;
		return prev;
	}
}
