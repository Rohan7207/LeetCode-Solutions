// Problem: Calculate Digit Sum of a String
// Link: https://leetcode.com/problems/calculate-digit-sum-of-a-string/?envType=daily-question&envId=2026-11-16
// Difficulty: Easy

// Approach:
// 1. Continue processing while the string length is greater than k.
//
// 2. Create a new StringBuilder for the transformed string.
//
// 3. Divide the current string into groups of size k.
//
// 4. For each group:
//    a. Calculate the sum of its digits.
//    b. Append the sum to the new StringBuilder.
//
// 5. Convert the StringBuilder into a string.
//
// 6. Repeat until s.length() <= k.
//
// 7. Return the final string.

// Time Complexity: O(n log_k(n)) approximately, for k > 1
// Space Complexity: O(n)


class Solution {
    public String digitSum(String s, int k) {

        while (s.length() > k) {
            StringBuilder next = new StringBuilder();

            for (int i = 0; i < s.length(); i += k) {
                int sum = 0;

                for (int j = i; j < Math.min(i + k, s.length()); j++) {
                    sum += s.charAt(j) - '0';
                }

                next.append(sum);
            }

            s = next.toString();
        }

        return s;
    }
}
