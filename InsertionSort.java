import java.util.ArrayList;
public class InsertionSort
{
    public InsertionSort()
    {
        
    }
    public static void insertionArrayList(ArrayList<Frequency> array){
        //sorts an array from gratest to least
        for (int i = 1; i < array.size(); i++) { 
           int z = (i-1);
           while (z >= 0 && array.get(z+1).frequency < array.get(z).frequency) {
               Frequency temp = array.get(z+1);
               Frequency temp1 = array.get(z);
               array.set(z, temp);
               array.set(z + 1, temp1);
               z--;
           }   
        } 
    } 
}
