import java.io.*;
import java.util.ArrayList;
public class Reader
{
    String filename;
    ArrayList<String> lines = new ArrayList<String>();
    String plaintext = "";
    int numBits = 0;
    int numChars = 0;
    int numBytes = 0;
    public Reader(String fileName)
    {
        filename = fileName;
    }
    public ArrayList<String> readIn() throws Exception
    {
       //Reads in file, substitues new lines with "/"
       FileReader reading = new FileReader(filename);
       BufferedReader br = new BufferedReader(reading);
       String currentLine = null;
       currentLine = br.readLine();
       while(currentLine != null)
       {
           lines.add(currentLine);
           plaintext += currentLine + "/";
           currentLine = br.readLine();
       } 
       return lines;
    }
    public String concatonateText() 
    {
        //returns the string version of the text
        return plaintext + "#";
    }
    public int printNumCharacters()
    {
        //prints the character count and storage of each file
        //returns the amount of storage a file takes up
        for (int i = 0; i < plaintext.length(); i++)
        {
            String letter = plaintext.substring(i, i+1);
            if(letter.equals("0") || letter.equals("1"))
            {
                numBits++;
            }
        }
        numBytes = plaintext.length() - numBits;
        numChars = plaintext.length();
        int numBitsLeftover = (numBits % 8);
        int additionalBytes = (numBits / 8);
        int totalBytes = numBytes + additionalBytes;
        System.out.println("\t" + "File " + "'" + filename + "':");
        System.out.println("\t" + "Number of 0's and 1's: " + numBits );
        System.out.println("\t" + "Number of other characters: " + numBytes);
        System.out.println("\t" + "Together there are " + numChars + " characters, which are stored in " + totalBytes + " bytes" + " and " + numBitsLeftover + " bits");
        return totalBytes;
    }
}
