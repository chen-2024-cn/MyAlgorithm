package DFS_Backtracking;

import java.util.Scanner;

public class SpellNumbers {
    //拼数字
    //输入两个整数n2和m3，要求拼出一个长度为n2+m3的数字，要求数字中只包含2和3，并且2的数量不能超过n2，3的数量不能超过m3，
    // 并且拼出来的数字是2023的倍数。使得拼出的数字尽可能大，输出这个数字，如果不存在满足条件的数字，输出-1
    //输入：2 2
    //输出：-1
    //输入：2 8
    //输出：2233333333
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n2=sc.nextInt();
        int m3=sc.nextInt();
        int total=n2+m3;
        StringBuilder sb=new StringBuilder();
        backtrack(n2,m3,sb,total,0);
        System.out.println(res);
    }
    static String res="-1";
    private static void backtrack(int n2, int m3, StringBuilder sb, int total, int currentMode) {
        if(!res.equals("-1")){//如果已经找到一个结果，就不再继续搜索
            return;
        }
        if(total==0){
            if(currentMode==0){
                res=sb.toString();
            }
            return;
        }
        //从大到小搜索
        if(m3>0){
            sb.append("3");
            int  newMode=(currentMode*10+3)%2023;
            backtrack(n2,m3-1,sb,total-1,newMode);
            sb.deleteCharAt(sb.length()-1);
        }
        if(n2>0){
            sb.append("2");
            int newMode=(currentMode*10+2)%2023;
            backtrack(n2-1,m3,sb,total-1,newMode);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
