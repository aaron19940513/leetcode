package leetcode;
/**
 * 题目描述
 * 题目：数组中有一个数字出现的次数超过了数组长度的一半，找出这个数字。
 *
 * @author sam
 * @date 10/18/19 10:14
 */
public class OverHalf {
    public static void main(String[] args) {
        int[] a = new int[] {0, 1, 1, 2, 5, 3, 1, 1, 1, 4, 5, 6, 1, 7, 1, 9, 1, 1, 1};
        System.out.println(findOneNumber(a));
        // find();
    }

    /**
     * 考虑到这个问题本身的特殊性，我们可以在遍历数组的时候保存两个值：一个candidate，用来保存数组中遍历到的某个数字；一个nTimes，表示当前数字的出现次数，其中，nTimes初始化为1。当我们遍历到数组中下一个数字的时候：
     * 如果下一个数字与之前candidate保存的数字相同，则nTimes加1；
     * 如果下一个数字与之前candidate保存的数字不同，则nTimes减1；
     * 每当出现次数nTimes变为0后，用candidate保存下一个数字，并把nTimes重新设为1。 直到遍历完数组中的所有数字为止。
     */
    private static int findOneNumber(int[] a) {
        int candidate = -1;
        int nTimes = 0;
        for (int i : a) {
            if (nTimes == 0) {
                nTimes++;
                candidate = i;
            } else if (i == candidate) {
                nTimes++;

            } else {
                nTimes--;
            }
        }
        return candidate;
    }


    private static void find() {
        int[] a = new int[] {1, 2, 3, 4, 5};
        int[] b = new int[] {2, 3, 4};
        //int index_a = 0;
        int index_b = 0;
        for (int index_a = 0; index_a < a.length && index_b < b.length; index_a++) {
            if (a[index_a] == b[index_b]) {
                System.out.println(a[index_a]);
                index_b++;
            }
        }
        String c = "asdasd";
    }

}
