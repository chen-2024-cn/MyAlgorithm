package Sort;

public class Insert_sort {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5, 2, 2, 6, 3, 7};
        for (int i = 0; i <arr.length-1; i++) {
            int e = i;
            int temp = arr[e + 1];
            while (e >= 0) {
                if (arr[e] > temp) {
                    arr[e + 1] = arr[e];
                    e--;
                } else
                   break;
            }
            arr[e+1]=temp;
        }
        for (int j : arr) {
            System.out.print(j + " ");
        }
    }
}
