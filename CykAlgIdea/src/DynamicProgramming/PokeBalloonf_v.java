package DynamicProgramming;

/**
 * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
 * 求所能获得硬币的最大数量。
 * 输入：nums = [3,1,5,8]
 * 输出：167
 * 解释：
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 */
public class PokeBalloonf_v {
    public static void main(String[] args) {
        int[] nums = {3, 1, 5, 8};
        int n = nums.length;
        int[] point = new int[n + 2];
        point[0] = point[n + 1] = 1;
        for (int i = 1; i < n + 1; i++) {
          point[i]=nums[i-1];
        }
        int[][] dp = new int[n + 2][n + 2];
        for (int i = n; i >= 0; i--) {
            for (int j = i + 1; j < n + 2; j++) {
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + point[i]*point[k]*point[j]);
                }
            }
        }
        System.out.println(dp[0][n + 1]);
    }
}
