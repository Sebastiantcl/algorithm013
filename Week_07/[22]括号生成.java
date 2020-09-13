//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例： 
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
// 
// Related Topics 字符串 回溯算法 
// 👍 1300 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        public List<String> generateParenthesis(int n) {
            _generate(0, 0, n, "");
            return list;
        }

        private void _generate(int left_level, int right_level, int max, String s){
            //terminator
            if(left_level == max && right_level == max){
                list.add(s);
                return;
            }

            //process current logic: left right

            //drill down
            if(left_level < max) {
                _generate(left_level + 1, right_level, max, s + "(");
            }
            if(right_level < left_level) {
                _generate(left_level, right_level + 1, max, s + ")");
            }
            //reverse state
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
