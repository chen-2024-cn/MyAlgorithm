package BFS;
import java.util.*;
public class ShortestMazePath {
    public static void main(String[] args) {
        int[][] maze = {
                {0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0}
        };
        int pathLength = bfs(maze);
        if (pathLength != -1) {
            System.out.println("最短路径长度: " + pathLength);
            System.out.print("路径: ");
            printPath(maze, maze.length - 1, maze[0].length - 1);
        } else {
            System.out.println("没有找到路径");
        }
    }

    private static int bfs(int[][] a) {
        Queue<int[]> queue = new LinkedList<>();
        int[][] visited=new int[a.length][a[0].length];
        queue.offer(new int[]{0, 0});
        visited[0][0] = 1; // 标记为已访问
        int path = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];

                if (x == a.length - 1 && y == a[0].length - 1) {
                    return path;
                }

                for (int[] direction : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) {
                    int newX = x + direction[0];
                    int newY = y + direction[1];

                    if (newX >= 0 && newX < a.length && newY >= 0 && newY < a[0].length && visited[newX][newY] == 0) {
                        queue.offer(new int[]{newX, newY});
                        visited[newX][newY] = 1; // 标记为已访问
                    }
                }
            }
            path++;
        }
        return -1; // 没有找到路径
    }

    private static void printPath(int[][] a, int x, int y) {
        if (x == 0 && y == 0) { // 起点
            System.out.print("(0, " + y + ")");
            return;
        }
        for (int[] direction : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) {
            int prevX = x - direction[0];
            int prevY = y - direction[1];
            if (prevX >= 0 && prevX < a.length && prevY >= 0 && prevY < a[0].length && a[prevX][prevY] == 0) {
                printPath(a, prevX, prevY);
                System.out.print(" -> (" + x + ", " + y + ")");
                break;
            }
        }
    }
}
