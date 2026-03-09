package DynamicProgramming;

import java.util.Scanner;

public class KnapsackProblem {
    public static void main(String[] args) {
        //背包问题(求背包里可以装的最大价值)
        Scanner sc = new Scanner(System.in);
        int allWeight = 4;//最大重量
        int N = 3;//物品数量
        int[] weight = {1, 2, 4};//每个物品的重量
        int[] value = {15, 20, 30};//每个物品的价值
        //dp[i][w]表示前i个物品在重量为w时获得的最大价值(二维)
        int[][] dp = new int[N + 1][allWeight + 1];
        for (int i = 0; i <= N; i++) {
            for (int w = i; w <= allWeight; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (w >= weight[i - 1]) {//当前物品重量小于当前容量时
                    dp[i][w] = Math.max(dp[i - 1][w], value[i - 1] + dp[i - 1][w - weight[i - 1]]);//max(当前物品的价值+剩余容量的最大价值，不选择当前物品的价值)
                } else {
                    dp[i][w] = dp[i - 1][w];//继承前一个状态的值
                }
            }
        }
        System.out.println(dp[N][allWeight]);
        System.out.println(_01Knapsack(weight,value,3,4));
        System.out.println(_allKnapsack(weight,value,3,4));
    }

    //01背包（一维）
    public static int _01Knapsack(int[] weight, int[] value, int n, int allWeight) {
        int[] dp = new int[allWeight + 1];
        for (int i = 1; i <= n; i++) {
            for(int j=allWeight;j>i;j--){
                dp[j]=Math.max(dp[j],dp[j-weight[i-1]]+value[i-1]);
            }
        }
        return dp[allWeight];
    }
    //完全背包(表示选的物品可以重复)
    public static int _allKnapsack(int[] weight,int[] value, int n ,int allWeight){
        int[][] dp=new int[n+1][allWeight+1];
        for(int i=1;i<=n;i++){
            for(int j=0;j<=allWeight;j++){
                dp[i][j]=dp[i-1][j];
                if(j<weight[i-1]){
                    dp[i][j]=dp[i-1][j];
                }else{
                    dp[i][j]=Math.max(dp[i][j],dp[i][j-weight[i-1]]+value[i-1]);
                }
            }
        }
        return dp[n][allWeight];
    }
}
