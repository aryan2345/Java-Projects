import java.util.LinkedList;
public class HashDictionary implements DictionaryADT
{
    // stores the hash table as a linkedlist array of type Data.
    LinkedList<Data>[] table;
    // stores the capacity.
    int cap;
    // parameterized constructor that initializes the hash table with the given size
    public HashDictionary(int size)
    {
        cap = size;
        int i;
        table = new LinkedList[cap];
        // memory allocation required for the linked list array.
        for (i = 0; i < cap; i++)
        {
            table[i] = new LinkedList<>();
        }
    }
    // gets the score associated with a specific configuration
    public int get(String config)
    {
        // calculates the index for the configuration to retrieve calling the hash function.
        int index;
        index = hash(config, cap); 
        // gets the linked list at the calculated index.
        LinkedList<Data>
        list = table[index];
        // stores the value of the score of the given configuration.
        int score;
        // for loop required to traverse the list at that specified index to see if the configuration exists.
        for (Data data : list)
        {
            if (data.getConfiguration().equals(config)) 
            {
                // returns the score that is a part of the configuration
                score = data.getScore();
                return score;  
            }
        }
        // returns -1 if the configuration is not found
        return -1;  
    }
    
    // Adding a Data record to the hash table (required for getConfiguration() method)
    public int put(Data record) throws DictionaryException
    {
        // stores the value of the configuration.
        String config = record.getConfiguration();
        // Calculate the index for this record using the hash function
        int index;
        index = hash(config , cap);  
        // Get the linked list at the calculated index
        LinkedList<Data> list;
        list = table[index];  

        // checks if a record with the same configuration already exists
        for (Data data : list)
        {
            if (data.getConfiguration().equals(config)) 
            {
                // A record with the same configuration exists, throw an exception
                throw new DictionaryException(); 
            }
        }
        // Adding the record to the linked list when it's configuration doesn't is unique. 
        list.add(record); 
        // stores the number of collisions
        int col;
        col= list.size();
        // Return 1 if there was a collision, 0 otherwise
         if(col>1)
         {
             return 1;
         }
         else
         {
             return 0;
         }
    }
    // Remove a Data record with a specific configuration
    public void remove(String config) throws DictionaryException 
    {
        // Calculate the index for the configuration to be removed
        int index;
        index = hash(config, cap);  
        // Get the linked list at the calculated index
        LinkedList<Data> list;
        list = table[index]; 
        Data toRemove = null;
        for (Data data : list)
        {
            if (data.getConfiguration().equals(config))
            {
                // finds the record with the specified configuration
                toRemove = data;  
                break;
            }
        }

        if (toRemove == null) 
        {
            // If the record to remove is not found, throw an exception
            throw new DictionaryException();  
        }
        // removes the record from the linked list
        list.remove(toRemove);  
    }
    // we use a hash method in this to calculate the index for a given key (configuration)
    private int hash(String config , int size)
    {
        int hashIndex = 0;
        int i;
        // stores the value of the length of configuration
        int length = config.length();
        for (i = 0; i < length; i++) 
        {
            // polynomial hash function
            hashIndex = (hashIndex * 11 + (int)config.charAt(i)) % size;
        }
        // returning the required hash index
        return hashIndex;
    }
    // gets the total number of records in the hash table
    public int numRecords() 
    {
        int count = 0;
        for (LinkedList<Data> list : table) 
        {
            // counts the number of records in each linked list
            count = count + list.size();  
        }
        // returns the total number of records
        return count; 
    }
}