//给出一个区间的集合，请合并所有重叠的区间。 
//
// 
//
// 示例 1: 
//
// 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出: [[1,6],[8,10],[15,18]]
//解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2: 
//
// 输入: intervals = [[1,4],[4,5]]
//输出: [[1,5]]
//解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。 
//
// 注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。 
//
// 
//
// 提示： 
//
// 
// intervals[i][0] <= intervals[i][1] 
// 
// Related Topics 排序 数组 
// 👍 615 👎 0


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }

        //核心是这个排序算法
        //官方：binary insertion sort
//        Arrays.sort(intervals, new Comparator<int[]>() {
//            public int compare(int[] interval1, int[] interval2) {
//                return interval1[0] - interval2[0];
//            }
//        });
        //快速排序
        // this.quickSort(intervals, (v1, v2) -> v1[0] - v2[0],0,intervals.length-1);

        //归并排序
        this.mergeSort(intervals, (v1, v2) -> v1[0] - v2[0],0,intervals.length-1);

        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    //快速排序
    public void quickSort(int[][] array, Comparator<int[]> comparator, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(array, comparator, left, right);
            quickSort(array, comparator, left, partitionIndex-1);
            quickSort(array, comparator,partitionIndex+1, right);
        }
    }

    private int partition(int[][] array, Comparator<int[]> comparator, int left, int right) {     // 分区操作
        int pivot = left,                  // 设定基准值（pivot） 这里把左边界作为基准
                index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (comparator.compare(array[i], array[pivot]) < 0) {
                swap(array, i, index);
                index++;//一番对比，得到pivot应该排的位置
            }
        }
        swap(array, pivot, index - 1);
        return index-1;
    }

    private void swap(int[][] array, int i, int j) {
        int[] temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    //归并排序
    public void mergeSort(int[][] array,Comparator<int[]> comparator, int begin, int end) {
        if(end <= begin) return;
        int mid = (begin + end) >>1;

        mergeSort(array,comparator, begin, mid);
        mergeSort(array,comparator,mid+1, end);
        merge(array, comparator, begin, mid, end);
    }

    private void merge(int[][] array, Comparator<int[]> comparator, int begin, int mid, int end) {
        int len = array[begin].length;
        int[][] tmp = new int[end-begin+1][len];
        int i = begin, j = mid + 1, k= 0;

        while(i <= mid && j <= end) {
            if(comparator.compare(array[i],array[j]) <= 0) {
                System.arraycopy(array[i++],0,tmp[k++],0,len);
            } else {
                System.arraycopy(array[j++],0,tmp[k++],0,len);
            }
        }

        while(i <= mid) System.arraycopy(array[i++],0,tmp[k++],0,len);
        while(j <= end) System.arraycopy(array[j++],0,tmp[k++],0,len);

        System.arraycopy(tmp,0,array,begin,tmp.length);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
