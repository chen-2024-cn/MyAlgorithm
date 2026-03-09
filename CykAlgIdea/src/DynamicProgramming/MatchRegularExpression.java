package DynamicProgramming;

import java.util.Arrays;

/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * 输入：s = "aa", p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 * 输入：s = "aa", p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 输入：s = "ab", p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 输入：s = "aab", p = "c*a*b"
 * 输出：true
 * 解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 输入：s = "mississippi", p = "mis*is*p*."
 * 输出：false
 */
public class MatchRegularExpression {
    int[][] memo;
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        //i,j分别是s,p的索引
        return dp(s, 0, p, 0);
    }
    /**
     * 定义：如果s[i..]可以匹配p[j..]，则返回true，否则返回false
     * 选择：如果p[j+1]是*，则可以匹配0次或多次p[j]，如果p[j+1]不是*，则只能匹配一次p[j]
     * 状态转移：
     * 如果p[j+1]是*，则可以匹配0次或多次p[j]，如果p[j]和s[i]匹配，则可以匹配0次或多次p[j]，如果p[j]和s[i]不匹配，则只能匹配0次p[j]
     * 如果p[j+1]不是*，则只能匹配一次p[j]，如果p[j]和s[i]匹配，则可以匹配一次p[j]，如果p[j]和s[i]不匹配，则返回false
     */
    boolean dp(String s, int i, String p, int j) {
        int m = s.length(), n = p.length();
        //base case
        if (j == n) {
            return i == m;
        }
        //如果s已经匹配完，但是p还有剩余，需要判断p是否是a*b*c*的形式
        if (i == m) {
            //如果p的长度是奇数，那么肯定不匹配
            //如果p的长度是偶数，那么需要判断p[j+1]是否是*，如果是*，则可以匹配0次或多次p[j]，如果不是*，则不匹配
            if ((n - j) % 2 == 1) {
                return false;
            }
            //如果p[j+1]是*，则可以匹配0次或多次p[j]，如果p[j+1]不是*，则不匹配
            for (; j + 1 < n; j += 2) {
                if (p.charAt(j + 1) != '*') {
                    return false;
                }
            }
            return true;
        }
        if (memo[i][j] != -1) {
            return memo[i][j] == 1;
        }
        boolean res ;
        //记录p[j+1]是否为*
        boolean b = j < n - 1 && p.charAt(j + 1) == '*';
        //.通配符是万能的
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
            //匹配成功
            if (b) {
                //有通配符*，可以匹配0次或多次p[j]
                res = dp(s, i, p, j + 2) || dp(s, i + 1, p, j);
            }else{
                //没有通配符*，只能匹配一次p[j]
                res = dp(s, i + 1, p, j + 1);
            }
        }else{
            //匹配失败
            if (b) {
                //有通配符*，只能匹配0次p[j]
                res = dp(s, i, p, j + 2);
            }else{
                //没有通配符*，匹配失败
                res = false;
            }
        }
        memo[i][j] = res ? 1 : 0;
        return res;
    }
    public static void main(String[] args) {
        MatchRegularExpression m = new MatchRegularExpression();
        System.out.println(m.isMatch("baa", "a*"));
    }
}
