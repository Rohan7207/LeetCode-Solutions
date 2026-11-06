// Problem: Count Zero Request Servers
// Link: https://leetcode.com/problems/count-zero-request-servers/?envType=daily-question&envId=2026-11-06
// Difficulty: Medium

// Approach:
// 1. Sort logs by request timestamp (logs[i][1]).
// 2. Pair every query with its original index and sort by query time.
// 3. Maintain a sliding window [time - x, time] using two pointers:
//      j -> expands the window by adding logs with timestamp <= time.
//      i -> shrinks the window by removing logs with timestamp < time - x.
// 4. Use a HashMap to maintain the frequency of requests per server.
// 5. map.size() gives the number of unique active servers.
// 6. Calculate inactive servers = n - map.size().
// 7. Store the result using the original query index.

// Time Complexity: O(m log m + q log q + m + q)
// Space Complexity: O(n + q)


class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = logs.length;

        // Sort ascending based on the 2nd term (index 1) 
        Arrays.sort(logs, Comparator.comparingInt(a -> a[1]));

        int len = queries.length;
        int[] res = new int[len];

        // Create a 2D array pairing each element with its index {{value, index}, ...} 
        int[][] paired = new int[len][2];  
        for (int i = 0; i < len; i++) {
            paired[i][0] = queries[i];
            paired[i][1] = i;
        }

        // Sort by the 1st term (the value at index 0)
        Arrays.sort(paired, Comparator.comparingInt(a -> a[0]));

        Map<Integer, Integer> map = new HashMap<>();  
        int i = 0, j = 0;

        // We are touching each log only two times: one by i and another by j, so O(2 * m) => O(m)
        for (int[] pair : paired) {
            int time = pair[0];
            int idx = pair[1];

            int start = time - x;
            int end = time;

            // Expansion
            while (j < m && logs[j][1] <= end) {
                map.put(logs[j][0], map.getOrDefault(logs[j][0], 0) + 1);
                j++;
            }

            // Shrinking
            while (i < m && logs[i][1] < start) {
                map.put(logs[i][0], map.get(logs[i][0]) - 1);

                if (map.get(logs[i][0]) == 0) {
                    map.remove(logs[i][0]);
                }

                i++;
            }

            // map.size() = no.of unique servers getting request in [start, end]
            res[idx] = n - map.size();
        }

        return res;  // (sorting)
    }
}
