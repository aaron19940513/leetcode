package Leetcode.daily;
import java.util.Stack;

import org.junit.Test;

/**
 * 117. 填充每个节点的下一个右侧节点指针 II 中等
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * <p>
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * <p>
 *  
 * <p>
 * 进阶：
 * <p>
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 09/28/20 10:57
 */

// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};


public class Connect {
    private Node start = null;
    private Node pre = null;

    // 把next节点利用起来，只需要拿到一层的第一个元素，就可以一直走下去
    public Node connect1(Node root) {
        if (root == null) {
            return null;
        }
        Node cur = root;
        while (cur != null) {
            update(cur.left);
            update(cur.right);
            cur = cur.next;
            if (cur == null) {
                cur = start;
                start = null;
                pre = null;
            }
        }
        return root;
    }

    private void update(Node node) {
        if (node != null) {
            if (pre != null) {
                pre.next = node;
            } else {
                start = node;
            }
            pre = node;
        }
    }

    // 如果一层一层的遍历，通过一个堆栈来存放每层的元素，性能很差。而且不是使用常数级别的额外空间
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Stack<Node> nodes = new Stack<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            Stack<Node> temp = new Stack<>();
            for (Node node : nodes) {
                connect(temp, node.left);
                connect(temp, node.right);
            }
            nodes = temp;
        }
        return root;
    }

    private void connect(Stack<Node> temp, Node node) {
        if (node != null) {
            if (!temp.isEmpty()) {
                temp.peek().next = node;
            }
            temp.push(node);
        }
    }

    @Test
    public void test() {
        Node root = new Node(1);
        Node first = new Node(2);
        Node second = new Node(3);
        Node third = new Node(4);
        Node fourth = new Node(5);
        Node fifth = new Node(6);
        Node sixth = new Node(7);
        root.left = first;
        root.right = second;
        first.left = third;
        first.right = fourth;
        second.right = sixth;
        //Node connect = connect(root);
        Node connect1 = connect1(root);
    }
}
