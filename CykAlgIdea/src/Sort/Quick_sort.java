package Sort;

public class Quick_sort {
    public static void main(String[] args) {
        int[] a = {1,4,6,2,6,2,3,2,9};
        quick_sort(a,0,a.length-1);
        for(int i:a){
            System.out.print(i+" ");
        }
    }

    public static void quick_sort(int[] a, int start, int end) {
        if (start>end)return;
        int index=partition(a,start,end);
        quick_sort(a,start,index-1);
        quick_sort(a,index+1,end);
    }
    public static int partition(int[] a, int start, int end){
        int i=start-1;
        int index=a[end];
        for(int j=start;j<end;j++){
            if (a[j] <= index) {
                i++;
                swap(i,j,a);
            }
        }
        swap(i+1,end,a);
        return i+1;
    }
    public static void swap(int a,int b,int[] arr){
        int temp=arr[a];
        arr[a]=arr[b];
        arr[b]=temp;
    }
}
