package BFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class slide_Puzzle {
    public static void main(String[] args) {
        int[][] a = {{4, 1, 3},
                {7, 2, 5},
                {8, 0, 6}};
        System.out.println(smallDepth(a,"123456780"));

    }

    static int[][] dir = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

    public static int smallDepth(int[][] a, String target) {
        int row = a.length, col = a[0].length;
        Queue<String> q = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int[] ints : a) {
            for (int j = 0; j < col; j++) {
                sb.append(ints[j]);
            }
        }
        if (sb.toString().equals(target)) return 0;
        q.offer(sb.toString());
        visited.add(sb.toString());
        int step = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            step++;
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();
                int zero=cur.indexOf('0');
                int r=zero/col,c=zero%col;//0的下标
                for (int[] index : dir) {//遍历四个方向
                    int newRow=r+index[0],newCol=c+index[1];
                    if (newRow<0||newCol<0||newRow>=row||newCol>=col)continue;
                    String newState=swap(cur.toCharArray(),newRow*col+newCol,zero);//交换与0相邻的元素
                    if(newState.equals(target))return step;
                    if (!visited.contains(newState)){
                        q.offer(newState);
                        visited.add(newState);
                    }
                }
            }
        }
        return -1;
    }

    public static String swap(char[] c, int i, int j) {
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
        return new String(c);
    }
}
