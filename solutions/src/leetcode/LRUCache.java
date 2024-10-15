 package leetcode;

 import java.util.HashMap;

class LRUCache {
    private class Node {
        int val;
        int key ;
        Node next;
        Node prev;

        Node() {
        }

        Node(int key , int val) {
            this.key = key ;
            this.val = val;
        }
    }

    private final int capacity;
    private HashMap<Integer, Node> storage;
    private final Node cache;
    private final Node cacheTail;
    private int size;


    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.storage = new HashMap<>(capacity);
        Node head = new Node();
        Node tail = new Node();
        head.next = tail;
        tail.prev = head;
        this.cache = head;
        this.cacheTail = tail;
    }

    public int get(int key) {
        if (!storage.containsKey(key)) return -1;
        Node valueNode = storage.get(key);
        makeHead(valueNode) ;
        return valueNode.val;
    }

    public void put(int key, int value) {

        if (storage.containsKey(key)) {
            storage.get(key).val = value;
            makeHead(storage.get(key));
        } else {
            if (size == capacity) {
                //evict tail
                Node toRemove = cacheTail.prev;
                toRemove.prev.next = cacheTail;
                cacheTail.prev = toRemove.prev;
                storage.remove(toRemove.key) ;
                size-- ;
            }
            Node curr = new Node(key , value);
            storage.put(key, curr);

            makeHead(curr);
            size++ ;

        }
    }

    private void makeHead(Node currNode) {

        //insertion of new node
        if (currNode.next == null && currNode.prev == null) {
            currNode.prev = cache;
            currNode.next = cache.next;
            Node temp = cache.next ;
            cache.next = currNode;
            temp.prev = currNode ;
            return;
        }

        // making recently accessed
        Node prevNode = currNode.prev;
        Node nextNode = currNode.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        Node currHeadNode = cache.next ;
        currNode.prev = cache;
        currNode.next = currHeadNode;
        cache.next = currNode;
        currHeadNode.prev = currNode ;
    }
    private void printCache() {
        Node curr = cache ;
        while(curr != null) {
            System.out.print("[ "+ curr.key + " : " + curr.val + " ] -> ");
            curr = curr.next ;
        }
        System.out.println();
    }
}
