package leetcode.daily.y2022m08;

import java.util.Arrays;

public class MyCircularQueue {

    private int head = 0;

    private int tail = 0;

    private int count = 0;

    private int size = 0;

    private int[] arr;

    public MyCircularQueue(int k) {
        size = k;
        arr = new int[k];
        Arrays.fill(arr, -1);
    }

    public boolean enQueue(int value) {
        if (count == size) {
            return false;
        }
        if (count == 0) {
            arr[tail] = value;
        } else {
            tail = (tail + 1) % size;
            arr[tail] = value;
        }
        count++;
        return true;

    }

    public boolean deQueue() {
        if (count == 0) {
            return false;
        }


        if (count == 1) {
            arr[head] = -1;
        }else{
            arr[head] = -1;
            head = (head + 1) % size;
        }

        count--;

        return true;
    }

    public int Front() {
        return arr[head];
    }

    public int Rear() {
        return arr[tail];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == size;
    }

    public static void main(String[] args) {
        MyCircularQueue obj = new MyCircularQueue(3);
        boolean param_1 = obj.enQueue(2);
        boolean param_11 = obj.enQueue(3);
        boolean param_12 = obj.enQueue(4);
        boolean param_13 = obj.enQueue(5);
        boolean param_2 = obj.deQueue();
        boolean param_21 = obj.deQueue();
        boolean param_22 = obj.deQueue();
        boolean param_23 = obj.deQueue();
        int param_3 = obj.Front();
        int param_4 = obj.Rear();
        boolean param_5 = obj.isEmpty();
        boolean param_6 = obj.isFull();
    }
}
