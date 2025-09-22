package app;

import api.Sorter;
import api.Selector;
import api.ClosestPairSolver;
import sorting.MergeSort;
import sorting.QuickSort;
import selection.DeterministicSelect;
import geometry.ClosestPair;
import geometry.Point;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // тут мерге сортировка
        int[] a = {9, 2, 6, 4, 3, 7, 8, 5, 1};
        int[] aCopy = Arrays.copyOf(a, a.length);
        Sorter merge = new MergeSort();
        merge.sort(aCopy);
        System.out.println("MergeSort -> " + Arrays.toString(aCopy));

        // тут быстрая
        int[] b = {5, 1, 4, 2, 8, 3};
        int[] bCopy = Arrays.copyOf(b, b.length);
        Sorter quick = new QuickSort();
        quick.sort(bCopy);
        System.out.println("QuickSort -> " + Arrays.toString(bCopy));

        // тут детерка
        int[] c = {7, 2, 1, 6, 8, 5, 3, 4};
        Selector sel = new DeterministicSelect();
        int k = 3;
        int kth = sel.select(Arrays.copyOf(c, c.length), k);
        System.out.println("DeterministicSelect k=" + k + " -> " + kth);

        // тут клоусер
        Point[] pts = {
                new Point(2.0, 3.0),
                new Point(12.0, 30.0),
                new Point(40.0, 50.0),
                new Point(5.0, 1.0),
                new Point(12.0, 10.0),
                new Point(3.0, 4.0)
        };
        ClosestPairSolver cps = new ClosestPair();
        double d = cps.closestDistance(pts);
        System.out.println("ClosestPair distance -> " + d);
    }
}
