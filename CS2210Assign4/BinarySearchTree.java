public class BinarySearchTree
 {
    // private instance variable.
    private BSTNode root;

    // constructor to initialize an empty binary search tree
    public BinarySearchTree()
    {
        this.root = null;
    }

    // getter method to retrieve the root of the tree
    public BSTNode getRoot() 
    {
        return root;
    }

    // recursive method to search for a node with a specific key in the tree rooted at r
    public BSTNode get(BSTNode r, Key k)
    {
        // compare variable used to compare the key of r node and k.
        int compare;
        if (r == null)
        {
            return null;
        }
        compare = r.getRecord().getKey().compareTo(k);
        // when we have found the node r we return in.
        if(compare == 0)
        {
            return r;
        }
        // recursion to travserse the bst, to reach the node r.
        if (compare < 0) 
        {
            return get(r.getRightChild(), k);
        }
        return get(r.getLeftChild(), k);
    }

    // method to insert a new record into the tree, throwing an exception if a duplicate key is found
    public void insert(BSTNode r, Record d) throws DictionaryException
    {
        BSTNode check;
        check = get(r, d.getKey());
        if (check != null)
        {
            throw new DictionaryException("Duplicate key found");
        }
        // calling the required insert helper method to insert node r with record d.
        root = insertRec(r, d);
    }

    // recursive helper method to insert a record into the tree rooted at root
    private BSTNode insertRec(BSTNode root, Record d)
    {
        int comp;
        if (root == null)
        {
            return new BSTNode(d);
        }
        // comparision variable used for key of d and the key of root.
        comp  = d.getKey().compareTo(root.getRecord().getKey());
        if (comp < 0)
        {
            root.setLeftChild(insertRec(root.getLeftChild(), d));
        } 
        else if (comp > 0)
        {
            root.setRightChild(insertRec(root.getRightChild(), d));
        }

        return root;
    }

    // method to remove a node with a specific key from the tree, throwing an exception if the key is not found
    public void remove(BSTNode r, Key k) throws DictionaryException
    {
        BSTNode check;
        check = get(r, k);
        // if null, then we throw a dictionary exception
        if (check == null) 
        {
            throw new DictionaryException("Key not found");
        }
        // calling the helper remove method to remove the given node r.
        root = removeRec(r, k);
    }

    // recursive helper method to remove a node with a specific key from the tree rooted at root
    private BSTNode removeRec(BSTNode root, Key k) 
    {
        int compare;
        if (root == null)
        {
            return root;
        }
        compare = k.compareTo(root.getRecord().getKey());
        // recursion to traverse the bst to find the node to be removed.
        if (compare < 0)
        {
            root.setLeftChild(removeRec(root.getLeftChild(), k));
        } 
        else if (compare > 0)
        {
            root.setRightChild(removeRec(root.getRightChild(), k));
        } 
        // when we have found the node to be removed, we remove it according to 
        // the number of children of node.
        else 
        {
            if (root.getLeftChild() == null) 
            {
                return root.getRightChild();
            } 
            else if (root.getRightChild() == null)
            {
                return root.getLeftChild();
            }
            root.setRecord(smallest(root.getRightChild()).getRecord());
            root.setRightChild(removeRec(root.getRightChild(), root.getRecord().getKey()));
        }
        // returning the required root
        return root;
    }

    // method to find the successor of a node with a specific key in the tree rooted at r
    public BSTNode successor(BSTNode r, Key k)
    {
        // stores current node
        BSTNode current = get(r, k);
        // stores successor as required by program
        BSTNode successor;
        // stores ancestor required to find successor when left subtree doesn't exist.
        BSTNode ancestor;
        // comparision variable
        int comp;
        if (current == null) 
        {
            return null;
        }
        if (current.getRightChild() != null)
        {
            return smallest(current.getRightChild());
        }
        successor = null;
        ancestor = r;
        
        // while loop required to find the successor as stated in question.
        while (ancestor != current)
        {
            comp = current.getRecord().getKey().compareTo(ancestor.getRecord().getKey());
            if (comp < 0)
            {
                successor = ancestor;
                ancestor = ancestor.getLeftChild();
            } 
            else
            {
                ancestor = ancestor.getRightChild();
            }
        }
        return successor;
    }

    // method to find the predecessor of a node with a specific key in the tree rooted at r
    public BSTNode predecessor(BSTNode r, Key k) 
    {
        // stores current node
        BSTNode current = get(r, k);
        // stores predecessor as required by program
        BSTNode predecessor;
        // stores ancestor required to find successor when left subtree doesn't exist.
        BSTNode ancestor;
        // comparision variable
        int comp;
        if (current == null) 
        {
            return null;
        }

        if (current.getLeftChild() != null)
        {
            return largest(current.getLeftChild());
        }
        predecessor = null;
        ancestor = r;
        // while loop required to find the predecessor as stated in question.
        while (ancestor != current)
        {
            comp = current.getRecord().getKey().compareTo(ancestor.getRecord().getKey());
            if (comp > 0) 
            {
                predecessor = ancestor; 
                ancestor = ancestor.getRightChild();
            }
            else
            {
                ancestor = ancestor.getLeftChild();
            }
        }

        return predecessor;
    }

    // method to find the node with the smallest key in the tree rooted at r
    public BSTNode smallest(BSTNode r)
    {
        // stores left child
        BSTNode leftChild;
        leftChild = r.getLeftChild();
        // when leftChild is null, it means we found the smallest element.
        if (leftChild == null)
        {
            return r;
        }
        // recursion for us to check the smallest element in bst.
        return smallest(r.getLeftChild());
    }

    // method to find the node with the largest key in the tree rooted at r
    public BSTNode largest(BSTNode r)
    {
        // stores right child.
        BSTNode rightChild;
        rightChild = r.getRightChild();
        // when rightChild is null, it means we found the biggest element.
        if (rightChild == null)
        {
            return r;
        }
        // recursion for us to check the largest element in bst.
        return largest(r.getRightChild());
    }
}