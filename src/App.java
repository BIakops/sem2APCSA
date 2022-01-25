import java.util.List;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        // int[] a = smth.computeDigitSummation(new int[] { 1, 2, 3, 50, 5, 6, 7, 8, 9,
        // 10 }, 4);
        // smth s = new smth();
        // System.out.println(a[0] + " and " + a[1]);
        // System.out.println(s.fibo(5));
        // System.out.println(smth.bigsmall(new double[] { 0.0, -1, 15, 200 })[0]);
        List<Integer> testArr  =  Matchmaking.getTreeLastRow(5);

        System.out.println(testArr);
        System.out.println(Matchmaking.getScores(5));
        // Matchmaking.RoundRobin(5, 3);
    }
}
