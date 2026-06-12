class MyCircularQueue {
    int[] arr;
    int cap;
    int head;
    int size;

    public MyCircularQueue(int k) {
        arr = new int[k];
        cap = k;
        head = 0;
        size = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) return false;

        int idx = (head + size) % cap;
        arr[idx] = value;
        size++;

        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) return false;

        head = (head + 1) % cap;
        size--;

        return true;
    }

    public int Front() {
        return isEmpty() ? -1 : arr[head];
    }

    public int Rear() {
        if (isEmpty()) return -1;
        
        int idx = (head + size - 1) % cap;
        return arr[idx];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == cap;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */