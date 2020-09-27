学习笔记
写出不同路径 2 这道题目的状态转移方程

1、状态定义：
dp[i][j] 表示走到格子 (i,j) 的方法数。

2、状态转移：
如果网格 (i,j) 上有障碍物，则 dp[i][j] 值为 0，表示走到该格子的方法数为 0；
否则网格 (i, j)可以从网格 (i - 1, j) 或者 网格 (i,j−1) 走过来，因此走到该格子的方法数为走到网格 (i - 1, j) 和网格 (i, j - 1) 的方法数之和，
即 dp[i, j] = dp[i - 1, j] + dp[i, j - 1]

状态转移方程如下：

dp[i][j] = dp[i - 1, j] + dp[i, j - 1]  (i, j) 上无障碍物
dp[i][j] = 0                            (i, j) 上有障碍物

DP实现如下:
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }
        // 定义 dp 数组并初始化第 1 行和第 1 列
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];//初始化为0
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n && obstacleGrid[0][j] == 0; j++) {
            dp[0][j] = 1;
        }

        // 根据状态转移方程 dp[i][j] = dp[i - 1][j] + dp[i][j - 1] 进行递推。
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
   }


​	
  
