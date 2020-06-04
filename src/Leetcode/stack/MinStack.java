package Leetcode.stack;


import java.util.ArrayList;
import java.util.List;

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
public class MinStack {

    // Node collection;
    //
    // List<Node> stack;
    //
    // /**
    //  * initialize your data structure here.
    //  */
    // public MinStack() {
    //     collection = new Node(Integer.MIN_VALUE);
    //     stack = new ArrayList<>();
    // }
    //
    // public void push(int x) {
    //     Node newNode = new Node(x);
    //     stack.add(newNode);
    //     Node node = collection.next;
    //     Node pre = collection;
    //     while (node != null && x >= node.getValue()) {
    //         pre = node;
    //         node = node.next;
    //     }
    //     if (node == null) {
    //         pre.setNext(newNode);
    //     } else {
    //         pre.setNext(newNode);
    //         newNode.setNext(node);
    //     }
    // }
    //
    // public void pop() {
    //     int value = stack.get(stack.size() - 1).getValue();
    //     stack.remove(stack.size() - 1);
    //     Node node = collection.next;
    //     Node pre = collection;
    //     while (node != null && value != node.getValue()) {
    //         pre = node;
    //         node = node.next;
    //     }
    //     pre.next = node.next;
    // }
    //
    // public int top() {
    //     return stack.get(stack.size() - 1).getValue();
    // }
    //
    // public int getMin() {
    //     return collection.getNext().getValue();
    // }
    //
    // private class Node {
    //
    //     Node next;
    //     int value;
    //
    //     public Node(int value) {
    //         this.value = value;
    //     }
    //
    //     public Node getNext() {
    //         return next;
    //     }
    //
    //     public void setNext(Node next) {
    //         this.next = next;
    //     }
    //
    //     public int getValue() {
    //         return value;
    //     }
    //
    //     public void setValue(int value) {
    //         this.value = value;
    //     }
    // }
    //
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(1);
        minStack.push(2);
        minStack.push(3);
        minStack.push(4);
        minStack.push(0);
        System.out.println(minStack.getMin());
        minStack.push(-1);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
    }

    private ListNode head = null;
    private int min = Integer.MAX_VALUE;

    /**
     * initialize your data structure here.
     */
    public MinStack() {

    }

    private static class ListNode {
        private int val;
        private ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public void push(int x) {
        if (x <= min) {
            head = new ListNode(min, head);
            min = x;
        }
        head = new ListNode(x, head);
    }

    public void pop() {
        if (top() == getMin()) {
            min = head.next.val;
            head = head.next.next;
        } else {
            head = head.next;
        }
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return min;
    }
}
