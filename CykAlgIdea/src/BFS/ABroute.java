package BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 有一个由 N×M 个方格组成的迷宫，每个方格写有一个字母 A 或者 B。小蓝站在迷宫左上角的方格，目标是走到右下角的方格。他每一步可以移动到上下左右相邻的方格去。
 * 由于特殊的原因，小蓝的路线必须先走
 * K 个 A 格子、再走 K 个 B 格子、再走 K 个 A 格子、再走 K 个 B 格子......如此反复交替。
 * 请你计算小蓝最少需要走多少步，才能到达右下角方格? 注意路线经过的格子数不必一定是K 的倍数，即最后一段 A 或 B 的格子可以不满
 * K 个。起点保证是 A 格子。
 * 输入
 * 第一行包含三个整数 N,M,K。
 * 接下来 N 行，每行 M 个字母，字母为 A 或 B。
 * 输出
 * 输出一个整数，表示答案。
 * 样例输入
 *4 4 2
 *AAAB
 *ABAB
 *BBAB
 *BAAA
 * 样例输出
 * 8
 * 提示
 * 【样例说明】
 * 每一步方向如下：下右下右上右下下；路线序列：AABBAABBA
 */
public class ABroute {
    static class State {
        int x, y;           // 当前位置
        char currentType;    // 当前段类型(A/B)
        int currentCount;    // 当前段已走格子数
        int steps;           // 总步数

        State(int x, int y, char currentType, int currentCount, int steps) {
            this.x = x;
            this.y = y;
            this.currentType = currentType;
            this.currentCount = currentCount;
            this.steps = steps;
        }
    }

    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        char[][] grid = new char[n][m];

        for (int i = 0; i < n; i++) {
            String row = sc.next();
            grid[i] = row.toCharArray();
        }

        // 访问记录结构: [x][y][currentType][currentCount]
        // 0代表'A', 1代表'B'
        boolean[][][][] visited = new boolean[n][m][2][k+1];
        Queue<State> queue = new LinkedList<>();

        // 起点是A，当前段计数为1
        queue.add(new State(0, 0, 'A', 1, 1));
        visited[0][0][0][1] = true;

        int result = -1;

        while (!queue.isEmpty()) {
            State curr = queue.poll();

            // 检查是否到达终点
            if (curr.x == n-1 && curr.y == m-1) {
                result = curr.steps-1;
                break;
            }

            for (int[] dir : dirs) {
                int nx = curr.x + dir[0];
                int ny = curr.y + dir[1];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                char nextType = grid[nx][ny];
                int newCount;
                char newCurrentType;

                if (nextType == curr.currentType) {
                    // 同类型，继续当前段
                    newCount = curr.currentCount + 1;
                    newCurrentType = curr.currentType;
                } else {
                    // 不同类型，检查当前段是否已满足K个
                    if (curr.currentCount < k) continue;
                    newCount = 1;
                    newCurrentType = nextType;
                }

                // 当前段不能超过K个
                if (newCount > k) continue;

                int typeIndex = newCurrentType == 'A' ? 0 : 1;

                if (!visited[nx][ny][typeIndex][newCount]) {
                    visited[nx][ny][typeIndex][newCount] = true;
                    queue.add(new State(nx, ny, newCurrentType, newCount, curr.steps + 1));
                }
            }
        }

        System.out.println(result);
    }
}
