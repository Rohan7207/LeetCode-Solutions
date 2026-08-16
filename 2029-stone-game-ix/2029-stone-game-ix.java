// Problem : Stone Game IX
// Link : https://leetcode.com/problems/stone-game-ix/?envType=daily-question&envId=2026-08-16
// Difficulty : Medium

// Approach:
// Only the remainder of each stone when divided by 3 matters,
// because the player loses whenever the sum of removed stones
// becomes divisible by 3.
//
// 1. Count how many stones have each remainder:
//      count[0] → stone % 3 == 0
//      count[1] → stone % 3 == 1
//      count[2] → stone % 3 == 2
//
// 2. Remainder-0 stones do not change the current sum modulo 3.
//    Their main effect is changing the turn order.
//
// 3. If count[0] is even:
//    Alice wins when both remainder-1 and remainder-2 stones exist.
//
// 4. If count[0] is odd:
//    The difference between the number of remainder-1 and remainder-2
//    stones determines whether Alice can force Bob into the losing move.
//    Alice wins when:
//
//        |count[1] - count[2]| > 2
//
// 5. Return the corresponding condition.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] count = new int[3];

        for (int stone : stones) {
            count[stone % 3]++;
        }

        if (count[0] % 2 == 0) {
            return count[1] > 0 && count[2] > 0;
        }

        return Math.abs(count[1] - count[2]) > 2;
    }
}
