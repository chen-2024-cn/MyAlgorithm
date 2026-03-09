package DynamicProgramming;

import java.util.Arrays;


/**
 * 输入一个dungeon数组，表示一个二维的地下城，每个格子内有一个数字，负数表示消耗生命值，正数表示增加生命值。
 * 初始位置是左上角，终点是右下角，每次只能向下或向右移动一格。
 * 求从左上角到右下角至少需要多少生命值才能到达终点。
 * 输入：dungeon = [[-2,-3,3],[-5,-10,1],[10,30,-5]]
 * 输出：7
 * 解释：如果我们按照以下路径走，需要的生命值最少：
 * 右 -> 右 -> 下 -> 下。
 * 生命值为 7。
 * 注意：
 * 1. 骑士的初始生命值为 1。
 * 2. 任何房间都可能对骑士的生命值造成威胁，也可能增加骑士的生命值，包括骑士进入的左上角房间以及公主被监禁的右下角房间。
 */
public class DungeonGames {

    static int[][] memo;

    public static int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dp(dungeon, 0, 0);

    }

    /**
     * 定义：从（i,j）到达右下角，至少需要的生命值是dp(dungeon,i,j)
     * 选择：从（i,j）向右走还是向下走？
     * 状态转移：
     * 如果从（i,j）向右走，至少需要的生命值是dp(dungeon,i,j+1)
     * 如果从（i,j）向下走，至少需要的生命值是dp(dungeon,i+1,j)
     * 我们希望dp(dungeon,i,j)的值尽可能小，从而让骑士能够有更多的生命值
     * 所以，dp(dungeon,i,j)=min(dp(dungeon,i,j+1),dp(dungeon,i+1,j))-dungeon[i][j]
     * 如果dungeon[i][j]>=0，说明从（i,j）向右或向下走都可以，不需要额外的生命值，所以dp(dungeon,i,j)=1
     * 如果dungeon[i][j]<0，说明从（i,j）向右或向下走都不行，需要额外的生命值，所以dp(dungeon,i,j)=min(dp(dungeon,i,j+1),dp(dungeon,i+1,j))-dungeon[i][j]
     * 注意：dp(dungeon,i,j)可能小于等于0，因为骑士的生命值至少为1，所以dp(dungeon,i,j)至少为1
     */
    static public int dp(int[][] dungeon, int i, int j) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        if (i == m - 1 && j == n - 1) {
            return dungeon[i][j] >= 0 ? 1 : -dungeon[i][j] + 1;
        }
        if (i == m || j == n) {
            return Integer.MAX_VALUE;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        //从（i,j）向右或向下走，取最小值
        //如果dungeon[i][j]>=0，说明从（i,j）向右或向下走都可以，不需要额外的生命值，所以dp(dungeon,i,j)=1
        //如果dungeon[i][j]<0，说明从（i,j）向右或向下走都不行，需要额外的生命值，所以dp(dungeon,i,j)=min(dp(dungeon,i,j+1),dp(dungeon,i+1,j))-dungeon[i][j]
        //注意：dp(dungeon,i,j)可能小于等于0，因为骑士的生命值至少为1，所以dp(dungeon,i,j)至少为1
        //所以，dp(dungeon,i,j)=min(dp(dungeon,i,j+1),dp(dungeon,i+1,j))-dungeon[i][j]
        int res = Math.min(dp(dungeon, i + 1, j), dp(dungeon, i, j + 1)) - dungeon[i][j];
        //骑士的生命值至少为1
        memo[i][j] = res <= 0 ? 1 : res;
        return memo[i][j];
    }
    public static void main(String[] args) {
        int[][] dungeon = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        System.out.println(calculateMinimumHP(dungeon));
    }
}
