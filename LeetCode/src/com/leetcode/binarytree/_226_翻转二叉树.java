package com.leetcode.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName _226_翻转二叉树
 * @Description TODO
 * @Author carl
 * @Date 2020/8/23 22:35
 * @Version 1.0
 **/
/*翻转一棵二叉树。

示例：

输入：

     4
   /   \
  2     7
 / \   / \
1   3 6   9
输出：

     4
   /   \
  7     2
 / \   / \
9   6 3   1
备注:
这个问题是受到 Max Howell 的 原问题 启发的 ：

谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/invert-binary-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
public class _226_翻转二叉树 {
    /*注意在中序遍历中,因为已经变换了左右的元素,所以后面的递归是 left 而不是 right*/
    public TreeNode invertTree(TreeNode root) {
        /*后序遍历*/
//        if (root == null) return null;
//
//        TreeNode left = invertTree(root.left);
//        TreeNode right = invertTree(root.right);
//        root.left = right;
//        root.right = left;
//
//        return root;


        /*前序遍历*/
//        if (root == null) return null;
//
//        TreeNode tmp = root.left;
//        root.left = root.right;
//        root.right = tmp;
//
//        invertTree(root.left);
//        invertTree(root.right);
//
//        return root;


        /*中序遍历*/
//        if (root == null) return null;
//
//        invertTree(root.left);
//        TreeNode tmp = root.left;
//        root.left = root.right;
//        root.right = tmp;
//        invertTree(root.left);
//
//        return root;



        /*层序遍历*/
        if (root == null) return null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return root;
    }

}
