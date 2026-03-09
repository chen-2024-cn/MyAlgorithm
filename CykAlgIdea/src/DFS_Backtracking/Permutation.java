package DFS_Backtracking;

import java.util.*;

//全排列
public class Permutation {
    public static void main(String[] args) {
        int n = 3;
        boolean[] used = new boolean[n];
        permutation(n, 0, used);
        System.out.println(res);
    }

    static List<List<Integer>> res = new LinkedList<>();
    static List<Integer> trace = new ArrayList<>();

    private static void permutation(int n, int index, boolean[] used) {
        if (index == n) {
            res.add(new ArrayList<>(trace));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (used[i]) continue;
            trace.add(i + 1);
            used[i] = true;
            permutation(n, index + 1, used);
            used[i] = false;
            trace.remove(trace.size() - 1);
        }
    }
}
