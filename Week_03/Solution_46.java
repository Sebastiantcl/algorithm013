//全排列
import java.util.*;

public class Solution_46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums == null || nums.length < 1){
            return result;
        }

        _generate(nums, new boolean[nums.length],0,new ArrayList<>(), result);
        return result;
    }

    private void _generate(final int[] nums,
                           boolean[] choice,
                           int depth,
                           List<Integer> path,
                           List<List<Integer>> result) {
        if (depth == choice.length) {//已经选完，路到尽头
            result.add(path);
            return;
        }

        for(int i = 0; i < choice.length; i++) {
            if(!choice[i]) {
                System.out.println("begin "+nums[i]);
                path.add(nums[i]);
                choice[i] = true;

                //注意1到了下一层要新建path，才能保证道路分叉
                //注意2这里是一个递归，会走很多层，会耗时很久，
                //会从choice[i] = true 这条路线一直走到底，走到 depth == choice.length 为止
                //走到 每个子选择的底部 depth == choice.length 为止
                _generate(nums,choice,depth+1, new ArrayList<Integer>(path), result);

                //送到下一层之后，当前层的状态要回置
                //因为这是一个for循环，当前层的选择并没有结束，必须在这里遍历完所有选择,岔路还有很多条
                //注意在这一层每次都只能有一个选择，往下的步骤，都是以这个选择为前提条件，完成回溯
                choice[i] = false;
                path.remove(path.size()-1);

            }
        }

    }

    public static void main(String[] args){
        int[] nums = {1,2,3};
        Solution_46 s = new Solution_46();
        System.out.println(s.permute(nums));
    }
}
