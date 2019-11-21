package org.ventry.commons.leetcode.list;

import org.ventry.commons.leetcode.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * file: org.ventry.commons.leetcode.list.CopyListWithRandomPointer
 * author: ventry
 * create: 2019-11-21 18:46
 * description:
 */
public class CopyListWithRandomPointer {

    public Node copyRandomList(Node head) {
        if (head == null)
            return null;

        Node anchor = new Node();
        Node cur = new Node();
        anchor.next = cur;
        Map<Node, Node> map = new HashMap<>();
        map.put(head, cur);
        while (head != null) {
            cur = map.get(head);
            cur.val = head.val;
            if (head.next != null) {
                if (!map.containsKey(head.next))
                    map.put(head.next, new Node());

                cur.next = map.get(head.next);
            }
            if (head.random != null) {
                if (!map.containsKey(head.random))
                    map.put(head.random, new Node());

                cur.random = map.get(head.random);
            }
            head = head.next;
        }
        return anchor.next;
    }
}
