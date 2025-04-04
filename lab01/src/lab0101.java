import java.util.Scanner;

public class lab0101 {
    private static int n;
    private static final int maxn = 10;
    private static final int[][] dist = new int[maxn][maxn];
    private static final int[] cities = new int[maxn];
    private static final boolean[] used = new boolean[maxn];
    private static int min_dist = Integer.MAX_VALUE;

    private static void dfs(int depth, int last, int current_dist) {
        if (current_dist >= min_dist) {
            return;
        }
        if (depth == n) {
            if (current_dist < min_dist) {
                min_dist = current_dist;
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!used[i] && dist[last][i] != -1) {
                used[i] = true;
                cities[depth] = i;
                dfs(depth + 1, i, current_dist + dist[last][i]);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = sc.nextInt();
            }
        }
        used[0] = true;
        dfs(1, 0, 0);
        System.out.println(min_dist);
    }
}
