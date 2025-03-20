import java.util.Arrays;
import java.util.Random;

public class lab0104 {
    public static void main(String[] args) {
        int[] sizes = {10000, 20000, 40000, 80000, 160000};
        int times = 10;

        for (int size : sizes) {
            System.out.println("date size: " + size);
            long totalTimeHeap = 0;
            long totalTimeInsertion = 0;

            for (int i = 0; i < times; i++) {
                int[] array = generateRandomArray(size);
                int[] arrforheap = Arrays.copyOf(array, array.length);
                int[] arrforinsertion = Arrays.copyOf(arrforheap, arrforheap.length);

                long startHeap = System.nanoTime();
                heapSort(arrforheap);
                long endHeap = System.nanoTime();
                long timeHeap = endHeap - startHeap;
                totalTimeHeap += timeHeap;

                long startInsertion = System.nanoTime();
                insertionSort(arrforinsertion);
                long endInsertion = System.nanoTime();
                long timeInsertion = endInsertion - startInsertion;
                totalTimeInsertion += timeInsertion;

            }
            System.out.println("average heap: " + totalTimeHeap / times);
            System.out.println("average insertion: " + totalTimeInsertion / times);
        }
    }

    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size);
        }
        return array;
    }

    public static void heapSort(int[] array) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            swap(array, 0, i);
            heapify(array, i, 0);
        }

    }

    public static void heapify(int[] array, int heapSize, int index) {
        int largest = index;
        int left = index * 2 + 1;
        int right = index * 2 + 2;

        if (left < heapSize && array[left] > array[largest]) {
            largest = left;
        }

        if (largest != index) {
            swap(array, index, largest);
            heapify(array, heapSize, largest);
        }
    }

    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
