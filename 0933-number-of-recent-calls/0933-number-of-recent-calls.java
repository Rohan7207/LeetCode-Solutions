// Problem: Number of Recent Calls
// Link: https://leetcode.com/problems/number-of-recent-calls/
// Difficulty: Easy

// Approach:
// Since every ping() call receives timestamps in strictly increasing order,
// all requests are naturally stored in chronological order.
//
// A queue is the ideal data structure because:
// - New requests are always added at the rear.
// - Expired requests are always removed from the front.
//
// For every ping(t):
//
// 1. Add the current timestamp to the queue.
// 2. Remove all timestamps that are older than (t - 3000),
//    because they are no longer within the valid time window.
// 3. After removing expired requests, every timestamp remaining
//    in the queue lies within the inclusive range [t - 3000, t].
// 4. The queue size is exactly the number of recent requests,
//    so return queue.size().
//
// Each timestamp is inserted once and removed at most once,
// giving an amortized constant time solution.

// Time Complexity:
// Amortized O(1) per ping()
//
// Space Complexity:
// O(n), where n is the number of recent requests currently stored.


class RecentCounter {

    Queue<Integer> q;

    public RecentCounter() {
        q = new LinkedList<>();
    }

    public int ping(int t) {
        q.offer(t);

        while (q.peek() < t - 3000) {
            q.poll();
        }
        return q.size();
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */
