import java.lang.reflect.GenericArrayType;

public class smth {
    public int a;
    public double b;
    public float c;

    smth() {
        a = 5;
        b = 2.0;
        c = 1f;
    }

    public void computeSum(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            a += arr[i];
        }
    }

    static int[] computeDigitSummation(int[] arr, int digit) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (!(j == i))
                    if (arr[i] + arr[j] == digit && i != j) {
                        return new int[] { arr[i], arr[j] };
                    }

            }
        }
        return null;
    }

    /// 1, 2, 3, 4, 5, 6, 7
    /// [0][1][2][3][4][5][6]
    int a() {
        return a;
    }

    int b() {
        return (int) b;
    }

    public int fibo(int n) {
        if (n == 0)
            return 0;
        return n + fibo(n - 1);
    }

    public int calcSpain(String[] arr) {
        int amt = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("Spain")) {
                amt++;
            }
        }

        return amt;
    }

    public static double[] bigsmall(double[] arr) {
        double min = Double.MIN_VALUE;
        double max = Double.MAX_VALUE;
        int minI = 0;
        int maxI = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < max)
                max = arr[i];
            maxI = i;
            if (arr[i] > min)
                min = arr[i];
            minI = i;
        }
        System.out.println(maxI + ", " + minI);
        return new double[] { maxI, minI };
    }
}
