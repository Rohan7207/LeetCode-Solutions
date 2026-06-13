// Problem: Dota2 Senate
// Link: https://leetcode.com/problems/dota2-senate/
// Difficulty: Medium

// Approach:
// Simulate the senate voting process using two queues.
// Store indices of senators:
//     - Radiant senators in one queue
//     - Dire senators in another queue
// The senator with the smaller index gets
// the next turn first.
// In each round:
//     - Pop one Radiant index
//     - Pop one Dire index
//     - Compare both indices
//     - Smaller index acts first and bans
//       the opposite senator
//     - The winning senator survives and
//       comes back in the next round by
//       pushing index + n into its queue
//     - The losing senator is removed
//       and not added back
// Continue until one queue becomes empty.
// If Radiant queue is empty:
//     Dire wins
// If Dire queue is empty:
//     Radiant wins

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public String predictPartyVictory(String senate) {
        Queue<Integer> r = new ArrayDeque<>();
        Queue<Integer> d = new ArrayDeque<>();
        int n = senate.length();

        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'R')
                r.offer(i);
            else
                d.offer(i);
        }

        while (!r.isEmpty() && !d.isEmpty()) {
            int r1 = r.poll();
            int d1 = d.poll();
            if (r1 < d1) {
                r.offer(r1 + n);
            } else {
                d.offer(d1 + n);
            }
        }
        
        return r.isEmpty() ? "Dire" : "Radiant";
    }
}
