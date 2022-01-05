package leetcode.daily.y2020m12;
/**
 * 387. 字符串中的第一个唯一字符 easy
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 *  
 *
 * 示例：
 *
 * s = "leetcode"
 * 返回 0
 *
 * s = "loveleetcode"
 * 返回 2
 *  
 *
 * 提示：你可以假定该字符串只包含小写字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-unique-character-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 12/23/20 8:50
 */
public class FirstUniqChar {
    public int firstUniqChar(String s) {
        return s2(s);
    }

    public int s1(String s){
        for (int i = 0; i < s.length() ; i++) {
            char c = s.charAt(i);
            int lastIndex = s.lastIndexOf(c);
            int firstIndex = s.indexOf(c);
            //如果相等表示不重复
            if(firstIndex == lastIndex){
                return i;
            }
        }
        return -1;
    }

    public int s2(String s){
        //只循环了26次
        int result = s.length();
        for (char c = 'a'; c <= 'z' ; c++) {
            int firstIndex = s.indexOf(c);
            int lastIndex = s.lastIndexOf(c);

            //如果相等表示不重复
            if(lastIndex == firstIndex && firstIndex != -1 ){
                result = Math.min(firstIndex,result);
            }
        }
        if(result != s.length()){
            return result;
        }
        return -1;
    }
}
