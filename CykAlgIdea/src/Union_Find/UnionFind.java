package Union_Find;

public class UnionFind {
    private static int[] parent;//存储每个节点的父节点
    private int[] rank;//存储树的高度
    private int count;//连通分量个数

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        count = n;
        //初始化每个父节点指向自己
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;//每个连通分量高度为1
        }
    }

    public int find(int p) {
        //递归压缩
//        if(p!=parent[p]){//直接将路径压缩为两层
//            p=find(parent[p]);
//        }
//        return parent[p];
        //路径压缩
//        while(p!=parent[p]){//将路径压缩一半
//            parent[p]=parent[parent[p]];
//            p=parent[p];
//        }
//        return p;
        //迭代实现的路径压缩（最优版）
        int root = p;
        while (root != parent[root]) {//先找到根节点
            root = parent[root];
        }
        while (p != root) {//再次遍历，将路径压缩为两层
            int next = parent[p];
            parent[p] = root;
            p = next;
        }
        return root;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootQ == rootP) return;//p和q在同一集合里
        //将较矮地树合并到较高地树下
        if (rank[rootP] >= rank[rootQ]) {
            parent[rootQ] = rootP;
            rank[rootP] += rank[rootQ];
        } else if (rank[rootQ] > rank[rootP]) {
            parent[rootP] = rootQ;
            rank[rootP] += rank[rootQ];
        }

        count--;//连通分量减少
    }

    //检查两个连通分量是否连通
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    //返回连通分量数量
    public int count() {
        return count;
    }

    public static void main(String[] args) {
        UnionFind unionFind = new UnionFind(10);
        unionFind.union(1, 2);
        unionFind.union(3, 4);
        unionFind.union(1, 4);
        System.out.println(unionFind.isConnected(2, 3));
        System.out.println(unionFind.isConnected(2, 5));
        System.out.println(unionFind.count());
    }
}
