package LRU;


/**LRU工作原理
 新访问的数据放在缓存顶部
 每次访问都将数据移到顶部
 当缓存满时，淘汰最底部的数据（最久未被访问的）
 实现方式：通常使用双向链表+哈希表，链表头部是最新访问的，尾部是最久未用的
 */


public class Main {
    public static void main(String[] args) {
        //cache相当于一个队列，最近使用的放在队列头，最久未使用的放在队列尾
        //cache的容量有限，当cache满时，删除最久未使用的元素
        //当cache中有某个元素被访问时，将该元素移到队列头
        //当cache中没有某个元素时，将该元素插入到队列头
        //当cache中有某个元素被插入时，将该元素移到队列头
        //当cache中有某个元素被删除时，将该元素从队列中删除
        LRUCache cache = new LRUCache(2);
        /* cache.put(1, 1);
         * cache.put(2, 2);
         */
        cache.put(1, 1);//cache=[(1,1)]
        cache.put(2, 2);//cache=[(1,1),(2,2)]
        cache.put(3, 3);//cache=[(1,1),(3,3)]
        cache.put(2,5);
        System.out.println(cache.get(2));
        System.out.println(cache);

       System.out.println("=======================");
       LRUCache_LinkedHashMap cache2 = new LRUCache_LinkedHashMap(2);
       cache2.put(1, 1);//cache=[(1,1)]
       cache2.put(2, 2);//cache=[(1,1),(2,2)]

        cache2.put(3, 3);
        cache2.put(2,5);
        System.out.println(cache2.get(3));
        System.out.println(cache2);
    }
}
