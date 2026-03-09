package SlidingWindow;
public class FixedSonStrLen {
    //求固定长度的子串中包含的元音字母的数量
    public static void main(String[] args) {
        String s = "abcioidef";
        int k = 3;
        char[] ch = { 'a', 'e', 'i', 'o', 'u' };
        int right = 0, left = 0;
        int valid = 0, max = Integer.MIN_VALUE;
        while (right < s.length()) {
            char cur = s.charAt(right);
            right++;
            for (int c : ch) {
                if (c == cur)
                    valid++;
            }
            if (right - left - 1 == k) {
                char cur2 = s.charAt(left);
                left++;
                for (int c : ch) {
                    if (c == cur2)
                        valid--;
                }
            }
            max = Math.max(valid, max);
        }
        System.out.println(max);
    }
}
