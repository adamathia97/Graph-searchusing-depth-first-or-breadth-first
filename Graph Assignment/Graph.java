import java.util.ArrayList;

/*
 * A class representing a Graph as an adjacency matrix,
 * and providing search functions for the Graph.
 */

/*
 * Student 1 Name:Aayush Damathia
 * Student 1 Number:300178855
 * 
 */

public class Graph
{

	
    boolean[][] adjMat;  

    // In the adjacency matrix representation of a Graph,
    // if adjMat[i][j] == true, then you can move from 
    // node i to node j.
    // i.e. j is a neighbour of i.
    
    // The constructor builds the adjacency matrix,
    // with initially all values false.
    public Graph(int size)
    {
        adjMat = new boolean[size][size];
        for (int i=0; i < adjMat.length; i++)
        {
            for (int j=0; j < adjMat.length; j++)
            {
                adjMat[i][j] = false;
            }
        }     
    }

    // Add a transition to the adjacency matrix,
    // i.e. a neighbour relation between two nodes.
    public void add(int from, int to)
    {
        adjMat[from][to] = true;
    }

    // Carry out uninformed search of the Graph,
    // from the start node to goal node.
    public boolean search(int start, int goal, boolean dp)
    {
    	// The frontier is an ArrayList of Paths.
        ArrayList<Path> frontier = new ArrayList<Path>();
        
        // Initially the frontier contains just the Path
        // containing only the start node.
        Path firstPath = new Path(start);        
        frontier.add(firstPath);
        
        ArrayList<String> previous = new ArrayList<String>();//This is to store all the paths
        previous.add("[" + start);
        
         // Search until the goal is found,
        // or the frontier is empty.
        while (! frontier.isEmpty())
        {
         // *** TO-DO ***
         // CARRY OUT DEPTH-FIRST OR BREADTH-FIRST SEARCH
            
            if(dp)//This if statment is for depth first search
            {
                int lastPointer = frontier.size() - 1;//This is to get the last node number fropm the frontier
                int node = frontier.get(lastPointer).getLastNode(); // This is to get the last node in  that path
                System.out.println("Inspect Node: " + node);
                boolean[] x = adjMat[node];//This is to store the row of that adj matrix graph
                String currentLocation = previous.get(lastPointer);
                frontier.remove(lastPointer);
                previous.remove(lastPointer);
                if(x[goal])//If the path to the goal node is true then we found the goal node
                {
                    int newNode = node + 1;
                    System.out.println("Inspect Node: " + newNode);
                    System.out.println("Found Goal Node!");
                    System.out.println(currentLocation + " , " + newNode + "]");
                    return true;
                }
                for(int i = 0; i < x.length; i++)
                {
                    if(x[i] == true)
                    {
                        Path temp = new Path(i);
                        frontier.add(temp);
                        String j = currentLocation + " , " + i;
                        previous.add(j);
                    }
                }
            }
            else//This is for breadth first search similar to above just need to start from zero like the first one
            {
                int node = frontier.get(0).getLastNode();
                System.out.println("Inspect Node: "  + node);
                boolean[] x = adjMat[node];
                String currentLocation = previous.get(0);
                frontier.remove(0);
                previous.remove(0);
                if(x[goal])
                {
                    int newNode = node + 1;
                    System.out.println("Inspect Node: " + newNode);
                    System.out.println("Found Goal Node!");
                    System.out.println(currentLocation + " , " + newNode + "]");
                    return true;
                }
                for(int i = 0; i < x.length; i++)
                {
                    if(x[i] == true)
                    {
                        Path temp = new Path(i);
                        frontier.add(temp);
                        String j = currentLocation + " , " + i;
                        previous.add(j);
                    }
                }
            }
        	
        }
        	
        return false;
    }

    public static void main(String[] args)
    {
    	// Create a Graph containing 7 nodes
        Graph g = new Graph(7);
        
        // Add edges to the Graph
        g.add(0, 1);
        g.add(0, 2);
        g.add(1,5);
        g.add(1,6);
        g.add(2, 3);
        g.add(3, 4);
        
        // select a search type
        boolean depthFirst = false;
        
        // start searching
        g.search(0,4, depthFirst);
    }
}