package org.ventry.commons.leetcode.bfs;

import java.util.*;

/**
 * file: org.ventry.commons.leetcode.bfs.BusRoutes
 * author: ventry
 * create: 2020/10/18 23:27
 * description:
 */
public class BusRoutes {

    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S == T) return 0;

        List<List<Integer>> graph = buildGraph(routes);
        List<Integer> target = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < routes.length; i++) {
            if (Arrays.binarySearch(routes[i], S) >= 0) { // lane i contains S
                queue.add(i);
            }
            if (Arrays.binarySearch(routes[i], T) >= 0) {
                target.add(i);
            }
        }
        Set<Integer> checked = new HashSet<>();
        Set<Integer> next = new HashSet<>();
        int count = 1;
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            if (target.contains(node)) {
                return count;
            }
            List<Integer> nextNodes = graph.get(node);
            for (Integer nextNode : nextNodes) {
                if (!checked.contains(nextNode)) {
                    checked.add(nextNode);
                    next.add(nextNode);
                }
            }
            if (queue.isEmpty()) {
                count++;
                queue.addAll(next);
                next.clear();
            }
        }
        return -1;
    }

    private List<List<Integer>> buildGraph(int[][] routes) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int[] route : routes) {
            Arrays.sort(route);
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < routes.length; i++) {
            for (int j = i + 1; j < routes.length; j++) {
                if (hasIntersection(routes[i], routes[j])) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        return graph;
    }

    private boolean hasIntersection(int[] first, int[] sec) {
        for (int i = 0, j = 0; i < first.length && j < sec.length; ) {
            if (first[i] == sec[j]) {
                return true;
            } else if (first[i] < sec[j]) {
                i++;
            } else {
                j++;
            }
        }
        return false;
    }
}
