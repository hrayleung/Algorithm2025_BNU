import java.io.*;
import java.util.*;

public class luogu_p1908 {
    public static void main(String[] args) {
        try {
            // Create a BufferedReader to read input from standard input
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            // Read the number of elements
            int n = Integer.parseInt(br.readLine());

            // Initialize an array to store the input elements
            int[] arr = new int[n];

            // Use StringTokenizer to parse the input line
            StringTokenizer st = new StringTokenizer(br.readLine());

            // Populate the array with input elements
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            // Temporary array for merging
            int[] temp = new int[n];

            // Count the number of inversions using merge sort
            long invCount = mergeSortAndCount(arr, temp, 0, n - 1);

            // Print the number of inversions
            System.out.println(invCount);
        } catch (IOException e) {
            // Handle IOException
            System.out.println("An error occurred while reading input.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // Handle NumberFormatException
            System.out.println("Invalid input format.");
            e.printStackTrace();
        }
    }

    // Merge sort and count inversions
    private static long mergeSortAndCount(int[] arr, int[] temp, int left, int right) {
        // Base case: if the subarray has one or zero elements
        if (left >= right) {
            return 0;
        }

        // Find the midpoint
        int mid = (left + right) / 2;

        // Recursively count inversions in the left and right subarrays
        long invCount = mergeSortAndCount(arr, temp, left, mid);
        invCount += mergeSortAndCount(arr, temp, mid + 1, right);

        // Count inversions while merging
        invCount += mergeAndCount(arr, temp, left, mid, right);

        return invCount;
    }

    // Merge two sorted subarrays and count inversions
    private static long mergeAndCount(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;
        long invCount = 0;

        // Merge the subarrays and count inversions
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                invCount += (mid - i + 1);
            }
        }

        // Copy any remaining elements from the left subarray
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // Copy any remaining elements from the right subarray
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // Copy the merged subarray back to the original array
        for (int idx = left; idx <= right; idx++) {
            arr[idx] = temp[idx];
        }

        return invCount;
    }
}