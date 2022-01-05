package leetcode;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sam
 * @date 11/07/19 9:37
 */
public class superEggDrop {
    public static void main(String[] args) {
        //System.out.println(solution(2, 6));
        System.out.println(solution(3, 15));
    }

    static Map<Integer, Integer> cache = new HashMap<>();

    public static int solution(int K, int N) {
        if (N == 0) {
            return 0;
        } else if (K == 1) {
            return N;
        }

        Integer key = N * 1000 + K; // K <= 100
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        int low = 1, high = N;
        while (low + 1 < high) {
            int middle = (low + high) / 2;
            int lowVal = solution(K - 1, middle - 1);
            int highVal = solution(K, N - middle);

            if (lowVal < highVal) {
                low = middle;
            } else if (lowVal > highVal) {
                high = middle;
            } else {
                low = high = middle;
            }
        }
        int minimum = 1 + Math.min(
            Math.max(solution(K - 1, low - 1), solution(K, N - low)),
            Math.max(solution(K - 1, high - 1), solution(K, N - high))
        );

        cache.put(key, minimum);

        return cache.get(key);
    }


}
