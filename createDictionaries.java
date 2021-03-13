import java.util.ArrayList;
import java.util.Collections;
public class createDictionaries
{
    ArrayList<Frequency> charList = new ArrayList<Frequency>();
    ArrayList<Frequency> wordList = new ArrayList<Frequency>();
    ArrayList<Frequency> phraseList = new ArrayList<Frequency>(); 
    ArrayList<Frequency> newCharList = new ArrayList<Frequency>();
    ArrayList<Frequency> newWordList = new ArrayList<Frequency>();
    ArrayList<Frequency> newPhraseList = new ArrayList<Frequency>();
    InsertionSort ins = new InsertionSort();
    String charDictionary = "";
    String wordDictionary = "";
    String phraseDictionary = "";
    Tree charTree;
    Tree wordTree;
    Tree phraseTree;
    Compress comp = new Compress();
    public createDictionaries()
    {
        
    }
    //Creates the dictionary to translate characters into binary
    public void createCharDictionary(String str)
    {
        int j = 0;
        //checks if character is in charList and adds it if it isn't
        for(int i = 0; i < str.length(); i++)
        {
            String letter = str.substring(i,i+1);
            for(j = 0; j < charList.size(); j++)
            {
                if(letter.equals(charList.get(j).str))
                {
                    charList.get(j).frequency++;
                    j = 50;
                }
            }
            if(j < 49)
            {
               charList.add(new Frequency(letter, 1));
            }
        }
        ins.insertionArrayList(charList);
        charTree = new Tree(charList);
        charTree.createTree();
        charTree.setCodes();
        charTree.printTree();
        newCharList = charTree.getList();
        //removes all characters where the binary code is greater than the letter's size
        for(int i = 0; i < newCharList.size(); i++)
        {
            if(newCharList.get(i).code.length() > 7)
            {
                newCharList.remove(i);
                i--;
            }
        }
        Dictionary dic = new Dictionary(newCharList);
        charDictionary = dic.toString();
    }
    //Creates the dictionary to translate words into binary
    public void createWordDictionary(String str)
    {
        str = str.replaceAll("/", " / ");
        str = str.replaceAll("#", " # ");
        String[] lines = str.split(" ", 10000);
        int j = 0;
        //checks if character is in wordList and adds it if it isn't
        for(int i = 0; i < lines.length; i++)
        {
            for(j = 0; j < wordList.size(); j++)
            {
                if(lines[i].equals(wordList.get(j).str))
                {
                    wordList.get(j).frequency++;
                    j = 1000;
                }
            }
            if(j < 1000)
            {
               if(lines[i].length() > 0)
               {
                   wordList.add(new Frequency(lines[i],1));
               }
            }
        }
        ins.insertionArrayList(wordList);
        wordTree = new Tree(wordList);
        wordTree.createTree();
        wordTree.setCodes();
        wordTree.printTree();
        newWordList = wordTree.getList();
        //removes all words where the binary code is greater than the words size and that have a frequency
        //less than 3
        for(int i = 0; i < newWordList.size(); i++)
        {
            int wordLength = (newWordList.get(i).str.length()* 3);
            int codeLength = newWordList.get(i).code.length();
            int frequency = newWordList.get(i).frequency;
            if(codeLength > wordLength || frequency < 3)
            {
                newWordList.remove(i);
                i--;
            }
        }
        Dictionary dic = new Dictionary(newWordList);
        wordDictionary = dic.toString();
    }
    //Creates the dictionary to phrases characters into binary
    public void createPhraseDictionary(String line)
    {
        String phrase5 = "";
        String phrase4 = "";
        String phrase3 = "";
        String phrase2 = "";
        line = line.replaceAll("/", " ");
        String[] words = line.split(" ", 100);
        for(int j = 0; j < words.length-1; j++)
        {
            //Creates phrases of varying lengths based on text
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
            
            
            Boolean found = false;
            for(int q = 0; q < phraseList.size(); q++)
            {
                //Checks if phrase matches a phrase already in the phraseList
                if(phrase5.equals(phraseList.get(q).str) && !found)
                {
                    phraseList.get(q).frequency++;
                    j = j + 4;
                    q = 10000;
                    found = true;
                    
                }
               
                else if(phrase4.equals(phraseList.get(q).str) && !found)
                {
                    phraseList.get(q).frequency++;
                    j = j + 3;
                    q = 10000;
                    found = true;
                   
                }
               
                else if(phrase3.equals(phraseList.get(q).str) && !found)
                {
                    phraseList.get(q).frequency++;
                    j = j + 2;
                    q= 10000;
                    found = true;
                    
                }
               
                else if(phrase2.equals(phraseList.get(q).str) && !found)
                {
                    phraseList.get(q).frequency++;
                    j = j + 1;
                    q = 10000;
                    found = true;
                    
                }
                
            }
            if(!found)
            {
                    //Adds phrases to phraseList if they have not already been found in list
                    phraseList.add(new Frequency(phrase5, 1));
                    phraseList.add(new Frequency(phrase4, 1));
                    phraseList.add(new Frequency(phrase3, 1)); 
                    phraseList.add(new Frequency(phrase2, 1));
            }
                
                
        }
        //removes any phrases that only appear once
        for(int i = 0; i < phraseList.size(); i++)
        {
            if(phraseList.get(i).frequency < 2)
            {
                phraseList.remove(i);
                i--;
            }
        }
        ins.insertionArrayList(phraseList);
        phraseTree = new Tree(phraseList);
        phraseTree.createTree();
        phraseTree.setCodes();
        phraseTree.printTree();
        newPhraseList = phraseTree.getList();
        Dictionary dic = new Dictionary(newPhraseList);
        phraseDictionary = dic.toString();
    }
    //Displays frequencies of letters, words, and phrases in a text
    public void displayFrequenciesLetters()
    {
       for(int i = 0; i < charList.size(); i++)
       {
           System.out.println("Char: " + charList.get(i).str + ", frequency: " + charList.get(i).frequency);
       }
    }
    public void displayFrequenciesWords()
    {
       for(int i = 0; i < wordList.size(); i++)
       {
           System.out.println("Word: " + wordList.get(i).str + ", frequency: " + wordList.get(i).frequency);
       }
    }
    public void displayFrequenciesPhrases()
    {
       for(int i = 0; i < phraseList.size(); i++)
       {
           if(phraseList.get(i).frequency != 0)
           {
               System.out.println("Phrase: " + phraseList.get(i).str + ", frequency: " + phraseList.get(i).frequency);
           }
       }
    }
    
}
