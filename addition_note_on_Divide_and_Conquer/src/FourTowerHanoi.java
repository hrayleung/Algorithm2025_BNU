import java.util.*;

public class FourTowerHanoi {
    int step = 0;

    void move(List<Integer> src, List<Integer> tar) {
        Integer pan = src.remove(src.size() - 1);
        tar.add(pan);
    }

    void dfs(int i, List<Integer> src, List<Integer> buf, List<Integer> tar) {
        if (i == 1) {
            move(src, tar);
            step++;
            return;
        }
        dfs(i - 1, src, tar, buf);
        move(src, tar);
        step++;
        dfs(i - 1, buf, src, tar);

    }

    void hanoi3(int n, List<Integer> A, List<Integer> B, List<Integer> C) {
        if (n <= 0) return;
        dfs(n, A, B, C);
    }

    void hanoi4(int n, List<Integer> A, List<Integer> B, List<Integer> C, List<Integer> D) {
        if (n == 0) return;
        if (n == 1) {
            move(A, D);
            step++;
            return;
        }

        int k = (int) Math.sqrt(2 * n);
        if (k >= n) k = n - 1;
        hanoi4(k, A, C, D, B);
        hanoi3(n - k, A, C, D);
        hanoi4(k, B, A, C, D);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> A = new ArrayList<>();
        List<Integer> B = new ArrayList<>();
        List<Integer> C = new ArrayList<>();
        List<Integer> D = new ArrayList<>();
        System.out.println("Please input the number of pans:");
        int n = sc.nextInt();
        sc.close();
        for (int i = n; i > 0; i--) {
            A.add(i);
        }

        FourTowerHanoi hanoi = new FourTowerHanoi();

        hanoi.hanoi4(n, A, B, C, D);

        System.out.println("Total Step:" + hanoi.step);
    }
}
