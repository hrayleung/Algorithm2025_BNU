import java.util.Scanner;

public class lab0102 {
    private static final int[] dx = {-1, 1, -2, 2, -2, 2, -1, 1};
    private static final int[] dy = {2, 2, 1, 1, -1, -1, -2, -2};

    private static final int board_size = 8;
    private static final int[][] board = new int[board_size][board_size];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();
        sc.close();

        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                board[i][j] = 0;
            }
        }

        board[row][col] = 1;

        if (solve(row, col, 2)) {
        printBoard();
        } else {
            System.out.println("No solution exists");
    }
        }

    public static boolean solve(int row, int col, int step) {
        if (step > board_size * board_size) {
                return true;
            }

        int[] nextMoves = new int[8];
        int[] accessibility = new int[8];
        int numMoves = 0;
        for (int i = 0; i < 8; i++) {
            int newRow = row + dx[i];
            int newCol = col + dy[i];

            if (isValidPosition(newRow, newCol) && board[newRow][newCol] == 0) {
                nextMoves[numMoves] = i;
                accessibility[numMoves] = getAccessibility(newRow, newCol);
                numMoves++;
            }
        }

        for (int i = 0; i < numMoves - 1; i++) {
            for (int j = 0; j < numMoves - i - 1; j++) {
                if (accessibility[j] > accessibility[j + 1]) {
                    int tempMove = nextMoves[j];
                    nextMoves[j] = nextMoves[j + 1];
                    nextMoves[j + 1] = tempMove;

                    int tempAccessibility = accessibility[j];
                    accessibility[j] = accessibility[j + 1];
                    accessibility[j + 1] = tempAccessibility;
    }
    }
        }

        for (int i = 0; i < numMoves; i++) {
            int moveIndex = nextMoves[i];
            int newRow = row + dx[moveIndex];
            int newCol = col + dy[moveIndex];

            board[newRow][newCol] = step;

            if (solve(newRow, newCol, step + 1)) {
                return true;
                }

            board[newRow][newCol] = 0;
            }
        return false;
        }

    private static int getAccessibility(int row, int col) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            int newRow = row + dx[i];
            int newCol = col + dy[i];
            if (isValidPosition(newRow, newCol) && board[newRow][newCol] == 0) {
                count++;
    }
}
        return count;
    }

    private static boolean isValidPosition(int row, int col) {
        return row >= 0 && row < board_size && col >= 0 && col < board_size;
    }

    private static void printBoard() {
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                System.out.print(board[i][j]);
                if (j < board_size - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
