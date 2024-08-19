public class Configurations
{
    // size of the board
    int size;
    // minimum number of length required to win
    int lengthWin;
    // maximum number of levels of the game tree
    int max;
    // the character board double dimensional array
    char board[][];
    // parameterized constructor which inializes board size, length required to win, and maximum number of levels of the given game tree.
    public Configurations (int board_size, int lengthToWin, int max_levels)
    {
        this.size = board_size;
        this.lengthWin = lengthToWin;
        this.max = max_levels;
        this.board = new char[size][size];
        // filling the array with ' ' as stated in the question.
        for(int row = 0;row<size;row++)
        {
            for(int col = 0; col<size; col++)
            {
                board[row][col] = ' ';
            }
        }
    }
    // creating the hash dictionary with the given prime number size between 6000 and 10000
    public HashDictionary createDictionary()
    {
        // Size of the Hash table
        int tSize = 6137;
        return new HashDictionary(tSize);
    }
    // returning the score of the given configuration. if it's not present, return -1. also helps us check for repeated configurations.
    public int repeatedConfiguration(HashDictionary hashTable)
    {
        // empty config initially
        String config = "";
        // storing config as string using this
        for(int row = 0;row<size;row++)
        {
            for(int col = 0; col<size; col++)
            {
                config+=board[row][col];
            }
        }
        // score of the given configuration
        int score = hashTable.get(config);
        if(score!=-1)
        {
            // if score isn't -1, we return it.
            return score;
        }
        // else we return -1
        return -1;
    }
    // in this method, we give the configuration a score if it already doesn't have a score.
    public void addConfiguration(HashDictionary hashDictionary, int score)
    {
        // empty config as given above.
        String config = "";
        // convert the character array to string to obtain the configuration.
        for(int row = 0;row<size;row++)
        {
            for(int col = 0; col<size; col++)
            {
                config+=board[row][col];
            }
        }
        // checking if it already has the same configuration in the given table index.
        int check = hashDictionary.get(config);
        // if the configuration is unique, we add the given score to the given configuration.
        if(check!=-1)
        {
            // using the put method in HashDictionary to add the score to the given configuration.
            hashDictionary.put(new Data(config,score));
        }
    }
    // stores the given symbol in board[row][col] 
    public void savePlay(int row, int col, char symbol)
    {
        board[row][col] = symbol;
    }
    // checking if a square is empty in the given row and col of board[][].
    public boolean squareIsEmpty(int row, int col)
    {
        // if there's a ' ', we return true as given in the question.
        if (board[row][col] == ' ')
        {
            return true;
        }
        // if there isn't a space we return false
        else
        {
            return false;
        }
    }
    // to check if it is a draw with 2 conditions
    // first condition is to make sure that it is a draw that all spaces have been occupied
    // second condition is to check if either player has won.
    public boolean isDraw()
    {
        // to check for spaces on the given board.
        for(int row = 0;row<size;row++)
        {
            for(int col = 0; col<size; col++)
            {
                if(board[row][col] == ' ')
                {
                    return false;
                }
            }
        }
        // if either player has won (human or computer).
        if(wins('X')||wins('O'))
        {
            return false;
        }
        // returning true if it is a draw after going through the following conditions.
        return true;
    }
    // to return an integer based on the outcome of the game.
    public int evalBoard()
    {
        // if the human wins, we return 0
        if(wins('X'))
        {
            return 0;
        }
        // if the computer wins, we return -1
        if(wins('O'))
        {
            return 3;
        }
        // if it is a draw, we return 2
        if(isDraw())
        {
            return 2;
        }
        // if game is undecided, we return 1.
        return 1;
    }
    // core method used to check if the player (either human or computer depending on symbol) has won the game or not.
    public boolean wins (char symbol)
    {        
        // to check for + shape on the board with minimum number of tiles lengthWin to win(instance variable)
        // counter is 1 as we are already counting the centre square.
        int count = 1;
        // starting off with a nested loop. In this method, we will take each element as the centre square except 1st row,
        // last row, 1st colomn, last colomn as they cannot form a + being the centre square. 
        for(int row = 1;row<size-1;row++)
        {
            for(int col = 1;col <size-1; col++)
            {
                // if the centre square isn't the required symbol, we go back to the inner column loop.
                if(board[row][col] != symbol)
                {
                    // used to skip the rest of the code below, and take the program control back to the inner column loop.
                    continue;
                }
                // to check if the given symbol has formed a + or not on the board.
                for (int i = 1; i < size; i++)
                {
                    int rightCol = col + i;
                    int leftCol = col - i;
                    int belowRow = row + i;
                    int aboveRow = row - i;

                    // to check if we are out of bounds on all the sides
                    boolean outOfBoundsRight = rightCol < size;
                    boolean outOfBoundsLeft = leftCol >= 0;
                    boolean outOfBoundsBelow = belowRow < size;
                    boolean outOfBoundsAbove = aboveRow >= 0;

                    // Checking symbols on all the sides while handling the out of bounds situation.
                    // checking out bounds on right side and checking if right side has the same symbol or not
                    if (outOfBoundsRight && board[row][rightCol] == symbol)
                    {
                        count++;
                    }
                    else
                    {
                        // we need to exit when even the first + is not made
                        if(i == 1)
                        {
                            break;
                        }
                    }
                    // checking out bounds on left side and checking if left side has the same symbol or not
                    if (outOfBoundsLeft && board[row][leftCol] == symbol)
                    {
                        count++;
                    }
                    else
                    {
                        // we need to exit when even the first + is not made
                        if(i == 1)
                        {
                            break;
                        }
                    }
                    // checking out bounds on bottom side and checking if bottom side has the same symbol or not
                    if (outOfBoundsBelow && board[belowRow][col] == symbol)
                    {
                        count++;
                    }
                    else
                    {
                        // we need to exit when even the first + is not made
                        if(i == 1)
                        {
                            break;
                        }
                    }
                    // checking out bounds on top side and checking if top side has the same symbol or not
                    if (outOfBoundsAbove && board[aboveRow][col] == symbol)
                    {
                        count++;
                    }
                    else
                    {
                        // we need to exit when even the first + is not made
                        if(i == 1)
                        {
                            break;
                        }
                    }
                    // if the count is greater than the minimum length required to win, that means the player has won and we return true.
                    if (count>= lengthWin) 
                    {
                        return true;
                    }
                }

            }
        }
         // to check for X shape on the board with minimum number of tiles lengthWin to win the game(instance variable)
        count = 1;
        // starting off with a nested loop. In this method, we will take each element as the centre square except 1st row,
        // last row, 1st colomn, last colomn as they cannot form a X being the centre square.
        for(int row = 1;row<size-1;row++)
        {
            for(int col = 1;col <size-1; col++)
            {
                // if the centre square isn't the required symbol, we go back to the inner column loop.
                if(board[row][col] != symbol)
                {
                    continue;
                }
                // to check if the given symbol has formed a X or not on the board.
                for (int i = 1; i < size; i++)
                {
                    // to check if we are out of bounds on all the sides
                    int rightDiagUpCol = col + i;
                    int leftDiagUpCol = col - i;
                    int rightDiagDownRow = row + i;
                    int leftDiagDownRow = row + i;
                    
                    int rightDiagUpRow =row-i;
                    int leftDiagUpRow = row - i;
                    int rightDiagDownCol =col + i;
                    int leftDiagDownCol = col - i;
                    // Flags to track if we're out of bounds in each direction
                    boolean topRightCol = rightDiagUpCol < size;
                    boolean topLeftCol =  leftDiagUpCol >=0;
                    boolean bottomRightRow = rightDiagDownRow < size;
                    boolean bottomLeftRow = leftDiagDownRow < size;
                    
                    
                    boolean topRightRow = rightDiagUpRow >= 0;
                    boolean topLeftRow =  leftDiagUpRow >=0;
                    boolean bottomRightCol = rightDiagDownCol < size;
                    boolean bottomLeftCol = leftDiagDownCol>=0;
                    // Checking symbols on all the sides while handling the out of bounds situation similar to what
                    // we did for +.
                    if (topRightCol && topRightRow && board[rightDiagUpRow][rightDiagUpCol] == symbol)
                    {
                        count++;
                    }
                    else
                    {
                        // we need to exit when even the first + is not made
                        if(i == 1)
                        {
                            break;
                        }
                    }
                    if (topLeftCol && topLeftRow && board[leftDiagUpRow][leftDiagUpCol] == symbol)
                    {
                        count++;
                    }
                    else
                    {
                        // we need to exit when even the first + is not made
                        if(i == 1)
                        {
                            break;
                        }
                    }
                    if (bottomRightRow && bottomRightCol && board[rightDiagDownRow][rightDiagDownCol] == symbol)
                    {
                        count++;
                    }
                    else
                    {
                        // we need to exit when even the first + is not made
                        if(i == 1)
                        {
                            break;
                        }
                    }
                    if (bottomLeftRow && bottomLeftCol && board[leftDiagDownRow][leftDiagDownCol] == symbol)
                    {
                        count++;
                    }
                    else
                    {
                        // we need to exit when even the first + is not made
                        if(i == 1)
                        {
                            break;
                        }
                    }
                    // if the count is greater than the minimum length required to win, that means the player has won and we return true.
                    if (count >= lengthWin) 
                    {
                        return true;
                    }
                }

            }
        }
        // returning false if neither player has won till now.
        return false;
    }
}
