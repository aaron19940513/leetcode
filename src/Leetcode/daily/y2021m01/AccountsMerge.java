package Leetcode.daily.y2021m01;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * 721. 账户合并 mid
 * 给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails 表示该账户的邮箱地址。
 * <p>
 * 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
 * <p>
 * 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是按顺序排列的邮箱地址。账户本身可以以任意顺序返回。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"],
 * ["Mary", "mary@mail.com"]]
 * 输出：
 * [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
 * 解释：
 * 第一个和第三个 John 是同一个人，因为他们有共同的邮箱地址 "johnsmith@mail.com"。
 * 第二个 John 和 Mary 是不同的人，因为他们的邮箱地址没有被其他帐户使用。
 * 可以以任何顺序返回这些列表，例如答案 [['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
 * ['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']] 也是正确的。
 * <p>
 * <p>
 * 提示：
 * <p>
 * accounts的长度将在[1，1000]的范围内。
 * accounts[i]的长度将在[1，10]的范围内。
 * accounts[i][j]的长度将在[1，30]的范围内。
 *
 * @date 01/18/21 9:13
 */
public class AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> emailMap = new HashMap<>();
        int[] father = new int[accounts.size()];
        for (int i = 0; i < father.length; i++) {
            father[i] = i;
        }
        for (int i = 0; i < accounts.size(); i++) {
            List<String> emailList = accounts.get(i);
            for (int j = 1; j < emailList.size(); j++) {
                if (!emailMap.containsKey(emailList.get(j))) {
                    emailMap.put(emailList.get(j), i);
                } else {
                    union(father, i, emailMap.get(emailList.get(j)));
                }
            }
        }
        List<List<String>> ans = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        for (int i = 0; i < accounts.size(); i++) {
            if (i != father[i]) {
                accounts.get(i).remove(0);
                accounts.get(find(father, i)).addAll(accounts.get(i));
            } else {
                nameList.add(accounts.get(i).get(0));
                accounts.get(i).remove(0);
                ans.add(accounts.get(i));
            }
        }
        for (int i = 0; i < ans.size(); i++) {
            ans.set(i, ans.get(i).stream().distinct().sorted().collect(Collectors.toList()));
            ans.get(i).add(0, nameList.get(i));
        }
        return ans;
    }

    private void union(int[] father, int i, int j) {
        int i1 = find(father, i);
        int i2 = find(father, j);
        father[i1] = i2;
    }

    private int find(int[] father, int i) {
        return i == father[i] ? i : (father[i] = find(father, father[i]));
    }

    @Test
    public void test() {
        String[] arr = new String[] {"john_newyork@mail.com", "johnsmith@mail.com", "john00@mail.com"};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test1() {
        List<List<String>> accounts = new ArrayList<>();
        List<String> accounts1 = new ArrayList<>();
        accounts1.add("John");
        accounts1.add("johnsmith@mail.com");
        accounts1.add("john00@mail.com");
        accounts.add(accounts1);
        List<String> accounts2 = new ArrayList<>();
        accounts2.add("John");
        accounts2.add("johnnybravo@mail.com");
        accounts.add(accounts2);
        List<String> accounts3 = new ArrayList<>();
        accounts3.add("John");
        accounts3.add("johnsmith@mail.com");
        accounts3.add("john_newyork@mail.com");
        accounts.add(accounts3);
        List<String> accounts4 = new ArrayList<>();
        accounts4.add("Mary");
        accounts4.add("mary@mail.com");
        accounts.add(accounts4);
        System.out.println(accountsMerge(accounts));
    }


}
