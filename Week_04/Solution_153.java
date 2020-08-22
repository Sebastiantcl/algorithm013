//假设按照升序排序的数组在预先未知的某个点上进行了旋转。
//
// ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
//
// 请找出其中最小的元素。
//
// 你可以假设数组中不存在重复元素。
//
// 示例 1:
//
// 输入: [3,4,5,1,2]
//输出: 1
//
// 示例 2:
//
// 输入: [4,5,6,7,0,1,2]
//输出: 0
// Related Topics 数组 二分查找
// 👍 241 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution_153 {
    public int findMin(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        int lo = 0, hi = nums.length - 1, mid = 0;
        int min = lo;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            // 先根据 nums[mid] 与 nums[hi] 的关系判断 mid 是在左段还是右段
            if (nums[mid] > nums[hi]) { //mid 在左段
                //min 只可能在右段,所以lo 要加
                lo = mid + 1;
            } else {//mid 在右段,则min 一定小于等于mid
                hi = mid;
            }
            min = lo;
        }
        return nums[min];
    }

}
//leetcode submit region end(Prohibit modification and deletion)

