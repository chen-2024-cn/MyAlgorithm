package LRU;

public class DoubleList {
    //头节点，尾节点
    private final Node head;
    private final Node tail;
    //链表元素数
    private int size;

    public DoubleList() {
        //初始化双向链表的数据
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    //在链表尾部部添加节点x，时间O(1)
    public void addLast(Node x) {
        x.prev = tail.prev;
        x.next = tail;
        tail.prev.next = x;
        tail.prev = x;
        size++;
    }

    //删除链表中的x节点（x一定存在）
    //由于是双链表且给的是目标Node节点，时间O(1)
    public void remove(Node x) {
        x.prev.next = x.next;
        x.next.prev = x.prev;
        size--;
    }

    //删除链表中第一个节点，并返回该节点，时间O(1)
    public Node removeFirst() {
        if (head.next == tail) {
            return null;
        }
        Node first = head.next;
        remove(first);
        return first;
    }

    //返回链表长度，时间O(1)
    public int size() {
        return size;
    }

    //打印链表
    public String toString() {
        Node p = head.next;
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        while (p != tail) {
            if (p.next == tail) {
                sb.append(p.key).append("=").append(p.val);
            } else {
                sb.append(p.key).append("=").append(p.val).append(", ");
            }
            p = p.next;

        }
        sb.append("}");
        return sb.toString();
    }

}
