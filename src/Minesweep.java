
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//This now should be able to commit
public class Minesweep {
    class gridNode {
        char value;
        int row;
        int col;

        gridNode(char v, int r, int c) {
            value = v;
            row = r;
            col = c;
        }

    };

    public int row;
    public int col;
    public Character[][] ansGrid;
    public Character[][] grid;
    private boolean gameInstance = false;
    private int maxMine;

    Minesweep(int r, int c) {
        row = r;
        col = c;
        ansGrid = ansGridCreate();
        grid = createGrid();
    }

    Minesweep(int r, int c, int mM) {
        row = r;
        col = c;
        maxMine = mM;
        ansGrid = ansGridCreate();
        grid = createGrid();
    }

    Character[][] createGrid() {
        Character[][] grid = new Character[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grid[i][j] = '#';
            }
        }

        return grid;
    }

    Character[][] ansGridCreate() {
        Character[][] ansGrid = new Character[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (Math.random() > 0.89578) {
                    ansGrid[i][j] = '*'; // Notation for mine
                } else {
                    ansGrid[i][j] = '0'; // Notation for empty space
                }
            }
        }
        ArrayList<int[]> mineLocations = new ArrayList<int[]>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (ansGrid[i][j] == '*') {
                    mineLocations.add(new int[] { i, j });
                }
            }
        }
        if (mineLocations.size() > maxMine) {
            while (mineLocations.size() > maxMine) {
                int[] loc = mineLocations.remove((int) (Math.random() * mineLocations.size()));
                ansGrid[loc[0]][loc[1]] = '0';
            }
        }
        for (int i = 0; i < row; i++) {
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

            }
        }
        return ansGrid;
    }

    public void checkInput(int r, int c) {
        checkInput(grid, r, c);
    }

    public static void startSession() {
        Scanner session = new Scanner(System.in);
        System.out.println("Enter the number of rows: ");
        int row = session.nextInt();
        System.out.println("Enter the number of columns: ");
        int col = session.nextInt();
        System.out.println("Enter the number of mines: ");
        int mine = session.nextInt();
        Minesweep game = new Minesweep(row, col, mine);
        game.gameInstance = true;
        game.printGrid(game.grid);

        while (game.gameInstance) {

            System.out.println("Enter the row: ");
            int r = session.nextInt();
            System.out.println("Enter the column: ");
            int c = session.nextInt();
            game.checkInput(r, c);
            game.printGrid(game.grid);
            session.close();
        }
        session.close();

    }

    private void printGrid(Character[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

    public void checkInput(Character[][] input, int r, int c) {
        List<gridNode> bfs = new ArrayList<>();
        // Breath first search from input, dont queue if it is a number
        bfs.add(new gridNode(input[r][c], r, c));
        if (ansGrid[r][c] == '*') {
            gameInstance = false;
        }
        input[r][c] = ansGrid[r][c];
        while (!bfs.isEmpty()) {
            gridNode temp = bfs.remove(0);
            if (ansGrid[temp.row][temp.col] == '.') {
                if (temp.row - 1 >= 0 && temp.col >= 0 && ansGrid[temp.row - 1][temp.col] != '*') {
                    bfs.add(new gridNode(ansGrid[temp.row - 1][temp.col], temp.row - 1, temp.col));

                }
                if (temp.row + 1 < row && temp.col >= 0 && ansGrid[temp.row + 1][temp.col] != '*') {
                    bfs.add(new gridNode(ansGrid[temp.row + 1][temp.col], temp.row + 1, temp.col));

                }
                if (temp.row >= 0 && temp.col - 1 >= 0 && ansGrid[temp.row][temp.col - 1] != '*') {
                    bfs.add(new gridNode(ansGrid[temp.row][temp.col - 1], temp.row, temp.col - 1));

                }
                if (temp.row >= 0 && temp.col + 1 < col && ansGrid[temp.row][temp.col + 1] != '*') {
                    bfs.add(new gridNode(ansGrid[temp.row][temp.col + 1], temp.row, temp.col + 1));

                }
                if (temp.row - 1 >= 0 && temp.col - 1 >= 0 && ansGrid[temp.row - 1][temp.col - 1] != '*') {
                    bfs.add(new gridNode(ansGrid[temp.row - 1][temp.col - 1], temp.row - 1, temp.col - 1));

                }
                if (temp.row + 1 < r && temp.col + 1 < col && ansGrid[temp.row + 1][temp.col + 1] != '*') {
                    bfs.add(new gridNode(ansGrid[temp.row + 1][temp.col + 1], temp.row + 1, temp.col + 1));

                }
                if (temp.row - 1 >= 0 && temp.col + 1 < col && ansGrid[temp.row - 1][temp.col + 1] != '*') {
                    bfs.add(new gridNode(ansGrid[temp.row - 1][temp.col + 1], temp.row - 1, temp.col + 1));

                }
                if (temp.row + 1 < row && temp.col - 1 >= 0 && ansGrid[temp.row + 1][temp.col - 1] != '*') {
                    bfs.add(new gridNode(ansGrid[temp.row + 1][temp.col - 1], temp.row - 1, temp.col - 1));

                }
            }
            input[temp.row][temp.col] = ansGrid[temp.row][temp.col];

        }
    }

    // Create a compare method between ansGrid and Grid
    // If the two grids are equal, return true
    // If the two grids are not equal, return false
    public boolean compare(char[][] ansGrid, char[][] grid) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (ansGrid[i][j] != grid[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}