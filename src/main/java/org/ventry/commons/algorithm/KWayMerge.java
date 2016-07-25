package org.ventry.commons.algorithm;

import org.ventry.commons.utils.ArrayUtils;
import org.ventry.commons.utils.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ventry on 16/7/26.
 */
public class KWayMerge {

    private class TrackableData {
        int data;
        int comeFrom;

        TrackableData(int data, int comeFrom) {
            this.data = data;
            this.comeFrom = comeFrom;
        }

        public int getData() {
            return data;
        }

        public int getComeFrom() {
            return comeFrom;
        }
    }

    private class MinHeap {
        private TrackableData[] trackableData;
        private int size;

        MinHeap(TrackableData[] source) {
            if (source == null)
                source = new TrackableData[0];

            build(source);
        }

        private void build(TrackableData[] source) {
            trackableData = source;
            size = source.length;
            for (int i = size / 2; i > -1; i--) {
                minHeapify(i);
            }
        }

        private void minHeapify(int i) {
            while (i <= size / 2) {
                int left = 2 * (i + 1) - 1;
                int right = 2 * (i + 1);
                int min = i;
                if (left < size && trackableData[left].data < trackableData[min].data)
                    min = left;
                if (right < size && trackableData[right].data < trackableData[min].data)
                    min = right;

                if (min != i) {
                    ArrayUtils.swap(trackableData, i, min);
                    i = min;
                } else {
                    break;
                }
            }
        }

        private TrackableData minimum() {
            if (size < 1)
                throw new RuntimeException("trackableData is empty");

            return trackableData[0];
        }

        private TrackableData extractorMinimum() {
            TrackableData min = minimum();
            size--;
            ArrayUtils.swap(trackableData, 0, size);
            minHeapify(0);
            return min;
        }

        private void insert(TrackableData key) {
            if (size == trackableData.length) {
                TrackableData[] newArray = new TrackableData[size * 2 + 1];
                System.arraycopy(trackableData, 0, newArray, 0, size);
                trackableData = newArray;
            }

            trackableData[size] = new TrackableData(Integer.MAX_VALUE, key.comeFrom);
            size++;
            decreaseKey(size - 1, key);
        }

        private void decreaseKey(int index, TrackableData key) {
            if (index >= size || index < 0)
                throw new ArrayIndexOutOfBoundsException("index is out of the trackableData's bound");
            if (key.data > trackableData[index].data)
                throw new RuntimeException("this key is smaller than current key.");

            int parentIndex = getParentIndex(index);
            while (index > 0 && trackableData[parentIndex].data > key.data) {
                trackableData[index] = trackableData[parentIndex];
                index = parentIndex;
                parentIndex = getParentIndex(index);
            }
            trackableData[index] = key;
        }

        private int getParentIndex(int i) {
            return i % 2 == 0 ? (i - 1) >> 1 : i >> 1;
        }
    }

    public static int[] merge1(int[] one, int[] two, int[] three) {
        //Assert.isNotEmpty(one, two, three);

        int[] target = new int[one.length + two.length + three.length];
        MaxHeap maxHeap = new MaxHeap(null);

        for (int i : one) {
            maxHeap.insert(i);
        }

        for (int i : two) {
            maxHeap.insert(i);
        }

        for (int i : three) {
            maxHeap.insert(i);
        }

        for (int i = 0; i < target.length; i++) {
            target[i] = maxHeap.extractorMaximum();
        }
        return target;
    }

    public List<Integer> merge(List<int[]> arrays) {
        //Assert.isNotEmpty(arrays)
        MinHeap minHeap = new MinHeap(null);
        int[] cursors = initCursors(arrays.size());

        for (int i = 0; i < arrays.size(); i++) {
            if (ArrayUtils.isEmpty(arrays.get(i))) continue;

            minHeap.insert(new TrackableData(arrays.get(i)[0], i));
        }

        List<Integer> target = new ArrayList<>();
        while (true) {
            TrackableData item = minHeap.extractorMinimum();
            if (item.data == Integer.MAX_VALUE)
                break;

            cursors[item.comeFrom]++;
            if (cursors[item.comeFrom] == arrays.get(item.comeFrom).length) {
                minHeap.insert(new TrackableData(Integer.MAX_VALUE, item.comeFrom));
            } else {
                minHeap.insert(new TrackableData(arrays.get(item.comeFrom)[cursors[item.comeFrom]], item.comeFrom));
            }

            target.add(item.data);
        }
        return target;
    }

    private int[] initCursors(int length) {
        int[] cursors = new int[length];
        for (int i = 0; i < cursors.length; i++) {
            cursors[i] = 0;
        }
        return cursors;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 5, 7, 11, 13, 17, 19};
        int[] b = new int[]{2, 4, 6, 8, 10, 12, 14, 16};
        int[] c = new int[]{3, 9, 15, 18};
        List<int[]> ls = Arrays.asList(a, b, c);
        int iterator = 10000;
        long start = System.nanoTime();
        for (int i = 0; i < iterator; i++) {
            merge1(a, b, c);
        }
        Console.writeLine("3-way merge` cost:" + (System.nanoTime() - start) / 1000000 + "ms");

        start = System.nanoTime();
        KWayMerge kWayMerge = new KWayMerge();
        for (int i = 0; i < iterator; i++) {
            kWayMerge.merge(ls);
        }
        Console.writeLine("3-way merge cost:" + (System.nanoTime() - start) / 1000000 + "ms");

        Console.write(20, merge1(a, b, c));
        Console.write(20, new KWayMerge().merge(ls));
    }
}
