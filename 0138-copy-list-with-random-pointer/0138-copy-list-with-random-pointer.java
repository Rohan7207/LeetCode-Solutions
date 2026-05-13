/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        HashMap<Node, Node> m = new HashMap<>();

        Node newhead = new Node(head.val);
        Node oldtemp = head.next;
        Node newtemp = newhead;
        m.put(head, newhead);

        while (oldtemp != null) {
            Node copynode = new Node(oldtemp.val);
            m.put(oldtemp, copynode);
            newtemp.next = copynode;
            oldtemp = oldtemp.next;
            newtemp = newtemp.next;
        }

        oldtemp = head;
        newtemp = newhead;

        while (oldtemp != null) {
            newtemp.random = (oldtemp.random == null) ? null : m.get(oldtemp.random);
            oldtemp = oldtemp.next;
            newtemp = newtemp.next;
        }

        return newhead;
    }
}

/*
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Map<Node, Node> map = new HashMap<>();

        Node newHead = new ListNode(head.val);
        Node oldTemp = head.next;
        Node newTemp = newHead;
        map.put(head, newHead);

        while(oldTemp != null) {
            Node copyNode = new Node(oldTemp.val);
            map.put(oldTemp, copyNode);
            newTemp.next = copyNode;
            oldTemp = oldTemp.next;
            newTemp = newTemp.next;
        }

        oldTemp = head, newTemp = newHead;

        while(oldTemp != null) {
            newTemp.random = (oldTemp.random == null) ? null : map.get(oldTemp.random);
            oldTemp = oldTemp.next;
            newTemp = newTemp.next;
        }

        return newHead;
    }
*/