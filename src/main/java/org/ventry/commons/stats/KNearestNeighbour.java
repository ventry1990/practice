package org.ventry.commons.stats;

import org.apache.commons.collections.CollectionUtils;
import org.ventry.commons.utils.ArrayUtils;
import org.ventry.commons.utils.Console;

import java.util.*;

/**
 * file: org.ventry.commons.stats.KNearestNeighbour
 * author: jojo
 * create: 2017/3/6 19:46
 * description:
 */

public class KNearestNeighbour {
    private static final int DIMENSIONALITY = 5;

    private static final EuclideanDistance EUCLIDEAN_DISTANCE_STRATEGY = new EuclideanDistance();
    private static final MinkowskiDistance MINKOWSKI_DISTANCE_STRATEGY = new MinkowskiDistance();

    private static final KDimensionTreeNode NIL_NODE = new KDimensionTreeNode(-1);

    private static class FeatureVector {
        private double[] features;
        private char label;

        public FeatureVector(double... features) {
            this(features, 'A');
        }

        public FeatureVector(double[] features, char label) {
            this.features = Arrays.copyOf(features, Math.min(features.length, DIMENSIONALITY));
            this.label = label;
        }

        public double distanceWith(FeatureVector one) {
            return distanceWith(one, EUCLIDEAN_DISTANCE_STRATEGY);
        }

        public double distanceWith(FeatureVector one, DistanceComputingStrategy strategy) {
            return strategy.distance(this.features, one.features);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            FeatureVector vector = (FeatureVector) o;

            return Arrays.equals(features, vector.features);

        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(features);
        }

        @Override
        public String toString() {
            StringBuilder content = new StringBuilder("(");
            for (double v : features) {
                content.append(v).append(", ");
            }
            content.delete(content.length() - 2, content.length());
            content.append(": ").append(label).append(")");
            return content.toString();
        }
    }

    private static class Neighbour implements Comparable<Neighbour> {
        private double distanceWithTarget;
        private FeatureVector vector;

        Neighbour(double distanceWithTarget, FeatureVector vector) {
            this.distanceWithTarget = distanceWithTarget;
            this.vector = vector;
        }

        @Override
        public int compareTo(Neighbour neighbour) {
            assert neighbour != null;

            return Double.compare(distanceWithTarget, neighbour.distanceWithTarget);
        }
    }

    private static class Neighbours {
        private transient int size;
        private transient int capacity;
        private Neighbour[] neighbours;

        Neighbours(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            this.neighbours = new Neighbour[capacity + 1];
        }

        public void add(Neighbour one) {
            if (one == null)
                return;

            for (int i = 0; i < size; i++) {
                if (one.compareTo(neighbours[i]) < 0) {
                    System.arraycopy(neighbours, i, neighbours, i + 1, size - i);
                    neighbours[i] = one;
                    size = Math.min(++size, capacity);
                    neighbours[capacity] = null;
                    return;
                }
            }

            if (size < capacity)
                neighbours[size] = one;
            size = Math.min(++size, capacity);
        }

        public double getMaxDistance() {
            return size == 0 ? Double.MAX_VALUE : neighbours[size - 1].distanceWithTarget;
        }

