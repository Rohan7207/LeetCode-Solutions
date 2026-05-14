class LRUCache {

    class Node {
        int key, val;
        Node prev;
        Node next;

        Node(int k, int v) {
            this.key = k;
            this.val = v;
            prev = null;
            next = null;
        }
    };

    Node head = new Node(-1, -1);
    Node tail = new Node(-1, -1);

    HashMap<Integer, Node> m = new HashMap<>();
    int limit;

    void addNode(Node newNode) {
        Node oldNext = head.next;

        head.next = newNode;
        oldNext.prev = newNode;

        newNode.next = oldNext;
        newNode.prev = head;
    }

    void delNode(Node oldNode) {
        Node oldPrev = oldNode.prev;
        Node oldNext = oldNode.next;

        oldPrev.next = oldNext;
        oldNext.prev = oldPrev;
    }

    public LRUCache(int capacity) {
        limit = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!m.containsKey(key)) {
            return -1;
        }

        Node ansNode = m.get(key);
        int ans = ansNode.val;
        delNode(ansNode);
        addNode(ansNode);

        return ans;
    }

    public void put(int key, int value) {
        if (m.containsKey(key)) {
            Node oldNode = m.get(key);
            oldNode.val = value;
            delNode(oldNode);
            addNode(oldNode);
        } else {
            if (m.size() == limit) {
                //delete LRU data
                m.remove(tail.prev.key);
                delNode(tail.prev);
            }

            Node newNode = new Node(key, value);
            addNode(newNode);
            m.put(key, newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */