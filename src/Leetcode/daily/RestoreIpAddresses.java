package Leetcode.daily;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * 93. 复原IP地址 中等
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * <p>
 * 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/restore-ip-addresses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 08/14/20 9:14
 */
public class RestoreIpAddresses {
    //todo
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        return ans;
    }

    @Test
    public void test() {
        Assert.assertEquals(restoreIpAddresses("25525511135"), Arrays.asList("255.255.11.135", "255.255.111.35"));
    }
}
