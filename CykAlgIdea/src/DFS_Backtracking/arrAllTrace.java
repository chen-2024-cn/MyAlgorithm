package DFS_Backtracking;

import java.util.*;

//从左上角到右下角的所有路径
public class arrAllTrace {
    public static void main(String[] args) {
        int[][] a = {{4, 1, 3},
                    {7, 2, 5},
                    {8, 0, 6},
                    {9,11,99}};
        trace.add(a[0][0]);
        dfs(a,0, 0, a.length, a[0].length);
        System.out.println(res.size());
        System.out.println(res);
    }

    static LinkedList<Integer> trace = new LinkedList<>();
    static HashSet<LinkedList<Integer>> res = new HashSet<>();

    public static void dfs(int[][] a,int down, int right, int x, int y) {
        if (trace.size() == (x + y - 1)) {
            res.add(new LinkedList<>(trace));
            return;
        }
        if (down < x-1 ) {//向下移动
            trace.addLast(a[down+1][right]);
            dfs(a,down + 1, right, x, y);
            trace.removeLast();
        }
        if (right < y-1) {//向右移动
            trace.addLast(a[down][right+1]);
            dfs(a,down, right + 1, x, y);
            trace.removeLast();
        }
    }
}
