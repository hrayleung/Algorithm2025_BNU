import java.util.Scanner;

public class lab0203 {
    public static void main(String[] args) {
        // Create a Scanner object for input
        Scanner sc = new Scanner(System.in);

        // Read the number of elements and the target k
        int n = sc.nextInt();
        int k = sc.nextInt();

        // Create an array to hold the input elements
        int[] l = new int[n];

        // Read the elements into the array
        for (int i = 0; i < n; i++) {
            l[i] = sc.nextInt();
        }

        // Find and print the k-th smallest element
        System.out.println(quickSelect(l, 0, l.length - 1, k - 1));
    }

    // QuickSelect algorithm to find the k-th smallest element
    private static int quickSelect(int[] arr, int low, int high, int k) {
        // Loop until the correct partition index is found
        while (low < high) {
            // Partition the array and get the pivot index
        int pivotIndex = partition(arr, low, high);

            // If pivot index is the target k, return the element at that index
        if (k == pivotIndex) {
            return arr[k];
        }
            // If k is less than pivot index, search in the left part of the array
            else if (k < pivotIndex) {
                high = pivotIndex - 1;
    }
            // If k is greater than pivot index, search in the right part of the array
            else {
                low = pivotIndex + 1;
    }
        }
        // Return the element at the found partition index
        return arr[low];
    }

    // Partition the array and return the pivot index
    private static int partition(int[] arr, int low, int high) {
        // Choose the last element as the pivot
        int pivot = arr[high];
        // Initialize the index for the smaller element
        int i = low;

        // Loop through the array and rearrange elements based on the pivot
        for (int j = low; j < high; j++) {
            // If the current element is less than or equal to the pivot, swap it with the element at index i
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        // Place the pivot at the correct position
        swap(arr, i, high);
        // Return the pivot index
        return i;
    }

    // Swap elements at index i and j in the array
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
