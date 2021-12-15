public class smth {
    public int a;
    public double b;
    public float c;

    smth() {
        a = 5;
        b = 2.0;
        c = 1f;
    }

    void urMOM() {

    }

    void shit() {

    }

    void compute(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            a += arr[i];
        }
    }

    static int[] computeArrTI(int[] arr, int digit) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (!(j == i))
                    if (arr[i] + arr[j] == digit) {
                        return new int[] { arr[i], arr[j] };
                    }

            }
        }
        return null;
    }

    int a() {
        return a;
    }

    int b() {
        return (int) b;
    }

}
