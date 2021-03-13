import java.util.ArrayList;
import java.util.Scanner;
public class Driver
{
     static String mainstr;
     static createDictionaries CD;
     static ArrayList<String> lines = new ArrayList<String>();
     public static void main()
     {
         try{
                 
             System.out.println("Hi user");
             //System.out.println();
             sleepLess(2000);
             
             System.out.println("Welcome to Izzy's proprietary compression algorithm");
             //System.out.println();
             sleepLess(2000);
             System.out.println();
             System.out.println("This program uses three different types of compression in order to acheive the best compression");
             System.out.println("and of course get a good grade on this final project");
            
             sleep(2300);
             System.out.println();
             System.out.println("The program uses character, word, and phrase compression and determines the which type of");
             System.out.println("compression worked best on any set of given text");
             sleep(2300);
             System.out.println();
             System.out.println("Please do no include '/', '#' or '~' in your program");
             System.out.println("Every other character should work");
             sleep(1500);
             
             boolean cont = false;
             System.out.println("Please press '1' for a guided program, '2' for a unguided program, or '0' to exit");
             String userInput = "";
             Scanner sc = new Scanner(System.in);
             userInput = sc.nextLine();
             int x = 0;
             while(!cont)
             {
                 if(userInput.equals("1"))
                 {
                     cont = true;
                      x = 4000;
                     System.out.println("You have selected a guided program");
                 }
                 else if (userInput.equals("2"))
                 {
                     cont = true;
                     System.out.println("You have selected an unguided program");
                 }
                 else if(userInput.equals("0"))
                 {
                     System.out.println("You have selected to exit");
                     exit();
                 }
                 else
                 {
                     System.out.println("Please enter an integer between '0' and '1'");
                     Scanner sc1 = new Scanner(System.in);
                     userInput = sc1.nextLine();
                 }
             }
             
             
             while(true)
             {
                 System.out.println("Your file will now begin to compress");
                 sleep(x);
                 Long start = System.currentTimeMillis();
                 Reader uncompressedReader = new Reader("uncompressedFile.txt");
                 try {
                     lines = uncompressedReader.readIn();
                 }
                 catch(Exception e)
                 {
                     System.out.println(e);
                 }
                 mainstr = uncompressedReader.concatonateText();
                 String otherString = mainstr;
                 
                 
                 CD = new createDictionaries();
                 createDictionaries(); 
                 Compress comp = new Compress();
                 comp.compressWords(mainstr, CD.wordDictionary, CD.newWordList);
                 comp.compressChars(otherString, CD.charDictionary, CD.newCharList);
                 comp.compressPhrases(mainstr, CD.phraseDictionary, CD.newPhraseList);
                 Long endCompress = System.currentTimeMillis();
                 
                 System.out.println("Your file has finished compression");
                 
                 System.out.println("Here is your uncompressed file:");
                 Double numBytesUn = (double) uncompressedReader.printNumCharacters();
                 System.out.println();
                
                 System.out.println("Here are your compressed files:");
                 
                 Reader read2 = new Reader("compressedFileChars.txt");
                 try {
                     lines = read2.readIn();
                 }
                 catch(Exception e)
                 {
                     System.out.println(e);
                 }
                 Reader read3 = new Reader("compressedFileWords.txt");
                 try {
                     lines = read3.readIn();
                 }
                 catch(Exception e)
                 {
                     System.out.println(e);
                 }
                  Reader read4 = new Reader("compressedFilePhrases.txt");
                 try {
                     lines = read4.readIn();
                 }
                 catch(Exception e)
                 {
                     System.out.println(e);
                 }
                 Double numBytesCo = (double) read2.printNumCharacters();
                 System.out.println();
                 Double numBytesCo1 = (double) read3.printNumCharacters();
                 System.out.println();
                 Double numBytesCo2 = (double) read4.printNumCharacters();
                 sleep(x);
                 sleep(x);
                 
                
                 System.out.println("Decompression will now begin");
                 Long start2 = System.currentTimeMillis();
                 
                 Decompress dec = new Decompress(read2.concatonateText(),7, "reuncompressedChars.txt");
                 Decompress dec1 = new Decompress(read3.concatonateText(),5, "reuncompressedWords.txt");
                 Decompress dec2 = new Decompress(read4.concatonateText(), 5, "reuncompressedPhrases.txt");
                 
                
                 dec.createDictionary();
                 dec.translate();
                 dec1.createDictionary();
                 dec1.translate();
                 dec2.createDictionary();
                 dec2.translate();
                 
                 
                 Long end = System.currentTimeMillis();
                 sleep(x);
                 System.out.println("Decompression finished");
                 sleep(x);
                 
                 System.out.println("The new decompressed files have been output to 'reuncompressedWords.txt', 'reuncompressedChars.txt',"); 
                 System.out.println("and 'reuncompressedPhrases.txt'");
                 System.out.println();
                 sleep(x);
                
                 System.out.println("Storage:");
                 int charStorage = (int) (((1 - (numBytesCo/numBytesUn))*100));
                 int wordStorage = (int) (((1 - (numBytesCo1/numBytesUn))*100));
                 int phraseStorage = (int) (((1 - (numBytesCo2/numBytesUn))*100));
                 if(charStorage > 0)
                 {
                     System.out.println("\t" +"You saved aproximately " + charStorage + "% storage with character compression,");
                 }
                 else
                 {
                     System.out.println("\t" +"You saved no space with character compression,");
                 }
                 if(wordStorage > 0)
                 {
                     System.out.println("\t" +"aproximately " + wordStorage + "% storage with word compression,");
                 }
                 else
                 {
                     System.out.println("no space was saved with word compression,");
                 }
                 if(phraseStorage > 0)
                 {
                     System.out.println("\t" +"and aproximately " + phraseStorage + "% storage with phrase compression");
                 }
                 else
                 {
                     System.out.println("\t" + "and no space was saved with phrase compression");
                 }
                 System.out.println();
                 
                 sleep(x);
                 if(wordStorage > charStorage)
                 {
                     System.out.println("For this file, word compression is the best compression method");
                 }
                 else
                 {
                     System.out.println("For this file, character compression is the best compression method");
                 }
                 sleep(x);
                 
                 Long var1 = endCompress - start;
                 Long var2 = end - start2 ;
                 System.out.println("Running time:");
                 System.out.println("\t" + "Compression time: " + (var1) + " milliseconds");
                 System.out.println("\t" +"Decompression time: " + (var2) + " milliseconds");
                 System.out.println("\t" + "Total time: " + (var1 + var2) + " milliseconds");
                 Scanner sc2 = new Scanner(System.in);
                 System.out.println("Would you like to compress another file?");
                 System.out.println("Press '1' to compress or '0' to exit");
                 userInput = sc2.nextLine();
                 cont = false;
                 while(!cont)
                 {
                     Scanner sc3 = new Scanner(System.in);
                     if(userInput.equals("1"))
                     {
                         cont = true;
                         System.out.println("You have selected to continue");
                         System.out.println("Please enter the new text into 'uncompressed.txt' and press anything when you are"); 
                         System.out.println("ready to continue");
                         userInput = sc3.nextLine();
                         
                     }
                     else if(userInput.equals("0"))
                     {
                         System.out.println("You have selected to exit");
                         exit();
                     }
                     else
                     {
                         System.out.println("Please enter an integer between '0' and '1'");
                         Scanner sc1 = new Scanner(System.in);
                         userInput = sc1.nextLine();
                     }
                 
                 
                     
                 }
            
             }
         }
        catch(Exception e)
        {
            System.out.println("A problem has occured");
            System.out.println("Please exit the program and try again entering different text");
        }
     }
     public static void createDictionaries()
     {
         CD.createCharDictionary(mainstr);
         CD.createWordDictionary(mainstr);
         CD.createPhraseDictionary(mainstr);
     }
     public static void displayFrequencies()
     {
         //CD.displayFrequenciesLetters();
         //CD.displayFrequenciesWords();
         //CD.displayFrequenciesPhrases();
     }
     public static void sleep(int x)
     {
         try
         {
             Thread.sleep(x);
         }
         catch(InterruptedException ex)
         {
             System.out.println("A problem has occured");
         }    
     }
      public static void sleepLess(int x)
     {
         try
         {
             Thread.sleep(x);
         }
         catch(InterruptedException ex)
         {
             System.out.println("A problem has occured");
         }    
     }
     public static void exit()
     {
         System.exit(0);
     }
}
