package sorting;

import api.Sorter;
import java.util.Random;

public class QuickSort implements Sorter {
    private static final Random RAND = new Random();

    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        quickSort(arr, 0, arr.length - 1);
    }

    @Override
    public String name() {
        return "QuickSort";
    }

    public static void quickSort(int[] arr, int from, int to) {
        while (from < to) {
            int pivotIdx = from + RAND.nextInt(to - from + 1);
            swap(arr, from, pivotIdx);

            int divideIndex = partition(arr, from, to);

            int leftSize = divideIndex - from;
            int rightSize = to - divideIndex + 1;

            if (leftSize < rightSize) {
                quickSort(arr, from, divideIndex - 1);
                from = divideIndex;
            } else {
                quickSort(arr, divideIndex, to);
                to = divideIndex - 1;
            }
        }
    }

    private static int partition(int[] arr, int from, int to) {
        int leftIndex = from;
        int rightIndex = to;
        int pivot = arr[from];

        while (leftIndex <= rightIndex) {
            while (leftIndex <= rightIndex && arr[leftIndex] < pivot) {
                leftIndex++;
            }
            while (leftIndex <= rightIndex && arr[rightIndex] > pivot) {
                rightIndex--;
            }
            if (leftIndex <= rightIndex) {
                swap(arr, leftIndex, rightIndex);
                leftIndex++;
                rightIndex--;
            }
        }
        return leftIndex;
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
