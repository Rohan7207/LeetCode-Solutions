// Problem: Fair Candy Swap
// Link: https://leetcode.com/problems/fair-candy-swap/
// Difficulty: Easy

// Approach:
// Let:
//     AliceTotal = Total candies Alice has
//     BobTotal   = Total candies Bob has
//
// Suppose:
//     Alice gives a box with x candies.
//     Bob gives a box with y candies.
//
// After swapping:
//
//     Alice = AliceTotal - x + y
//     Bob   = BobTotal - y + x
//
// Since both totals must become equal:
//
//     AliceTotal - x + y = BobTotal - y + x
//
// Rearranging:
//
//     x - y = (AliceTotal - BobTotal) / 2
//
// Let:
//
//     diff = (AliceTotal - BobTotal) / 2
//
// Then:
//
//     y = x - diff
//
// Algorithm:
//     1. Compute AliceTotal and BobTotal.
//     2. Calculate diff.
//     3. Store all Bob's box sizes in a HashSet.
//     4. For every box x in Alice:
//            Compute y = x - diff.
//            If y exists in the HashSet,
//            swapping x and y equalizes both totals.
//     5. Return {x, y}.

// Time Complexity:
//     Computing sums : O(n + m)
//     Building HashSet : O(m)
//     Checking Alice's boxes : O(n)
//     Total : O(n + m)
//
// Space Complexity:
//     HashSet : O(m)


class Solution {
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int aliceTotal = 0;
        int bobTotal = 0;
        for (int i = 0; i < aliceSizes.length; i++) {
            aliceTotal += aliceSizes[i];
        }

        for (int i = 0; i < bobSizes.length; i++) {
            bobTotal += bobSizes[i];
        }

        int diff = (aliceTotal - bobTotal) / 2;

        Set<Integer> set = new HashSet<>();

        for (int num : bobSizes) {
            set.add(num);
        }

        for (int x : aliceSizes) {
            int y = x - diff;

            if (set.contains(y)) {
                return new int[] { x, y };
            }
        }

        return new int[] {};
    }
}
