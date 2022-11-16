package leetcode.daily.y2022m08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class GroupThePeople {

    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        Map<Integer, List<Integer>> datum = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < groupSizes.length; i++) {
            List<Integer> tempList = datum.computeIfAbsent(groupSizes[i], ArrayList::new);
            if (tempList.size() == groupSizes[i]) {
                result.add(tempList);
                tempList = new ArrayList<>(groupSizes[i]);
                datum.put(groupSizes[i], tempList);
            }
            tempList.add(i);
        }
        result.addAll(datum.values());
        return result;
    }

    @Test
    public void test() {
        List<List<Integer>> lists = groupThePeople(new int[]{3, 3, 3, 3, 3, 1, 3});
        List<List<Integer>> lists1 = groupThePeople(new int[]{2, 1, 3, 3, 3, 2});
    }

    @Test
    public void errorTest(){
        List<List<Integer>> list = groupThePeople(new int[]{1});
    }
}
