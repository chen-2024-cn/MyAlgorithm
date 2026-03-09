package DynamicProgramming;

import java.util.Arrays;

public class SmallChangeProblem {
    //方法一
    public static int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subProblem = coinChange(coins, amount - coin);
            if (subProblem == -1) {
                continue;
            }
            res = Math.min(res, subProblem + 1);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    //方法二：添加数组memo，减少重复计算
    private static int[] memo;

    public static int coinChange1(int[] coins, int amount) {
        memo = new int[amount + 1];
        Arrays.fill(memo, -666);
        return dp(coins, amount);
    }

    public static int dp(int[] coins, int amount) {
        //base case
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        if (memo[amount] != -666) return memo[amount];

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subProblem = dp(coins, amount - coin);
            if (subProblem == -1) {
                continue;
            }
            res = Math.min(res, subProblem + 1);
        }
        memo[amount] = (res == Integer.MAX_VALUE) ? -1 : res;
        return memo[amount];
    }

    //方法三：迭代dp求解
    public static int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        //外层循环遍历所有状态的所有取值
        for (int i = 0; i < dp.length; i++) {
            //内层循环遍历所有选择的最小值
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] a = {1, 5, 10, 20};
        long now = System.currentTimeMillis();
        System.out.println(coinChange(a, 60));
        System.out.println(coinChange1(a, 60));
        System.out.println(coinChange2(a, 60));
        System.out.println(System.currentTimeMillis() - now + "ms");
    }
}
