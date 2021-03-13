public class Frequency
{
    //The base unit of the tree
    int frequency;
    Frequency left, right;
    String str;
    String code;
    public Frequency(String charString, int frq)
    {
       frequency = frq;
       str = charString;
       left = null;
       right = null;
       code = "";
    }
    

}
