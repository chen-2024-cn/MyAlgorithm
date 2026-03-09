package BinarySearch;

import java.util.*;

public class Group {
    /**
     * 你需要将 n 个元素分出 k 组，使得最大的极差尽可能小。你需要输出这个最小化后的值
     * 样例输入
     * 5 3
     * 8 4 3 6 9
     * 样例输出
     * 1
     * 说明
     * 样例分组情况：{ 3,4 } ，{ 6 } ，{ 8,9 } 。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] heights = new int[n];

        for (int i = 0; i < n; i++) {
            heights[i] = scanner.nextInt();
        }
        int result = getMinHeightDiff(heights, k);
        System.out.println(result);
    }

    private static int getMinHeightDiff(int[] heights, int k) {
        Arrays.sort(heights); // 将身高数组按升序排序

        int left = 0; // 最小极差的最小可能值
        int right = heights[heights.length - 1] - heights[0]; // 最小极差的最大可能值
        int result = right; // 最小极差的初始值

        //可以利用二分查找最小极差的范围，将搜索空间逐渐缩小，直到找到满足要求得极差
        while (left <= right) {
            int mid = (left + right) / 2; // 二分查找的中间值
            int count = 1; // 当前极差下的分组数量
            int i = 0;
            for (int j = 1; j < heights.length; j++) {
                if (heights[j] - heights[i] > mid) {
                    count++; // 超过当前极差，需要再分一个组
                    //当发现两个连续身高之差大于当前的极差时，我们需要将分组的起始位置更新为当前的位置。
                    // 这样做的目的是确保每个分组都是连续的，即每个分组中的身高差都小于或等于当前的极差。
                    i = j; // 更新分组的起始位置
                }
            }

            //如果count小于或等于目标分组数量k，则表示当前极差满足要求，更新result为mid，也就是result还能再小,同时缩小最小极差的范围，令right等于mid-1，继续向左边界查找更小的极差。
            //否则，当前极差不满足要求，增大极差，令left等于mid+1，继续向右边界查找更大的极差。
            if (count <= k) {
                result = mid; // 当前极差满足要求，更新最小极差的值
                right = mid - 1; // 继续查找更小的极差
            } else {
                left = mid + 1; // 当前极差不满足要求，增大极差
            }
        }
        return result;
    }
}
