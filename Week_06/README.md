动态规划关键点 学习笔记

1.最优子结构 option[n] = best_of(opt[n-1],opt(n-2),...)
解释：推导第n个值是前面几步的最佳值，有时候最佳值是最大值或最小值
2.存储中间状态: opt[i]
解释：必须定义状态且存储中间状态，一般需要新建数组存储中间状态
3.递推公式 （状态转移方程或者DP方程）
Fib:opt[i] = opt[i-1] + opt[i-2]
二维路径: opt[i,j] = opt[i+1][j] + opt[i][j+1]  (且判断a[i,j]是否空地)
解释：不同的问题DP方程会不一样，难题的关键是找DP方程


附回溯法 暴力解决“戳气球问题"
class Solution_312 {
    int maxCoin=0;
    public int maxCoins(int[] nums) {
        if(nums == null) return 0;
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());

        getCoins(list,0,0,list.size());
        return maxCoin;
    }

    private void getCoins(List<Integer> nums,int currentLevel,int currentCoin,final int MAX_LENGTH) {
        Log.d("size " + nums.size()+ " level "+ currentLevel + " currentCoin " + currentCoin);
        //每层的气球数，也是层级数
        int length = nums.size();
        //回归条件，我们走完最后一层了
        if (currentLevel == MAX_LENGTH) {
            //如果该路径所得金币数大于当前最大值，更新最大值
            if (currentCoin > maxCoin) {
                maxCoin = currentCoin;
            }
            return ;
        }
        //尝试该层所有可走的路径
        for (int i = 0; i < length; i++) {
            //to do something
            int before_index = i-1;
            int next_index = i+1;
            int beforeNum;
            int nextNum;

            if (before_index < 0) {
                beforeNum = 1;
            } else {
                beforeNum = nums.get(before_index);
            }

            if (next_index >= length) {
                nextNum = 1;
            } else {
                nextNum = nums.get(next_index);
            }

            int currentNum = nums.get(i);
            Log.d("get index " + i+ " value " + currentNum);
            currentCoin += currentNum * nextNum * beforeNum;

            List<Integer> next = new ArrayList<>();
            nums.remove(i); next.addAll(nums);
            getCoins(next,currentLevel+1,currentCoin,MAX_LENGTH);

            nums.add(i,currentNum);
            currentCoin -= currentNum * nextNum * beforeNum;
        }
    }

    public static void main(String[] args) {
        int[] nums = {3,1,5,8};
        Solution_312 ss= new Solution_312();
        long time1 = System.currentTimeMillis();
        int result = ss.maxCoins(nums);
        Log.d("max result:" + result + " cost time:" +(System.currentTimeMillis()-time1));
    }
}
