// Problem: Check If N and Its Double Exist
// Link: https://leetcode.com/problems/check-if-n-and-its-double-exist/
// Difficulty: Easy

// Approach:
// Use HashSet to track previously seen numbers.
//
// 1. For each number, check whether its double (2 * num) was seen.
// 2. If num is even, check whether its half (num / 2) was seen.
// 3. If either condition is true, a valid pair exists.
// 4. Otherwise, add the current number to the set.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public boolean checkIfExist(int[] arr) {
        Set<Integer> seen = new HashSet<>();

        for (int num : arr) {
            if (seen.contains(2 * num) || (num % 2 == 0 && seen.contains(num / 2))) {
                return true;
            }

            seen.add(num);
        }

        return false;
    }
}
