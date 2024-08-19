public class Record
{
    // 2 instance variables
    private Key theKey;
    public  String data;
    // parameterised constructor
    public Record(Key k, String theData)
    {
        this.theKey = k;
        this.data = theData;
    }
    // getter for the key
    public Key getKey()
    {
        return theKey;
    }
    // getter for the data
    public String getDataItem()
    {
        return data;
    }
}