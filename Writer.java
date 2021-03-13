import java.io.*;
public class Writer
{
    String filename;
    String text;
    String dictionary;
    public Writer(String fileName, String Text, String Dictionary)
    {
        filename = fileName;
        text = Text;
        dictionary = Dictionary;
    }
    public void writeOut() throws Exception
    {
       //writes out the compressed text and the dictionary
       File newestFile = new File(filename);
       FileWriter writing = new FileWriter(newestFile);
       BufferedWriter bw = new BufferedWriter(writing);
       bw.write(text);
       bw.write(dictionary);
       bw.close();
    }
    
   
}
