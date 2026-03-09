import java.util.Arrays;
import java.util.Scanner;

public class Floyd {
    static class Graph{
        int u;
        int v;
        int ver;//权重
        public  Graph(int u, int v, int ver){
            this.v=v;
            this.u=u;
            this.ver=ver;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        Graph[] floyd =new Graph[n];
        for(int i=0;i<n;i++) {
            floyd[i]= new Graph(sc.nextInt(),sc.nextInt(),sc.nextInt());
        }
        int[][] dp=new int[n+1][n+1];//dp[i][j]表示从i到j的最短路径
        final int INF=Integer.MAX_VALUE/2;//防止整数溢出
        for (int i = 1; i <=n ; i++) {
            for (int j = 1; j <=n ; j++) {
                if (i != j) {
                    dp[i][j]=INF;
                }
            }
        }
        //v,u 表示相邻两个节点，ver 表示两节点之间的权值
        for (int i = 1; i <=n ; i++) {
            int v=floyd[i-1].v;
            int u=floyd[i-1].u;
            int ver=floyd[i-1].ver;
            dp[v][u]=ver;
            dp[u][v]=ver;//表示无向图，如果是有向图可移除
        }
        //动态规划最短路径
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <=n ; i++) {
                for (int j = 1; j <= n; j++) {
                    dp[i][j]=Math.min(dp[i][j],dp[i][k]+dp[k][j]);
                }
            }
        }
        //结点到它自己的权值为0
        for (int i = 1; i <=n; i++) {
            dp[i][i]=0;
        }
        System.out.println(dp[1][n]==INF?"不存在此路径":dp[1][n]);
    }
}
