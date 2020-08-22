学习笔记---33搜索旋转排序数组之暴力搜索法

实现代码如下：
三个步骤：查找最小值->升序还原->二分查找
其中查找最小值，参照了[153]寻找旋转排序数组中的最小值
升序还原，使用两个System.arraycopy搞定
二叉树查找的时候，我意识到实际上第二步还原是非必要的，直接进行数组局部二分查找也是可以的，更省空间

class Solution {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;
        if(nums.length == 1) return target == nums[0] ? 0 : -1;

        int min = findMin(nums);
        orderArrays(nums,min);
        int result = BinarySearch(nums,target);

        if(result >= 0){
            return (result + min) % nums.length;
        } else {
            return result;
        }
    }

    private int findMin(int[] nums) {
        int lo = 0, hi = nums.length - 1, mid = 0;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            if (nums[mid] > nums[hi]) { //mid 在左段
                lo = mid + 1;
            } else {//mid 在右段,则min 一定小于等于mid
                hi = mid;
            }
        }
        return lo;
    }

    private void orderArrays(int[] nums, int min){
        if(min > 0){
            int[] original = Arrays.copyOf(nums,nums.length);
            int left_length = min;
            int right_length = nums.length - min;
            System.arraycopy(original,min,nums,0,right_length);
            System.arraycopy(original,0,nums,right_length,left_length);
        }
    }

    private int BinarySearch(int[] nums,int target){
        int lo = 0, hi = nums.length - 1, mid = 0;
        while(lo <= hi) {//为什么这里要=，没有=，就会遗漏最后一个数字
            mid = lo + (hi - lo) / 2;
            if (nums[mid] == target){
                return mid;
            }
            if(nums[mid] > target){
                hi = mid - 1 ;
            } else {
                lo = mid + 1;
            }
        }
        return -1;
    }

   private int BinarySearch(int[] nums,int start,int end, int target){
        int lo = start, hi = end, mid = 0;
        while(lo <= hi) {//为什么这里要=，没有=，就会遗漏最后一个数字
            mid = lo + (hi - lo) / 2;
            if (nums[mid] == target){
                return mid;
            }
            if(nums[mid] > target){
                hi = mid - 1 ;
            } else {
                lo = mid + 1;
            }
        }
        return -1;
    }
}
