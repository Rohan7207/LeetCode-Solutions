// Problem : String to Integer (atoi)
// Link : https://leetcode.com/problems/string-to-integer-atoi/
// Difficulty : Medium

// Approach
// 1. Skip leading whitespaces.
// 2. Check for the optional sign (+ or -) and store it.
// 3. Traverse the digits and convert them to integers.
// 4. Before adding each digit, check for overflow/underflow.
// 5. Update the result as res = res * 10 + digit. 
// 6. Stop when the first non-digit is encountered.
// 7. Return the result with correct sign.

// Time Complexity : O(n)

// Space Complexity : O(1)

class Solution {
    public int myAtoi(String s) {
        if (s == null || s.length() == 0)
            return 0;

        //Constants for 32-bit signed integer range, used to handle overflow and underflow
        final int INT_MIN = Integer.MIN_VALUE;
        final int INT_MAX = Integer.MAX_VALUE;

        int i = 0;
        int n = s.length();

        //Skip leading whitespace
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        //Check if we have reached end
        if (i == n)
            return 0;

        //Check for sign
        int sign = 1;
        if (s.charAt(i) == '+') {
            i++;
        } else if (s.charAt(i) == '-') {
            sign = -1;
            i++;
        }

        //Read digits and convert
        long res = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';

            //overflow handling
            if (res > Integer.MAX_VALUE / 10 ||
                    (res == Integer.MAX_VALUE / 10 && digit > 7)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            res = res * 10 + digit; //here there is chance of no. going overflow or underflow

            i++;
        }

        //Apply sign and return 
        return (int) (res * sign);
    }
}
