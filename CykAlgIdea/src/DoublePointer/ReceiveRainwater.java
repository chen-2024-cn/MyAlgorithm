package DoublePointer;

public class ReceiveRainwater {
    //接雨水问题
    public static void main(String[] args) {
        int[] height={0,1,0,2,1,0,1,3,2,1,2,1};
        int left=0,right=height.length-1;
        int rightMax=height[height.length-1];
        int leftMax=height[0];
        int sum=0;
        while(left<right){
            rightMax=Math.max(height[right], rightMax);
            leftMax=Math.max(height[left], leftMax);
            if (height[left]<height[right]) {
                sum+=leftMax-height[left];
                left++;
            }else{
                sum+=rightMax-height[right];
                right--;
            }
        }
        System.out.println(sum);
    }
}
