import java.util.ArrayList;
public class Tree
{
    ArrayList<Frequency> list = new ArrayList<Frequency>();
    ArrayList<Frequency> newList = new ArrayList<Frequency>();
    InsertionSort ins = new InsertionSort();
    Frequency root;
    //Manages the code setting aspect of the tree
    public Tree(ArrayList<Frequency> List)
    {
        list = List;
    }
    public void createTree()
    {
        //Creates the tree 
        while(list.size() > 1)
        {
            Frequency frq1 = list.get(0);
            Frequency frq2 = list.get(1);
            Frequency internal = new Frequency(null, frq1.frequency + frq2.frequency);
            internal.right = frq1;
            internal.left = frq2;
            list.remove(1);
            list.remove(0);
            list.add(internal);
            ins.insertionArrayList(list);
        }
        root = list.get(0);
    }
    public void setCodes()
    {
        //sets the code 
        if(root.left != null)
         {
             root.left.code = "";
             setCodesRecursive(root);
         }
    }
    public Frequency setCodesRecursive(Frequency root)
    {
        //sets code using recursion
        if(root == null)
        {
            return null;
        }
        if(root.left != null)
        {
            root.left.code = root.code + "0";
        }
        setCodesRecursive(root.left);
        if(root.right != null)
        {
            root.right.code = root.code + "1";
        }
        setCodesRecursive(root.right);
        return root;
    }
    public void printTree()
    {
        //print the tree and adds all roots to a new list
        printTreeRecursive(root);
    }
    public Frequency printTreeRecursive(Frequency root)
    {
        //Adds the roots to a list which will become the dictionary
        if(root == null)
        {
            return null;
        }
        printTreeRecursive(root.left);
        if(root.str != null)
        {
            //System.out.println(root.str + ", " + root.code);
            newList.add(root);
        }
        printTreeRecursive(root.right);
        return null;
    }
    public ArrayList<Frequency> getList()
    {
        return newList;
    }
}

