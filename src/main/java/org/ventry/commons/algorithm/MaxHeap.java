package org.ventry.commons.algorithm;

import org.ventry.commons.utils.ArrayUtils;
import org.ventry.commons.utils.Console;

/**
 * Created by ventry on 16/7/24.
 */
public class MaxHeap {
    private int[] data;
    private int size;

    public MaxHeap(int[] source) {
        if(source == null)
            source = new int[0];

        build(source);
    }

    private void build(int[] source) {
        data = source;
        size = source.length;
        for (int i = size / 2; i > -1; i--) {
            maxHeapify(i);
        }
    }

    private void maxHeapify(int i) {
        maxHeapify(i, size);
    }

    private void maxHeapify(int i, int heapSize) {
        while (i <= heapSize / 2) {
            int left = ((i + 1) << 1) - 1;
            int right = (i + 1) << 1;
            int max = i;
            if (left < heapSize && data[left] > data[max])
                max = left;
            if (right < heapSize && data[right] > data[max])
                max = right;

            if (max != i) {
                ArrayUtils.swap(data, i, max);
                i = max;
            } else {
                break;
            }
        }
    }

    public int maximum() {
        if (size < 1)
            throw new RuntimeException("data is empty");

        return data[0];
    }

    public int extractorMaximum() {
        int max = maximum();
        size--;
        ArrayUtils.swap(data, 0, size);
        maxHeapify(0);
        return max;
    }

    public void insert(int key) {
        if (size == data.length) {
            int[] newArray = new int[size * 2 + 1];
            System.arraycopy(data, 0, newArray, 0, size);
            data = newArray;
        }

        data[size] = Integer.MIN_VALUE;
        size++;
        increaseKey(size - 1, key);
    }

    public void increaseKey(int index, int key) {
        if (index >= size || index < 0)
            throw new ArrayIndexOutOfBoundsException("index is out of the data's bound");
        if (key < data[index])
            throw new RuntimeException("this key is smaller than current key.");

        int parentIndex = getParentIndex(index);
        while (index > 0 && data[parentIndex] < key) {
            data[index] = data[parentIndex];
            index = parentIndex;
            parentIndex = getParentIndex(index);
        }
        data[index] = key;
    }

    private int getParentIndex(int i) {
        return i % 2 == 0 ? (i - 1) >> 1 : i >> 1;
    }

    public void delete(int i) {
        data[i] = data[size - 1];
        size--;
        maxHeapify(i);
    }

    public static void main(String[] args) {
        int[] is = ArrayUtils.randomIntArrays(20);
        Console.write(20, is);

        MaxHeap maxHeap = new MaxHeap(is);
        maxHeap.print();

        Console.writeLine("-------------------------------");
        maxHeap.insert(43);
        maxHeap.insert(2);
        maxHeap.print();

        Console.writeLine("-------------------------------");
        Console.writeLine(maxHeap.maximum());
        Console.writeLine(maxHeap.extractorMaximum());
        Console.writeLine(maxHeap.maximum());
        maxHeap.print();

        Console.writeLine("-------------------------------");
        maxHeap.increaseKey(5, 20);
        maxHeap.print();

        Console.writeLine("-------------------------------");
        maxHeap.delete(5);
        maxHeap.print();

        Console.writeLine("-------------------------------");
        maxHeap = new MaxHeap(null);
        maxHeap.insert(9);
        maxHeap.insert(4);
        maxHeap.insert(20);
        maxHeap.insert(1);
        maxHeap.insert(33);
        maxHeap.insert(25);
        maxHeap.insert(11);
        maxHeap.print();
    }

    private void print() {
        System.out.println("size: " + size);
        System.out.print("data: ");
        Console.write(size, data);

        int height = (int) (Math.log(size) / Math.log(2)) + 1;
        int full = 2 << (height - 1);
        int prefix = (full - 1) / 2;
        StringBuilder sb = new StringBuilder();
        int j = 1;
        for (int i = 0; i < size; i++) {
            sb.append(createEmptyString(prefix)).append(data[i]).append(createEmptyString(prefix)).append(" ");
            if ((int) (Math.log(i + 2) / Math.log(2)) == j) {
                j++;
                prefix = (prefix - 1) / 2;
                System.out.println(sb.toString());
                sb = new StringBuilder();
            }
        }
        System.out.println(sb.toString());
        System.out.println();
    }

    private String createEmptyString(int length) {
        return length == 0 ? "" : String.format("%" + length + "s", " ");
    }

}
