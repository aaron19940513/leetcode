package leetcode.sort;
import java.util.PriorityQueue;

public class LargestNumber {
    public String largestNumber(int[] nums) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> String.valueOf(y).concat(String.valueOf(x))
                                                                           .compareTo(String.valueOf(x).concat(String.valueOf(y))));
        int count_0 = 0;
        for (int num : nums) {
            if (num == 0) {
                count_0++;
            }
            queue.add(num);
        }
        if (count_0 == nums.length) {
            return "0";
        }
        StringBuffer sb = new StringBuffer();
        while (queue.size() > 0) {
            sb.append(queue.poll());
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        LargestNumber largestNumber = new LargestNumber();
        System.out.println(largestNumber.largestNumber(new int[] {3, 30, 34, 5, 9}));
        System.out.println(largestNumber.largestNumber(new int[] {0, 0, 0}));
    }
}
