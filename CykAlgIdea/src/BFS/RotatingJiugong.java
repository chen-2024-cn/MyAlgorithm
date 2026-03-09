package BFS;

import java.util.*;


/**
 * 给定一个 3×3 的九宫格，每个格子内分别含有一个数字，每个格子里的数字互不相同。每步我们可以选择任意一个 2×2 的区域将其顺时针旋转
 * 问最少需要几步才能将给定的状态旋转为如下目标状态：
 * 1 2 3
 * 4 5 6
 * 7 8 9
 * 输入格式
 * 输入的第一行包含一个整数 T 表示询问的组数。
 * 接下来依次输入每组询问。
 * 每组询问包含三行，每行包含三个数，表示询问的九宫格的状态。
 * 输出格式
 * 输出 T 行，每行包含一个整数表示本次询问的答案。
 */
public class RotatingJiugong {
    public static int[][] rotation = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();
        String target = "123456789";
        while (T-- > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                String str = sc.nextLine().replaceAll("\\s+", "");
                sb.append(str);
            }
            String a = sb.toString();
            System.out.println(bfs(a, target));
        }
    }

    private static int bfs(String str, String target) {
        if (str.equals(target)) {
            return 0;
        }
        Queue<String> q = new LinkedList<>();
        Map<String,Integer> visited = new HashMap<>();
        q.offer(str);
        visited.put(str,0);
        while (!q.isEmpty()) {
            String current = q.poll();
            for (int[] rot : rotation) {
                String next = rotate(current, rot[0], rot[1]);
                int currentSteps = visited.get(current);
                if (!visited.containsKey(next)) {
                    if (next.equals(target)) {
                        return currentSteps + 1;
                    }
                    q.offer(next);
                    visited.put(next,currentSteps+1);
                }
            }
        }
        return -1;
    }

    private static String rotate(String str, int row, int col) {
        char[] arr = str.toCharArray();
        int p1 = row * 3 + col;
        int p2 = row * 3 + col + 1;
        int p3 = (row + 1) * 3 + col;
        int p4 = (row + 1) * 3 + col + 1;
        char temp = arr[p1];
        arr[p1] = arr[p3];
        arr[p3] = arr[p4];
        arr[p4] = arr[p2];
        arr[p2] = temp;
        return new String(arr);
    }
}
