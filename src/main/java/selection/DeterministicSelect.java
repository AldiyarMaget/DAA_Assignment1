package selection;

import api.Selector;

public class DeterministicSelect implements Selector {
    @Override
    public int select(int[] a, int k) {
        return selectStatic(a, k);
    }

    @Override
    public String name() {
        return "DeterministicSelect";
    }

    public static int selectStatic(int[] a, int k) {
        return select(a, 0, a.length - 1, k);
    }

    private static int select(int[] a, int left, int right, int k) {
        while (true) {
            if (left == right) return a[left];
            int pivot = medianOfMedians(a, left, right);
            int pivotIndex = partition(a, left, right, pivot);
            if (k == pivotIndex) return a[k];
            int leftSize = pivotIndex - left;
            int rightSize = right - pivotIndex;
            if (leftSize < rightSize) {
                if (k <= pivotIndex - 1) {
                    right = pivotIndex - 1;
                } else {
                    left = pivotIndex + 1;
                }
            } else {
                if (k >= pivotIndex + 1) {
                    left = pivotIndex + 1;
                } else {
                    right = pivotIndex - 1;
                }
            }
        }
    }

    private static int medianOfMedians(int[] a, int left, int right) {
        int n = right - left + 1;
        int numMedians = (n + 4) / 5;
        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            insertionSort(a, subLeft, subRight);
            int medianIndex = subLeft + (subRight - subLeft) / 2;
            swap(a, left + i, medianIndex);
        }
        if (numMedians == 1) return a[left];
        return select(a, left, left + numMedians - 1, left + numMedians / 2);
    }

    private static void insertionSort(int[] a, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= l && a[j] > key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    private static int partition(int[] a, int left, int right, int pivotValue) {
        int pivotIndex = left;
        for (int i = left; i <= right; i++) {
            if (a[i] == pivotValue) {
                pivotIndex = i;
                break;
            }
        }
        swap(a, pivotIndex, right);
        int store = left;
        for (int i = left; i < right; i++) {
            if (a[i] < pivotValue) {
                swap(a, store, i);
                store++;
            }
        }
        swap(a, store, right);
        return store;
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
