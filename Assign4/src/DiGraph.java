
public class DiGraph {
	int V;	// number of vertices
	Vertex [] adjacent;	// array of adjacent vertices
	Vertex [] weightedAdj;	// array of adjacent vertices with weights
	
	DiGraph(int numofVertices) {
		V = numofVertices;
		adjacent = new Vertex[V];
		weightedAdj = new Vertex[V];	
		for(int i = 0; i < V; i++) {
			adjacent[i] = new Vertex(i);
			weightedAdj[i] = new Vertex(i);
		}
	}
	
	public void addEdge(Vertex from, Vertex to) {
		if(from.outgoingEdge[0] == null)	// if no connection
			from.outgoingEdge[0] = new Edge(from,to);
		else {	// if there's a connection already add another one
			int len = from.outgoingEdge.length;
			Edge[] temp = new Edge[len + 1];
			int index = 0;
			while(index < len) {
				temp[index] = from.outgoingEdge[index];
				index++;
			}
			temp[index] = new Edge(from,to);
			from.outgoingEdge = temp;
		}
	}
	
	public void addWeightedEdge(Vertex from, Vertex to, int weight) {
		if(from.outgoingWeightedEdge[0] == null)	// if no connection
			from.outgoingWeightedEdge[0] = new WeightedEdge(from, to, weight);
		else {	//if there's a connection already
			int len = from.outgoingWeightedEdge.length;
			WeightedEdge[] temp = new WeightedEdge[len + 1];
			int index = 0;
			while(index < len) {
				temp[index] = from.outgoingWeightedEdge[index];
				index++;
			}
			temp[index] = new WeightedEdge(from, to, weight);
			from.outgoingWeightedEdge = temp;
		}
	}
}
