package Leetcode.tree;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

public class Serialize {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (null == root) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        Deque<Optional<TreeNode>> deque = new ArrayDeque<>();
        deque.addLast(Optional.ofNullable(root));
        int nullCount = 0;
        while (deque.size() > 0 && nullCount != deque.size()) {
            Optional<TreeNode> optionalTreeNode = deque.pop();
            if (!optionalTreeNode.isPresent()) {
                sb.append("null").append(",");
                nullCount--;
            } else {
                TreeNode node = optionalTreeNode.get();
                sb.append(node.val).append(",");
                if (null == node.left) {
                    nullCount++;
                }
                deque.addLast(Optional.ofNullable(node.left));
                if (null == node.right) {
                    nullCount++;
                }
                deque.addLast(Optional.ofNullable(node.right));
            }
        }
        return sb.substring(0, sb.length() - 1);
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] a = data.split(",");
        Deque<TreeNode> deque = new ArrayDeque<>();
        int size = a.length;
        TreeNode node = new TreeNode(Integer.parseInt(a[0]));
        TreeNode root = node;
        int index_child = 0;
        while (index_child < size - 1) {
            index_child++;
            if (index_child < size && !a[index_child].equals("null")) {
                deque.addLast(new TreeNode(Integer.parseInt(a[index_child])));
                node.left = deque.getLast();
            }
            index_child++;
            if (index_child < size && !a[index_child].equals("null")) {
                deque.addLast(new TreeNode(Integer.parseInt(a[index_child])));
                node.right = deque.getLast();
            }
            node = deque.pop();
        }
        return root;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode first = new TreeNode(5);
        TreeNode second = new TreeNode(1);
        TreeNode third = new TreeNode(6);
        TreeNode fourth = new TreeNode(2);
        TreeNode fifth = new TreeNode(0);
        TreeNode sixth = new TreeNode(8);
        TreeNode ninth = new TreeNode(7);
        TreeNode ten = new TreeNode(4);
        root.left = first;
        root.right = second;
        first.left = third;
        first.right = fourth;
        second.left = fifth;
        second.right = sixth;
        fourth.left = ninth;
        fourth.right = ten;
        Serialize serialize = new Serialize();
        String ans = serialize.serialize(root);
        TreeNode deserialize = serialize.deserialize(ans);

        String ans1 = serialize.serialize(null);
        TreeNode deserialize1 = serialize.deserialize(ans1);

        String ans2 = serialize.serialize(ten);
        TreeNode deserialize2 = serialize.deserialize(ans2);
    }
}
