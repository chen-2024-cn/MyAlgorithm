package DFS_Backtracking;

import java.util.Scanner;

/**
 * 问题描述
 * N 架飞机准备降落到某个只有一条跑道的机场。其中第i 架飞机在T(i)时刻到达机场上空，到达时它的剩余油料还可以继续盘旋D(i)个单位时间，即它最早可以于
    T(i)时刻开始降落，最晚可以于T(i)+D(i)时刻开始降落。降落过程需要L(i)个单位时间。一架飞机降落完毕时，另一架飞机可以立即在同一时刻开始降落，
    但是不能在前一架飞机完成降落前开始降落.请你判断 N 架飞机是否可以全部安全降落。
 * 输入格式
 * 输入包含多组数据。
 * 第一行包含一个整数T，代表测试数据的组数。
 * 对于每组数据，第一行包含一个整数N。
 * 样例输入
 * 2
 * 3
 * 0 100 10
 * 10 10 10
 * 0 2 20
 * 3
 * 0 10 20
 * 10 10 20
 * 20 10 20
 * 样例输出
 * YES
 * NO
 */
public class AircraftLanding {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        while (T-- > 0) {
            int n=sc.nextInt();
            int[][] a=new int[n][3];
            for (int i = 0; i < n; i++) {
                a[i][0]=sc.nextInt();
                a[i][1]=sc.nextInt();
                a[i][2]=sc.nextInt();
            }
            boolean[] used=new boolean[n];
            System.out.println(dfs(a,0,0,used)?"YES":"NO");
        }
    }
    private static boolean dfs(int[][] a,int count,int last,boolean[] used){
        if (count == a.length) {//所有飞机已安排
            return true;
        }
        for (int i = 0; i < a.length; i++) {
            if(!used[i]) {
                int t = a[i][0];//飞机到达的时间
                int d = a[i][1];//飞机盘旋的时间
                int l = a[i][2];//飞机降落的时间
                int start = Math.max(t, last);//当前飞机的最早开始时间(last表示前一架飞机降落结束时间)
                if (start <= d + t) {// t<=start<=d+t
                    used[i] = true;
                    if (dfs(a, count + 1, last + l, used)) {
                        return true;
                    }
                    used[i] = false;
                }
            }
        }
        return false;
    }
}
