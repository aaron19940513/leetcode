package Leetcode.tree;
import java.util.PriorityQueue;

public class KthSmallest {
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((x, y) -> y - x);

    public int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return 0;
        } else {
            if (priorityQueue.size() < k) {
                priorityQueue.add(root.val);
            } else if (priorityQueue.peek() > root.val) {
                priorityQueue.poll();
                priorityQueue.add(root.val);
            }
        }
        kthSmallest(root.left, k);
        kthSmallest(root.right, k);
        return priorityQueue.peek();
    }
}
