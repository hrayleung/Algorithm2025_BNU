import java.util.*;

public class MergeSort {

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        printArray(arr);
        mergeSort(arr, 0, n - 1);
        System.out.println();
        printArray(arr);
    }

    // Recursively sorts the subarray using merge sort.
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2 ;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    // Merges two sorted subarrays.
    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] l = new int[n1];
        int[] r = new int[n2];

        for (int i = 0; i < n1; i++) {
            l[i] = arr[left + i];
        }

        for (int j = 0; j < n2; j++) {
            r[j] = arr[mid + 1 + j];
        }
        int k = left;
        int i = 0, j = 0;
        while (i < n1 && j < n2) {
            if (l[i] <= r[j]) {
                arr[k] = l[i];
                i++;
            } else {
                arr[k] = r[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = l[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = r[j];
            j++;
            k++;
        }
    }

    // Utility method to print an array.
    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length - 2; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.print(arr[arr.length-1]);
        System.out.print("]");
    }
}
