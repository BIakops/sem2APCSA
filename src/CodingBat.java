public class CodingBat {
    public static String[] fizzBuzz(int start, int end) {
        String[] rArr = new String[end - start];
        int index = 0;
        for (int i = start; i < end; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                rArr[index] = "FizzBuzz";
            } else if (i % 5 == 0) {
                rArr[index] = "Fizz";
            } else if (i % 3 == 0) {
                rArr[index] = "Buzz";
            } else {
                rArr[index] = String.valueOf(i);
            }
            index++;
        }
        return rArr;
    }

    public static int countClumps(int[] arr) {
        /// Search each node
        // if previous is not
        int amtClumps = 0;
        boolean isPastClump = false;
        for (int i = 0; i + 1 < arr.length; i++) {
            if (isPastClump && arr[i] == arr[i + 1]) {
                isPastClump = true;
                amtClumps++;
            }
        }
        return amtClumps;
    }
}
