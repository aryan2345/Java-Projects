public class BSTDictionary implements BSTDictionaryADT
{

    // instance variable to hold the binary search tree
    private BinarySearchTree tree;

    // constructor to initialize the binary search tree
    public BSTDictionary() 
    {
        this.tree = new BinarySearchTree();
    }

    // retrieves the record associated with the given key
    @Override
    public Record get(Key k)
    {
        // find the node with the given key in the tree
        BSTNode node = tree.get(tree.getRoot(), k);
        // if the node is found, return the associated record, otherwise return null
        if (node != null) 
        {
            return node.getRecord();
        } 
        else
        {
            return null;
        }
    }

    // inserts a new record into the dictionary, throwing an exception if a duplicate key is found
    @Override
    public void put(Record d) throws DictionaryException 
    {
        try 
        {
            // insert the record into the binary search tree
            tree.insert(tree.getRoot(), d);
        } 
        catch (DictionaryException e) 
        {
            // if a duplicate key is found, rethrow the exception with the same message
            throw new DictionaryException(e.getMessage());
        }
    }

    // removes the record with the given key from the dictionary, throwing an exception if the key is not found
    @Override
    public void remove(Key k) throws DictionaryException 
    {
        try 
        {
            // remove the record with the given key from the binary search tree
            tree.remove(tree.getRoot(), k);
        } 
        catch (DictionaryException e) 
        {
            // if the key is not found, rethrow the exception with the same message
            throw new DictionaryException(e.getMessage());
        }
    }

    // retrieves the record with the smallest key that is greater than the given key
    @Override
    public Record successor(Key k) 
    {
        // find the successor node in the binary search tree
        BSTNode successorNode = tree.successor(tree.getRoot(), k);
        // if the successor node is found, return the associated record, otherwise return null
        if (successorNode != null)
        {
            return successorNode.getRecord();
        }
        else 
        {
            return null;
        }
    }

    // retrieves the record with the largest key that is smaller than the given key
    @Override
    public Record predecessor(Key k) 
    {
        // find the predecessor node in the binary search tree
        BSTNode predecessorNode = tree.predecessor(tree.getRoot(), k);
        // if the predecessor node is found, return the associated record, otherwise return null
        if (predecessorNode != null)
        {
            return predecessorNode.getRecord();
        } 
        else
        {
            return null;
        }
    }

    // retrieves the record with the smallest key in the dictionary
    @Override
    public Record smallest()
    {
        // find the node with the smallest key in the binary search tree
        BSTNode smallestNode = tree.smallest(tree.getRoot());
        // if the smallest node is found, return the associated record, otherwise return null
        if (smallestNode != null)
        {
            return smallestNode.getRecord();
        } 
        else
        {
            return null;
        }
    }

    // retrieves the record with the largest key in the dictionary
    @Override
    public Record largest()
    {
        // find the node with the largest key in the binary search tree
        BSTNode largestNode = tree.largest(tree.getRoot());
        // if the largest node is found, return the associated record, otherwise return null
        if (largestNode != null)
        {
            return largestNode.getRecord();
        }
        else
        {
            return null;
        }
    }
}