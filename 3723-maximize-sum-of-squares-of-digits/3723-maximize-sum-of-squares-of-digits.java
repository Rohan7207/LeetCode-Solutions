// Problem: Maximize Sum of Squares of Digits
// Link: https://leetcode.com/problems/maximize-sum-of-squares-of-digits/?envType=daily-question&envId=2026-10-14
// Difficulty: Medium

// Approach:
// 1. Check if the required sum exceeds the maximum possible sum, num * 9.
//    If it does, return an empty string because no valid number exists.
// 2. Calculate how many 9s can fit into the required sum using sum / 9.
// 3. Calculate the remaining sum using sum % 9.
// 4. Append all the 9s first to maximize the sum of squared digits.
// 5. If the remainder is greater than 0, append it as the next digit.
// 6. Fill the remaining positions with zeros to make the string length equal to num.
// 7. Return the constructed string.

// Time Complexity: O(num)
// Space Complexity: O(num)


class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > num * 9) {
            return "";
        }

        int nines = sum / 9;
        int rem = sum % 9;
        StringBuilder sb = new StringBuilder();

        sb.repeat('9', nines);

        if (rem > 0) {
            sb.append((char) (rem + '0'));
        }

        int remLength = num - sb.length();
        if (remLength > 0) {
            sb.repeat('0', remLength);
        }

        return sb.toString();
    }
}
