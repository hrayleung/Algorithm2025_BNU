import java.util.Scanner;

public class lab0203 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] l = new int[n];

        for (int i = 0; i < n; i++) {
            l[i] = sc.nextInt();
        }

        System.out.println(quickSelect(l, 0, l.length - 1, k - 1));
    }

    private static int quickSelect(int[] arr, int low, int high, int k) {
        while (low < high) {
        int pivotIndex = partition(arr, low, high);
        if (k == pivotIndex) {
            return arr[k];
        } else if (k < pivotIndex) {
                high = pivotIndex - 1;
        } else {
                low = pivotIndex + 1;
        }
    }
        return arr[low];
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, high);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
