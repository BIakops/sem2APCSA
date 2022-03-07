
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Created By Sean Lai 
//Created for U8 APCSA SAS 2D Array Project
//Free for use for Diren Gomez
public class Minesweep {
    class gridNode {
        static final char flag = 'F';
        static final char hidden = '#';
        public char value;
        public int row;
        public int col;
        public int reveal;

        gridNode(char v, int r, int c) {
            value = v;
            row = r;
            col = c;
            reveal = 2;
        }

        public char getVal() {
            if (reveal == 0) {
                return value;
            }
            if (reveal == 1) {
                return flag;
            }
            return hidden;
        }

        @Override
        public int hashCode() {
            final int prime = 127;
            int result = 1;
            result = prime * result + col;
            result = prime * result + row;
            result = prime * result + value;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            gridNode other = (gridNode) obj;
            if (col != other.col)
                return false;
            if (row != other.row)
                return false;
            if (value != other.value)
                return false;
            return true;
        }

    };

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";
    public static final String WHITE_BACKGROUND = "\033[47m";
    public int row;
    public int col;
    public Character[][] ansGrid; // For Convience in checkInput
    private boolean gameInstance = false;
    private int maxMine;
    public gridNode[][] gridN; // To Access Row & Col of Grid
    private double difficulty = 0.89578;

    Minesweep(int r, int c) {
        row = r;
        col = c;
        maxMine = (int) (r * c / 2);
        gridN = new gridNode[row][col];
        ansGrid = ansGridCreate();
    }

    Minesweep(int r, int c, int mM) {
        row = r;
        col = c;
        maxMine = mM;
        gridN = new gridNode[row][col];
        ansGrid = ansGridCreate();

    }

    Character[][] ansGridCreate() {
        Character[][] ansGrid = new Character[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (Math.random() > difficulty) {
                    ansGrid[i][j] = '*'; // Notation for mine
                } else {
                    ansGrid[i][j] = '0'; // Notation for empty space
                }
            }
        }
        ArrayList<int[]> mineLocations = new ArrayList<int[]>();

