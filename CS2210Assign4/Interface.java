
import java.io.*;
import java.util.*;
public class Interface 
{
    // main method used to ask user input to complete the tasks according to the input.
    public static void main(String[] args) 
    {
        // to check if the correct number of command-line arguments is provided
        if (args.length != 1) 
        {
            System.out.println("Error. The Usage is java Interface inputFile");
            return;
        }
        // retrieves the input file path from the command-line arguments
        String inputFile = args[0];
        // creates an instance of the binary search tree dictionary
        BSTDictionary dictionary = new BSTDictionary();
        // list to store labels required for the case "list" command
        List<String> labelsList = new ArrayList<>();
        // reads input file and populates the dictionary and labelsList.
        try (Scanner scanner = new Scanner(new FileReader(inputFile)))
        {
            while (scanner.hasNextLine()) 
            {
                // Read and convert the label to lowercase
                String label = scanner.nextLine().toLowerCase();
                // Exit the loop if there are no more lines
                if (!scanner.hasNextLine())
                {
                    break;
                }
                // reads the type and data line
                String typeAndData = scanner.nextLine();
                // creates a record using the label, type, and data, and add it to the dictionary
                Record record = createRecord(label, typeAndData);
                // stores the label in the list
                labelsList.add(label); 
                dictionary.put(record);
            }
        } 
        catch (IOException | DictionaryException e) 
        {
            // handles exceptions related to reading the input file or dictionary operations
            System.out.println("Error reading input file: " + e.getMessage());
            return;
        }

        // main command loop
        Scanner keyboard;
        keyboard = new Scanner(System.in);
        String command;
        do         
        {
            System.out.print("Enter next command: ");
            command = keyboard.nextLine();
            // processing the user command
            command(command, dictionary, labelsList);
        } 
        while (!command.equals("exit"));
    }

