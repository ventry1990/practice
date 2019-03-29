package org.ventry.commons.leetcode.hash;

import java.util.*;

/**
 * file: org.ventry.commons.leetcode.hash.TopKFrequentElements
 * author: ventry
 * create: 2019/3/29 18:58
 * description:
 */

public class TopKFrequentElements {

    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0 || k == 0) return res;

        Map<Integer, Entry> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) map.get(num).incr();
            else map.put(num, new Entry(num));
        }
        List<Entry> counts = new ArrayList<>(map.values());
        Collections.sort(counts);
        for (int i = 0; i < k; i++) {
            res.add(counts.get(i).key);
        }
        return res;
    }

    private static class Entry implements Comparable<Entry> {
        int key;
        int count;

        private Entry(int k) {
            key = k;
            count = 1;
        }

        private void incr() {
            count++;
        }

        @Override
        public int compareTo(Entry o) {
            return o.count - count;
        }
    }

    private int k;

    public List<Integer> topKFrequent2(int[] nums, int k) {
        Node<Integer> root = new Node<>();

        for (int n : nums) {
            add(root, n, -1);
        }

        Node<Integer> root1 = new Node<>();
        preOrder(root, root1);
        this.k = k;
        List<Integer> list = new ArrayList<>();
        rInOrder(root1, list);
        return list;

    }

    private static class Node<T> {
        Node<T> left;
        Node<T> right;
        T data;
        int count;
        int buffer;

        Node() {
            this.data = null;
        }

        Node(T data, int buffer) {
            this.data = data;
            this.count = 1;
            this.buffer = buffer;
        }
    }

    private Node<Integer> findNode(Node<Integer> node, Node<Integer> parent, int data, int buffer) {
        if (node == null) {
            return parent;
        }

        if (node.data == data) {
            if (buffer == -1) {
                node.count++;
                return null;
            } else if (buffer == node.buffer) {
                node.count++;
                return null;
            }
        }

        if (node.data < data) {
            return findNode(node.right, node, data, buffer);
        } else {
            return findNode(node.left, node, data, buffer);
        }
    }

    private void add(Node<Integer> node, int data, int buffer) {
        if (node.data == null) {
            node.data = data;
            node.buffer = buffer;
            node.count++;
            return;
        }

        Node<Integer> parent = findNode(node, node, data, buffer);
        if (parent != null) {
            if (parent.data < data) {
                parent.right = new Node<>(data, buffer);
            } else {
                parent.left = new Node<>(data, buffer);
            }
        }
    }

    private void preOrder(Node<Integer> node, Node<Integer> root1) {
        if (node == null) {
            return;
        }
        add(root1, node.count, node.data);
        preOrder(node.left, root1);
        preOrder(node.right, root1);
    }

    private void rInOrder(Node<Integer> node, List<Integer> list) {
        if (node == null) {
            return;
        }
        rInOrder(node.right, list);
        if (k > 0) {
            k--;
            list.add(node.buffer);
        }

        rInOrder(node.left, list);
    }
}
