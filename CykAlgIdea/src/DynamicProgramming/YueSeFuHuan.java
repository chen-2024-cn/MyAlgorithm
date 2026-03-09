package DynamicProgramming;

import java.util.ArrayList;
import java.util.Scanner;

public class YueSeFuHuan {
    //（约瑟夫环的变形，起点为m）n个数，第一次删除m，后面每个k个数删除一个数，求最后被删除的数
    //例如：1 2 3 4 5 m=2,k=2 第一次删3，第二次删5，第三次删2，第四次删1 最后返回4
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();//总共n个数
        int m=sc.nextInt();//从m开始删除(m>=1)
        int k=sc.nextInt();//每报k个数删一个
        if(m<1){
            System.out.println("必须从1开始");
            return;
        }
        int[] f=new int[n+1];
        for(int i=2;i<=n;i++){//f[i]表示从0开始，每隔k个数删除一个而留下的最后一个数
            f[i]=(f[i-1]+k)%i;
        }
        //第一种方法，从1开始删
        System.out.println(f[n]+1);
        //第二种方法，从k开始删
        // 调整起始位置k（转换为从0开始）
        int offset = (m - 1) % n;
        System.out.println( (f[n] + offset) % n + 1); // 结果转为从1开始
        //第三种方法，从1开始删
        System.out.println(josephus(n,k));

    }
    //经典约瑟夫环问题，起点是0
    public static int josephus(int n,int k){
        ArrayList<Integer> list= new ArrayList<>();
        for (int i = 1; i <=n ; i++) {
            list.add(i);
        }
        int index=0;
        while (list.size() != 1) {
            index = (index+k-1)%list.size();
            list.remove(index);
        }
        return list.get(0);
    }
}
