//给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。 
//
// 示例 1: 
//
// 
//输入: "aba"
//输出: True
// 
//
// 示例 2: 
//
// 
//输入: "abca"
//输出: True
//解释: 你可以删除c字符。
// 
//
// 注意: 
//
// 
// 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。 
// 
// Related Topics 字符串 
// 👍 263 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
     public boolean validPalindrome(String str) {
         if(s==null||s.length()==0) return true;
          char[] s = str.toCharArray();
          int i = 0, j = s.size() - 1;
          for (; i < j && s[i] == s[j]; ++i, --j); //不相等就中断
          return palindrome(s, i, j - 1) || palindrome(s, i + 1, j);
     }

     private boolean palindrome(char[] s, int i, int j) {
          for ( ; i < j && s[i] == s[j]; ++i, --j);
          return i >= j;//没到底说明中断了，aba是=，aa是>
     }
}
//leetcode submit region end(Prohibit modification and deletion)
