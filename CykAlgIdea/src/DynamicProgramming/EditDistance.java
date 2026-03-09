package DynamicProgramming;

public class EditDistance {
    //求将str1转换为str2的最小操作次数
    public static void main(String[] args) {
        String str1 = "word1";
        String str2 = "word2";
        //dp[i][j]表示将str1的前i个字符转换为str2的前j个字符的最少转换次数
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        //删除str2所有字符
        for (int i = 0; i <= str1.length(); i++) {
            dp[i][0] = i;
        }
        //插入str2所有字符
        for (int j = 0; j <= str2.length(); j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) != str2.charAt(j - 1)) {
                    //字符不同，取插入、删除、替换的最小值+1
                    dp[i][j] = 1 + Math.min(dp[i - 1][j]/*删除*/, Math.min(dp[i][j - 1]/*插入*/, dp[i - 1][j - 1]/*替换*/));
                } else {
                    //字符相同，继承左上方值
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        System.out.println(dp[str1.length()][str2.length()]);
    }
}
