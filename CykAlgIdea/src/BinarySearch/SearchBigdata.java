package BinarySearch;

import java.util.Arrays;
import java.util.Scanner;

public class SearchBigdata {
    /**
     *算法核心：
     * 排序a数组
     * 对每个b元素，二分查找a中大于它的元素个数
     * 累加所有结果
     */
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] a=new int[n],b=new int[n];
        for(int i=0;i<n;i++){
            a[i]=sc.nextInt();
        }
        Arrays.sort(a);
        int ans=0;
        for(int i=0;i<n;i++){
            int x=sc.nextInt();
            int l=0,r=n-1,p=n;
            while(l<=r){
                int mid=(l+r)>>1;
                if(a[mid]>x){
                    p=mid;
                    r=mid-1;
                }else{
                    l=mid+1;
                }
            }
            ans+=n-p;
        }
        System.out.println(ans);
    }
}
