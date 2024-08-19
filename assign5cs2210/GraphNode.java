// represents a node in a graph
public class GraphNode 
{
    // the name or identifier of the graph node
    private int name;
    // indicates whether the node has been marked or visited
    private boolean marked;
    // constructs a new GraphNode with the given name
    public GraphNode(int name) 
    {
        this.name = name;
        this.marked = false;
    }
    // marks or unmarks the node, indicating whether it has been visited
    public void mark(boolean mark) 
    {
        this.marked = mark;
    }
    // checks if the node has been marked or visited
    public boolean isMarked() 
    {
        return marked;
    }
    // gets the name or identifier of the graph node
    public int getName() 
    {
        return name;
    }
}