//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。 
//
// 示例 1： 
//
// 输入: "babad"
//输出: "bab"
//注意: "aba" 也是一个有效答案。
// 
//
// 示例 2： 
//
// 输入: "cbbd"
//输出: "bb"
// 
// Related Topics 字符串 动态规划 
// 👍 2730 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestPalindrome(String str) {
        String result = "";
        if(str == null || str.length() == 0) return result;
        int len = str.length();
        boolean[][] dp = new boolean[len][len];
        for(int length = 1; length <=len; length++) {
            for(int i = 0 ,j = i + length - 1 ; j < len; i++,j++) {
                if(length == 1){
                    dp[i][j] = true;
                } else if(length == 2){
                    dp[i][j] = str.charAt(i) == str.charAt(j);
                } else {
                    dp[i][j] = dp[i+1][j-1] && str.charAt(i) == str.charAt(j);
                }

                if(dp[i][j] && result.length() < length){
                    result = str.substring(i,j+1);
                }
            }

        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
