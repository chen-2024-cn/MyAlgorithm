package DFS_Backtracking;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 划分k个相等子集
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * 输出： True
 * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 */
public class DivideEqualSubsets {
    static boolean canPartitionKSubsets(int[] nums, int k) {
        if(k>nums.length){
            return false;
        }
        int sum=0;
        for(int v:nums){
            sum+=v;
        }
        if(sum%k!=0){
            return false;
        }
        int[] bucket=new int[k];//k个桶，每个桶的和
        int target=sum/k;//每个桶的目标和
        //降序排列(减少时间复杂度)
        Arrays.sort(nums);
        for(int i=0,j=nums.length-1;i<j;i++,j--){
            //交换nums[i]和nums[j]
            int temp=nums[i];
            nums[i]=nums[j];
            nums[j]=temp;
        }
        //从第0个桶开始，第0个元素开始，每个桶的和为0
        return backtrack1(nums,0,bucket,target);
    }
    /**
     * 方法一
     * 回溯算法：穷举所有可能的组合
     * 时间复杂度：O(k^n)
     * 空间复杂度：O(n)
     * 从数字的角度来看，每个数字都要选择进入到k个桶中的某一个
     * 而对于桶，是有k种选择的，k就是递归树的高度，而每个桶的和是不一样的，所以不能简单的用一个变量来表示
     * 所以用一个数组来表示k个桶的和
     */
    static boolean backtrack1(int []nums,int index,int[] bucket,int target){
        if(index==nums.length){
            //检查所有桶的和是否都等于target
            for(int i=0;i<bucket.length;i++){
                if(bucket[i]!=target){
                    return false;
                }
            }
            return true;//所有桶的和都等于target
        }
        //穷举nums[index]可能装入的桶
        for(int i=0;i<bucket.length;i++){
            //剪枝
            if(bucket[i]+nums[index]>target){
                continue;
            }
            //将nums[index]装入bucket[i]
            bucket[i]+=nums[index];
            //递归穷举下一个数字的选择
            if(backtrack1(nums,index+1,bucket,target)){
                return true;
            }
            //撤销选择
            bucket[i]-=nums[index];
        }
        //nums[index]装入哪个桶都不行
        return false;
    }

    /**
     * 方法二
     * 回溯算法+备忘录
     * 时间复杂度：O(k*2^n)
     * 空间复杂度：O(2^n)
     * 每个桶要遍历n个数字，选择或不选择，总共有2^n种状态
     * 而k个桶就是k*2^n
     * 用一个哈希表来记录状态，避免冗余计算
     * 用一个二进制数来表示当前的状态
     * 1表示这个数字已经被装入了某个桶中，0表示这个数字还没有被装入任何桶中
     * 例如：101010表示第0，2，4个数字已经被装入了某个桶中，第1，3，5个数字还没有被装入任何桶中
     * 用一个整数来表示当前的状态
     */
   static HashMap<Integer,Boolean> memo=new HashMap<>();//记录状态
    static boolean backtrack2(int k,int bucket,int[] nums,int start,int used, int target) {
        //base case
        if (k == 0) {
            //所有桶都被装满了，而且nums一定全部用完了
            return true;
        }
        if (bucket == target) {
        //装满了当前桶，递归穷举下一个桶的选择
        //让下一个桶从nums[0]开始选数字
            boolean res = backtrack2(k - 1, 0, nums, 0, used, target);
            //将当前状态存入备忘录
            memo.put(used, res);
            return res;
        }
        if(memo.containsKey(used)){
            //避免冗余计算
            return memo.get(used);
        }
        //从start开始向后探查有效的nums[i]装入当前桶
        for (int i = start; i < nums.length; i++) {
            //剪枝
            if (((used >> i) & 1) == 1) {//nums[i]已经被装入别的桶中,判断第i位是否为1
                continue;
            }
            if (nums[i] + bucket > target) {
                continue;
            }
            //将nums[i]装入当前桶
            used |= 1 << i; //将第i位标记为1
            bucket += nums[i];
            //递归穷举下一个数字是否装入当前桶
            if (backtrack2(k, bucket, nums, i + 1, used, target)) {
                return true;
            }
            //撤销选择
            used ^= 1 << i; //将第i位标记为0
            bucket -= nums[i];
        }
        //穷举所有可能，都无法装满当前桶
        return false;
    }
    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        int k = 4;
        System.out.println(canPartitionKSubsets(nums, k));
    }
}
