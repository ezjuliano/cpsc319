
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Public class for Assignment#3 
 * Description: <Some details regarding the pipeline of the program + classes..>
 * @author John Ezekiel Juliano, 30000523
 * @version 1.0
 */

public class Assign4 {
	Matrix2D adjacencyMatrix;
	Matrix2D weightedAdjacencyMatrix;
    Matrix2D queryList;
    Matrix2D weightedQueryList;
    boolean pathFound;
    /**
     * Converts String to integer array
     * @param str input string
     * @return integer array
     */
    public static int[] StringtoIntArr(String str) {
        // Replaces new-line by "" and splits each spaced integer to String
        String[] stringLine = str.replace("\n", "").replace("\r", "")
                .split("\t");
        // Creates the integer array
        int[] intArr = new int[stringLine.length];
        for (int i = 0; i < intArr.length; i++) {
            // Parses the integer for each string
            intArr[i] = Integer.parseInt(stringLine[i]);
        }
        return intArr;
    }
    /**
     * Reads from input file and returns 2D Matrix
     * 
     * @param inputFile
     *            - the File to read from
     * @return Matrix2D - 2D integer array
     * @throws IOException
     */
    public static Matrix2D read2DMatrix(String inputFile) throws IOException {
        BufferedReader br = null;
        Matrix2D mat2D = new Matrix2D();
        try {
            br = new BufferedReader(new FileReader(inputFile));
            // Read the first line to get the no. of columns
            String str = br.readLine();
            int[] intArr = StringtoIntArr(str);
            int col = intArr.length; // number of columns
            int i = 0; // i is row index of 2D matrix
            while (str != null) {
                intArr = StringtoIntArr(str);	// parses the read line
                for (int j = 0; j < col; j++)
                    mat2D.addValue(i, j, intArr[j]); // j is the column index
                str = br.readLine();	// reads a new line from the input file
                i++;
            }
        } catch (IOException err) {
            System.err.println("An I/O error occurred.");
        } finally {
            if (br != null) {
                br.close();
            } else
                System.err.println("BufferedReader could not be opened.");
        }
        return mat2D;
    }
    
    public void clearFile(String filename) throws IOException {
    	File file = new File(filename);
    	if(!file.exists())
    		file.createNewFile();
    	else
    		file.delete();
    }
    
    public void printDFS(Vertex[] adj, int start, int end) {
    	String text = "";
    	pathFound = false;
    	int index = 0;
    	while(index < adj.length) {
    		adj[index].visited = false;
    		index++;
    	}
    	try {
			DFS(adj,adj[start],adj[end],text);
		} catch (NullPointerException|IOException e) {
			e.printStackTrace();
		}
    	if(!pathFound) {
    		System.out.print(start + "   => -1  => " + end);
    	}
    	System.out.println();    	
    }
    
    public void DFS(Vertex[] adj, Vertex source, Vertex sink, String text) throws IOException, NullPointerException {
    	if(source.visited) {
    		return;
    	}
    	else {
    		source.visited = true;
    		String temp = Integer.toString(source.key);
    		while(temp.length() < 3)
    			temp += " ";
    		if(source.key != sink.key)
    			temp += " => ";
    		text += temp;
    		int index = 0;
    		if(source.key == sink.key) {
    			pathFound = true;
	    		while(index < adj.length) {
	    			adj[index].visited = true;
	    			index++;
	    		}
	    		System.out.print(text);
    		}
    		else if(source.outgoingEdge[0] != null) {
    			while(index < source.outgoingEdge.length) {
    				DFS(adj, source.outgoingEdge[index].next, sink, text);
    				index++;
    			}
    		}
    	}
    }
    
    public void printBFS(Vertex[] adj, int start, int end) throws IOException, NullPointerException {
    	if(adj == null)
    		return;
    	Queue queue = new Queue();
    	Vertex current = null;
    	int index = 0;
    	while(index < adj.length) {
    		adj[index].visited = false;
    		index++;
    	}
    	queue.enqueue(adj[start]);
    	String text = "";
    	boolean found = false;
    	while(!queue.isEmpty()) {
    		current = queue.dequeue();
    		String temp = Integer.toString(current.key);
    		while(temp.length() < 3)
    			temp += " ";
    		if(current.key != adj[end].key)
    			temp += " => ";
    		text += temp;
    		index = 0;
    		if(current.outgoingEdge[index] != null) {
    			while(index < current.outgoingEdge.length) {
    				if(!current.outgoingEdge[index].next.visited) {
    					current.outgoingEdge[index].next.visited = true;
    					queue.enqueue(current.outgoingEdge[index].next);
    				}
    				if(current.outgoingEdge[index].next.key == adj[end].key) {
    					int i = 0;
    					while(i < adj.length) {
    						adj[i].visited = true;
    						i++;
    					}
    					found = true;
    				}
    				index++;
    			}
    		}
    	}
    	if(!found)
    		text = start + "   => " + "-1" + "  => " + end;
    	System.out.println(text);
    }
    
