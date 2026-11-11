// Problem: Minimum Cost to Acquire Required Items
// Link: https://leetcode.com/problems/minimum-cost-to-acquire-required-items/?envType=daily-question&envId=2026-11-11
// Difficulty: Medium

// Approach:
// 1. Check whether buying one Type 1 item and one Type 2 item
//    individually is cheaper than buying one combined item.
//
// 2. If cost1 + cost2 <= costBoth:
//      Buy everything individually.
//      Total = need1 * cost1 + need2 * cost2.
//
// 3. Otherwise:
//      Buying a combined item is cheaper for satisfying both needs.
//
// 4. Buy combined items for the common requirement:
//      both = min(need1, need2).
//
// 5. Subtract the combined quantity from both requirements.
//
// 6. Only one requirement can remain:
//      If need1 > 0, satisfy it using min(cost1, costBoth).
//      If need2 > 0, satisfy it using min(cost2, costBoth).
//
// 7. Return the minimum total cost.

// Time Complexity: O(1)
// Space Complexity: O(1)


class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        // costBoth {Type1, Type2} is costlier or equal to cost1 + cost2
        if (cost1 + cost2 <= costBoth) {
            return (1L * cost1 * need1) + (1L * cost2 * need2);
        }

        int both = Math.min(need1, need2);
        long total = 1L * both * costBoth;

        need1 -= both;
        need2 -= both;

        if (need1 > 0) {
            total += 1L * need1 * Math.min(cost1, costBoth);
        } else if (need2 > 0) {
            total += 1L * need2 * Math.min(cost2, costBoth);
        }

        return total;
    }
}
