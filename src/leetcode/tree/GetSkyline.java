package leetcode.tree;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import javafx.util.Pair;

public class GetSkyline {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<Pair<Integer, Integer>> queue = new ArrayList<>(buildings.length * 2);
        for (int[] building : buildings) {
            queue.add(new Pair<>(building[0], -building[2]));
            queue.add(new Pair<>(building[1], building[2]));
        }
        queue.sort(new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                if (o1.getKey().equals(o2.getKey())) {
                    return o1.getValue() - o2.getValue();
                } else {
                    return o1.getKey() - o2.getKey();
                }
            }
        });
        PriorityQueue<Integer> high = new PriorityQueue<>((x, y) -> y - x);
        high.add(0);
        List<List<Integer>> ans = new ArrayList<>();
        Pair<Integer, Integer> before = new Pair<>(0, 0);
        for (Pair<Integer, Integer> pair : queue) {
            if (pair.getValue() < 0) {
                high.add(-pair.getValue());
            } else {
                high.remove(pair.getValue());
            }

            if (!high.peek().equals(before.getValue())) {
                before = new Pair<>(pair.getKey(), high.peek());
                ans.add(Arrays.asList(pair.getKey(), high.peek()));
            }
        }
        return ans;
    }

    public List<List<Integer>> getSkyline1(int[][] buildings) {
        int[][] arrange = new int[2 * buildings.length][2];
        List<List<Integer>> ans = new ArrayList<>();
        if (buildings.length <= 0) {
            return ans;
        }
        for (int i = 0; i < buildings.length; i++) {

            arrange[2 * i][0] = buildings[i][0];
            arrange[2 * i][1] = -buildings[i][2];
            arrange[2 * i + 1][0] = buildings[i][1];
            arrange[2 * i + 1][1] = buildings[i][2];

        }
        //sort
        Arrays.sort(arrange, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[1] - o2[1];//这里区间可以拼接，所以start在前 end 在后
                }
            }
        });
        // for height calculation
        PriorityQueue<Integer> que = new PriorityQueue(11, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        int pre = 0;
        int cp = 0;
        //sweep
        for (int i = 0; i < arrange.length; i++) {

            if (arrange[i][1] < 0) {
                //start
                que.offer(-arrange[i][1]);
            } else {
                que.remove(arrange[i][1]);//end
            }

            int now = 0;
            //now height
            if (que.size() > 0) {
                now = que.peek();
            }
            // they are different store change point
            if (now != pre) {
                List<Integer> subans = new ArrayList<>();
                cp = arrange[i][0];
                pre = now;
                subans.add(cp);
                subans.add(pre);
                ans.add(subans);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        GetSkyline getSkyline = new GetSkyline();
        // List<List<Integer>> skyline = getSkyline.getSkyline(new int[][] {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}});
        // for (List<Integer> integers : skyline) {
        //     System.out.println(integers.get(0) + "  " + integers.get(1));
        // }
        // System.out.println(" ------------------------- ");
        // List<List<Integer>> skyline1 = getSkyline.getSkyline(new int[][] {{2, 9, 10}, {2, 11, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}});
        // for (List<Integer> integers : skyline1) {
        //     System.out.println(integers.get(0) + "  " + integers.get(1));
        // }
        // System.out.println(" ------------------------- ");
        // List<List<Integer>> skyline2 = getSkyline.getSkyline(new int[][] {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {18, 20, 8}});
        // for (List<Integer> integers : skyline2) {
        //     System.out.println(integers.get(0) + "  " + integers.get(1));
        // }
        // System.out.println(" ------------------------- ");
        // List<List<Integer>> skyline3 = getSkyline.getSkyline(new int[][] {{0, 1, 3}});
        // for (List<Integer> integers : skyline3) {
        //     System.out.println(integers.get(0) + "  " + integers.get(1));
        // }
        // System.out.println(" ------------------------- ");
        // List<List<Integer>> skyline4 = getSkyline.getSkyline(new int[][] {{0, 2, 3}, {2, 5, 3}});
        // for (List<Integer> integers : skyline4) {
        //     System.out.println(integers.get(0) + "  " + integers.get(1));
        // }
        System.out.println(" ------------------------- ");
        List<List<Integer>> skyline5 = getSkyline.getSkyline(new int[][] {{0, 4, 3}, {3, 5, 6}, {2, 6, 3}});
        for (List<Integer> integers : skyline5) {
            System.out.println(integers.get(0) + "  " + integers.get(1));
        }

        System.out.println(" ------------------------- ");
        List<List<Integer>> skyline6 = getSkyline.getSkyline(
            new int[][] {{1, 10001, 10000}, {2, 10001, 9999}, {3, 10001, 9998}, {4, 10001, 9997}, {5, 10001, 9996}, {6, 10001, 9995}, {7, 10001, 9994},
                {8, 10001, 9993}, {9, 10001, 9992}, {10, 10001, 9991}});
        for (List<Integer> integers : skyline6) {
            System.out.println(integers.get(0) + "  " + integers.get(1));
        }
    }
}
