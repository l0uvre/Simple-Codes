/** LC 146 -- Design, Doubly Linked List, HashTable. **/
import java.util.*;

/** Every time we call put(), try to add the node to the head
 * of the linked list; every time we call get(), remove the node from
 * the linked list and add it to the head; we delete the tail node if
 * the capacity is reached;
 *
 * The reason why we used doubly linked list is because it has O(1)
 * deletion for any node and O(1) addition to the tail or the head.
 **/
class LRUCache {
    /** sentinel head **/
    private Node head; 
    /** sentinel tail **/
    private Node tail;
    private Map<Integer, Node> keyToNode;
    private int capacity;
    
    class Node {
        int key, val;
        Node prev;
        Node next;
        Node (int key, int val) {
            this.key = key;
            this.val = val;
            prev = null;
            next = null;
        }
    }

    private void addToHead(Node node) {
        Node next = head.next;
        head.next = node;
        node.prev = head;
        node.next = next;
        next.prev = node;
    }

    private void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    private void removeTail() {
        remove(tail.prev);
    }
    
    public LRUCache(int capacity) {
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        keyToNode = new HashMap<>();
        this.capacity = capacity;
    }

    private int size() {
        return keyToNode.size();
    }
    
   
    public int get(int key) {
        if (!keyToNode.containsKey(key)) {
            return -1;
        } else {
            Node node = keyToNode.get(key);
            remove(node);
            addToHead(node);
            return node.val;
        }

    }
    
    public void put(int key, int value) {
        if (keyToNode.containsKey(key)) {
            Node node = keyToNode.get(key);
            node.val = value;
            remove(node);
            addToHead(node);
        } else {
            if (capacity == size()) {
                keyToNode.remove(tail.prev.key);
                removeTail();
            }
            Node node = new Node(key, value);
            keyToNode.put(key, node);
            addToHead(node);
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
