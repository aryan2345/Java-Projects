public class BSTNode
{
    private Record item;
    private BSTNode leftChild;
    private BSTNode rightChild;
    private BSTNode parent;

    // parameterized constructor
    public BSTNode(Record item)
    {
        this.item = item;
        this.leftChild = null;
        this.rightChild = null;
        this.parent = null;
    }

    // returns value of record
    public Record getRecord()
    {
        return item;
    }

    // sets vaue for the record 
    public void setRecord(Record d)
    {
        this.item = d;
    }

    // returns leftchild
    public BSTNode getLeftChild() 
    {
        return leftChild;
    }

    // returns rightChild
    public BSTNode getRightChild() 
    {
        return rightChild;
    }

    // returns parent
    public BSTNode getParent()
    {
        return parent;
    }

    // sets value for left child
    public void setLeftChild(BSTNode u) 
    {
        this.leftChild = u;
    }

    // sets value for right child
    public void setRightChild(BSTNode u) 
    {
        this.rightChild = u;
    }

    // sets value for parent
    public void setParent(BSTNode u)
    {
        this.parent = u;
    }

    // to check if node is a leaf
    public boolean isLeaf()
    {
        if(rightChild == null  && leftChild == null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
