package org.ventry.commons.algorithm;

import org.apache.commons.collections.CollectionUtils;
import org.ventry.commons.utils.Console;

import java.util.*;

/**
 * file: org.ventry.commons.algorithm.ConvexHull
 * author: jojo
 * create: 2017/3/4 16:34
 * description:
 */

public class ConvexHull {
    private static final Random RANDOM = new Random();
    private static final Point INIT_POINT = new Point(Double.MIN_VALUE, Double.MIN_VALUE);

    private final Set<Point> CONVEX_POINTS = new HashSet<>();

    private static class Point {
        private final double x;
        private final double y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            temp = Double.doubleToLongBits(x);
            result = (int) (temp ^ (temp >>> 32));
            temp = Double.doubleToLongBits(y);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            return result;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ')';
        }
    }

    private static class Vector {
        private final Point start;
        private final Point end;

        Vector(Point start, Point end) {
            this.start = start;
            this.end = end;
        }

        /**
         * 计算判定点与基向量的位置
         *
         * @param judged 判定点
         * @return 叉积大于0(直观上判定点位于基向量逆时针旋转180度区域内)时，判定点归入正分类；反之，归入负分类
         */
        public double crossProduct(Point judged) {
            if (Objects.equals(start, judged) || Objects.equals(end, judged))
                return 0D;

            return (start.x * end.y + end.x * judged.y + judged.x * start.y
                    - start.x * judged.y - end.x * start.y - judged.x * end.y);
        }

        public double dotProduct(Vector one) {
            return (end.x - start.x) * (one.end.x - one.start.x)
                    + (end.y - start.y) * (one.end.y - one.start.y);
        }

        public double length() {
            return Math.sqrt(Math.pow(end.x - start.x, 2D) + Math.pow(end.y - start.y, 2D));
        }

        public double cosin(Vector one) {
            assert length() > 0;
            assert one.length() > 0;

            return dotProduct(one) / (length() * one.length());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Vector vector = (Vector) o;

            return !(start != null ? !start.equals(vector.start) : vector.start != null)
                    && !(end != null ? !end.equals(vector.end) : vector.end != null);
        }

        @Override
        public int hashCode() {
            int result = start != null ? start.hashCode() : 0;
            result = 31 * result + (end != null ? end.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return start + " -> " + end;
        }
    }

    private interface LocatingStrategy {
        Point locate(Collection<Point> set);
    }

    private static class OriginLocation implements LocatingStrategy {
        public Point locate(Collection<Point> set) {
            if (CollectionUtils.isEmpty(set))
                return INIT_POINT;

            Iterator<Point> $it = set.iterator();
            Point origin = $it.next();
            while ($it.hasNext()) {
                Point point = $it.next();
                if (origin.x > point.x
                        || (origin.x == point.x && origin.y > point.y)) {
                    origin = point;
                }
            }
            return origin;
        }
    }

    private static class FarthestFromOrigin implements LocatingStrategy {
        public Point locate(Collection<Point> set) {
            Point[] points = new Point[set.size()];
            set.toArray(points);
            Arrays.sort(points, new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    assert o1 != null;
                    assert o2 != null;

                    return o1.x - o2.x < 0 ? -1 : (o1.x == o2.x ? 0 : 1);
                }
            });

            return points[points.length - 1];
        }
    }

    private static class FarthestFromVector implements LocatingStrategy {
        private Vector vector;

        FarthestFromVector(Vector vector) {
            this.vector = vector;
        }

        public Point locate(Collection<Point> set) {
            Point result = null;
            double maxDistance = 0D;
            for (Point point : set) {
                double distance = Math.abs(vector.crossProduct(point));
                if (distance > maxDistance) {
                    result = point;
                    maxDistance = distance;
                }
            }

            return result == null ? INIT_POINT : result;
        }
    }

    private static class MinIncludedAngleFromVector implements LocatingStrategy {
        private Vector vector;

        MinIncludedAngleFromVector(Vector vector) {
            this.vector = vector;
        }

        @Override
        public Point locate(Collection<Point> set) {
            double maxCosin = -1D;
            Point marked = null;
            for (Point point : set) {
                Vector one = new Vector(vector.end, point);
                double currentCosin = vector.cosin(one);
                if (currentCosin > maxCosin) {
                    maxCosin = currentCosin;
                    marked = point;
                }
            }
            return marked;
        }
    }

    public void divideAndConquer(Set<Point> source) {
        CONVEX_POINTS.clear();
        if (CollectionUtils.isEmpty(source))
            return;

        Set<Point> points = new HashSet<>(source);
        Set<Point> positiveSet = new HashSet<>(points.size());
        Set<Point> negativeSet = new HashSet<>(points.size());
        Point origin = new OriginLocation().locate(points);
        Point maxPoint = new FarthestFromOrigin().locate(points);
        Vector divideVector = new Vector(origin, maxPoint);

        points.remove(origin);
        points.remove(maxPoint);
        for (Point point : points) {
            if (divideVector.crossProduct(point) > 0) {
                positiveSet.add(point);
            } else {
                negativeSet.add(point);
            }
        }

        CONVEX_POINTS.add(origin);
        CONVEX_POINTS.add(maxPoint);
        recursiveFindConvexPoint(new Vector(origin, maxPoint), positiveSet);
        recursiveFindConvexPoint(new Vector(maxPoint, origin), negativeSet);
    }

    private void recursiveFindConvexPoint(Vector base, Set<Point> points) {
        if (CollectionUtils.isEmpty(points))
            return;

        Set<Point> positiveSet = new HashSet<>(points.size());
        Set<Point> negativeSet = new HashSet<>(points.size());
        Point maxPoint = new FarthestFromVector(base).locate(points);
        Vector vector1 = new Vector(base.start, maxPoint);
        Vector vector2 = new Vector(maxPoint, base.end);

        for (Point point : points) {
            if (vector1.crossProduct(point) > 0) {
                positiveSet.add(point);
            } else if (vector2.crossProduct(point) > 0) {
                negativeSet.add(point);
            }
        }

        CONVEX_POINTS.add(maxPoint);
        recursiveFindConvexPoint(new Vector(base.start, maxPoint), positiveSet);
        recursiveFindConvexPoint(new Vector(maxPoint, base.end), negativeSet);
    }

    public void jarvisStepping(Set<Point> source) {
        CONVEX_POINTS.clear();
        if (CollectionUtils.isEmpty(source))
            return;

        Set<Point> points = new HashSet<>(source);
        Point origin = new OriginLocation().locate(points);
        Point initPoint = new Point(origin.x, origin.y + 1D);
        CONVEX_POINTS.add(origin);

        Vector base = new Vector(initPoint, origin);
        while (true) {
            Point marked = new MinIncludedAngleFromVector(base).locate(points);

            CONVEX_POINTS.add(marked);
            if (origin.equals(marked)) {
                return;
            } else {
                base = new Vector(base.end, marked);
                points.remove(marked);
            }
        }
    }

    public Set<Point> getConvexHull() {
        return CONVEX_POINTS;
    }

    public static void main(String[] args) {
        int iter = 10;
        for (int i = 0; i < iter; i++) {
            Console.writeLine("iter" + i + ": ");
            Set<Point> points = new HashSet<>();
            for (int j = 0; j < 100; j++) {
                Point point = new Point(RANDOM.nextDouble() * 100, RANDOM.nextDouble() * 100);
                points.add(point);
                Console.writeLine(point);
            }
            Console.writeLine("--------------------------------------------");

            ConvexHull convexHull = new ConvexHull();
            convexHull.divideAndConquer(points);
            for (Point point : convexHull.CONVEX_POINTS) {
                Console.writeLine(point);
            }
            Console.writeLine("");
        }
    }
}
