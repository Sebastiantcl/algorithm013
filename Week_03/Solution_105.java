//从前序与中序遍历序列构造二叉树

import java.util.HashMap;

public class Solution_105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null
                || preorder.length == 0 || inorder.length == 0){
            return null;
        }
        final HashMap<Integer,Integer> map = new HashMap<>(inorder.length);
        for(int i=0 ; i < inorder.length; i++) {
            map.put(inorder[i],i);
        }
        return buildTreeHelper(preorder,0,preorder.length-1,inorder,0,inorder.length-1,map);

    }

    private TreeNode buildTreeHelper(final int[] preorder, int pre_start,int pre_end,
                                     final int[] inorder,  int in_start,int in_end,
                                     final HashMap<Integer,Integer> map){
        //if less than 1 or it is 1, we think it is a left node.
        //So it doesn't need continue;
        if(pre_start > pre_end || in_start > in_end) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[pre_start]);
        int root_index = map.get(root.val);

        //pre_end - pre_start == in_end - in_start
        int left_size = root_index - in_start;

        root.left = buildTreeHelper(preorder,pre_start + 1,pre_start + left_size, inorder,in_start,root_index -1,map);
        root.right = buildTreeHelper(preorder,pre_start + left_size + 1,pre_end,inorder,root_index + 1,in_end,map);

        return root;
    }

    public static void main(String[] args){
        Solution_105 solution_105 = new Solution_105();
        int[] pre = {1,2,3};
        int[] in = {3,2,1};
        solution_105.buildTree(pre,in);
    }



    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}
