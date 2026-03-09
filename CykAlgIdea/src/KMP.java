import java.util.Arrays;

public class KMP {
    public static void main(String[] args) {
        String t = "ABCABCAAABBBCCC";
        String s = "AAABB";
        System.out.println(serchKmp(t, s));
    }

    /**
     * 获取模式串的next数组
     *
     * @param s 模式串
     * @return next数组 表示每个位置的最长公共前后缀长度
     */
    public static int[] getNext(String s) {
        int[] next = new int[s.length()];
        int i = 1;
        int preLen = 0;//前后缀长度
        while (i < s.length()) {
            if (s.charAt(i) == s.charAt(preLen)) {
                preLen++;
                next[i] = preLen;
                i++;
            } else {
                if (preLen != 0) {
                    preLen = next[preLen - 1];
                } else {
                    next[i] = 0;
                    i++;
                }
            }
        }
        return next;
    }

    /**
     * KMP算法主函数
     *
     * @param t 主串
     * @param s 模式串
     * @return 模式串在主串首次出现的位置，未找到返回-1
     */
    public static int serchKmp(String t, String s) {
        int[] next = getNext(s);
        System.out.println(Arrays.toString(next));
        int i = 0;//主串指针
        int j = 0;//模式串指针
        while (i < t.length()) {
            if (t.charAt(i) == s.charAt(j)) {
                i++;
                j++;
                if (j == s.length()) {
                    return i - j;
                }
            } else {
                if (j > 0) {
                    j = next[j - 1];
                } else {
                    i++;
                }
            }
        }
        return -1;
    }
}
