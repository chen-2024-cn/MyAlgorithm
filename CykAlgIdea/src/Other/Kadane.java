package Other;

/**
 * 最大子矩阵和
 * 行列压缩法（降维+Kadane）
 * 思路：
 * 固定矩阵左右边界，对于每一对左右边界，计算每一行在这两列的之间的元素和，得到一个一维数组，对一维数组使用kadane算法求最大子数组和
 */
public class Kadane {
    public static void main(String[] args) {
        int[][] a = {{-1, -4, 3},
                {3, 4, -1},
                {-5, -2, 8}};
        int n = a.length;
        int m = a[0].length;
        int max = Integer.MIN_VALUE;
        for (int left = 0; left < m; left++) {
            int[] temp = new int[n];
            for (int right = left; right < m; right++) {
                for (int i = 0; i < n; i++) {
                    temp[i] += a[i][right];
                }
                int cur = temp[0];
                int curMax = temp[0];
                for (int i = 1; i < n; i++) {
                    cur = temp[i] + (cur > 0 ? cur : 0);
                    curMax = Math.max(cur, curMax);
                }
                max = Math.max(curMax, max);
            }
        }
        System.out.println(max);
    }
}
