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