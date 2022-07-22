package leetcode.sort;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LargestNumber {
    public String largestNumber(Double1[] nums) {
        PriorityQueue<Double1> queue = new PriorityQueue<>(Comparator.comparing(Double1::getValue));
        int count_0 = 0;
        for (Double1 num : nums) {
            queue.add(num);
        }
        if (count_0 == nums.length) {
            return "0";
        }
        StringBuffer sb = new StringBuffer();
        while (queue.size() > 0) {
            sb.append(queue.poll().value).append(",");
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        LargestNumber largestNumber = new LargestNumber();
        System.out.println(largestNumber.largestNumber(new Double1[]{new Double1(3D), new Double1(30D), new Double1(34D),
                new Double1(5D), new Double1(9D)}));
        // System.out.println(largestNumber.largestNumber(new int[] {0, 0, 0}));
    }

    public static class Double1 {
        public Double1(Double value){
            this.value =value;
        }

        public Double getValue() {
            return value;
        }

        public void setValue(Double value) {
            this.value = value;
        }

        Double value;
    }
}
