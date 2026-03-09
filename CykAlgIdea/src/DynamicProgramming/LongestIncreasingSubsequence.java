package DynamicProgramming;

import java.util.Arrays;
//最长递增子序列
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        int[] num = {10, 2, 5, 3, 7, 101, 18};
        int[] dp = new int[num.length];
        Arrays.fill(dp, 1);
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < num.length; i++) {
            for (int j = 0; j < i; j++) {
                if (num[i] >= num[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }
}

