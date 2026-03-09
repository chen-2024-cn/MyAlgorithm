package DoublePointer;

import java.util.Scanner;

/**
 * 输入：s = "aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 * 采用中心扩展法
 */
public class LongestPalindrome {
    static int count;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s=scanner.next();
        for(int i=0;i<s.length();i++){//i is mid-point
            extendPalindrome(s, i, i);//odd length
            extendPalindrome(s, i, i+1);//even length
        }
        System.out.println(count);
    }
    private static void extendPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
    }
}
