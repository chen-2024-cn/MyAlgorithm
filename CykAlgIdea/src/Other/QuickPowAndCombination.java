package Other;

public class QuickPowAndCombination {
    static long mod = 1000000007;

    public static void main(String[] args) {
        int m = 11, n = 3;
        System.out.println(quickPow(m, n));//m^n
        System.out.println(combination1(m, n));//C(m,n)
        System.out.println(combination2(m, n));//C(m,n)
    }

    private static long combination2(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];//dp[i][j]表示从i个元素中选择j个元素的组合数
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (int) ((dp[i - 1][j] + dp[i - 1][j - 1]) % mod);
            }
        }
        return dp[m][n];
    }

    private static long combination1(int m, int n) {
        if (m - n > n) n = m - n;//利用C(m,n)=C(m,m-n)减少计算量
        long res = 1;
        for (int i = 1; i <= n; i++) {
            res = res * (m - i + 1) / i % mod;
        }
        return res;
    }

    private static long quickPow(long a, int b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) res *= a % mod;
            a *= a % mod;
            b >>= 1;
        }
        return res;
    }
}
