// Problem: Find Servers That Handled Most Number of Requests
// Link: https://leetcode.com/problems/find-servers-that-handled-most-number-of-requests/?envType=daily-question&envId=2026-11-10
// Difficulty: Hard

// Approach:
// 1. Maintain a TreeSet called available containing all currently free servers.
//      Initially, all servers [0, 1, ..., k-1] are available.
//
// 2. Maintain a min heap called busy storing:
//      {finishTime, serverId}
//
// 3. Process requests in their arrival order.
//
// 4. Before assigning each request:
//      Move all servers whose finishTime <= currTime
//      from busy back into available.
//
// 5. If available is empty:
//      Drop the request because all servers are busy.
//
// 6. Find the preferred server:
//      i % k
//
// 7. Use available.ceiling(i % k) to find the smallest
//    available server ID >= the preferred server.
//
// 8. If no such server exists:
//      Wrap around and select available.first().
//
// 9. Remove the selected server from available and insert it
//    into busy with finishTime = currTime + duration.
//
// 10. Increment its request count and update maxRequest.
//
// 11. Finally, return all server IDs whose request count
//     equals maxRequest.

// Time Complexity: O((n + k) log k)
// Space Complexity: O(k)


class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        TreeSet<Integer> available = new TreeSet<>();

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        // {finishTime, serverId}
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        int[] count = new int[k];
        int maxRequest = 0;

        for (int i = 0; i < arrival.length; i++) {
            int currTime = arrival[i];
            int duration = load[i];

            while (!busy.isEmpty() && busy.peek()[0] <= currTime) {
                available.add(busy.poll()[1]);
            }

            // If all servers are busy, drop that request
            if (available.isEmpty()) {
                continue;
            }

            Integer assignedServer = available.ceiling(i % k);
            if (assignedServer == null) {
                assignedServer = available.first(); // Wrap around and start from 0
            }

            available.remove(assignedServer);
            busy.add(new int[] { currTime + duration, assignedServer });

            count[assignedServer]++;
            maxRequest = Math.max(maxRequest, count[assignedServer]);
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (count[i] == maxRequest) {
                res.add(i);
            }
        }

        return res;
    }
}
