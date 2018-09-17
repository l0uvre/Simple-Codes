import java.util.*;
class LRUCache {
    private int size;
    private Node head;
    private Node tail;
    private Map<Integer, Node> map;
    private int capacity;
    
    class Node {
        Integer key;
        Integer val;
        Node prev;
        Node next;
        Node(Integer key, Integer val) {
            this.key = key;
            this.val = val;
            prev = null;
            next = null;
        }
    }
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = tail = null;
        size = 0;
    }
    
    private void remove(Node curr) {
        Node prev = curr.prev;
        Node next = curr.next;
        if (prev != null) {
            prev.next = next;
        }
        if (next != null) {
            next.prev = prev;
        }
        
        if (curr == tail) {
            tail = prev;
        }
        
        if (curr == head) {
            head = next;
        }
    }
    
    private void addToTail(Node curr) {
        if (tail != null) {
            tail.next = curr;
        }
        curr.prev = tail;
        curr.next = null;
        tail = curr;
        if (head == null) {
            head = curr;
        }
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        } else {
            Node curr = map.get(key);
            int res = curr.val;
            remove(curr);
            addToTail(curr);
            return res;
        }
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node curr = map.get(key);
            map.remove(key);
            remove(curr);
            curr = new Node(key, value);
            map.put(key, curr);
            addToTail(curr);
        } else {
            Node curr = new Node(key, value);
            if (size < capacity) {
                size++;
            } else {
                map.remove(head.key);
                remove(head); // this order is important to avoid NullPointer
            }
            addToTail(curr);
            map.put(key, curr); 
        }
    }

    public static void main(String[] args) {
        int capacity = 2;
        LRUCache cache = new LRUCache(capacity);     
        cache.put(1,1);
        cache.put(2,2);
        System.out.println(cache.get(1));
        cache.put(3,3);
        System.out.println(cache.get(2));
        cache.put(4,4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
