public class Data
{
    String config;
    int score;
    //: A constructor which initializes a new Data object
    //with the specified configuration and score. The string config will be used as the key attribute
    //for every Data object.
    public Data(String config, int score)
    {
        this.config = config;
        this.score = score;
    }
    //Returns the score in this Data.
    public int getScore()
    {
         return score;
    }
    // Returns the configuration stored in this Data object.
    public String getConfiguration()
    {
        return config;
    }
}