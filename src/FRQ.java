public class FRQ {
    class printING { // Jan 25 U6 Arrays
        // Method 1: print words in arr if last 3 chars = "ing"
        // All are lowercase
        public void printWordING(String[] words) {
            for (String w : words) {
                if (w.substring(w.length() - 3).equals("ing")) {
                    System.out.println(w);
                }
            }
        }
    }

    class wageBonus { // Jan 25 U6 Arrays
        private int[] itemsSold;
        private double[] wages;
        /// Assume getter setter all made correctly

        public double computeBonusThreshold() {

            double min = (double) Integer.MAX_VALUE;
            double max = (double) Integer.MIN_VALUE;
            double sum = 0;
            for (int item : itemsSold) {
                if (item < min) {
                    min = item;
                }
                if (item > max) {
                    max = item;
                }
                sum += item;
            }
            return (sum - min - max) / (itemsSold.length - 2);

        }
        /*
         * Write the method computeWages.
         * Assumptions:
         * - itemsSold init properly and size >= 3
         * - itemsSold.length == wages.length
         * Your solution must call computeBonusThreshold appropriately to receive full
         * credit.
         * Computes employee wages as described in part (b)and stores them in wages.
         * The parameter fixedWage represents the fixed amount each employee
         * is paid per day.
         * The parameter perItemWage represents the amount each employee
         * is paid per item sold.
         * 
         * Example output:
         * fixedWage = 10.0 perItemWage = 1.5
         * bonusThreshold = 51.5
         * Employee 0; itemsSold[0] = 48, wage[0] = 82.0
         * Employee 9; itemsSold[9] = 60, wage[9] = 110.0
         * 
         * 
         */

        public void computeWages(double fixedWage, double perItemWage) {
            for (int i = 0; i < itemsSold.length; i++) {
                double bonus = 1;
                if (itemsSold[i] > computeBonusThreshold()) {
                    bonus += 0.1;
                }
                wages[i] = (fixedWage + perItemWage * itemsSold[i]) * bonus;
            }
        }
    }
}
