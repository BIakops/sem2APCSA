import java.util.ArrayList;
import java.util.List;

public class Matchmaking {

  static boolean DEBUG = false;

  static class Node {

    int data;
    Node left, right;

    Node(int data) {
      this.data = data;
      this.left = null;
      this.right = null;
    }
  }

  public static ArrayList<ArrayList<Integer>> groupNums(List<Integer> arr) { /// Works now Jan 25 Push
    /// Sort thru document

    ArrayList<ArrayList<Integer>> finalArray = new ArrayList<>();
    int prevInt = arr.get(0);
    ArrayList<Integer> temp = new ArrayList<>();
    for (int i = 0; i < arr.size(); i++) {
      if (arr.get(i) == prevInt) {
        temp.add(arr.get(i));
      } else {
        
        finalArray.add(new ArrayList<>( temp)); // Make sure it refrences a new arr not the current one, otherwise it'll rewrite.
        if(DEBUG) System.out.println(finalArray + " " + prevInt);
        temp.clear();
        temp.add(arr.get(i));
        prevInt = arr.get(i);
      }
      finalArray.add(new ArrayList<>(temp));
      
    }
    if(DEBUG) System.out.println(finalArray);
    return finalArray;
  }

  public static void RoundRobin(int numTeams, int teamsAdvancing) {
    /// Determine if tiebreakers occur
    /// When an arr of equal losses occur when n place to advance has at least n + 1
    /// teams with same score

    ArrayList<int[]> results = getScores(numTeams);
    boolean tieOccurs = false;
    int numTies = 0;
    for (int[] scores : results) {
      if (
        teamsAdvancing < scores.length &&
        scores[teamsAdvancing - 1] == scores[teamsAdvancing]
      ) {
        tieOccurs = true;
        numTies++;
      }
    }
    if (tieOccurs) System.out.println(
      "Tie occurs, tiebreaker measures suggested. Probability occuring is " +
      (float) numTies /
      results.size() *
      100 +
      "%"
    );
  }

  public static ArrayList<int[]> getScores(int numTeams) {
    ArrayList<ArrayList<Integer>> scores = createScores(numTeams);
    ArrayList<int[]> results = new ArrayList<>();
    for (int i = 0;i < (int)Math.round(scores.size() / 2);i++) { /// Iterate through half,
      for (int j = 0; j < scores.get(i).size(); j++) {
        int[] tempArr = new int[numTeams];
        int lengthOfArr = 0;
        for (int k = 0; k < j; k++) { /// Add up in curr index
          tempArr[k] = scores.get(j).get(k);
          lengthOfArr++;
        }
        int scoreIndex = i + 1;
        int currI = scores.size() - scoreIndex;
        while (lengthOfArr <= numTeams) { // implement validation  checks on runtine creation of these arr, save metod creation
          tempArr[lengthOfArr - 1] = scores.get(currI).get(0); // Error part
          if (!scores.get(i).equals(scores.get(currI))) {
            scoreIndex++;
          }
        }
        if (isValid(tempArr)) {
          results.add(tempArr);
        }
      }
    }
    System.out.println(results);
    return results;
  }

  private static boolean isValid(int[] arr) {
    /// Sum of all team values must == 0
    int sum = 0;
    for (int i : arr) {
      sum += i;
    }
    return sum == 0;
  }
  
  public static ArrayList<ArrayList<Integer>> createScores(int numTeams) {
    return groupNums(getTreeLastRow(numTeams));
  }
  ///dont touch below
  public static List<Integer> getTreeLastRow(int numTeams)
  {
    return getTreeLastRow(fillTree(numTeams),getNumParentNodes(numTeams-1));
  }
  public static List<Integer> getTreeLastRow(List<Integer> list,int startingIndex) {
    List<Integer> tempArr = new ArrayList<Integer>();
    if (DEBUG) System.out.println(list.size() - startingIndex);
    for (int i = 0; i < list.size() - startingIndex; i++) {
      tempArr.add(list.get(startingIndex + i));
    }
    tempArr = maxBubbleSort(tempArr);
    return tempArr;
  }
  
  private static List<Integer> fillTree(int numTeams) {
    List<Integer> tree = new ArrayList<Integer>();
    numTeams--;
    int numIterations = getNumParentNodes(numTeams);
    //System.out.println(getNumParentNodes(numTeams));
    // = num times to iterate to create tree
    // I.e 2 teams means 3 nodes i=0 i<=numIterations
    /// 0
    /// 1 -1 T = 2
    /// 2 0 0 -2 T = 3
    ///
    /// 2 var 1 parent index,
    /*
     * 0 2^0
     * 1 -1 2^1
     * 2 0 0 -2 2^2
     * 3 1 1 -1 1 -1 -1 3
     * 4 2 2 0 2 0 0-2 2 0
     *
     *
     */
    /// boolean parent Index valid

    for (int i = 0; i < numIterations; i++) {
      if (i == 0) {
        tree.add(i);
      }
      populateChilds(tree, i);
    }

    return tree;
  }
  /// Populate left right child nodes from given parent index
  private static void populateChilds(List<Integer> binaryTree, int parentIndex) {
    /// Add left right vals
    binaryTree.add(binaryTree.get(parentIndex) + 1);
    binaryTree.add(binaryTree.get(parentIndex) - 1);
  }

  public static int getNumParentNodes(int rows) { // WORKS
    int sumP2 = 0;
    for (int i = 0; i < rows; i++) {
      /// Using 2^n is size of each row in tree,
      /// can determine num of Parent nodes
      ///
      sumP2 += (int) (Math.pow(2, i));
    }
    return sumP2;
  }
  /// Basic Bubble Sort
  private static List<Integer> maxBubbleSort(List<Integer> arr) { //Works
    // Works,
    // Merge Sort, quick sort proved ineffective given how much each side had mirroing values of unequal sizes.
    boolean searched = false;

    while (!searched) {
      searched = true;

      for (int i = 0; i < arr.size() - 1; i++) {
        if (arr.get(i) < arr.get(i + 1)) {
          //Swap nodes
          int temp = arr.get(i + 1);
          arr.set(i + 1, arr.get(i));
          arr.set(i, temp);
          searched = false;
        }
      }
    }
    return arr;
  }
}
