public class Key
{
    private String label;
    private int type;
    
    // parameterized constructor
    public Key(String label, int type)
    {
        this.label = label.toLowerCase();
        this.type = type;
    }
    // returns the label ( lowercase)
    public String getLabel()
    {
        return label;
    }
    // returns the type
    public int getType()
    {
        return type;
    }
    public int compareTo(Key k)
    {
        // if both the labels and the types of the Key object and k are equal we return 0.
        if(label.equals(k.getLabel()) && type == k.getType())
        {
            return 0;
        }
        // if the label of Key object is lesser than k (comparing lexicographically)
        // or if the labels are equal and the type of k is greater than the
        //  type of Key Object, we return -1.
        if(label.compareTo(k.getLabel())<0 || (label.equals(k.getLabel()) && type < k.getType()))
        {
            return -1;
        }
        // If the label of Key object is greater than k or if when labels are equals, the type of
        // Key object is greater than the type of key, we return 1.
        return 1;
    }
    
}