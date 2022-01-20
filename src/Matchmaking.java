import java.util.List;
import java.io.Console;
import java.util.ArrayList;
public class Matchmaking {
    static boolean DEBUG = true;
    static class Node{
        int data;
        Node left,right;
        Node(int data)
        {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    
    public void RoundRobin(int numteams, int teamsAdvancing) {
        /// Determine if tiebreakers occur
        /// When an arr of equal losses occur when n place to advance has at least n + 1 teams with same score
        List<Integer> tree = fillTree(numteams);


    }
    public static void printList(List<Integer> tree)
    {
        for(int i = 0; i <tree.size();i++)
        {
            System.out.println(tree.get(i));
        }
    }
    public static List<Integer> fillTree(int numteams)
    {
        List<Integer> tree = new ArrayList<Integer>();
        int numIterations = (int)(Math.pow(2,numteams-2))+1; 
        System.out.println(numIterations);
        // = num times to iterate to create tree
        //I.e 2 teams means 3 nodes i=0 i<=numIterations
        ///      0 
        ///    1   -1
        ///  2  0 0  -2
        /// 2 var 1 parent index,

        /// boolean parent Index valid

        /// Edit this, part
        if(numteams %2==0)
        {
            numIterations +=2;
        }
        //Edit above
        for (int i = 0; i < numIterations; i++) {
            if(i == 0)
            {
                tree.add(i);
            }
            populateChilds(tree, i);

            
        }

        System.out.println(tree.size());
        return tree;
    }
    public static boolean isParentIndexValid(int myIndex, int oddEven)
    {
        double cToPFormula = (myIndex - oddEven)/2;
        System.out.println(cToPFormula);
        if(cToPFormula % 1 == 0){
            return true;
        }

        return false;
    }
    public static void populateChilds(List<Integer> binaryTree, int parentIndex)
    {
        
            binaryTree.add(binaryTree.get(parentIndex) + 1);
            binaryTree.add(binaryTree.get(parentIndex) - 1);
        
    }
}
