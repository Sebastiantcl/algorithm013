//给定一个无序的整数数组，找到其中最长上升子序列的长度。 
//
// 示例: 
//
// 输入: [10,9,2,5,3,7,101,18]
//输出: 4 
//解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。 
//
// 说明: 
//
// 
// 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。 
// 你算法的时间复杂度应该为 O(n2) 。 
// 
//
// 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗? 
// Related Topics 二分查找 动态规划 
// 👍 1030 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int result = 1;
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        if(nums.length == 1) return 1;
        int len = nums.length;

        for(int i = 0; i < len && result < len - i; i++) {
            int temp = 1;
            int last_compare = nums[i];
            backtrack(nums,i+1,last_compare,temp);
        }
        return result;
    }

    private void backtrack(int[] nums,int current_index, int last_compare,int temp) {
        int len = nums.length;
        if(current_index >= len || result >= temp + len - current_index) {
            result = temp > result ? temp:result;
            return;
        }

        if(nums[current_index] > last_compare) {
            int keep1 = last_compare; int keep2 = temp;
            //选择1,添加,可能引起result的变化，所以必须比较
            last_compare = nums[current_index];
            temp++;
            result = temp > result ? temp:result;
            backtrack(nums,current_index+1,last_compare,temp);

            //选择2,不添加，跳过，不会引起result的变化
            last_compare = keep1;
            temp = keep2;
            backtrack(nums,current_index+1,last_compare,temp);
        } else {
            backtrack(nums,current_index+1,last_compare,temp);
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)
