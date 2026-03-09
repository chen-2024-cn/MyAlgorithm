package Sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class CustomSort_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int[] a=new int[n];
        for (int i = 0; i < n; i++) {
            a[i]=sc.nextInt();
        }
        long[] pre=new long[n+1];
        for (int i = 1; i <= a.length; i++) {
            pre[i]=pre[i-1]+a[i-1];
        }
        //预处理所有连续区间
        long[] sums=new long[n*(n+1)/2];//每个区间的和
        int[][] bounds=new int[sums.length][2];//对应区间的左右边界
        int x=0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                sums[x]=pre[j+1]-pre[i];
                bounds[x][0]=i;
                bounds[x][1]=j;
                x++;
            }
        }
        for(long i:sums){
            System.out.print(i+" ");
        }
        System.out.println();
        //排序区间和,便于后面找到和接近的区间
        Integer[] indices=new Integer[sums.length];
        for(int i=0;i<indices.length;i++){
            indices[i]=i;
        }
        /*
         *保持原数组不变，按照sums中的数据对indices进行排序（索引排序）
         * 例如：sums={10,5,8,3},indices={0,1,2,3}
         * 排序后indices={3,1,2,0}
         */
        Arrays.sort(indices, Comparator.comparingLong(i->sums[i]));
        for(int i:indices){
            System.out.print(i+" ");
        }
    }
}
