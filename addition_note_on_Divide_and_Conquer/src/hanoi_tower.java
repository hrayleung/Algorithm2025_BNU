import java.util.*;

public class hanoi_tower {
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
        step++;
        dfs(i - 1, buf, src, tar);

    }

    void solve(List<Integer> A, List<Integer> B, List<Integer> C) {
        int n = A.size();
        dfs(n, A, B, C);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> A = new ArrayList<>();
        List<Integer> B = new ArrayList<>();
        List<Integer> C = new ArrayList<>();
        System.out.println("Please input the number of pans:");
        int n = sc.nextInt();
        sc.close();
        for (int i = n; i > 0; i--) {
            A.add(i);
        }

        hanoi_tower hanoiTower = new hanoi_tower();
        hanoiTower.solve(A, B, C);
        System.out.println("Total Step:" + hanoiTower.step);
    }
}

