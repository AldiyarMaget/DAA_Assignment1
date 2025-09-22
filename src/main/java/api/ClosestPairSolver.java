package api;
import geometry.Point;
public interface ClosestPairSolver {
    double closestDistance(Point[] points);
    String name();
}