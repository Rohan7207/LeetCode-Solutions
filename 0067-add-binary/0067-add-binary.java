// Problem: Add Binary
// Link: https://leetcode.com/problems/add-binary/
// Difficulty: Easy

// Approach:
// Use two pointers starting from the end of both strings
// to simulate binary addition from right to left.
// Maintain a carry value during addition.
// Repeat while either string still has digits
// or carry exists:
//     - Add carry to current sum.
//     - Add current digit from string a if available.
//     - Add current digit from string b if available.
//     - Append sum % 2 to the result
//       because binary digits can only be 0 or 1.
//     - Update carry using sum / 2.
// Reverse the result string and return it.

// Time Complexity: O(max(n, m))
// Space Complexity: O(max(n, m))

class Solution {
    public String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        int i = a.length() - 1, j = b.length() -1 , carry = 0;

        while (i >= 0 || j >= 0 || carry == 1) {
            int sum = carry;
            if(i >= 0) sum += a.charAt(i--) - '0';
            if(j >= 0) sum += b.charAt(j--) - '0';

            res.append(sum % 2);
            carry = sum / 2;
        }

        return res.reverse().toString();
    }
}
