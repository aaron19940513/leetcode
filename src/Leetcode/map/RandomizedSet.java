package Leetcode.map;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomizedSet {
    Map<Integer, Integer> value2IndexMap = new HashMap<>();
    Map<Integer, Integer> index2ValueMap = new HashMap<>();
    int size;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        size = 0;
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (value2IndexMap.get(val) == null) {
            value2IndexMap.put(val, size);
            index2ValueMap.put(size, val);
            size++;
            return true;
        }
        return false;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (value2IndexMap.get(val) != null) {
            int index = value2IndexMap.get(val);
            value2IndexMap.put(index2ValueMap.get(size - 1), index);
            value2IndexMap.remove(val);
            index2ValueMap.put(index, index2ValueMap.get(size - 1));
            index2ValueMap.remove(size - 1);
            size--;
            return true;
        }
        return false;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        if (size == 0) {
            return 0;
        } else if (size == 1) {
            return index2ValueMap.get(0);
        }

        Random random = new Random();
        int randomValue = random.nextInt(size);
        return index2ValueMap.get(randomValue);
    }

    public static void main(String[] args) {
      RandomizedSet randomizedSet = new RandomizedSet();
      System.out.println(randomizedSet.insert(1));
      System.out.println(randomizedSet.insert(2));
      System.out.println(randomizedSet.insert(3));
      int i =100;
        while(i>0){
            i--;
            System.out.println(randomizedSet.getRandom());
        }

    }
}
