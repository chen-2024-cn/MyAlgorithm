package Sort;

import java.util.*;

public class CustomSort {

    /**
     * 输入n个数，每行两个数，对这n行的两个数的差值进行降序排序
     *输出的每一行，第一个表示差值，第二个表示对应坐标
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] a = new int[n][2];
        List<int[]> list = new ArrayList<>();//调用外部类
        int[][] b=new int[n][2];//用二维数组表示
        for (int i = 0; i < n; i++) {
            a[i][0] = scanner.nextInt();
            a[i][1] = scanner.nextInt();
            b[i][0]=a[i][0]-a[i][1];
            b[i][1]=i;
            list.add(new int[] { a[i][0] - a[i][1], i });
        }
        System.out.println("二维数组表示的结果：");
        Arrays.sort(b,(x,y)->y[0]-x[0]);
        for(int[] x:b){
            System.out.println(x[0]+" "+x[1]);
        }
        System.out.println("外部类表示的结果：");
        list.sort((x,y)->y[0]-x[0]);
        for(int[] x:list){
            System.out.println(x[0]+" "+x[1]);
        }
    }
}
