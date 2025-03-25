import java.util.*;

/**
 * This class implements the Tower of Hanoi problem with 4 towers.
 * The goal is to move a stack of disks from one tower to another,
 * subject to the rule that a larger disk can never be placed on top of a smaller one.
 */
public class FourTowerHanoi {
    // Initialize the step counter
    int step = 0;

    /**
     * Move the top disk from the source tower to the target tower.
     * @param src The source tower.
     * @param tar The target tower.
     */
    void move(List<Integer> src, List<Integer> tar) {
        // Remove the top disk from the source tower
        Integer pan = src.remove(src.size() - 1);
        // Add the disk to the target tower
        tar.add(pan);
    }

    /**
     * Recursive function to solve the Tower of Hanoi problem with 3 towers.
     * @param i The number of disks to move.
     * @param src The source tower.
     * @param buf The buffer tower.
     * @param tar The target tower.
     */
    void dfs(int i, List<Integer> src, List<Integer> buf, List<Integer> tar) {
        // Base case: if there's only one disk to move, move it directly
        if (i == 1) {
            move(src, tar);
            step++;
            return;
        }
        // Recursive case: move i-1 disks to the buffer tower,
        // then move the ith disk to the target tower,
        // and finally move the i-1 disks from the buffer tower to the target tower
        dfs(i - 1, src, tar, buf);
        move(src, tar);
        step++;
        dfs(i - 1, buf, src, tar);
    }

    /**
     * Solve the Tower of Hanoi problem with 3 towers.
     * @param n The number of disks.
     * @param A The source tower.
     * @param B The buffer tower.
     * @param C The target tower.
     */
    void hanoi3(int n, List<Integer> A, List<Integer> B, List<Integer> C) {
        // Base case: if there are no disks to move, do nothing
        if (n <= 0) return;
        // Call the recursive function to solve the problem
        dfs(n, A, B, C);
    }

    /**
     * Solve the Tower of Hanoi problem with 4 towers.
     * @param n The number of disks.
     * @param A The source tower.
     * @param B The first buffer tower.
     * @param C The second buffer tower.
     * @param D The target tower.
     */
    void hanoi4(int n, List<Integer> A, List<Integer> B, List<Integer> C, List<Integer> D) {
        // Base case: if there are no disks to move, do nothing
        if (n == 0) return;
        // Special case: if there's only one disk to move, move it directly
        if (n == 1) {
            move(A, D);
            step++;
            return;
        }

        // Calculate the optimal number of disks to move to the first buffer tower
        int k = (int) Math.sqrt(2 * n);
        if (k >= n) k = n - 1;
        // Move k disks to the first buffer tower
        hanoi4(k, A, C, D, B);
        // Move the remaining n-k disks to the target tower using 3 towers
        hanoi3(n - k, A, C, D);
        // Move the k disks from the first buffer tower to the target tower
        hanoi4(k, B, A, C, D);
    }

    public static void main(String[] args) {
        // Create a scanner to read the input
        Scanner sc = new Scanner(System.in);
        // Create lists to represent the towers
        List<Integer> A = new ArrayList<>();
        List<Integer> B = new ArrayList<>();
        List<Integer> C = new ArrayList<>();
        List<Integer> D = new ArrayList<>();
        // Prompt the user to input the number of disks
        System.out.println("Please input the number of disks:");
        int n = sc.nextInt();
        // Close the scanner
        sc.close();
        // Initialize the source tower with the disks
        for (int i = n; i > 0; i--) {
            A.add(i);
        }

        // Create an instance of the FourTowerHanoi class
        FourTowerHanoi hanoi = new FourTowerHanoi();

        // Solve the Tower of Hanoi problem with 4 towers
        hanoi.hanoi4(n, A, B, C, D);

        // Print the total number of steps
        System.out.println("Total Step:" + hanoi.step);
    }
}
