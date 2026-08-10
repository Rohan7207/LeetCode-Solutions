// Problem: Binary Prefix Divisible by 5
// Link: https://leetcode.com/problems/binary-prefix-divisible-by-5/
// Difficulty: Easy

// Approach:
// Treat the array as a binary number where each element is a bit.
//
// 1. Maintain `currentRemainder`, which stores the remainder of
//    the current binary prefix when divided by 5.
//
// 2. When a new binary digit is added, the previous binary number
//    is shifted left by one position, which is equivalent to
//    multiplying it by 2.
//
//    newNumber = oldNumber * 2 + num
//
// 3. We do not need to store the complete binary number.
//    We only need its remainder:
//
//    currentRemainder = (currentRemainder * 2 + num) % 5
//
// 4. If the remainder is 0, the current prefix is divisible by 5,
//    so add true to the result.
//
// 5. Otherwise, add false.

// Time Complexity: O(n)
// Space Complexity: O(n) for the result list.
// Auxiliary Space: O(1)


class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> ans = new ArrayList<>();
        int currentRemainder = 0;

        for (int num : nums) {
            // Shift left by 1 (multiply by 2), add the new bit, and keep it under 5
            currentRemainder = (currentRemainder * 2 + num) % 5;
            
            // If the remainder is 0, the prefix is divisible by 5
            ans.add(currentRemainder == 0);
        }

        return ans;
    }
}
