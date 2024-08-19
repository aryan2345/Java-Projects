import java.util.*;
public class Graph implements GraphADT 
{
    // the number of nodes in the graph
    private int numNodes;
    // a mapping of node names to GraphNode objects
    private Map<Integer, GraphNode> nodes;
    // an array of lists representing the adjacency list of the graph
    private List<GraphEdge>[] adjacencyList;
    // constructs a new Graph with the given number of nodes
    public Graph(int n) 
    {
        this.adjacencyList = new ArrayList[n];
        this.numNodes = n;
        this.nodes = new HashMap<>();

        for (int i = 0; i < n; i++) 
        {
            this.adjacencyList[i] = new ArrayList<>();
            GraphNode node = new GraphNode(i);
            nodes.put(i, node);
        }
    }
    // inserts an edge between two nodes with a specified type and label
    @Override
    public void insertEdge(GraphNode u, GraphNode v, int edgeType, String label) throws GraphException 
    {
        if (!nodes.containsValue(u) == true) 
        {
            throw new GraphException("Invalid nodes");
        }
        if (!nodes.containsValue(v) == true)
        {
            throw new GraphException("Invalid nodes");
        }

        // Check if the edge already exists
        if (!areAdjacent(u, v) == true) 
        {
            GraphEdge edge = new GraphEdge(u, v, edgeType, label);
            adjacencyList[u.getName()].add(edge);
            adjacencyList[v.getName()].add(edge);
        } 
        else 
        {
            throw new GraphException("Edge already exists");
        }
    }
    // gets the node with the specified name
    @Override
    public GraphNode getNode(int name) throws GraphException 
    {
        if (!nodes.containsKey(name)) 
        {
            throw new GraphException("Node not found");
        }
        return nodes.get(name);
    }
    // gets an iterator over the edges incident to a node
    @Override
    public Iterator<GraphEdge> incidentEdges(GraphNode u) throws GraphException 
    {
        if (!nodes.containsValue(u)) 
        {
            throw new GraphException("Invalid node");
        }
        return adjacencyList[u.getName()].iterator();
    }
    // gets the edge between two nodes
    @Override
    public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException 
    {
        if (!nodes.containsValue(u)) 
        {
            throw new GraphException("Invalid nodes");
        }
        if (!nodes.containsValue(v)) 
        {
            throw new GraphException("Invalid nodes");
        }
        for (GraphEdge edge : adjacencyList[u.getName()]) 
        {
            if (edge.firstEndpoint() == v) 
            {
                return edge;
            }
            if (edge.secondEndpoint() == v) 
            {
                return edge;
            }
        }
        throw new GraphException("Edge not found");
    }
    // checks if two nodes are adjacent in the graph
    @Override
    public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException 
    {
        if (!nodes.containsValue(u)) 
        {
            throw new GraphException("Invalid nodes");
        }
        if (!nodes.containsValue(v)) 
        {
            throw new GraphException("Invalid nodes");
        }
        for (GraphEdge edge : adjacencyList[u.getName()]) 
        {
            if (edge.firstEndpoint() == v) 
            {
                return true;
            }
            if (edge.secondEndpoint() == v) 
            {
                return true;
            }
        }
        return false;
    }
}