    // helper method to create a record from a label, type, and data string
    private static Record createRecord(String label, String typeAndData) throws DictionaryException 
    {
        // extracts type and data from typeAndData
        char typeIndicator = typeAndData.charAt(0);
        String data = typeAndData.substring(1).trim();

        int type;
        switch (typeIndicator)
        {
            // Sound file
            case '-':
                type = 3; 
                break;
            // Music file
            case '+':
                type = 4; 
                break;
            // Voice file
            case '*':
                type = 5; 
                break;
            // French translation
            case '/':
                type = 2; 
                break;
            // for the others that contain info as an extention and not in the beggining.
            default:
                String[] parts = typeAndData.split("\\.");
                if (parts.length == 2)
                {
                    // Extract file extension and map it to corresponding type values
                    String fileExtension = parts[1];
                    // we will return in switch case itself as we return the entire label
                    // and don't skip the first character 
                    // like we do for type 1,2,3,4,5.
                    switch (fileExtension)
                    {
                        // Image file
                        case "jpg":
                            type = 6;
                            return new Record(new Key(label.toLowerCase(), type), typeAndData);
                        // Animated image file
                        case "gif":
                            type = 7;
                            return new Record(new Key(label.toLowerCase(), type), typeAndData);
                        // URL
                        case "html":
                            type = 8; 
                            return new Record(new Key(label.toLowerCase(), type), typeAndData);
                        // Default type
                        default:
                            type = 1;
                            return new Record(new Key(label.toLowerCase(), type), typeAndData);
                    }
                } 
                else
                {
                    // Default type
                    type = 1; 
                    return new Record(new Key(label.toLowerCase(), type), typeAndData);
                }
        }

        // creates and return a new record with the determined label, type, and data
        return new Record(new Key(label.toLowerCase(), type), data);
    }
    // this method processes a command for the ordered dictionary application.
    // it takes a command string, a binary search tree dictionary, and a list of label attributes.
    // the command is split to identify the operation, and corresponding actions are performed.
    private static void command(String command, BSTDictionary dictionary , List<String> labelsList) 
    {
        String[] parts = command.split(" "); // Split command into parts
        String operation = parts[0].toLowerCase(); // Get the operation (define, translate, sound, etc.)
        // uses the SoundPlayer class to play the sound files.
        SoundPlayer soundPlayer = new SoundPlayer();
        // uses the PictureViewer class to display the image files
        PictureViewer pictureViewer = new PictureViewer();
        // uses the ShowHTML class to display the webpage
        ShowHTML showHTML = new ShowHTML();
        try
        {
            switch (operation)
            {
                // in this case data is a string containing a definition of label.
                case "define":
                    if (parts.length != 2)
                    {
                        System.out.println("Invalid command. Usage: define w");
                        break;
                    }
                    String wordToDefine = parts[1].toLowerCase();
                    Key keyToFind = new Key(wordToDefine, 1);
                    Record foundRecord = dictionary.get(keyToFind);
                    if (foundRecord != null)
                    {
                        System.out.println(foundRecord.getDataItem());
                    }
                    else 
                    {
                        System.out.println("The word " + wordToDefine + " is not in the ordered dictionary");
                    }
                    break;
                // In this case data is a translation of label to French.
                case "translate":
                    if (parts.length != 2)
                    {
                        System.out.println("Invalid command. Usage: translate w");
                        break;
                    }

                    String wordToTranslate = parts[1].toLowerCase();
                    Key translateKey = new Key(wordToTranslate, 2);

                    Record translateRecord = dictionary.get(translateKey);

                    if (translateRecord != null) 
                    {
                        System.out.println(translateRecord.getDataItem());
                    } 
                    else
                    {
                        System.out.println("There is no definition for the word " + wordToTranslate);
                    }
                    break;
                // In this case data is the name of a sound file.
                case "sound":
                    if (parts.length != 2)
                    {
                        System.out.println("Invalid command. Usage: sound w");
                        break;
                    }
                    String soundWord = parts[1].toLowerCase();
                    Key soundKey = new Key(soundWord, 3);
                    Record soundRecord = dictionary.get(soundKey);
                    if (soundRecord != null)
                    {
                        // Use the SoundPlayer class to play the audio file

                        try 
                        {
                            soundPlayer.play(soundRecord.getDataItem());
                        }
                        catch (MultimediaException e) 
                        {
                            System.out.println("Error playing sound file for " + soundWord + ": " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.out.println("There is no sound file for " + soundWord);
                    }
                    break;
                // In this case data is the name of a music file.
                case "play":
                    if (parts.length != 2)
                    {
                        System.out.println("Invalid command. Usage: play w");
                        break;
                    }

                    String playWord = parts[1].toLowerCase();
                    Key playKey = new Key(playWord, 4); 
                    Record playRecord = dictionary.get(playKey);

                    if (playRecord != null) 
                    {

                        try 
                        {
                            soundPlayer.play(playRecord.getDataItem());
                        } 
                        catch (MultimediaException e)
                        {
                            System.out.println("Error playing music file for " + playWord + ": " + e.getMessage());
                        }
                    }
                    else
                    {
                        System.out.println("There is no music file for " + playWord);
                    }
                    break;
                // In this case data is the name of a voice file (a file containing a human voice).
                case "say":
                    if (parts.length != 2)
                    {
                        System.out.println("Invalid command. Usage: say w");
                        break;
                    }

                    String wordToSay = parts[1].toLowerCase();
                    Key sayKey = new Key(wordToSay, 5); 
                    Record sayRecord = dictionary.get(sayKey);

                    if (sayRecord != null)
                    {
                        try
                        {
                            soundPlayer.play(sayRecord.getDataItem());
                        }
                        catch (MultimediaException e)
                        {
                            System.out.println("Error playing voice file for " + wordToSay + ": " + e.getMessage());
                        }
                    }
                    else
                    {
                        System.out.println("There is no voice file for " + wordToSay);
                    }
                    break;
                //  In this case data is the name of an image file.
                case "show":
                    if (parts.length != 2) 
                    {
                        System.out.println("Invalid command. Usage: show w");
                        break;
                    }

                    String showWord = parts[1].toLowerCase();
                    System.out.println(showWord);
                    Key showKey = new Key(showWord, 6); 
                    Record showRecord = dictionary.get(showKey);

                    if (showRecord != null) 
                    {
                        try
                        {
                            pictureViewer.show(showRecord.getDataItem());
                        }
                        catch (MultimediaException e) 
                        {
                            System.out.println("Error displaying image file for " + showWord + ": " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.out.println("There is no image file for " + showWord);
                    }
                    break;
                //  In this case data is the name of an animated image file.
                case "animate":
                    if (parts.length != 2) 
                    {
                        System.out.println("Invalid command. Usage: animate w");
                        break;
                    }

                    String animateWord = parts[1].toLowerCase();
                    Key animateKey = new Key(animateWord, 7); 
                    Record animateRecord = dictionary.get(animateKey);

                    if (animateRecord != null) 
                    {

                        try 
                        {
                            pictureViewer.show(animateRecord.getDataItem());
                        }
                        catch (MultimediaException e) 
                        {
                            System.out.println("Error displaying animated image file for " + animateWord + ": " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.out.println("There is no animated image file for " + animateWord);
                    }
                    break;
                // In this case data is the URL of a webpage.
                case "browse":
                    if (parts.length != 2)
                    {
                        System.out.println("Invalid command. Usage: browse w");
                        break;
                    }

                    String browseWord = parts[1].toLowerCase();
                    Key browseKey = new Key(browseWord, 8);
                    Record browseRecord = dictionary.get(browseKey);

                    if (browseRecord != null) 
                    {
                        try 
                        {
                            showHTML.show(browseRecord.getDataItem());
                        } 
                        catch (Exception e) 
                        {
                            System.out.println("Error displaying webpage for " + browseWord + ": " + e.getMessage());
                        }
                    } 
                    else 
                    {
                        System.out.println("There is no webpage called " + browseWord);
                    }
                    break;
                /*Removes from the ordered dictionary the Record object with key (w,k), or if no such record
                exists, it prints : No record in the ordered dictionary has key (w,k).*/
                case "delete":
                    if (parts.length != 3)
                    {
                        System.out.println("Invalid command. Usage: delete w k");
                        break;
                    }

                    String deleteWord = parts[1].toLowerCase();
                    int deleteType = Integer.parseInt(parts[2]);
                    Key deleteKey = new Key(deleteWord, deleteType);

                    try
                    {
                        // Remove the record with the given key from the binary search tree
                        dictionary.remove(deleteKey);
                        System.out.println("Record deleted successfully.");
                    } 
                    catch (DictionaryException e)
                    {
                        System.out.println("No record in the ordered dictionary has key (" + deleteWord + "," + deleteType + ").");
                    }
                    break;
                /*
                 *  add w t c
                    Inserts a Record object ((w,t),c) into the ordered dictionary if there is no record with key
                    (w,t) already there; otherwise it prints
                    A record with the given key (w,t) is already in the ordered dictionary.
                 */
                case "add":
                    if (parts.length != 4) 
                    {
                        System.out.println("Invalid command. Usage: add w t c");
                        break;
                    }

                    String addWord = parts[1].toLowerCase();
                    int addType = Integer.parseInt(parts[2]);
                    String addData = parts[3];

                    Key addKey = new Key(addWord, addType);
                    Record addRecord = new Record(addKey, addData);

                    try 
                    {
                        // Insert the new record into the binary search tree
                        dictionary.put(addRecord);
                        System.out.println("Record added successfully.");
                    } 
                    catch (DictionaryException e) 
                    {
                        System.out.println("A record with the given key (" + addWord + "," + addType + ") is already in the ordered dictionary.");
                    }
                    break;
                //prints the label attributes (if any) of all the Record objects in the ordered dictionary that start with prefix;
                // if no label exists, starting with prefix, we will print:
                //No label attributes in the ordered dictionary start with prefix label
                case "list":
                    if (parts.length != 2) 
                    {
                        System.out.println("Invalid command. Usage: list prefix");
                        break;
                    }

                    String prefix = parts[1].toLowerCase();
                    boolean found = false;
                    // counter variable to make sure not to print "," if only one label matches.
                    int count = 0;
                    // Iterate through the labelsList and print matching labels
                    for (String label : labelsList)
                    {
                        if (label.startsWith(prefix) == true) 
                        {
                            ++count;
                            if(count>=2)
                            {
                                System.out.print(",");
                            }
                            System.out.print(label);
                            found = true;
                        }
                        
                    }
                    System.out.println();
                    if (found == false)
                    {
                        System.out.println("No label attributes in the ordered dictionary start with prefix " + prefix);
                    }
                    break;
                // This command must print all the attributes of the Record object in the ordered dictionary with
                // smallest key
                case "first":
                    // Get the record with the smallest key in the dictionary
                    Record firstRecord = dictionary.smallest();

                    if (firstRecord != null) 
                    {
                        Key firstKey = firstRecord.getKey();
                        System.out.println(firstKey.getLabel() + "," + firstKey.getType() + "," + firstRecord.getDataItem() + ".");
                    } 
                    else
                    {
                        System.out.println("The dictionary is empty.");
                    }
                    break;
                // This command must print all the attributes of the Record object in the ordered dictionary
                // with largest key
                case "last":
                    // Get the record with the largest key in the dictionary
                    Record lastRecord = dictionary.largest();

                    if (lastRecord != null) 
                    {
                        Key lastKey = lastRecord.getKey();
                        System.out.println(lastKey.getLabel() + "," + lastKey.getType() + "," + lastRecord.getDataItem() + ".");
                    } 
                    else
                    {
                        System.out.println("The dictionary is empty.");
                    }
                    break;
                // This command terminates the program.
                case "exit":
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                /*
                 *  If an invalid command is entered your program must print the message
                    Invalid command.
                 */
                default:
                    System.out.println("Invalid command. ");
            }
        } 
        // In case an exception occurse, we need to catch it and print the required error message.
        catch (Exception e)
        {
            System.out.println("Error processing command: " + e.getMessage());
        }
    }
}

