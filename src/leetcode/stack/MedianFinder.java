package leetcode.stack;
import java.util.PriorityQueue;

public class MedianFinder {
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((x, y) -> y - x);
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    int size = 0;

    /**
     * initialize your data structure here.
     */
    public MedianFinder() {

    }

    public void addNum(int num) {
        size++;
        maxHeap.add(num);
        minHeap.add(maxHeap.poll());
        //max heap size = min heap size + 1
        if (size % 2 != 0) {
            maxHeap.add(minHeap.poll());
        }
    }

    public double findMedian() {
        if (size == 0) {
            return 0;
        }
        if (size % 2 != 0) {
            return maxHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) / 2D;
        }
    }
}
