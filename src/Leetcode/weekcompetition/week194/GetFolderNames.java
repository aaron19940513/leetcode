package Leetcode.weekcompetition.week194;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class GetFolderNames {
    public String[] getFolderNames(String[] names) {
        String regex = "\\([1-9][0-9]*\\)";
        String[] ans = new String[names.length];
        Map<String, Integer> nameMap = new HashMap<>();
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            Integer fileIndex = nameMap.get(name);
            if (null != fileIndex) {
                while (null != nameMap.get(name + '(' + (++fileIndex) + ')')) ;
                nameMap.put(name, fileIndex);
                ans[i] = name + '(' + (fileIndex) + ')';
            } else {
                boolean containSuffix = false;
                String namePrefix = null;
                Integer suffixIndex = 0;
                if (name.lastIndexOf('(') != -1 && name.lastIndexOf(')') == name.length() - 1) {
                    String nameSuffix = name.substring(name.lastIndexOf('('));
                    if (nameSuffix.matches(regex)) {
                        containSuffix = true;
                        suffixIndex = Integer.parseInt(name.substring(name.lastIndexOf('(') + 1, name.length() - 1));
                        namePrefix = name.substring(0, name.lastIndexOf('('));
                    }
                }
                if (containSuffix) {
                    Integer namePrefixIndex = nameMap.get(namePrefix);
                    if (namePrefixIndex == null || namePrefixIndex < suffixIndex) {
                        nameMap.put(name, 0);
                        ans[i] = name;
                    } else {
                        fileIndex = 0;
                        while (null != nameMap.get(name + '(' + (++fileIndex) + ')')) ;
                        nameMap.put(name, fileIndex);
                        ans[i] = name + '(' + (fileIndex) + ')';
                    }
                } else {
                    nameMap.put(name, 0);
                    ans[i] = name;
                }
            }
        }
        return ans;
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(getFolderNames(new String[]{"pes", "fifa", "gta", "pes(2019)"}), new String[]{"pes", "fifa", "gta", "pes(2019)"});
        Assert.assertArrayEquals(getFolderNames(new String[]{"gta", "gta(1)", "gta", "avalon"}), new String[]{"gta", "gta(1)", "gta(2)", "avalon"});
        Assert.assertArrayEquals(getFolderNames(new String[]{"onepiece", "onepiece(1)", "onepiece(2)", "onepiece(3)", "onepiece"}),
                new String[]{"onepiece", "onepiece(1)", "onepiece(2)", "onepiece(3)", "onepiece(4)"});
        Assert.assertArrayEquals(getFolderNames(new String[]{"wano", "wano", "wano", "wano"}), new String[]{"wano", "wano(1)", "wano(2)", "wano(3)"});
        Assert.assertArrayEquals(getFolderNames(new String[]{"kaido", "kaido(1)", "kaido", "kaido(1)"}), new String[]{"kaido", "kaido(1)", "kaido(2)", "kaido(1)(1)"});
        Assert.assertArrayEquals(getFolderNames(new String[]{"kaido", "kaido", "kaido", "kaido(1)", "kaido(1)(2)", "kaido(1)"}), new String[]{"kaido", "kaido(1)", "kaido(2)", "kaido(1)(1)", "kaido(1)(2)", "kaido(1)(3)"});
        Assert.assertArrayEquals(getFolderNames(new String[]{"A(1)(1)(1)","A(1)", "A(1)", "A(1)(1)"}), new String[]{"A(1)(1)(1)","A(1)", "A(1)(1)", "A(1)(1)(2)"});
    }
}
