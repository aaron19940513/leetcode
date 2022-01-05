package leetcode.weekcompetition.week237;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

/**
 * 5736. 单线程 CPU 显示英文描述
 * 题目难度Medium
 * 给你一个二维数组 tasks ，用于表示 n​​​​​​ 项从 0 到 n - 1 编号的任务。其中 tasks[i] = [enqueueTimei, processingTimei] 意味着第 i​​​​​​​​​​ 项任务将会于 enqueueTimei 时进入任务队列，需要
 * processingTimei 的时长完成执行。
 * <p>
 * 现有一个单线程 CPU ，同一时间只能执行 最多一项 任务，该 CPU 将会按照下述方式运行：
 * <p>
 * 如果 CPU 空闲，且任务队列中没有需要执行的任务，则 CPU 保持空闲状态。
 * 如果 CPU 空闲，但任务队列中有需要执行的任务，则 CPU 将会选择 执行时间最短 的任务开始执行。如果多个任务具有同样的最短执行时间，则选择下标最小的任务开始执行。
 * 一旦某项任务开始执行，CPU 在 执行完整个任务 前都不会停止。
 * CPU 可以在完成一项任务后，立即开始执行一项新任务。
 * 返回 CPU 处理任务的顺序。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：tasks = [[1,2],[2,4],[3,2],[4,1]]
 * 输出：[0,2,3,1]
 * 解释：事件按下述流程运行：
 * - time = 1 ，任务 0 进入任务队列，可执行任务项 = {0}
 * - 同样在 time = 1 ，空闲状态的 CPU 开始执行任务 0 ，可执行任务项 = {}
 * - time = 2 ，任务 1 进入任务队列，可执行任务项 = {1}
 * - time = 3 ，任务 2 进入任务队列，可执行任务项 = {1, 2}
 * - 同样在 time = 3 ，CPU 完成任务 0 并开始执行队列中用时最短的任务 2 ，可执行任务项 = {1}
 * - time = 4 ，任务 3 进入任务队列，可执行任务项 = {1, 3}
 * - time = 5 ，CPU 完成任务 2 并开始执行队列中用时最短的任务 3 ，可执行任务项 = {1}
 * - time = 6 ，CPU 完成任务 3 并开始执行任务 1 ，可执行任务项 = {}
 * - time = 10 ，CPU 完成任务 1 并进入空闲状态
 * 示例 2：
 * <p>
 * 输入：tasks = [[7,10],[7,12],[7,5],[7,4],[7,2]]
 * 输出：[4,3,2,0,1]
 * 解释：事件按下述流程运行：
 * - time = 7 ，所有任务同时进入任务队列，可执行任务项  = {0,1,2,3,4}
 * - 同样在 time = 7 ，空闲状态的 CPU 开始执行任务 4 ，可执行任务项 = {0,1,2,3}
 * - time = 9 ，CPU 完成任务 4 并开始执行任务 3 ，可执行任务项 = {0,1,2}
 * - time = 13 ，CPU 完成任务 3 并开始执行任务 2 ，可执行任务项 = {0,1}
 * - time = 18 ，CPU 完成任务 2 并开始执行任务 0 ，可执行任务项 = {1}
 * - time = 28 ，CPU 完成任务 0 并开始执行任务 1 ，可执行任务项 = {}
 * - time = 40 ，CPU 完成任务 1 并进入空闲状态
 *
 * @date 2021/4/18 10:40
 */
public class GetOrder {
    public int[] getOrder(int[][] tasks) {
        int[] ans = new int[tasks.length];
        int ansIndex = 0;
        PriorityQueue<Pair<Integer, Integer>> startTimeQueue = new PriorityQueue<>(new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                return o1.getKey() - o2.getKey();
            }
        });
        long startTime = Long.MAX_VALUE;
        for (int i = 0; i < tasks.length; i++) {
            startTime = Math.min(startTime, tasks[i][0]);
            startTimeQueue.offer(new Pair<>(tasks[i][0], i));
        }
        PriorityQueue<Pair<Integer, Integer>> executeTimeQueue = new PriorityQueue<>(new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                if (!Objects.equals(o1.getKey(), o2.getKey())) {
                    return o1.getKey() - o2.getKey();
                } else {
                    return o1.getValue() - o2.getValue();
                }
            }
        });

        while (!startTimeQueue.isEmpty()) {
            while (!startTimeQueue.isEmpty() && startTimeQueue.peek().getKey() <= startTime) {
                Pair<Integer, Integer> poll = startTimeQueue.poll();
                executeTimeQueue.offer(new Pair<>(tasks[poll.getValue()][1], poll.getValue()));
            }
            int nextStartTime = startTimeQueue.size() > 0 ? startTimeQueue.peek().getKey() : Integer.MAX_VALUE;

            do {
                Pair<Integer, Integer> poll = executeTimeQueue.poll();
                startTime = startTime + poll.getKey();
                ans[ansIndex++] = poll.getValue();
            } while (executeTimeQueue.size() > 0 && startTime < nextStartTime);
            startTime = Math.max(startTime, nextStartTime);
        }

        while (!executeTimeQueue.isEmpty()) {
            Pair<Integer, Integer> poll = executeTimeQueue.poll();
            startTime = startTime + poll.getKey();
            ans[ansIndex++] = poll.getValue();
        }
        return ans;
    }

    @Test
    public void test() {

        Assert.assertArrayEquals(new int[] {0}, getOrder(new int[][] {{1, 2}}));
        Assert.assertArrayEquals(new int[] {0, 2, 3, 1}, getOrder(new int[][] {{1, 2}, {2, 4}, {3, 2}, {4, 1}}));
        Assert.assertArrayEquals(new int[] {4, 3, 2, 0, 1}, getOrder(new int[][] {{7, 10}, {7, 12}, {7, 5}, {7, 4}, {7, 2}}));
    }

    @Test
    public void errorCae() {
        Assert.assertArrayEquals(new int[] {0, 1}, getOrder(new int[][] {{1, 2}, {10, 2}}));
        Assert.assertArrayEquals(new int[] {15, 14, 13, 1, 6, 3, 5, 12, 8, 11, 9, 4, 10, 7, 0, 2}, getOrder(
            new int[][] {{35, 36}, {11, 7}, {15, 47}, {34, 2}, {47, 19}, {16, 14}, {19, 8}, {7, 34}, {38, 15}, {16, 18}, {27, 22}, {7, 15}, {43, 2}, {10, 5},
                {5, 4}, {3, 11}}));
    }

}
