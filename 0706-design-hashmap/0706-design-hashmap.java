// Problem: Design HashMap
// Link: https://leetcode.com/problems/design-hashmap/
// Difficulty: Easy

// Approach:
// We need to design a HashMap without using
// built-in HashMap.
// A HashMap stores:
//     key -> value
// We use:
//     1. Array of buckets
//     2. Hash function
//     3. Linked list for collision handling
// Each index of the array is one bucket.
//     arr[0]
//     arr[1]
//     arr[2]
//     ...
// Each bucket stores a linked list of nodes.
// Each node stores:
//     key
//     value
//     next
// hash(key):
//     index = key % size
// This tells us which bucket the key belongs to.
// 
// put(key, value):
//     1. Find bucket index using hash(key)
//     2. If bucket is empty, create node and store it
//     3. Else traverse linked list
//     4. If key already exists, update its value
//     5. If key does not exist, add new node at end
//
// get(key):
//     1. Find bucket index using hash(key)
//     2. Traverse that bucket's linked list
//     3. If key is found, return its value
//     4. Else return -1
//
// remove(key):
//     1. Find bucket index using hash(key)
//     2. If bucket is empty, do nothing
//     3. If first node has the key, move bucket head to next node
//     4. Else traverse using previous node
//     5. If key is found, skip that node

// Time Complexity:
//     Average:
//         put()    -> O(1)
//         get()    -> O(1)
//         remove() -> O(1)
//
//     Worst case:
//         put()    -> O(n)
//         get()    -> O(n)
//         remove() -> O(n)
//
// Space Complexity:
//
//     O(size + n)
//
//     size = number of buckets
//     n = number of inserted key-value pairs


class ListNode {
    int key, value;
    ListNode next;

    ListNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class MyHashMap {
    ListNode[] arr;
    int size;

    public MyHashMap() {
        size = 1007;
        arr = new ListNode[size];
    }

    public int hash(int key) {
        return key % size;
    }

    public void put(int key, int value) {
        int hashKey = hash(key);

        if (arr[hashKey] == null) {
            ListNode node = new ListNode(key, value);
            arr[hashKey] = node;
            return;
        }

        ListNode head = arr[hashKey];
        ListNode curr = head;

        while (curr != null) {
            if (curr.key == key) {
                curr.value = value;
                return;
            }

            if (curr.next == null) break;

            curr = curr.next;
        }

        ListNode node = new ListNode(key, value);
        curr.next = node;
    }

    public int get(int key) {
        int hashKey = hash(key);
        ListNode head = arr[hashKey];

        if (head == null) return -1;

        while (head != null) {
            if (head.key == key) return head.value;

            head = head.next;
        }

        return -1;
    }

    public void remove(int key) {
        int hashKey = hash(key);
        ListNode head = arr[hashKey];

        if (head == null) return;

        if (head.key == key) {
            arr[hashKey] = head.next;
            return;
        }

        while (head.next != null) {
            if (head.next.key == key) {
                head.next = head.next.next;
                return;
            }

            head = head.next;
        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
