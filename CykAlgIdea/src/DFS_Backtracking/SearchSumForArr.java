package DFS_Backtracking;

import java.util.*;


public class SearchSumForArr {
    public static void main(String[] args) {
    Scanner sc=new Scanner(System.in);
    int n=sc.nextInt();
    int[] a = new int[n];
    for(int i=0;i<n;i++){
        a[i]=sc.nextInt();
    }
    Arrays.sort(a);
    backtrack(a,0,0,0,0);
        System.out.println(minDiff);
    }
    static long minDiff=Integer.MAX_VALUE;
    static public void backtrack(int[] a,int index,long sum1,long sum2,int count){
       if(Math.abs(sum1-sum2)>=minDiff)return;
        if(index==a.length){
            if(count!=0&&count!=a.length){
                long diff= Math.abs(sum1-sum2);
                minDiff=Math.min(diff,minDiff);
            }
            return;
        }
            backtrack(a,index+1,sum1+a[index],sum2,count+1);
            backtrack(a,index+1,sum1,sum2+a[index],count);
    }
}
