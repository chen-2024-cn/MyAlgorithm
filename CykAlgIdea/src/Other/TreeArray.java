package Other;

public class TreeArray {
    static class fenwickTree{
        int size;
        int[] tree;

        public fenwickTree(int size){
            this.size=size;
            this.tree=new int[size+1];
        }
        private int lowbit(int x){
            return x&-x;
        }

        /**
         * 单点更新操作，给index位置的元素增加delta
         * @param index
         * @param delta
         */
        public void update(int index,int delta){
            if (index < 1 || index > size) {
                throw new IllegalArgumentException("Index out of bounds");
            }
            while (index <= size) {//从index开始，沿着父节点路径向上更新
                tree[index]+=delta;
                index+=lowbit(index);
            }
        }

        /**
         * 前缀查询，前index个元素的和
         */
        public int query(int index){
            if (index < 1 || index > size) {
                throw new IllegalArgumentException("Index out of bounds");
            }
            int sum=0;
            while (index > 0) {//沿着子节点路径向下累加
                sum+=tree[index];
                index-=lowbit(index);
            }
            return sum;
        }

        /**
         * 区间查询，查询[l,r]区间内的元素和
         */
        public int rangeQuery(int l, int r){
            if (l < 1 || r > size || l > r) {
                throw new IllegalArgumentException("Invalid range");
            }

            return query(r)-query(l-1);
        }

        /**
         * 区间更新：给[l,r]区间增加delta
         */
        public void rangeUpdate(int l,int r,int delta){
            update(l,delta);
            if (r + 1 <= size) {
            update(r+1,-delta);
            }
        }


        public static fenwickTree build(int[] data){
            fenwickTree ft=new fenwickTree(data.length);
            for (int i = 0; i < data.length; i++) {
                ft.update(i+1,data[i]);//树状数组一般范围是从1到based
            }
            return ft;
        }
    }

    public static void main(String[] args) {
        int[] a={2,5,7,8,3,2,7,5};
       fenwickTree ft= fenwickTree.build(a);
        System.out.println(ft.rangeQuery(2,4));
        ft.rangeUpdate(2,4,5);
        System.out.println(ft.rangeQuery(2,4));
        System.out.println(ft.query(6));
    }
}
