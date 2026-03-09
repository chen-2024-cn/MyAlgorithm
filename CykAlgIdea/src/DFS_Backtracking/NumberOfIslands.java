package DFS_Backtracking;

public class NumberOfIslands {
    public static void main(String[] args) {
        int[][] nums={{1,1,0,0,0},
                      {1,1,0,0,0},
                      {0,0,1,0,0},
                      {0,0,0,1,1}};
        int count=0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                if(nums[i][j]==1){
                    dfs(nums,i,j);
                    count++;
                }
            }
        }
        System.out.println(count);
    }
    private static void dfs(int[][] a, int i, int j){
        if(i<0||j<0||i>=a.length||j>=a[0].length||a[i][j]==0)return;
        a[i][j]=0;
        dfs(a,i+1,j);
        dfs(a,i,j+1);
        dfs(a,i-1,j);
        dfs(a,i,j-1);
    }
}
