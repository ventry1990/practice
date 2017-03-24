package org.ventry.commons.stats;

import org.apache.commons.collections.bag.HashBag;
import org.ventry.commons.utils.Console;

import java.util.*;

/**
 * file: org.ventry.commons.stats.NaiveBayesian
 * author: ventry
 * create: 17/3/24 17/3/24
 * description:
 */
public class NaiveBayesian {

    private static class Record {
        private List<Object> features;
        private String label;

        public Record(String label, Object... features) {
            this.label = label;
            this.features = Arrays.asList(features);
        }

        @Override
        public String toString() {
            return features + " -> " + label;
        }
    }

    private static class Classification {
        private static final String SPLIT = "::";
        private static final double lambda = 1;

        private int total = 0;
        private HashBag labelBag = new HashBag();
        private HashBag featureBag = new HashBag();
        private HashMap<Integer, Set<Object>> featureCounter = new HashMap<>();

        public void train(List<Record> trainingData) {
            for (Record one : trainingData) {
                total++;

                labelBag.add(one.label);

                for (int i = 0; i < one.features.size(); i++) {
                    featureBag.add(one.label + SPLIT + i + SPLIT + one.features.get(i));

                    Integer index = i;
                    if (featureCounter.containsKey(index)) {
                        featureCounter.get(index).add(one.features.get(i));
                    } else {
                        Set<Object> set = new HashSet<>();
                        set.add(one.features.get(i));
                        featureCounter.put(index, set);
                    }
                }
            }
        }

        public void mle(Record... samples) {
            for (Record sample : samples) {
                double maxScore = 0D;
                String label = "?";

                for (Object key : labelBag.uniqueSet()) {
                    int labelCount = labelBag.getCount(key);
                    double curScore = labelCount * 1D / total;

                    for (int i = 0; i < sample.features.size(); i++) {
                        int featureCount = featureBag.getCount(key.toString() + SPLIT + i + SPLIT + sample.features.get(i));
                        double featureProbabilityOnLabel = featureCount * 1D / labelCount;

                        curScore *= featureProbabilityOnLabel;
                    }

                    Console.writeLine(String.format(">> %.4f", curScore));
                    if (curScore > maxScore) {
                        maxScore = curScore;
                        label = key.toString();
                    }
                }

                sample.label = label;
            }
        }

        public void be(Record... samples) {
            for (Record sample : samples) {
                double maxScore = 0D;
                String label = "?";

                Set labelSet = labelBag.uniqueSet();
                for (Object key : labelSet) {
                    int labelCount = labelBag.getCount(key);
                    double curScore = (labelCount * 1D + lambda) / (total + labelSet.size() * lambda);

                    for (int i = 0; i < sample.features.size(); i++) {
                        int featureCount = featureBag.getCount(key.toString() + SPLIT + i + SPLIT + sample.features.get(i));
                        double featureProbabilityOnLabel = (featureCount * 1D + lambda)
                                / (labelCount + featureCounter.get(i).size() * lambda);

                        curScore *= featureProbabilityOnLabel;
                    }

                    Console.writeLine(String.format(">> %.4f", curScore));
                    if (curScore > maxScore) {
                        maxScore = curScore;
                        label = key.toString();
                    }
                }

                sample.label = label;
            }
        }
    }

    public static void main(String[] args) {
        List<Record> trainingData = Arrays.asList(
                new Record("-1", 1, "S"),
                new Record("-1", 1, "M"),
                new Record("1", 1, "M"),
                new Record("1", 1, "S"),
                new Record("-1", 1, "S"),
                new Record("-1", 2, "S"),
                new Record("-1", 2, "M"),
                new Record("1", 2, "M"),
                new Record("1", 2, "L"),
                new Record("1", 2, "L"),
                new Record("1", 3, "L"),
                new Record("1", 3, "M"),
                new Record("1", 3, "M"),
                new Record("1", 3, "L"),
                new Record("-1", 3, "L")
        );
        Classification classification = new Classification();
        classification.train(trainingData);

        Record sample = new Record("", 2, "S");
        Console.writeLine("mle: ");
        classification.mle(sample);
        Console.writeLine(sample.label);

        Console.writeLine("be: ");
        classification.be(sample);
        Console.writeLine(sample.label);
    }
}
