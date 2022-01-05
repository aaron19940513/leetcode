package leetcode.list;
import java.util.HashMap;
import java.util.Map;

public class CopyRandomList {

    Map<Node, Node> nodeMap = new HashMap<>();

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node newNode = null;
        if (!nodeMap.containsKey(head)) {
            newNode = new Node(head.val, null, null);
            nodeMap.put(head, newNode);
        } else {
            return nodeMap.get(head);
        }
        newNode.next = copyRandomList(head.next);
        newNode.random = copyRandomList(head.random);
        return newNode;
    }


    public class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {
        }

        public Node(int _val, Node _next, Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }


}


