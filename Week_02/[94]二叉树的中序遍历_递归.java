//给定一个二叉树，返回它的中序 遍历。 
//
// 示例: 
//
// 输入: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//输出: [1,3,2] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 哈希表 
// 👍 614 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        process(root, list);
        return list;
    }

    private void process(TreeNode root, List <Integer> list) {
        Stack<TreeNode> stack = new Stack <> ();
        TreeNode current = root;
        //如果current不为空，先左遍历到底，找到最深的左节点，把它弹出，它会成为链表的前面元素；
        //1)如果它有右节点，current不会为空，下一轮从右节点开始，进行一轮新的遍历
        //2)如果它是叶子节点，则current会成空，则下一轮弹出新节点，也就是它的父节点
        //由此可知，中序遍历在树的结构上，是从左子树左下角开始，然后逐渐升到Root，然后再从右子树的左下角开始，完成右子树的遍历
        //对于一个完整的平衡树，List第一个元素是树的最左边叶子节点，最后一个元素是最右边的叶子节点
        while (current != null || !stack.isEmpty()) {
            while(current !=null){
                stack.push(current);
                current = current.left;
            }//左到底

            current = stack.pop();//中
            list.add(current.val);
            current = current.right;//遍历右

        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
