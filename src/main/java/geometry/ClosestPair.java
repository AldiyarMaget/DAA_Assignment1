package geometry;

import api.ClosestPairSolver;
import java.util.Arrays;
public class ClosestPair implements ClosestPairSolver {
    @Override
    public String name() { return "ClosestPair"; }

    @Override
    public double closestDistance(Point[] points) {
        if (points == null || points.length < 2) return Double.POSITIVE_INFINITY;
        Point[] px = Arrays.copyOf(points, points.length);
        Point[] py = Arrays.copyOf(points, points.length);
        Arrays.sort(px, (a,b) -> Double.compare(a.x, b.x));
        Arrays.sort(py, (a,b) -> Double.compare(a.y, b.y));
        return closestRec(px, py, 0, px.length - 1);
    }

    private double closestRec(Point[] px, Point[] py, int left, int right) {
        int n = right - left + 1;
        if (n <= 3) {
            double d = Double.POSITIVE_INFINITY;
            for (int i = left; i <= right; i++)
                for (int j = i+1; j <= right; j++)
                    d = Math.min(d, dist(px[i], px[j]));
            return d;
        }
        int mid = (left + right) >>> 1;
        Point midPoint = px[mid];

        Point[] pyl = new Point[mid - left + 1];
        Point[] pyr = new Point[right - mid];
        int li=0, ri=0;
        for (Point p: py) {
            if (p.x <= midPoint.x) pyl[li++] = p; else pyr[ri++] = p;
        }
        if (li < pyl.length) pyl = Arrays.copyOf(pyl, li);
        if (ri < pyr.length) pyr = Arrays.copyOf(pyr, ri);

        double dl = closestRec(px, pyl, left, mid);
        double dr = closestRec(px, pyr, mid+1, right);
        double d = Math.min(dl, dr);

        Point[] strip = new Point[py.length];
        int si = 0;
        for (Point p : py) if (Math.abs(p.x - midPoint.x) < d) strip[si++] = p;
        for (int i = 0; i < si; i++)
            for (int j = i+1; j < si && j <= i+7; j++)
                d = Math.min(d, dist(strip[i], strip[j]));
        return d;
    }

    private static double dist(Point a, Point b) {
        double dx = a.x - b.x, dy = a.y - b.y;
        return Math.hypot(dx, dy);
    }
}
