import java.util.*;

class Tic_tac_toe {

    String[][] grid;
    String[][] filledGrid;
    static int player;

    Tic_tac_toe() {
        grid = make2DGrid();
        filledGrid = make2DGrid();
        player = 1; /* Player X by default */
    }
    public static void main(String[] args)
    {
        String[][] grid = make2DGrid();
        player = 1;
        Scanner input = new Scanner(System.in);

        promting(input, grid);

        input.close();
    }

    public static boolean checkWin(String[][] grid, int player)
    {
        boolean win = false;

        if (checkRows(grid, player) || checkColumns(grid, player) || checkDiag(grid, player))
            win = true;

        return win;
    }

    public static boolean checkRows(String[][] grid, int player)
    {
        boolean win = false;
        String compare;
        int count;

        if (player == 1)
            compare = "X";
        else
            compare = "O";
        
        for (int i = 0; i < grid.length; ++i) /* Row */
        {
            count = 0;
            for (int j = 0; j < grid.length; ++j) /* Cols */
            {
                if (grid[j][i].equals(compare)) { /* keep a close eye on the i & j, they get swapped around in checkColumns() below */
                    count++;
                }
                if (count == 3) {
                    win = true;
                }
            }
        }

        return win;
    }

    public static boolean checkColumns(String[][] grid, int player)
    {
        boolean win = false;
        String compare;
        int count;

        if (player == 1)
            compare = "X";
        else
            compare = "O";
        
        for (int i = 0; i < grid.length; ++i) /* Row */
        {
            count = 0;
            for (int j = 0; j < grid.length; ++j) /* Cols */
            {
                if (grid[i][j].equals(compare)) {
                    count++;
                }
                if (count == 3) {
                    win = true;
                }
            }
        }
        return win;
    }

    public static boolean checkDiag(String[][] grid, int player)
    {
        boolean win = false;
        String compare;
        int count;

        if (player == 1)
            compare = "X";
        else
            compare = "O";

        for (int i = 0; i < grid.length; ++i)
        {
            count = 0;
            if (grid[0][0].equals(compare) || grid[0][2].equals(compare)) /* Check two top corners */
                count++; 
            if (grid[1][1].equals(compare)) /* Check middle center */
                count++;
            if (grid[2][0].equals(compare) || grid[2][2].equals(compare)) /* Check two bottom corners */
                count++;
            if (count == 3)
                win = true;
        }

        return win;
    }

    public static boolean checkCellIfOccupied(String[][] grid, int player, int row, int col)
    {
        boolean occupied = false;

        System.out.println(grid[row][col]);
        if (grid[row][col].equals("X") || grid[row][col].equals("O"))
            occupied = true;

        return occupied;
    }

    public static void promting(Scanner input, String[][] grid) 
    {
        int row ;
        int col ; 
        while (true)
        {
            if (player == 1) {
                System.out.println("Player X, please enter a row (0, 1 or 2): ");
                row = input.nextInt();
                System.out.println("Player X, please enter a column (0, 1 or 2): ");
                col = input.nextInt();
                
                if (row > 2 || col > 2)
                    System.out.println("Your choice is out of range!\nYou've lost your move");
                else if (row < 0 || col < 0)
                    System.out.println("Your choice is out of range!\nYou've lost your move");
                else if (checkCellIfOccupied(grid, player, row, col) == true)
                    System.out.println("Cell is occupied\nYou've lost your move");
                else
                {
                    grid = fillGrid(row, col, grid, player);
                    if (checkWin(grid, player))
                    {
                        System.out.println("Player X has won!");
                        return ;
                    }
                }
                player = 2;
            }
            else if (player == 2)
            {
                System.out.println("Player O, please enter a row (0, 1 or 2): ");
                row = input.nextInt();
                System.out.println("Player O, please enter a column (0, 1 or 2): ");
                col = input.nextInt();

                if (row > 2 || col > 2)
                    System.out.println("Your choice is out of range!\nYou've lost your move");
                else if (row < 0 || col < 0)
                    System.out.println("Your choice is out of range!\nYou've lost your move");
                else if (checkCellIfOccupied(grid, player, row, col))
                    System.out.println("Cell is occupied\nYou've lost your move");
                else
                {
                    grid = fillGrid(row, col, grid, player);
                    if (checkWin(grid, player))
                    {
                        System.out.println("Player O has won!");
                        return ;
                    }
                }
                player = 1;
            }
            else {
                System.out.println("Not in");
                break;
            }
        }
    }

    public static String[][] make2DGrid() {
        String[][] grid = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};

        return grid;
    }

    public static String[][] fillGrid(int row, int col, String[][] grid, int player) {
        String[][] newGrid = grid;

        if (player == 1)
            newGrid[row][col] = "X";
        else
            newGrid[row][col] = "O";

        /* ---The Game grid--- */
        for (int i = 0; i < newGrid.length; ++i) /* Row */
        {
            System.out.println("-------------");
            for (int j = 0; j < newGrid[i].length; ++j)  /* Column */
            {
                if (j == 0)
                    System.out.print("| ");
                else
                    System.out.print(" | ");

                System.out.print(newGrid[i][j]);
            }
            System.out.print(" |");
            System.out.println("");
        }
        System.out.println("-------------");

        return newGrid;
    }
}