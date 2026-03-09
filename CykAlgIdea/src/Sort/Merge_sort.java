package Sort;

public class Merge_sort {
    /*
    public static void process(int[] arr, int L, int R){
        if (L == R) {
            return;
        }
        int mid=(L+R)>>1;
        process(arr,L,mid);
        process(arr,mid+1,R);
        marge(arr,L,mid,R);
    }
    public static void marge(int[] arr, int L, int mid,int R){
        int[] help=new int[R-L+1];//辅助数组
        int p=L;
        int q=mid+1;
        int i=0;
        while (p <= mid && q <= R) {
            help[i++]=arr[p]<=arr[q]?arr[p++] : arr[q++];
        }
        while (p<=mid){
            help[i++]=arr[p++];
        }
        while (q <= R) {
            help[i++]=arr[q++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[L+j]=help[j];
        }
    }

    public static void main(String[] args) {
        int[] a={4,3,2,6,34,1,1,5,98};
        process(a,0,a.length-1);
        for (int x:a){
            System.out.print(x+" ");
        }
    }
    */

//求小和问题

    public static int smallSum(int[] a){
        if (a == null || a.length < 2) {
            return 0;
        }

        return process(a,0,a.length-1);
    }
    public static int process(int[] arr, int L, int R){
        if (L == R) {
            return 0;
        }
        int mid=(L+R)>>1;
       return process(arr,L,mid)+
        process(arr,mid+1,R)+
        marge(arr,L,mid,R);
    }
    public static int marge(int[] arr, int L, int mid,int R){
        int[] help=new int[R-L+1];//辅助数组
        int p=L;
        int q=mid+1;
        int i=0,res=0;
        while (p <= mid && q <= R) {
            res+=arr[p]<=arr[q]?(R-q+1)*arr[p]:0;
            help[i++]=arr[p]<=arr[q]?arr[p++] : arr[q++];
        }
        while (p<=mid){
            help[i++]=arr[p++];
        }
        while (q <= R) {
            help[i++]=arr[q++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[L+j]=help[j];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a={4,3,2,6,34,1,1,5,98};
        System.out.println(smallSum(a));
        for (int j:a){
            System.out.print(j+" ");
        }
    }


}
