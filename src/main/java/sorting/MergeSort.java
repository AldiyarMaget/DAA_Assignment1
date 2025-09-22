package sorting;

import api.Sorter;

public class MergeSort implements Sorter {
    @Override
    public void sort(int[] arr) {
        mergeSort(arr);
    }

    @Override
    public String name() {
        return "MergeSort";
    }

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int[] aux = arr.clone();
        mergeSortRecursive(aux, arr, 0, arr.length);
    }

    private static final int THRESHOLD = 5;

    private static void mergeSortRecursive(int[] src, int[] dest, int lo, int hi) {
        int len = hi - lo;
        if (len <= THRESHOLD) {
            insertionSort(dest, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        mergeSortRecursive(dest, src, lo, mid);
        mergeSortRecursive(dest, src, mid, hi);

        int i = lo, j = mid, k = lo;
        while (i < mid && j < hi) {
            if (src[i] <= src[j]) dest[k++] = src[i++];
            else dest[k++] = src[j++];
        }
        while (i < mid) dest[k++] = src[i++];
        while (j < hi) dest[k++] = src[j++];
    }

    private static void insertionSort(int[] a, int lo, int hi) {
        for (int i = lo + 1; i < hi; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= lo && a[j] > key) {
                a[j+1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }
}
