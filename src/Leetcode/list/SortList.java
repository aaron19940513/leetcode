package Leetcode.list;
import java.util.ArrayList;
import java.util.List;

/**
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 * <p>
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 */
public class SortList {
    public ListNode sortList(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        quickSort(list, 0, list.size() - 1);
        ListNode ans = new ListNode(0);
        ListNode ans1 = ans;
        for (ListNode t : list) {
            ans1.next = t;
            ans1 = ans1.next;
        }
        return ans.next;
    }

    private void quickSort(List<ListNode> list, int start, int end) {
        if (start < end) {
            int bigIndex = start;
            for (int index = start + 1; index <= end; index++) {
                if (list.get(index).val <= list.get(start).val) {
                    bigIndex++;
                    if (index > bigIndex) {
                        swap(list, index, bigIndex);
                    }
                }
            }
            if (bigIndex != start) {
                swap(list, start, bigIndex);
            }
            quickSort(list, start, bigIndex - 1);
            quickSort(list, bigIndex + 1, end);
        }
    }

    private void swap(List<ListNode> list, int index, int bigIndex) {
        if (index != bigIndex) {
            ListNode node = list.get(index);
            list.set(index, list.get(bigIndex));
            list.set(bigIndex, node);
        }
    }

    public class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
            next = null;
        }
    }

}
