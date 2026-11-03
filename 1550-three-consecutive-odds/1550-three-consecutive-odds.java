// Problem: Three Consecutive Odds
// Link: https://leetcode.com/problems/three-consecutive-odds/
// Difficulty: Easy

// Approach:
// 1. Initialize a counter to track consecutive odd numbers.
//
// 2. Traverse the array using a for-each loop.
//
// 3. For every element:
//    - If the number is odd, increment count.
//    - If the number is even, reset count to 0 because
//      the consecutive sequence is broken.
//
// 4. If count reaches 3, return true immediately.
//
// 5. If the traversal finishes without finding three consecutive
//    odd numbers, return false.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public boolean threeConsecutiveOdds(int[] arr) {
        int count = 0;

        for (int num : arr) {
            if (num % 2 != 0) {
                count++;
            } else {
                count = 0;
            }

            if (count == 3) {
                return true;
            }
        }

        return false;
    }
}
