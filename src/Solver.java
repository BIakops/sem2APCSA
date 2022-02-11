import java.util.ArrayList;

public class Solver {
    private String[] array;

    public Solver(String[] array) {
        this.array = array;
    }

    /// Num Message Finder where encrypted with a key at start and end. Start at
    /// first occurence of 0 and end at first occurance at 9
    public ArrayList<String> findMessage() {
        /// Search through the string array
        ArrayList<String> message = new ArrayList<String>();
        char temp = '0';
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals("")) {
                continue;
            }
            char t = array[i].charAt(0);
            if (t == temp && t != 9) {
                message.add(array[i].substring(1, array[i].length() - 1));
                temp = array[i].charAt(array[i].length() - 1);
            }
            if (temp == '9') {
                break;
            }
        }

        return message;
    }

}
