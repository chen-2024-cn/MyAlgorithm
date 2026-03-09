package BFS;

import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

public class DepthOfBinaryTree {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int depth = 1;
        while (q != null) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
//                if (cur.left == null&&cur.right == null) {
//                    return depth;
//                }
//                // 将非空子节点加入队列
//                if (cur.left != null) {
//                    q.offer(cur.left);
//                }
//                if (cur.right != null) {
//                    q.offer(cur.right);
//                }
            }
            depth++;
        }
        return depth;
    }
}
