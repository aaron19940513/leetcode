package Leetcode.tree;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

/**
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 示例: 
 *
 * 你可以将以下二叉树：
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * 序列化为 "[1,2,3,null,null,4,5]"
 * 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
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
