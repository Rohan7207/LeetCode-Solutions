// Problem: Thousand Separator
// Link: https://leetcode.com/problems/thousand-separator/
// Difficulty: Easy

// Approach:
//
// 1. Handle n == 0 separately and return "0".
//
// 2. Use StringBuilder to construct the result in reverse order.
//
// 3. Extract the last digit using n % 10 and append it.
//
// 4. Increment count to track the number of digits added
//    in the current group.
//
// 5. Remove the last digit using n /= 10.
//
// 6. Whenever count == 3 and remaining digits exist:
//    - Append '.'
//    - Reset count to 0.
//
// 7. Reverse the StringBuilder because digits were
//    extracted from right to left.
//
// 8. Return the formatted string.

// Time Complexity: O(log n)
// Space Complexity: O(log n)


class Solution {
    public String thousandSeparator(int n) {
        if (n == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;

        while (n > 0) {
            sb.append(n % 10);
            count++;
            n /= 10;

            if (count == 3 && n > 0) {
                sb.append('.');
                count = 0;
            }
        }

        return sb.reverse().toString();
    }
}
