package org.ventry.commons.leetcode.design;

import java.util.HashMap;
import java.util.Map;

/**
 * file: org.ventry.commons.leetcode.design.LRUCache
 * author: ventry
 * create: 2019/1/11 18:39
 * description:
 */

public class LRUCache {
    private int capacity;
    private Map<Integer, Node> map;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node hit = find(key);
        return hit == null ? -1 : hit.value;
    }

    private Node find(int key) {
        Node node = map.get(key);
        if (node == null) return null;

        node.next.prev = node.prev;
        node.prev.next = node.next;
        addLast(node);
        return node;
    }

    public void put(int key, int value) {
        if (capacity == 0)
            return;

        Node hit = find(key);
        if (hit == null) {
            if (map.size() == capacity)
                evictFirst();

            hit = new Node(key, value);
            addLast(hit);
            map.put(key, hit);
        } else {
            hit.value = value;
        }
    }

    private void evictFirst() {
        Node old = head.next;
        head.next = old.next;
        old.next.prev = head;
        old.prev = old.next = null;
        map.remove(old.key);
    }

    private void addLast(Node node) {
        node.prev = tail.prev;
        node.next = tail;
        node.prev.next = tail.prev = node;
    }

    static class Node {
        private int key;
        private int value;
        private Node prev;
        private Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
