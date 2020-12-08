import java.util.*; 
import java.lang.*; 
import java.io.*; 
  
class MST { 
    // Vertices in the graph 
    private static final int V = 9; 
  
    // Find the vertex with minimum key 
    // value, from the set of vertices not yet included in the MST 
    int minKey(int key[], Boolean mstSet[]) 
    { 
        // Initialize min value to the highest INT possible
    	// so that the values in our vertices are < @ the beginning
        int min = Integer.MAX_VALUE, min_index = -1; 
  
        
        for (int v = 0; v < V; v++) 
        	// If the value is not in mtSet && its value is less than the current value stored in min
        	// set min to the current value of key[v] and the index to the current iteration
            if (mstSet[v] == false && key[v] < min) { 
                min = key[v]; 
                min_index = v;
                // System.out.println("This is vertix's " + v + " edge cost: " + key[v]);
            } 
        return min_index; 
    }
  
    // Print the MST 
    void printMST(int parent[], int graph[][]) 
    { 
    	int totalCost = 0;
        System.out.println("Edge \tPath Cost"); 
        // Loop through the parent array (aka the MST) and print each vertix's value and edge's cost
        // excluding the root
        for (int i = 2; i < V; i++) {
            System.out.println(parent[i] + "--" + i + "\t" + graph[i][parent[i]]);
            totalCost += graph[i][parent[i]];
        }
        // Add all the edges' weights and print it
        System.out.println("\nTotal path cost: " + totalCost);
    } 
  
    // Where the MST will be built using an multidimensional array 
    void primMST(int graph[][]) 
    { 
        // Array to store the MST 
        int parent[] = new int[V]; 
  
        // Array to store key values with the lowest edge's cost
        int key[] = new int[V]; 
  
        // Array to keep track of vertices already in the tree 
        Boolean mstSet[] = new Boolean[V]; 
  
        // Initialize all keys as at the highest INT value possible 
        for (int i = 0; i < V; i++) { 
            key[i] = Integer.MAX_VALUE; 
            mstSet[i] = false; 
        } 
  
        // Initializing to 1 since our sample graph's first vertex is 1 and not 0
        key[1] = 1;
        parent[1] = 1; 
  
        // The MST will have V vertices 
        for (int count = 0; count < V - 1; count++) { 
            // Pick the minimum key vertex from the set of vertices 
            // not yet included in MST 
            int u = minKey(key, mstSet); 
  
            // Add the picked vertex to the MST array
            mstSet[u] = true;
  
            // Update key value and parent index of the adjacent 
            // vertices of the picked vertex. Consider only those 
            // vertices which are not yet included in MST 
            for (int v = 0; v < V; v++) 
  
                // Update the key only if graph[u][v] is < key[v] 
                if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) { 
                    parent[v] = u; 
                    key[v] = graph[u][v]; 
                } 
        } 
        // Print the constructed MST 
        printMST(parent, graph); 
    } 
  
    public static void main(String[] args) 
    { 
    	// Instantiating new MST
        MST t = new MST(); 
        int graph[][] = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        							  { 0, 0, 5, 4, 0, 0, 0, 0, 0 }, 
                                      { 0, 5, 0, 2, 3, 0, 0, 0, 0 }, 
                                      { 0, 4, 2, 0, 0, 4, 0, 0, 0 }, 
                                      { 0, 0, 3, 0, 0, 2, 0, 6, 0 }, 
                                      { 0, 0, 0, 4, 2, 0, 1, 0, 0 },
                                      { 0, 0, 0, 0, 0, 1, 0, 8, 0 }, 
                                      { 0, 0, 0, 0, 6, 0, 8, 0, 2 },
                                      { 0, 0, 0, 0, 0, 0, 0, 2, 0 } }; 
  
        // Print the solution 
        t.primMST(graph); 
    } 
}