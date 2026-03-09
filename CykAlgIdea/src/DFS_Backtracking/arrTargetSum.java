package DFS_Backtracking;

import java.util.LinkedList;
import java.util.List;

public class arrTargetSum {
    static List<List<Integer>> res = new LinkedList<>();
    static LinkedList<Integer> trace = new LinkedList<>();
    public static void main(String[] args) {
        int[] nums = {1, 1, 5, 4, 5};
        dfs(nums, 0, 3, 2);
        System.out.println(res);
    }
    /**
     * 求和为target的元素集合
     *
     * @param a      一维数组
     * @param start  数组下标
     * @param target 目标和
     * @param sum    与目标和相比较的和
     */
    public static void dfs(int[] a, int start, int target, int sum) {
        if (sum == target) {
            res.add(new LinkedList<>(trace));
            return;
        }
        if (start == a.length || sum > target) return;
        for (int j = start; j < a.length; j++) {
            trace.addLast(a[j]);
            dfs(a, j + 1, target, sum + a[j]);
            trace.removeLast();
        }
    }
}
