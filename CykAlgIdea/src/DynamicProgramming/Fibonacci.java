package DynamicProgramming;

public class Fibonacci {

    public static int f(int N) {
        if (N == 1 || N == 2) {
            return 1;
        }
        return f(N - 1) + f(N - 2);
    }

    //重叠子问题，用数组memo充当“备忘录”,减少重复计算
    public static int fib(int N) {
        int[] memo = new int[N + 1];
        return help(memo, N);
    }

    public static int help(int[] memo, int n) {
        if (n == 0 || n == 1) {//base case
            return n;
        }
        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = help(memo, n - 1) + help(memo, n - 2);
        return memo[n];
    }

    //DP table,状态转移方程（暴力解优化版）
    public static int fibo(int N) {
        if (N == 0) return 0;
       // int[] dp = new int[N + 1];
        //dp[0] = 0;dp[1] = 1;
        int dp1 = 1;int dp2 = 0;
        for (int i = 2; i <= N; i++) {
           // dp[i] = dp[i - 1] + dp[i - 2];
            int dp = dp1 + dp2;
            //滚动更新
            dp2 = dp1;
            dp1 = dp;
        }
        return dp1;
    }

    public static void main(String[] args) {
        long now = System.currentTimeMillis();
        System.out.println(f(40));
        System.out.println(fib(40));
        System.out.println(fibo(40));
        System.out.println(System.currentTimeMillis() - now + "ms");
    }
}
