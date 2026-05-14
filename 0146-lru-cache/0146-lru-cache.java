// Problem: LRU Cache
// Link: https://leetcode.com/problems/lru-cache/
// Difficulty: Medium

// Approach:
// Use a combination of:
//     - HashMap for O(1) access to nodes
//     - Doubly Linked List to maintain usage order
// The doubly linked list stores:
//     - Most Recently Used (MRU) node near head
//     - Least Recently Used (LRU) node near tail
// HashMap stores:
//     key -> corresponding node
// get(key):
//     - If key does not exist return -1.
//     - Otherwise move the node to front
//       because it becomes recently used.
//     - Return the node value.
// put(key, value):
//     - If key already exists:
//           - Update its value.
//           - Move node to front.
//     - Otherwise:
//           - If cache is full,
//             remove least recently used node
//             from both linked list and hashmap.
//           - Create new node.
//           - Insert it at front.
//           - Store it in hashmap.
// Dummy head and tail nodes are used
// to simplify insertion and deletion operations.

// Time Complexity:
//     get()  -> O(1)
//     put()  -> O(1)
// Space Complexity: O(capacity)

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
