import java.util.ArrayList;
public class Compress
{
    public Compress()
    {
    }
    //substitutes characters in charList with their binary codes
    public void compressChars(String uncompressedString, String charDictionary, ArrayList<Frequency> charList)
    {
        String compressedString = "";
        Boolean inList = false;
        for(int i = 0; i < uncompressedString.length(); i++)
        {
            inList = false;
            String letter = uncompressedString.substring(i, i+1);
            //Swithces string for binary
            for(int j = 0; j < charList.size(); j++)
            {
                if(letter.equals(charList.get(j).str))
                {
                    compressedString += charList.get(j).code;
                    j = 1000;
                    inList = true;
                }   
            }
            //Adds character if not yet substituted
            if(!inList)
            {
                compressedString += letter;
            }
        }
        Writer wri = new Writer("compressedFileChars.txt", compressedString, charDictionary);
        try
        {
            wri.writeOut();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void compressWords(String uncompressedString, String wordDictionary, ArrayList<Frequency> wordList)
    {
        uncompressedString = uncompressedString.replaceAll("/", " / ");
        uncompressedString = uncompressedString.replaceAll("#", " # ");
        String[] words = uncompressedString.split(" ", 100000);
        String compressedString = "";
        Boolean inList = false;
        //Swithces string for binary
        for(int i = 0; i < words.length; i++)
        {
            String word = words[i];
            
            inList = false;
            for(int j = 0; j < wordList.size(); j++)
            {
                if(word.equals(wordList.get(j).str))
                {
                    compressedString += wordList.get(j).code;
                    j = 1000;
                    
                    inList = true;
                }   
            }
            //Adds character if not yet substituted
            if(!inList)
            {
                compressedString += word + " ";
            }
        }
        Writer wri = new Writer("compressedFileWords.txt", compressedString, wordDictionary);
        try
        {
            wri.writeOut();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void compressPhrases(String uncompressedString, String phraseDictionary, ArrayList<Frequency> phraseList)
    {
        String compressedString = "";
        String phrase5 = "";
        String phrase4 = "";
        String phrase3 = "";
        String phrase2 = "";
        boolean found = false;
        uncompressedString = uncompressedString.replaceAll("/", " / ");
        
        String[] words = uncompressedString.split(" ", 100000);
        for(int j = 0; j < words.length-1; j++)
        {
            found = false;
            //Create various phrases
            if(j < words.length-4)
            {
                phrase5 = words[j] + " " + words[j+1] + " " + words[j+2] + " " + words[j+3] + " " + words[j+4];
            }
            if(j < words.length-3)
            {
                phrase4 = words[j] + " " + words[j+1] + " " + words[j+2] + " " + words[j+3];
            }
            if(j < words.length-2)
            {
                phrase3 = words[j] + " " + words[j+1] + " " + words[j+2];
            }
            phrase2 = words[j] + " " + words[j+1];
            //Swithces string for binary if found
            for(int q = 0; q < phraseList.size(); q++)
            {
                if(phrase5.equals(phraseList.get(q).str))
                {
                    compressedString += phraseList.get(q).code;
                    j = j + 4;
                    q = 10000;
                    found = true;
                }
                else if(phrase4.equals(phraseList.get(q).str))
                {
                    compressedString += phraseList.get(q).code;
                    j = j + 3;
                    q = 10000;
                    found = true;
                }
                else if(phrase3.equals(phraseList.get(q).str))
                {
                    compressedString += phraseList.get(q).code;
                    j = j + 2;
                    q= 10000;
                    found = true;
                }
                else if(phrase2.equals(phraseList.get(q).str))
                {
                    compressedString += phraseList.get(q).code;
                    j = j + 1;
                    q = 10000;
                    found = true;
                }
            }
            //Adds phrase if not found on list
            if(!found)
            {
                compressedString += words[j] + " ";
            }
        }
        Writer wri = new Writer("compressedFilePhrases.txt", compressedString, phraseDictionary);
        try
        {
            wri.writeOut();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
