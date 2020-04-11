package org.ventry.commons.leetcode.design;

import org.ventry.commons.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * file: org.ventry.commons.leetcode.design.Codec
 * author: ventry
 * create: 2020/4/10 00:41
 * description:
 */
public class Codec {

    public String serialize(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        serializeHelper(root, stringBuilder);
        return stringBuilder.toString();
    }

    private void serializeHelper(TreeNode root, StringBuilder stringBuilder) {
        if (root == null) {
            stringBuilder.append('$');
            stringBuilder.append(',');
        } else {
            stringBuilder.append(root.val);
            stringBuilder.append(',');
            serializeHelper(root.left, stringBuilder);
            serializeHelper(root.right, stringBuilder);
        }
    }

    public TreeNode deserialize(String data) {
        if (data.length() <= 0) {
            return null;
        }
        int[] pos = {0};
        return deserializeHelper(data, pos);
    }

    private TreeNode deserializeHelper(String data, int[] pos) {
        if (data.charAt(pos[0]) == '$') {
            pos[0] += 2;
            return null;
        } else {
            int flag = 0;
            int num = 0;
            if (data.charAt(pos[0]) == '-') {
                flag = 1;
                pos[0]++;
            }
            while (data.charAt(pos[0]) >= '0' && data.charAt(pos[0]) <= '9') {
                num *= 10;
                num += (data.charAt(pos[0])) - '0';
                pos[0]++;
            }
            num = flag == 1 ? -num : num;
            TreeNode root = new TreeNode(num);
            pos[0]++;
            root.left = deserializeHelper(data, pos);
            root.right = deserializeHelper(data, pos);
            return root;
        }
    }


    public String _serialize(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        List<TreeNode> level = new ArrayList<>();
        level.add(root);
        _serialize(level, list);

        if (list.isEmpty()) {
            return "[]";
        }

        if (list.size() == 1) {
            return "[" + list.get(0) + "]";
        }

        int end = list.size() - 1;
        while (list.get(end) == null) end--;
        StringBuilder builder = new StringBuilder("[");
        builder.append(list.get(0));
        for (int i = 1; i <= end; i++) {
            builder.append(",").append(list.get(i));
        }
        builder.append("]");
        return builder.toString();
    }

    private void _serialize(List<TreeNode> level, List<Integer> list) {
        if (level.isEmpty()) return;

        List<TreeNode> nextLevel = new ArrayList<>();
        for (TreeNode node : level) {
            if (node == null) {
                list.add(null);
            } else {
                list.add(node.val);
                nextLevel.add(node.left);
                nextLevel.add(node.right);
            }
        }
        _serialize(nextLevel, list);
    }

    public TreeNode _deserialize(String data) {
        if (data == null) return null;
        LinkedList<TreeNode> intermediate = _transform(data);
        if (intermediate.isEmpty()) return null;

        TreeNode root = intermediate.pollFirst();
        List<TreeNode> level = new ArrayList<>();
        level.add(root);
        while (!intermediate.isEmpty()) {
            List<TreeNode> newLevel = new ArrayList<>();
            for (TreeNode node : level) {
                if (node == null) continue;

                node.left = intermediate.pollFirst();
                node.right = intermediate.pollFirst();
                newLevel.add(node.left);
                newLevel.add(node.right);
            }
            level = newLevel;
        }
        return root;
    }

    private LinkedList<TreeNode> _transform(String data) {
        LinkedList<TreeNode> transformed = new LinkedList<>();
        int start = 0;
        int end = data.length();
        while (data.charAt(start++) != '[') ;
        while (data.charAt(--end) != ']') ;
        if (start >= end) return transformed;

        for (int i = start, j = start; i <= end; i++) {
            if (data.charAt(i) == ',' || i == end) {
                String val = data.substring(j, i);
                if ("null".equals(val)) {
                    transformed.addLast(null);
                } else {
                    transformed.addLast(new TreeNode(Integer.parseInt(val)));
                }
                j = i + 1;
            }
        }
        return transformed;
    }
}
