// Problem: Find the Maximum Number of Elements in Subset
// Link: https://leetcode.com/problems/find-the-maximum-number-of-elements-in-subset/?envType=daily-question&envId=2026-06-27
// Difficulty: Medium

// Approach:
// Count the frequency of every number using HashMap.
// The required subset pattern is:
// [x, x², x⁴, ..., middle, ..., x⁴, x², x]
// Because the pattern is symmetric:
//     - Every element except the middle element must appear twice.
//     - The middle element appears once.
// For every unique number:
//     1. Treat it as the starting value of the chain.
//     2. Keep moving:
//            current -> current * current
//        While the current number exists and has frequency >= 2:
//            - Add 2 to length because we can place it on both sides.
//            - Move to the next square value.
//     3. After pairs are completed:
//            If the next value exists,
//            it can act as the middle element,
//            so add 1.
// Special case:
//     For number 1:
//         1² = 1, so the chain never changes.
//         The answer is the count of ones,
//         but it must be odd.
//         If count is even, reduce it by 1.
//     Keep track of the maximum length obtained.
// If length is even reduce it by one.
// Finally, return answer

// Time Complexity: O(n * log(log(max(nums))))
// Space Complexity: O(n)


class Solution {
    public int maximumLength(int[] nums) {

        Map<Long, Integer> freq = new HashMap<>();

        for (int num : nums) {
            freq.put((long) num, freq.getOrDefault((long) num, 0) + 1);
        }

        int ans = 1;

        for (long num : freq.keySet()) {

            // special case for 1
            if (num == 1) {
                ans = Math.max(ans, freq.get(1L) % 2 == 0
                        ? freq.get(1L) - 1
                        : freq.get(1L));
                continue;
            }

            int len = 0;
            long curr = num;

            while (freq.containsKey(curr) && freq.get(curr) >= 2) {

                len += 2;

                curr = curr * curr;
            }

            // center element
            if (freq.containsKey(curr)) {
                len++;
            }

            if (len % 2 == 0)
                len--;

            ans = Math.max(ans, len);
        }

        return ans;
    }
}
