import java.util.Scanner;

public class lab0102 {

    // Array to store the x-direction moves for a knight (total 8 possible moves)
    private static final int[] dx = {-1, 1, -2, 2, -2, 2, -1, 1};

    // Array to store the y-direction moves for a knight (total 8 possible moves)
    private static final int[] dy = {2, 2, 1, 1, -1, -1, -2, -2};

    // Define board size as 8x8 for the chessboard
    private static final int board_size = 8;

    // 2D array representing the chessboard where each cell will hold the step number
    private static final int[][] board = new int[board_size][board_size];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Read the starting position (row and column) from user input
        int row = sc.nextInt();
        int col = sc.nextInt();
        sc.close();

        // Initialize the board to 0 (all positions unvisited)
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                board[i][j] = 0;
            }
        }

        // Mark the starting position as the first step
        board[row][col] = 1;

        // Attempt to find a Knight's Tour starting from the given position
        if (solve(row, col, 2)) {
            // If a solution is found, print the board
        printBoard();
        } else {
            // If no solution exists, output a message
            System.out.println("No solution exists");
    }
        }

    /**
     * Recursive method to solve the Knight's Tour problem using backtracking.
     *
     * @param row Current row position of the knight
     * @param col Current column position of the knight
     * @param step Current step number (starting from 1)
     * @return true if a solution is found, false otherwise
     */
    public static boolean solve(int row, int col, int step) {
        // Base case: if all squares are visited (step exceeds board size squared)
        if (step > board_size * board_size) {
                return true;
            }

        // Arrays to store valid next moves and their accessibility scores
        int[] nextMoves = new int[8];
        int[] accessibility = new int[8];
        int numMoves = 0;

        // Check all possible moves from current position
        for (int i = 0; i < 8; i++) {
            int newRow = row + dx[i];
            int newCol = col + dy[i];

            // If the new position is valid and unvisited
            if (isValidPosition(newRow, newCol) && board[newRow][newCol] == 0) {
                nextMoves[numMoves] = i;  // Store the move index
                accessibility[numMoves] = getAccessibility(newRow, newCol);  // Accessibility heuristic
                numMoves++;
            }
        }

        // Sort the valid moves based on accessibility (Warnsdorff's rule)
        // Using bubble sort for simplicity here (could be more optimized)
        for (int i = 0; i < numMoves - 1; i++) {
            for (int j = 0; j < numMoves - i - 1; j++) {
                // Sort in ascending order (moves with lower accessibility first)
                if (accessibility[j] > accessibility[j + 1]) {
                    // Swap moves
                    int tempMove = nextMoves[j];
                    nextMoves[j] = nextMoves[j + 1];
                    nextMoves[j + 1] = tempMove;

                    // Swap accessibility scores
                    int tempAccessibility = accessibility[j];
                    accessibility[j] = accessibility[j + 1];
                    accessibility[j + 1] = tempAccessibility;
    }
    }
        }

        // Try each valid move in sorted order
        for (int i = 0; i < numMoves; i++) {
            int moveIndex = nextMoves[i];
            int newRow = row + dx[moveIndex];
            int newCol = col + dy[moveIndex];

            board[newRow][newCol] = step;  // Mark the step on the board

            // Recursive call to proceed to next step
            if (solve(newRow, newCol, step + 1)) {
                return true;  // Propagate success back through recursion
                }

            board[newRow][newCol] = 0;  // Backtrack: undo the move
            }

        // If no move leads to a solution, backtrack
        return false;
        }

    /**
     * Calculates the accessibility score (number of available moves from a position).
     *
     * @param row Row to check accessibility for
     * @param col Column to check accessibility for
     * @return Number of available moves from (row, col)
     */
    private static int getAccessibility(int row, int col) {
        int count = 0;

        // Check all 8 possible knight moves
        for (int i = 0; i < 8; i++) {
            int newRow = row + dx[i];
            int newCol = col + dy[i];

            // Increment count if the new position is valid and unvisited
            if (isValidPosition(newRow, newCol) && board[newRow][newCol] == 0) {
                count++;
    }
}

        return count;
    }

    /**
     * Checks if a given position is valid (within board boundaries).
     *
     * @param row Row to check
     * @param col Column to check
     * @return true if the position is valid, false otherwise
     */
    private static boolean isValidPosition(int row, int col) {
        return row >= 0 && row < board_size && col >= 0 && col < board_size;
    }

    /**
     * Prints the filled-in board with move numbers.
     */
    private static void printBoard() {
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                System.out.print(board[i][j]);
                if (j < board_size - 1) {
                    System.out.print(" ");  // Print space between numbers except last
                }
            }
            System.out.println();  // New line after each row
        }
    }
}
