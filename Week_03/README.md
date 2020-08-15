学习笔记--回溯算法的框架：
1、路径：也就是已经做出的选择。

2、选择列表：也就是你当前可以做的选择。

3、结束条件：也就是到达决策树底层，无法再做选择的条件。

result = []
def backtrack(路径, 选择列表):
    if 满足结束条件:
        result.add(路径)
        return
    
    for 选择 in 选择列表:
        做选择
        backtrack(路径, 选择列表)
        撤销选择
其核心就是 for 循环里面的递归，在递归调用之前「做选择」，在递归调用之后「撤销选择」，特别简单。

路径 + 选择列表 特别好理解回溯问题

一种选择，对应一条路径

学习笔记--广度优先搜索范例

class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int total = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();//当前层的node数量
            while (size > 0) {
                TreeNode node = queue.poll();
                size--;

                //注意下面这些是下面一层的node，不要跟当前size混淆
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }                
            }
            total++;
        }

        return total;
    }
}
