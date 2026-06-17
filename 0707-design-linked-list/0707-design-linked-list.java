// Problem: Design Linked List
// Link: https://leetcode.com/problems/design-linked-list/
// Difficulty: Medium

// Approach:
// We need to design our own singly linked list.
// Each node contains:
//     value
//     next pointer
// We maintain:
//     head -> first node of linked list
//     size -> number of nodes in linked list
// get(index):
//     If index is invalid:
//         return -1
//     Otherwise:
//         Start from head
//         Move index times
//         Return current node value
// addAtHead(val):
//     Create new node
//     Point new node.next to current head
//     Make head = new node
//     Increase size
// addAtTail(val):
//     Create new node
//     If list is empty:
//         head = new node
//     Else:
//         Traverse till last node
//         Connect last.next = new node
//     Increase size
// addAtIndex(index, val):
//     If index > size:
//         Do nothing
//     If index <= 0:
//         Add at head
//     If index == size:
//         Add at tail
//     Otherwise:
//         Traverse to node before index
//         Insert new node between prev and prev.next
//         Increase size
// deleteAtIndex(index):
//     If index is invalid:
//         Do nothing
//     If index == 0:
//         Move head to head.next
//     Otherwise:
//         Traverse to node before index
//         Skip the node at index
//     Decrease size

// Time Complexity:
//
//     get(index)          -> O(index)
//     addAtHead(val)      -> O(1)
//     addAtTail(val)      -> O(n)
//     addAtIndex(index)   -> O(index)
//     deleteAtIndex(index)-> O(index)
//
// Space Complexity:
//
//     O(1) for each operation
//     O(n) overall for storing n nodes


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
