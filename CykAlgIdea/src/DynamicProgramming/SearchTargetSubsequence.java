package DynamicProgramming;

import java.util.Scanner;

/**
 * 输入样例
 * lanlan
 * 输出样例
 * 4
 * 说明
 * 在字符串 lanlan 中，子序列 lan 出现了4 次。具体来说，可以选取以下位置的字符：
 * 第1、2、3 个字符（l, a, n）
 * 第1、2、6个字符（l, a, n）
 * 第1、5、6 个字符（l, a, n）
 * 第4、5、6 个字符（l, a, n）
 */
public class SearchTargetSubsequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();//主串
        String t=scanner.next();//模式串
        int[][] dp=new int[str.length()+1][t.length()+1];//str的前i个中，t的前j个字符出现的个数
        for(int i=0;i<=t.length();i++){
            dp[i][0]=1;
        }
        for(int i=1;i<=str.length();i++){
            for(int j=1;j<=t.length();j++){
                if(str.charAt(i-1)==t.charAt(j-1)){
                    dp[i][j]=dp[i-1][j]+dp[i-1][j-1];
                }else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        System.out.println(dp[str.length()][t.length()]);
    }
}
