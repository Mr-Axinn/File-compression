import java.util.ArrayList;
import java.io.*;
public class Decompress
{
    String compressedStr;
    ArrayList<Frequency> dictionaryList = new ArrayList<Frequency>();
    String dictionary;
    int x = 0;
    String fileName = "";
    public Decompress(String CompressedStr, int X, String FileName)
    {
       compressedStr = CompressedStr;
       String[] strings = compressedStr.split("~", 2);
       //splits file into the dictionary and the text
       String text = strings[0];
       dictionary = strings[1];
       x = X;
       fileName = FileName;
    }
    //Substitutes code back to text and writes them out to a file
    public void translate()
    {
        
       String uncompressedString = "";
       boolean inCode = false;
       for(int i = 0; i < compressedStr.length(); i++)
       { 
           inCode = false;
           //Matches binary code and replaces it with the corresponding string
           for(int q = 2; q < 15; q++)
           {
               if(i+q < compressedStr.length())
               {
                   String substring = compressedStr.substring(i,i+q);
                   for(int j = 0; j < dictionaryList.size(); j++)
                   {
                       if(substring.equals(dictionaryList.get(j).code))
                       {
                           uncompressedString += dictionaryList.get(j).str;
                           if(x == 5)
                           {
                               uncompressedString += " ";
                           }
                           j = 5000;
                           i = (i + q)-1;
                           q = 1000;
                           inCode = true;
                        }
                   }
               }
          }
          //adds the letters directly if not written in binary
          if(!inCode)
          {
             uncompressedString += compressedStr.substring(i,i+1);
          }
       }
       File newestFile = new File(fileName);
       //writes out to file
       try
       {
           FileWriter writing = new FileWriter(newestFile);
           BufferedWriter bw = new BufferedWriter(writing);
           int previousIndex = 0;
           String finalText = "";
           for(int i= 0; i <uncompressedString.length()-1; i++)
           {
               //creates link breaks
               if(!uncompressedString.substring(i,i+2).contains("/"))
               {
                   finalText += uncompressedString.substring(i,i+1);
               }
               else
               {
                   //checks if the file has ended and writes out text if it has
                   if(!(finalText.contains("#") || finalText.contains("~")))
                   {
                       //x == 7 is for character translation
                       if(x == 7 && !uncompressedString.substring(i,i+1).equals("/"))
                       {
                           finalText += uncompressedString.substring(i,i+1);
                           i++;
                       }
                       bw.write(finalText);
                       bw.newLine();
                       //x == 5 is for word and phrase compression
                       if(x == 5)
                       {
                           if(uncompressedString.substring(i,i+1).equals("/"))
                           {
                               i++;
                           }
                           else
                           {
                               i+=2;
                           }
                       }
                       finalText = "";
                   }
               }
           }
           
           bw.close();
       } 
       catch(Exception e)
       {
           System.out.println(e);  
       }
    }
    public void createDictionary()
    {
        //takes the dictionary part of the text and sets up a list 
        dictionary = dictionary.replaceAll("~", "");
        for(int i = 0; i < dictionary.length(); i++)
        {
            String code = "";
            String letter = "";
            //finds where text ends and code begins
            if(!dictionary.substring(i,i+1).equals("1") && !dictionary.substring(i,i+1).equals("0"))
            {
                while(i < dictionary.length() && (!dictionary.substring(i,i+1).equals("1") && !dictionary.substring(i,i+1).equals("0")))
                {
                    letter += dictionary.substring(i,i+1);
                    i++;
                }
                i--;
            }
            i++;
            //finds binary code
            while(i < dictionary.length() && (dictionary.substring(i,i+1).equals("1") || dictionary.substring(i,i+1).equals("0")))
            {
                code += dictionary.substring(i,i+1);
                i++;
            }
            i--;
            Frequency frq = new Frequency(letter, 1);
            frq.code = code;
            dictionaryList.add(frq);
        }
        //removes last code because it usually contains weird character stuff
        dictionaryList.get(dictionaryList.size()-1).code = dictionaryList.get(dictionaryList.size()-1).code.replaceAll("/", "");
        dictionaryList.remove(dictionaryList.size()-1);
        
        /*
        for(int i = 0; i < dictionaryList.size(); i++)
        {
            System.out.println(dictionaryList.get(i).str + ", " + dictionaryList.get(i).code);
        }
        */
       
    }
}
