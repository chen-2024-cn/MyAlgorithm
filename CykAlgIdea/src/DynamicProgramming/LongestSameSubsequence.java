package DynamicProgramming;

import java.util.Arrays;

public class LongestSameSubsequence {
    //A和B的最长公共子序列
    public static void main(String[] args) {
        int[] a={1,7,5,4,8,3,9};
        int[] b={1,4,3,5,6,2,8,9};
        System.out.println(LCS2(a,b));
        System.out.println(LCS1(a,b));
    }
    //动态规划
    public static int LCS1(int[] a,int[] b){
        int[][] dp=new int[a.length+1][b.length+1];
        for(int i=1;i<=a.length;i++){
            for(int j=1;j<=b.length;j++){
                if(a[i-1]==b[j-1]){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else{
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[a.length][b.length];
    }
    //数字数组适用
    public static int LCS2(int[] a,int[] b){
        int[] num=new int[1000];
        int[] s=new int[b.length];
        //次解法前提是a，b里没有重复元素
        //将a重新编辑为{1,2,3,4,5,6,7}
        for(int i=0;i<a.length;i++){
            num[a[i]]=i+1;
        }
        //将b重新编辑为{1,4,6,3,0,0,5,7}（即b中对应的元素在a的位置）
        int n=0;
        for(int i=0;i<b.length;i++){
            if(num[b[i]]!=0){
                s[n++]=num[b[i]];
            }else{
                s[n++]=0;
            }
        }
        //转化为最长递增子序列
        int[] dp=new int[s.length];
        Arrays.fill(dp,1);
        for(int i=0;i<s.length;i++){
            for(int j=0;j<i;j++){
                if(s[j]<s[i]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
        }
        return dp[s.length-1];
    }
}