        for (int i = 0; i < row; i++) { // Find all mine locations
            for (int j = 0; j < col; j++) {
                if (ansGrid[i][j] == '*') {
                    mineLocations.add(new int[] { i, j });
                }
            }
        }
        if (mineLocations.size() > maxMine) { // Check if there are too many mines
            while (mineLocations.size() > maxMine) {
                int[] loc = mineLocations.remove((int) (Math.random() * mineLocations.size())); // Remove Randomly
                ansGrid[loc[0]][loc[1]] = '0';
            }
        }
        for (int i = 0; i < row; i++) { // Do an initial BFS to find all empty spaces and assign correct number if mine
                                        // is near
            for (int j = 0; j < col; j++) {
                /// check all directions including diagonals for mines denoted *
                if (ansGrid[i][j] != '*') {
                    int temp = (int) ansGrid[i][j];
                    if (i - 1 >= 0 && j >= 0 && ansGrid[i - 1][j] == '*') {
                        temp++;
                    }
                    if (i + 1 < row && j >= 0 && ansGrid[i + 1][j] == '*') {
                        temp++;
                    }
                    if (j - 1 >= 0 && ansGrid[i][j - 1] == '*') {
                        temp++;
                    }
                    if (j + 1 < col && ansGrid[i][j + 1] == '*') {
                        temp++;
                    }
                    if (i - 1 >= 0 && j - 1 >= 0 && ansGrid[i - 1][j - 1] == '*') {
                        temp++;
                    }
                    if (i + 1 < row && j + 1 < col && ansGrid[i + 1][j + 1] == '*') {
                        temp++;
                    }
                    if (i - 1 >= 0 && j + 1 < col && ansGrid[i - 1][j + 1] == '*') {
                        temp++;
                    }
                    if (i + 1 < row && j - 1 >= 0 && ansGrid[i + 1][j - 1] == '*') {
                        temp++;
                    }
                    if (temp == (int) ansGrid[i][j]) {
                        ansGrid[i][j] = '.';
                    } else {
                        ansGrid[i][j] = (Character) (char) temp;
                    }
                }
                gridN[i][j] = new gridNode(ansGrid[i][j], i, j);

            }
        }
        return ansGrid;
    }

    void setDiff(int d) {
        switch (d) {
            case 1:
                difficulty = 0.89578;
                break;
            case 2:
                difficulty = 0.8;
                break;
            case 3:
                difficulty = 0.68;
                break;
            case 4:
                difficulty = 0.5;
                break;
            case 5:
                difficulty = 0.3;
                break;
        }
    }

    public static void startSession() { // Need Account for bad Data
        int row = 0;
        int col = 0;
        int mine = 0;
        int diff = 0;
        Scanner session = new Scanner(System.in);
        while (row <= 0 || col <= 0 || mine <= 0 || diff <= 0 || diff > 5) {
            System.out.println("Please enter the number of rows, columns, and mines in the grid");
            row = session.nextInt();
            col = session.nextInt();
            mine = session.nextInt();
            System.out.println("Please enter the difficulty of the game (1-5)");
            diff = session.nextInt();
        }

        Minesweep game = new Minesweep(row, col, mine);
        game.setDiff(diff);
        game.gameInstance = true;
        while (true) {
            System.out.println("Enter the row: ");
            int rt = session.nextInt();
            System.out.println("Enter the column: ");
            int ct = session.nextInt();
            if (rt < 0 || rt >= game.row || ct < 0 || ct >= game.col) {
                System.out.println("Invalid Input");
                continue;
            }
            boolean i = false;
            while (!i) {
                if (game.gridN[rt][ct].getVal() == '*') {
                    game.ansGrid = game.ansGridCreate();
                } else {
                    game.checkInput(rt, ct);
                    i = true;
                }

            }
            if (i) {
                break;
            }
        }
        game.printGrid(game.gridN);
        while (game.gameInstance && !game.isWin()) {

            System.out.println("Enter the row: ");
            int r = session.nextInt();
            System.out.println("Enter the column: ");
            int c = session.nextInt();
            if (r < 0 || r >= game.row || c < 0 || c >= game.col) {
                System.out.println("Invalid Input, please try again:");
                continue;
            }
            System.out.println("Flag or Reveal? (f/r): ");
            char flag = session.next().charAt(0);
            if (flag == 'f') {
                game.flag(r, c);
            } else if (flag == 'r') {
                game.checkInput(r, c);
            } else {
                System.out.println("Invalid Input");
            }
            if (game.gameInstance == true) {
                game.printGrid(game.gridN);
            }

        }
        session.close();

    }

    private boolean isWin() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (gridN[i][j].value == '*' && gridN[i][j].reveal == 2) {
                    return false;
                }
                if (gridN[i][j].reveal != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void flag(int r, int c) {
        if (gridN[r][c].reveal == 1) {
            gridN[r][c].reveal = 2;
        } else if (gridN[r][c].reveal == 2) {
            gridN[r][c].reveal = 1;
        }
    }

    void printChar(gridNode t, boolean endGame) {
        if (t.reveal == 0) {
            if (t.value == '1') {
                System.out.print(WHITE_BACKGROUND + ANSI_GREEN + t.value + ANSI_RESET);
            } else if (t.value == '2') {
                System.out.print(WHITE_BACKGROUND + ANSI_YELLOW + t.value + ANSI_RESET);
            } else if (t.value == '3') {
                System.out.print(WHITE_BACKGROUND + ANSI_PURPLE + t.value + ANSI_RESET);
            } else if (t.value == '4') {
                System.out.print(WHITE_BACKGROUND + ANSI_CYAN + t.value + ANSI_RESET);
            } else if (t.value == '5') {
                System.out.print(WHITE_BACKGROUND + ANSI_BLUE + t.value + ANSI_RESET);
            } else if (t.value == '6') {
                System.out.print(ANSI_WHITE + t.value + ANSI_RESET);
            } else if (t.value == '.') {
                System.out.print(WHITE_BACKGROUND + ANSI_BLACK + t.getVal() + ANSI_RESET);
            } else { // for 7 8 9 nodes
                System.out.print(ANSI_BLACK + CYAN_BACKGROUND_BRIGHT + t.value + ANSI_RESET);
            }
        } else if (endGame && t.value == '*') {
            System.out.print(WHITE_BACKGROUND + ANSI_RED + t.value + ANSI_RESET);
        } else if (t.reveal == 1) {
            System.out.print(WHITE_BACKGROUND + ANSI_RED + t.getVal() + ANSI_RESET);
        } else {
            System.out.print(WHITE_BACKGROUND + ANSI_BLACK + t.getVal() + ANSI_RESET);
        }
    }

    void printGrid(Minesweep.gridNode[][] gridN) {
        for (int i = 0; i < gridN.length; i++) {
            for (int j = 0; j < gridN[i].length; j++) {
                printChar(gridN[i][j], false);
            }
            System.out.println();
        }
    }

    private void loseGame() {
        gameInstance = false;
        printGridS();
        System.out.println("You Lose");
    }

    private void printGridS() {
        // Print grid slowly
        for (int i = 0; i < gridN.length; i++) {
            for (int j = 0; j < gridN[i].length; j++) {
                printChar(gridN[i][j], true);
                try {
                    Thread.sleep(10000 / (row * col));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
        }
    }

    public void checkInput(int r, int c) {
        List<gridNode> bfs = new ArrayList<>();
        List<gridNode> visited = new ArrayList<>();
        // Breath first search from input, dont queue if it is a number
        bfs.add(gridN[r][c]);
        if (gridN[r][c].value == '*') {
            gameInstance = false;
            loseGame();
        }
        while (!bfs.isEmpty()) {
            gridNode t = bfs.remove(0);
            // Check if gridNode is visited
            if (visited.indexOf(t) != -1) {
                continue;
            }
            t.reveal = 0;
            visited.add(t);
            if (t.value == '.') { // Problem
                if (t.row - 1 >= 0 && t.col >= 0 && gridN[t.row - 1][t.col].value != '*'
                        && visited.indexOf(gridN[t.row - 1][t.col]) == -1) {
                    bfs.add(gridN[t.row - 1][t.col]);
                }
                if (t.row + 1 < row && t.col >= 0 && gridN[t.row + 1][t.col].value != '*'
                        && visited.indexOf(gridN[t.row + 1][t.col]) == -1) {
                    bfs.add(gridN[t.row + 1][t.col]);

                }
                if (t.row >= 0 && t.col - 1 >= 0 && gridN[t.row][t.col - 1].value != '*'
                        && visited.indexOf(gridN[t.row][t.col - 1]) == -1) {
                    bfs.add(gridN[t.row][t.col - 1]);

                }
                if (t.row >= 0 && t.col + 1 < col && gridN[t.row][t.col + 1].value != '*'
                        && visited.indexOf(gridN[t.row][t.col + 1]) == -1) {
                    bfs.add(gridN[t.row][t.col + 1]);

                }
                if (t.row - 1 >= 0 && t.col - 1 >= 0 && gridN[t.row - 1][t.col - 1].value != '*'
                        && visited.indexOf(gridN[t.row - 1][t.col - 1]) == -1) {
                    bfs.add(gridN[t.row - 1][t.col - 1]);

                }
                if (t.row + 1 < r && t.col + 1 < col && gridN[t.row + 1][t.col + 1].value != '*'
                        && visited.indexOf(gridN[t.row + 1][t.col + 1]) == -1) {
                    bfs.add(gridN[t.row + 1][t.col + 1]);

                }
                if (t.row - 1 >= 0 && t.col + 1 < col && gridN[t.row - 1][t.col + 1].value != '*'
                        && visited.indexOf(gridN[t.row - 1][t.col + 1]) == -1) {
                    bfs.add(gridN[t.row - 1][t.col + 1]);

                }
                if (t.row + 1 < row && t.col - 1 >= 0 && gridN[t.row + 1][t.col - 1].value != '*'
                        && visited.indexOf(gridN[t.row + 1][t.col - 1]) == -1) {
                    bfs.add(gridN[t.row + 1][t.col - 1]);

                }
            }

        }
    }

    public void printAns() {
        for (int i = 0; i < ansGrid.length; i++) {
            for (int j = 0; j < ansGrid[i].length; j++) {
                System.out.print(ansGrid[i][j]);
            }
            System.out.println();
        }
    }
}