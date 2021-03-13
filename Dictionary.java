import java.util.ArrayList;
public class Dictionary
{
    ArrayList<Frequency> dictionaryList = new ArrayList<Frequency>();
    public Dictionary(ArrayList<Frequency> DictionaryList)
    {
        dictionaryList = DictionaryList;
    }
    public String toString()
    {
        //writes the arrayList of translations into a single line which can be tacked on to the compressed file
        String dictionary = "~";
        for(int i = 0; i < dictionaryList.size(); i++)
        {
            dictionary += dictionaryList.get(i).str + dictionaryList.get(i).code;
        }
        return dictionary;
    }
    
    
    
    
    
}
