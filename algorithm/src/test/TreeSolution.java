package test;

import java.util.List;

/**
 * 树
 * 普通树搜索O(n)
 * 分叉 扩散出不同的状态
 * （有环就是图，链表）
 * 1、二叉树，
 * 二叉搜索树(左所有节点<根<右) --》 O(log(n))
 *
 * 树的解法：一般都是递归
 *
 * @Author luozhengchao
 * @Date 2021/3/28 下午2:06
 */
public class TreeSolution {

    /**
     * 前序遍历：根左右
     * @param node
     */
    public void perOrder(TreeNode node, List<Integer> res){
        if (node == null) return;
        res.add(node.val);
        perOrder(node.left, res);
        perOrder(node.right, res);
    }

}
