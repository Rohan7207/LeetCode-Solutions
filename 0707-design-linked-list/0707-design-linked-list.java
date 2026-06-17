class MyLinkedList {
    ListNode head;
    int size;

    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public MyLinkedList() {
        head = null;
        size = 0;
    }

    public int get(int index) {
        if (index < 0 || index >= size)
            return -1;

        ListNode curr = head;

        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }

        return curr.val;
    }

    public void addAtHead(int val) {
        ListNode node = new ListNode(val);

        node.next = head;
        head = node;

        size++;
    }

    public void addAtTail(int val) {
        ListNode node = new ListNode(val);

        if (head == null) {
            head = node;
            size++;
            return;
        }

        ListNode curr = head;

        while (curr.next != null) {
            curr = curr.next;
        }

        curr.next = node;
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index > size)
            return;

        if (index <= 0) {
            addAtHead(val);
            return;
        }

        if (index == size) {
            addAtTail(val);
            return;
        }

        ListNode node = new ListNode(val);

        ListNode prev = head;

        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }

        node.next = prev.next;
        prev.next = node;
        
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size)
            return;

        if (index == 0) {
            head = head.next;
            size--;
            return;
        }

        ListNode prev = head;

        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }

        prev.next = prev.next.next;
        size--;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */