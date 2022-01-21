import java.util.List;
import java.io.Console;
import java.util.ArrayList;

public class Matchmaking {
    static boolean DEBUG = true;

    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private static int[][] groupNums(List<Integer> arr, int numbersInRow) { /// Testpls
        /// Sort thru document

        int[][] tempArr = new int[numbersInRow][(int) (Math.pow(2, numbersInRow - 1))];
        List<Integer> sorted = maxBubbleSort(arr);
        int prevInt = sorted.get(0);
        int index = 1;
        int curNum = 0;
        tempArr[0][0] = prevInt;
        for (int i = 1; i < sorted.size(); i++) {
            if (prevInt == sorted.get(i)) {
                tempArr[curNum][index] = sorted.get(i);
                index++;

            } else {
                curNum++;
                index = 0;
                tempArr[curNum][index] = sorted.get(i);
            }
            prevInt = sorted.get(i);
        }
        return new int[][] { { 0 } };
    }

    public static List<Integer> maxBubbleSort(List<Integer> arr) {
        // Debug
        boolean searched = false;

        while (!searched) {
            searched = true;

            for (int i = 0; i < arr.size() - 1; i++) {
                if (arr.get(i) < arr.get(i + 1)) {
                    int temp = arr.get(i + 1);
                    arr.set(i + 1, arr.get(i));
                    arr.set(i, temp);
                    searched = false;
                }
            }
        }
        return arr;
    }

    public static void RoundRobin(int numTeams, int teamsAdvancing) {
        /// Determine if tiebreakers occur
        /// When an arr of equal losses occur when n place to advance has at least n + 1
        /// teams with same score
        List<Integer> tree = fillTree(numTeams);
        tree = getTreeLastRow(tree, getNumParentNodes(numTeams));
        List<int[]> scoresPoss = new ArrayList<int[]>();

    }

    private static List<Integer> getTreeLastRow(List<Integer> list, int startingIndex) {
        List<Integer> tempArr = new ArrayList<>();
        for (int i = 0; i < list.size() - startingIndex; i++) {
            tempArr.set(i, list.get(startingIndex + i));
        }
        return tempArr;
    }

    public static void printList(List<Integer> tree) {
        for (int i = 0; i < tree.size(); i++) {
            System.out.println(tree.get(i));
        }
    }

    public static List<Integer> fillTree(int numTeams) {
        List<Integer> tree = new ArrayList<Integer>();
        numTeams--;
        int numIterations = getNumParentNodes(numTeams);
        System.out.println(getNumParentNodes(numTeams));
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

    public static boolean isParentIndexValid(int myIndex, int oddEven) {
        double cToPFormula = (myIndex - oddEven) / 2;
        System.out.println(cToPFormula);
        if (cToPFormula % 1 == 0) {
            return true;
        }

        return false;
    }

    public static void populateChilds(List<Integer> binaryTree, int parentIndex) {
        /// Add left right vals
        binaryTree.add(binaryTree.get(parentIndex) + 1);
        binaryTree.add(binaryTree.get(parentIndex) - 1);
    }

    private static int getNumParentNodes(int rows) {

        int sumP2 = 0;
        for (int i = 0; i < rows; i++) {
            /// Using 2^n is size of each row in tree,
            /// can determine num of Parent nodes
            ///
            sumP2 += (int) (Math.pow(2, i));
        }
        return sumP2;
    }

    private static int[] createScoreFromNode(int treeLevel, int score) {
        int[] scores = new int[treeLevel];

        for (int i = 0; i < treeLevel; i++) {
            if (i == 0) {
                scores[0] = score;
            }
        }
        return scores;
    }

}
