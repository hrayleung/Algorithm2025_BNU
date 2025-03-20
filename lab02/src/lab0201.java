import java.util.Scanner;


public class lab0201 {
    private static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[] list = new int[n];
        for (int i = 0; i < n; i++) {
            list[i] = sc.nextInt();
        }
        int x = sc.nextInt();
        System.out.println(locate(list, x, 0, n - 1));
    }

    private static int locate(int[] list, int x, int start, int end) {
        if (start > end) {
            return -1;
        }
        int mid = start + (end - start) / 2;
        if (list[mid] == x) {
            return mid;
        } else if (list[mid] < x) {
            return locate(list, x, mid + 1, end);
        } else {
            return locate(list, x, start, mid - 1);
        }

    }
}