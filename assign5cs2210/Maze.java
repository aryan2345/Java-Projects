import java.io.*;
import java.util.*;
public class Maze 
{
    private Graph graph;
    private GraphNode entrance;
    private GraphNode exit;
    private int coins;
    public Maze(String inputFile) throws MazeException 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) 
        {
            // used to read the scale factor, width, height, and number of coins.
            int s = Integer.parseInt(reader.readLine().trim());
            int width = Integer.parseInt(reader.readLine().trim());
            int length = Integer.parseInt(reader.readLine().trim());
            coins = Integer.parseInt(reader.readLine().trim());
            // creates a graph with the given number of nodes
            graph = new Graph(width * length);
            // stores the node number.
            int nodeNumber = 0;
            // variable required for the "corridor" and "door" connectins vertically
            int check = 0;
            // Read maze layout
            for (int i = 0; i < (length*2-1); i++) 
            {
                // read to reinitialize it wheverever a new line is read.
                check = 0;
                String line = reader.readLine().trim();
                for (int j = 0; j < (width*2-1); j++) 
                {
                    char cell = line.charAt(j);
                    switch (cell) 
                    {
                        // stores entry node.
                        case 's':
                            entrance = graph.getNode(nodeNumber);
                            nodeNumber++;
                            break;
                        // in case it's an exit node.
                        case 'x':
                            exit = graph.getNode(nodeNumber);
                            nodeNumber++;
                            break;
                        // in case, it is a room 
                        case 'o':
                            nodeNumber++;
                            break;
                        // when Corridor,we add edges   
                        case 'c':
                            // horizontal connection to the right
                            if (i%2 ==0) 
                            {
                                graph.insertEdge(graph.getNode(nodeNumber-1), graph.getNode(nodeNumber), 0, "corridor");
                            }
                            // Vertical connection.
                            else
                            {
                                if(j%2==0)
                                {
                                    check++;
                                }
                                graph.insertEdge(graph.getNode((check+nodeNumber-1)-width), graph.getNode(check+nodeNumber-1), 0, "corridor");
                            }
                            break;
                        // when it's a wall we do nothing but update the check if neccessary.
                        case 'w':
                            if (i%2!=0 &&j%2==0) 
                            {     
                                check++;
                            }
                            break;
                        // in case it's a door that required specific number of coins to open.
                        default:
                            // door, add edges to adjacent rooms
                            int coinsNeeded;
                            coinsNeeded = Character.getNumericValue(cell);
                            // horizontal connection to the right
                            if (i%2 ==0) 
                            {
                                graph.insertEdge(graph.getNode(nodeNumber-1), graph.getNode(nodeNumber), coinsNeeded, "door");
                            }
                            // Vertical connection.
                            else
                            {
                                if(j%2==0)
                                {
                                    check++;
                                }
                                graph.insertEdge(graph.getNode((check+nodeNumber-1)-width), graph.getNode(check+nodeNumber-1), coinsNeeded, "door");
                            }
                    }
                }
            }
            reader.close();
        } 
        catch (IOException | GraphException e) 
        {
            throw new MazeException("Error reading maze from file: " + e.getMessage());
        }
    }

    public Graph getGraph() throws MazeException 
    {
        if (graph == null) 
        {
            throw new MazeException("Graph is null");
        }
        return graph;
    }
    /*
    // main method in which DFS will be used to calculate the path required to exit if it's possible
    // based on conditions given in the question
    public Iterator<GraphNode> solve() 
    {
        Set<GraphNode> visited;
        List<GraphNode> path;
        boolean foundPath;
        visited = new HashSet<>();
        path = new ArrayList<>();
        foundPath = dfs(entrance, exit, coins, visited, path);
        if (foundPath == true) 
        {
            return path.iterator();
        } 
        else 
        {
            return null;
        }
    }
    // helper method required to implement dfs.
    private boolean dfs(GraphNode current, GraphNode destination, int coinsRemaining,
    Set<GraphNode> visited, List<GraphNode> path) 
    {
        current.mark(true); // Mark the current node as visited
        path.add(current);

        if (current == destination) 
        {
            // We reached the destination
            return true;
        }
        Iterator<GraphEdge> edges;
        edges = null;
        try 
        {
            edges = graph.incidentEdges(current);
        } 
        catch (GraphException e) 
        {
            // Handle the exception based on your requirements
            e.printStackTrace();
        }

        while (edges != null && edges.hasNext()) 
        {
            GraphEdge edge;
            edge = edges.next();
            if (!edge.firstEndpoint().isMarked() == true || !edge.secondEndpoint().isMarked() == true) 
            {
                int coinsNeed = edge.getType();
                if (coinsRemaining >= coinsNeed) 
                {
                    // we will open the door and proceed
                    coinsRemaining -= coinsNeed;
                    GraphNode nextNode;
                    nextNode = (edge.firstEndpoint() == current) ? edge.secondEndpoint() : edge.firstEndpoint();
                    if (dfs(nextNode, destination, coinsRemaining, visited, path) == true) 
                    {
                        return true;
                    }
                    // we will backtrack if the path did not lead to the destination
                    coinsRemaining += coinsNeed;
                    path.remove(path.size() - 1);
                }
            }
        }
        // return false if no path is found.
        return false;
    }
    */
   /*
   public Iterator<GraphNode> solve() throws GraphException {
    Stack<GraphNode> stack = new Stack<>();

    dfs(entrance, exit, stack, coins);

    if (!stack.isEmpty()) {
        return stack.iterator();
    } else {
        return null;
    }
}

    private boolean dfs(GraphNode currentNode, GraphNode exitNode, Stack<GraphNode> stack, int availableCoins) throws GraphException {
    stack.push(currentNode);
    currentNode.mark(true);  // Mark the current node as visited

    // Check if the current node is the exit node
    if (currentNode == exitNode) 
    {
        return true; // Solution found
    }
    Iterator<GraphEdge> edgeIterator;
    edgeIterator = null;
    try
    {
        edgeIterator = graph.incidentEdges(currentNode);
    }
    catch(GraphException e) 
    {
        e.printStackTrace();
    }
    while (edgeIterator!=null &&edgeIterator.hasNext()) {
        GraphEdge edge = edgeIterator.next();
        GraphNode nextNode = edge.secondEndpoint();

        if ((!edge.firstEndpoint().isMarked() ||!nextNode.isMarked()) && edge.getType() <= availableCoins) {
            // Merge the functionality of canTraverse into the condition
            int coinsUsed = edge.getType();

            // Recursive call with updated available coins
            if (dfs(nextNode, exitNode, stack, availableCoins - coinsUsed)) {
                return true;  // Solution found, no need to explore further
            }
            // No solution found from this path, backtrack
            availableCoins = availableCoins + coinsUsed;
            
        }
    }

    
    stack.pop();  // Remove from the stack

    return false;
}
*/
            public Iterator<GraphNode> solve() 
        {  
        Stack<GraphNode> stack= new Stack<>();
        if (dfs(entrance, exit, coins, stack)) {
            return stack.iterator();
        } else {
            return null;
        }
        }

    private boolean dfs(GraphNode current, GraphNode destination, int coins, Stack<GraphNode> stack) {
        current.mark(true);
        stack.push(current);

        if (current == destination) {
            return true;
        }

        Iterator<GraphEdge> edgeIterator;
        try {
            edgeIterator= graph.incidentEdges(current);
        } catch (GraphException e) {
        
            e.printStackTrace();
            return false;
        }

        while (edgeIterator != null && edgeIterator.hasNext()) {
            GraphEdge edge = edgeIterator.next();
            GraphNode nextNode = (edge.firstEndpoint() != current) ? edge.firstEndpoint():edge.secondEndpoint();
            if (!nextNode.isMarked()) {
                int coinsNeeded = edge.getType();
                if (coins >= coinsNeeded) 
                {

                    if (dfs(nextNode, destination, coins-coinsNeeded, stack)) {
                        return true;
                    }

                    coins += coinsNeeded;
                }
            }
        }
        stack.pop();
        return false;
    }
}

