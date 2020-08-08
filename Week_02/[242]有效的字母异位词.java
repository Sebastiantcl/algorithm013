//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。 
//
// 示例 1: 
//
// 输入: s = "anagram", t = "nagaram"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s = "rat", t = "car"
//输出: false 
//
// 说明: 
//你可以假设字符串只包含小写字母。 
//
// 进阶: 
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
// Related Topics 排序 哈希表 
// 👍 227 👎 0


import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s == null || t == null || s.length() != t.length()) {
            return false;
        }

        int result = 0;
        int check[] = new int[26];
        for(int i = 0;i < s.length();i++){
            result += s.charAt(i) - t.charAt(i);
            check[s.charAt(i) - 'a']++;
            check[t.charAt(i) - 'a']--;
        }

        if(result == 0) {
            for (int i = 0; i < check.length; i++) {
                if(check[i] != 0) {
                    return false;
                }
            }
        }

        return result == 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
