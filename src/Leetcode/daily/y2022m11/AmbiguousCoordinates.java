package leetcode.daily.y2022m11;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AmbiguousCoordinates {

    public List<String> ambiguousCoordinates(String s) {
        s = s.substring(1, s.length() - 1);
        List<String> result = new ArrayList<>();
        for (int i = 1; i < s.length(); i++) {
            List<String> left = possible(s.substring(0, i));
            List<String> right = possible(s.substring(i));
            if (left.size() == 0 || right.size() == 0) {
                continue;
            }
            for (String s1 : left) {
                for (String s2 : right) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("(").append(s1).append(", ").append(s2).append(")");
                    result.add(stringBuilder.toString());
                }
            }
        }
        return result;
    }

    private List<String> possible(String substring) {
        List<String> result = new ArrayList<>();
        if (substring.length() == 1) {
            result.add(substring);
        } else {
            if (substring.startsWith("0") && substring.endsWith("0")) {
                return result;
            } else if (substring.startsWith("0")) {
                result.add("0" + "." + substring.substring(1));
            } else if (substring.endsWith("0")) {
                result.add(substring);
            } else {
                result.add(substring);
                for (int i = 1; i < substring.length(); i++) {
                    result.add(substring.substring(0, i) + "." + substring.substring(i));
                }
            }
        }

        return result;
    }

    @Test
    public void test() {
        System.out.println(ambiguousCoordinates("(123)"));
        System.out.println(ambiguousCoordinates("(00011)"));
        System.out.println(ambiguousCoordinates("(0123)"));
        System.out.println(ambiguousCoordinates("(100)"));
    }
}
