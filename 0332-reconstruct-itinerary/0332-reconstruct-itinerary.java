// Problem: Reconstruct Itinerary
// Link: https://leetcode.com/problems/reconstruct-itinerary/
// Difficulty: Hard

// Approach:
// Build a directed graph where:
//
//     departure airport -> destination airports
//
// Store destinations in a PriorityQueue
// so that the lexicographically smallest
// destination is always chosen first.
//
// Start DFS from "JFK".
//
// During DFS:
//     - Continuously take the smallest
//       destination from the min heap.
//     - Recursively visit that airport.
//     - Remove the ticket using poll().
//
// After all outgoing flights are used,
// add the current airport to the front
// of the answer list.
//
// This is Hierholzer's Algorithm used
// to find an Eulerian Path.
//
// Since airports are added during
// backtracking, the itinerary is built
// in reverse order.

// Time Complexity: O(E log E)
// Space Complexity: O(E)


class Solution {
    Map<String, PriorityQueue<String>> graph = new HashMap<>();
    LinkedList<String> res = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {

        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);

            graph.computeIfAbsent(from, k -> new PriorityQueue<>()).offer(to);
        }

        dfs("JFK");

        return res;
    }

    private void dfs(String airport) {
        PriorityQueue<String> pq = graph.get(airport);

        while (pq != null && !pq.isEmpty()) {
            String next = pq.poll();
            dfs(next);
        }

        res.addFirst(airport);
    }
}
