import java.util.Scanner;

public class lab0201 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] array = readArray(scanner, size);
        int target = scanner.nextInt();
        scanner.close();

        System.out.println(binarySearch(array, target));
    }

    private static int[] readArray(Scanner scanner, int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }

    private static int binarySearch(int[] array, int target) {
        return binarySearchRecursive(array, target, 0, array.length - 1);
    }

    private static int binarySearchRecursive(int[] array, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;

        if (array[mid] == target) {
            return mid;
        }

        if (array[mid] < target) {
            return binarySearchRecursive(array, target, mid + 1, right);
        }

        return binarySearchRecursive(array, target, left, mid - 1);
    }
}
