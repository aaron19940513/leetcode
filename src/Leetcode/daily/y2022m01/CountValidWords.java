package leetcode.daily.y2022m01;

public class CountValidWords {
    public int countValidWords(String sentence) {
        int result = 0;
        boolean legal = true;
        boolean isStart = false;
        for (int i = 0; i < sentence.length(); i++) {
            if (sentence.charAt(i) == ' ') {
                legal = true;
                isStart = true;
                continue;
            }

        }
        return result;
    }
}
