package DynamicProgramming;

/**
 * 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
 * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
 * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
 * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
 * 你的目标是确切地知道 F 的值是多少。
 * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
 * 输入：K = 1, N = 2
 * 输出：2
 * 解释：
 * 鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
 * 否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
 * 如果它没碎，那么我们肯定知道 F = 2 。
 * 因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
 */
public class EggDrop {
    static int[][] memo;
    public static int superEggDrop(int K, int N){
        memo=new int[K+1][N+1];
        return dp(K,N);
    }
    /**
     * 定义：当你有 K 个鸡蛋时，面对 N 层楼，这个状态下最少地扔鸡蛋次数是 dp(K, N)。
     * 选择：去哪一层楼扔鸡蛋？
     * 状态转移：
     * 如果你从第 i 楼扔鸡蛋：
     * 如果鸡蛋碎了，你就去 i 楼下面的楼层继续测试，一共就有 i - 1 层楼，剩下 k - 1 个鸡蛋，所以用 dp(k - 1, i - 1) 表示；
     * 如果鸡蛋没碎，你就去 i 楼上面的楼层继续测试，一共就有 n - i 层楼，剩下 k 个鸡蛋，所以用 dp(k, n - i) 表示。
     * 由于我们要求的是最坏情况下扔鸡蛋的次数，所以鸡蛋在第 i 楼碎没碎，取决于哪种情况的结果更大：
     * 如果鸡蛋碎了，最坏情况下就是在第 i 楼下面扔，总共要扔 dp(k - 1, i - 1) + 1 次；
     * 如果鸡蛋没碎，最坏情况下就是在第 i 楼上面扔，总共要扔 dp(k, n - i) + 1 次。
     * 而我们要找的是最坏情况下扔鸡蛋次数的最小值，所以鸡蛋在第 i 楼扔，最坏情况下要扔 max(dp(k - 1, i - 1), dp(k, n - i)) + 1 次。
     * 最终的答案就是在所有 i 中，找到这个最小值：
     * dp(K, N) = min(max(dp(K - 1, i - 1), dp(K, N - i)) + 1) for 1 <= i <= N
     */
    static int dp(int K, int N){
        if(K==1){
            return N;
        }
        if(N==0){
            return 0;
        }
        if(memo[K][N]!=0){
            return memo[K][N];
        }
        //用二分搜索代替线性搜索
        int res=Integer.MAX_VALUE;
        int lo=1,hi=N;
        while(lo<=hi){
            int mid=(lo+hi)/2;
            int broken=dp(K-1,mid-1);//碎了
            int not_broken=dp(K,N-mid);//没碎
            //res=min(max(碎，没碎)+1)
            if(broken>not_broken){
                hi=mid-1;
                res=Math.min(res,broken+1);
            }else{
                lo=mid+1;
                res=Math.min(res,not_broken+1);
            }
        }
        memo[K][N]=res;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(superEggDrop(2,20));
    }
}
