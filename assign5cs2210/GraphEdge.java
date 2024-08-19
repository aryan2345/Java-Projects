// represents an edge in a graph
public class GraphEdge 
{
    // the first endpoint of the edge
    private GraphNode u;
    // the second endpoint of the edge
    private GraphNode v;
    // the type or weight of the edge
    private int type;
    // the label or description of the edge
    private String label;
    // constructs a new GraphEdge with the given endpoints, type, and label
    public GraphEdge(GraphNode u, GraphNode v, int type, String label) 
    {
        this.u = u;
        this.v = v;
        this.type = type;
        this.label = label;
    }
    // gets the first endpoint of the edge
    public GraphNode firstEndpoint() 
    {
        return u;
    }
    // gets the second endpoint of the edge
    public GraphNode secondEndpoint() 
    {
        return v;
    }
    // gets the type or weight of the edge
    public int getType() 
    {
        return type;
    }
    // sets the type or weight of the edge
    public void setType(int newType) 
    {
        this.type = newType;
    }
    // gets the label or description of the edge
    public String getLabel() 
    {
        return label;
    }
    // sets the label or description of the edge
    public void setLabel(String newLabel) 
    {
        this.label = newLabel;
    }
}