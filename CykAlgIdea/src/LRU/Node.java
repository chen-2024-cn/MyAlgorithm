package LRU;

public class Node {
    public int key, val;//键值对,方便查找节点,同时也方便删除节点,因为删除节点只需要知道键值对
    public Node next, prev;//双向链表,方便删除节点,同时也方便插入节点,因为插入节点只需要知道前一个节点和后一个节点,而删除节点只需要知道前一个节点和后一个节点

    public Node(int k, int v) {
        this.key = k;
        this.val = v;
    }

}