        public List<FeatureVector> getFeatureVectors() {
            List<FeatureVector> vectors = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                vectors.add(neighbours[i].vector);
            }
            return vectors;
        }
    }

    private interface DistanceComputingStrategy {
        double distance(double[] feature1, double[] feature2);
    }

    private static class LpDistance implements DistanceComputingStrategy {
        private double p;

        LpDistance(double p) {
            this.p = p;
        }

        public double distance(double[] feature1, double[] feature2) {
            double distance = 0D;
            for (int i = 0; i < feature1.length; i++) {
                distance += Math.pow(feature2[i] - feature1[i], p);
            }

            return Math.pow(distance, 1.0 / p);
        }
    }

    private static class EuclideanDistance extends LpDistance {

        EuclideanDistance() {
            super(2);
        }

        @Override
        public double distance(double[] feature1, double[] feature2) {
            return super.distance(feature1, feature2);
        }
    }

    private static class MinkowskiDistance implements DistanceComputingStrategy {
        public double distance(double[] feature1, double[] feature2) {
            double distance = 0D;
            for (int i = 0; i < feature1.length; i++) {
                distance = Math.max(distance, Math.abs(feature2[i] - feature1[i]));
            }

            return distance;
        }
    }

    private static class KDimensionTreeNode {
        private int height;
        private int axis;
        private double cutPoint;
        private Set<FeatureVector> vectors;
        private KDimensionTreeNode left;
        private KDimensionTreeNode right;

        KDimensionTreeNode(int height) {
            this.height = height;
            this.axis = height & (DIMENSIONALITY - 1);
            this.vectors = new HashSet<>();
            left = NIL_NODE;
            right = NIL_NODE;
        }

        public static KDimensionTreeNode build(List<FeatureVector> source, int height) {
            if (CollectionUtils.isEmpty(source))
                return NIL_NODE;

            KDimensionTreeNode node = new KDimensionTreeNode(height);
            List<FeatureVector> leftSource = new ArrayList<>();
            List<FeatureVector> rightSource = new ArrayList<>();

            int kAxis = node.getAxis();
            FeatureVector medianVector = getMedianVector(source, kAxis);
            for (FeatureVector vector : source) {
                if (vector.features[kAxis] < medianVector.features[kAxis]) {
                    leftSource.add(vector);
                } else if (vector.features[kAxis] > medianVector.features[kAxis]) {
                    rightSource.add(vector);
                } else {
                    node.vectors.add(vector);
                }
            }

            node.cutPoint = medianVector.features[kAxis];

            int nextHeight = height + 1;
            node.left = KDimensionTreeNode.build(leftSource, nextHeight);
            node.right = KDimensionTreeNode.build(rightSource, nextHeight);

            return node;
        }

        private static FeatureVector getMedianVector(List<FeatureVector> source, int kAxis) {
            int seq = source.size() / 2;
            int i, j;
            for (i = 0, j = source.size() - 1; i < j; ) {
                int middle = partition(source, kAxis, i, j);
                if (middle == seq) {
                    return source.get(middle);
                } else if (middle > seq) {
                    j = middle - 1;
                } else {
                    i = middle + 1;
                }
            }

            return source.get(i);
        }

        private static int partition(List<FeatureVector> source, int kAxis, final int startIndex, final int endIndex) {
            int randomIndex = new Random().nextInt(endIndex - startIndex) + startIndex;
            Collections.swap(source, startIndex, randomIndex);

            int j = endIndex;
            for (int i = startIndex + 1; i <= j; ) {
                if (source.get(i).features[kAxis] > source.get(startIndex).features[kAxis]) {
                    Collections.swap(source, i, j);
                    j--;
                } else {
                    i++;
                }
            }

            Collections.swap(source, startIndex, j);
            return j;
        }

        public List<FeatureVector> findKNearest(final FeatureVector item, final int k) {
            Neighbours nearestNeighbours = new Neighbours(k);
            KDimensionTreeNode node = this;
            LinkedList<KDimensionTreeNode> stack = new LinkedList<>();
            Set<KDimensionTreeNode> visited = new HashSet<>();

            walkToLeaf(stack, node, item);
            while (!stack.isEmpty()) {
                node = stack.removeLast();
                visited.add(node);
                for (FeatureVector vector : node.vectors) {
                    double distance = item.distanceWith(vector);
                    nearestNeighbours.add(new Neighbour(distance, vector));
                }

                // 判断超矩形和以目标点为圆心，以目标点和第K个最近点之间距离为半径的超球体是否相交
                // 若不相交，向跟节点回溯；反之，递归遍历其另一子树
                FeatureVector perpendicular = new FeatureVector(item.features);
                perpendicular.features[node.axis] = node.cutPoint;
                if (item.distanceWith(perpendicular) < nearestNeighbours.getMaxDistance()) {
                    KDimensionTreeNode sub = visited.contains(node.left) ? node.right : node.left;
                    if (!visited.contains(sub)) {
                        walkToLeaf(stack, sub, item);
                    }
                }
            }

            return nearestNeighbours.getFeatureVectors();
        }

        private void walkToLeaf(LinkedList<KDimensionTreeNode> stack, KDimensionTreeNode node, FeatureVector key) {
            while (!NIL_NODE.equals(node)) {
                stack.addLast(node);
                if (node.cutPoint < key.features[node.axis]) {
                    node = node.right;
                } else {
                    node = node.left;
                }
            }
        }

        public int getAxis() {
            return axis;
        }

        @Override
        public String toString() {
            if (this.equals(NIL_NODE))
                return "NIL";

            StringBuilder s = new StringBuilder();
            for (int i = 0; i < height + 1; i++) {
                s.append('\t');
            }
            String retract = s.toString();
            return "Node{\n" + retract +
                    "height=" + height + ",\n" + retract +
                    "vectors=" + vectors + ",\n" + retract +
                    "left=" + left + ",\n" + retract +
                    "right=" + right + "\n" + retract +
                    "}";
        }
    }

    public static void main(String[] args) {
        int iter = 10;
        Random random = new Random();
        for (int i = 0; i < iter; i++) {
            int seed = 10000;
            List<FeatureVector> training = new ArrayList<>();
            FeatureVector origin = new FeatureVector(new double[DIMENSIONALITY]);
            for (int j = 0; j < 1000; j++) {
                FeatureVector vector = new FeatureVector(ArrayUtils.randomDoubleArrays(seed, DIMENSIONALITY));
                vector.label = generateLabel(vector.distanceWith(origin));
                training.add(vector);
            }

            List<FeatureVector> samples = new ArrayList<>();
            for (int j = 0; j < 100; j++) {
                int index = random.nextInt(training.size() - 1);
                samples.add(training.get(index));
                training.remove(index);
            }

            // 通过S折交叉验证取最优k值（省略）
            Console.writeLine("iter" + i + ": ");
            int k = 5;
            KDimensionTreeNode root = KDimensionTreeNode.build(training, 0);
            for (FeatureVector sample : samples) {
                Console.writeLine("k=" + k + ": " + sample + "->");
                List<FeatureVector> nearest = root.findKNearest(sample, k);
                Console.writeLine(nearest);
                Console.writeLine("");
            }
            Console.writeLine("-------------------------------");
        }

        Console.writeLine("end...");
    }

    private static char generateLabel(double value) {
        char[] chars = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        int k = (int) value;
        while (true) {
            if (k / 10 == 0)
                break;

            k = k / 10;
        }
        return chars[k];
    }

}