    public void dijkstra(Matrix2D mat, int source, int destination) {
    	int V = mat.getNumberOfRows();
    	int[] distance = new int[V];
    	boolean shortestPath[] = new boolean[V];
    	for(int i = 0; i < V; i++){
    		distance[i] = Integer.MAX_VALUE;
    		shortestPath[i] = false;
    	}
    	distance[source] = 0;
    	System.out.print(source + " => ");
    	for(int index = 0; index < V; index++) {
    		int u = minDist(distance,shortestPath);
    		shortestPath[u] = true;
    		for(int v = 0; v < V; v++) {
    			if(!shortestPath[v] && mat.getValue(u,v) != 0
    				&& distance[u] != Integer.MAX_VALUE && (distance[u] + mat.getValue(u,v) < distance[v])) {
    				distance[v] = distance[u] + mat.getValue(u,v);
    				System.out.print(v + " => ");
    				if(v == destination)
    					break;
    			}
    			
    		}
    	}
    	System.out.println(destination);
    }

    public int minDist(int[] distance, boolean[] shortestPath) {
    	int min = Integer.MAX_VALUE;
    	int index = -1;
    	for(int v = 0; v < distance.length; v++) {
    		if(!shortestPath[v] && distance[v] <= min) {
    			min = distance[v];
    			index = v;
    		}
    	}
    	return index;
    }

    
    public static void checkArgs(String[] args) {
    	if(args.length != 7) {
    		System.err.println("Insufficient arguments. The program needs four arguments.\n1. Input\n2.Query\n3.Output1\n4.Output2");
    		System.exit(0);
    	}
		if(!(args[0].contains(".txt")) || !(args[1].contains(".txt")) ||
				!(args[2].contains(".txt")) || !(args[3].contains(".txt")) ||
				!(args[4].contains(".txt")) || !(args[5].contains(".txt")) ||
				!(args[6].contains(".txt"))
				) {
			System.err.println("Filenames must be in the format [filename].txt");
			System.exit(0);
		}
    }

    public static void main(String[] args) {
    	
    	Assign4 test = new Assign4();
        // manage/handle command line arguments
        String input = args[0];
        String query = args[1];
        String output1 = args[2];
        String output2 = args[3];
        String d_input = args[4];
        String d_query = args[5];
        String output3 = args[6];
        
        checkArgs(args);
        
        DiGraph digraph = null;
        DiGraph weightedDigraph = null;
        try {
        	test.clearFile(output1);
			test.clearFile(output2);
			test.clearFile(output3);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

        PrintStream printStream = null; // for writing into output files
        try {
            test.adjacencyMatrix = read2DMatrix(input);
            int V = test.adjacencyMatrix.getNumberOfRows();
            digraph = new DiGraph(V);
            for(int row = 0; row < V; row++) {
            	for(int col = 0; col < V; col++) {
            		if(test.adjacencyMatrix.getValue(row, col) == 1)
            			digraph.addEdge(digraph.adjacent[row], digraph.adjacent[col]);
            	}
            }
            test.queryList = read2DMatrix(query);
            for(int i = 0; i < test.queryList.getNumberOfRows(); i++){
                int start_node = test.queryList.getValue(i, 0);
                int end_node = test.queryList.getValue(i, 1);   
                printStream = new PrintStream(System.out);
                // direct to output1
                System.setOut(new PrintStream(new FileOutputStream(output1,true)));
                //Print depth-first traversal
                test.printDFS(digraph.adjacent,start_node, end_node);
                System.setOut(printStream); // reset
                // direct to output2
                System.setOut(new PrintStream(new FileOutputStream(output2,true)));
                //Print breadth-first traversal
                test.printBFS(digraph.adjacent, start_node, end_node);
                System.setOut(printStream); // reset
            }

            test.weightedAdjacencyMatrix = read2DMatrix(d_input);
            int weightedV = test.weightedAdjacencyMatrix.getNumberOfRows();
            weightedDigraph = new DiGraph(weightedV);
            for(int row = 0; row < weightedV; row++) {
            	for(int col = 0; col < weightedV; col++) {
            		int weight = test.weightedAdjacencyMatrix.getValue(row, col);
            		if(weight != 0)
            			weightedDigraph.addWeightedEdge(weightedDigraph.weightedAdj[row], weightedDigraph.weightedAdj[col], weight);
            	}
            }
            test.weightedQueryList = read2DMatrix(d_query);
            for(int i = 0; i < test.weightedQueryList.getNumberOfRows(); i++) {
            	int start = test.weightedQueryList.getValue(i, 0);
            	int end = test.weightedQueryList.getValue(i, 1);
            	System.setOut(new PrintStream(new FileOutputStream(output3,true)));
            	test.dijkstra(test.weightedAdjacencyMatrix, start, end);
            	System.setOut(printStream);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}