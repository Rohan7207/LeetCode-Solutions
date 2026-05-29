class MedianFinder {
    PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;

    public MedianFinder() {
        left = new PriorityQueue<>((a, b) -> b - a);
        right = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (left.isEmpty() || num <= left.peek()) {
            left.add(num);
        } else
            right.add(num);

        if (left.size() > right.size() + 1) {
            right.add(left.poll());
        }
        if (right.size() > left.size() + 1) {
            left.add(right.poll());
        }
    }

    public double findMedian() {
        if (left.size() > right.size())
            return left.peek();
        if (right.size() > left.size())
            return right.peek();

        return (left.peek() + right.peek()) / 2.0;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

/*
   PriorityQueue<Integer> minHeap;
   PriorityQueue<Integer> maxHeap;

   public MedianFinder() {
       minHeap = new PriorityQueue<>();
       maxHeap = new PriorityQueue<>((a, b) -> b - a);
   }

   //adds num to data stream
   public void addNum(int num) {
       maxHeap.offer(num); //add to max heap

       minHeap.offer(maxHeap.poll());

       if (maxHeap.size() < minHeap.size()) {
           maxHeap.offer(minHeap.poll());
       }
   }

   public double findMedian() {
       return maxHeap.size() > minHeap.size() ? maxHeap.peek() : (maxHeap.peek() + minHeap.peek()) * 0.5;
   }
*/