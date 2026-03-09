package DynamicProgramming;

import java.util.ArrayList;
import java.util.List;

/**
 *给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
 *示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
 *      注意，你可以重复使用字典中的单词。
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 */
public class SplitWord {
    public static void main(String[] args) {
        String str="catsandog";
        List<String> wordDict=new ArrayList<>();
        wordDict.add("cats");
        wordDict.add("dog");
        wordDict.add("sand");
        wordDict.add("and");
        wordDict.add("cat");
        boolean[] dp=new boolean[str.length()+1];
        dp[0]=true;
        for (int i = 0; i < str.length(); i++ ) {
            for (int j =i+1; j<=str.length() ; j++) {
                if (dp[i] && wordDict.contains(str.substring(i, j))) {
                    dp[j] = true;
                }
            }
        }
        System.out.println(dp[str.length()]);
    }
}
