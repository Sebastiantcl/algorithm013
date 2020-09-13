//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1: 
//
// 输入:
//[
//['1','1','1','1','0'],
//['1','1','0','1','0'],
//['1','1','0','0','0'],
//['0','0','0','0','0']
//]
//输出: 1
// 
//
// 示例 2: 
//
// 输入:
//[
//['1','1','0','0','0'],
//['1','1','0','0','0'],
//['0','0','1','0','0'],
//['0','0','0','1','1']
//]
//输出: 3
//解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 763 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numIslands(char[][] grid) {
        private int row_length;
        private int column_length;
        public int numIslands(char[][] grid) {
            int count = 0;
            row_length = grid.length;
            if(row_length == 0) return 0;
            column_length = grid[0].length;
            for(int i = 0; i< row_length ; i++){
                for(int j = 0; j < column_length; j++){
                    if(grid[i][j] == '1'){
                        DFSSearching(grid, i, j);//感染它的每一个相邻的实节点，然后设为0
                        ++count;
                    }
                }
            }
            return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
