// Problem: Multiply Strings
// Link: https://leetcode.com/problems/multiply-strings/
// Difficulty: Medium

// Approach:
// Simulate manual multiplication used in mathematics.
// Create a result array of size num1.length + num2.length
// to store multiplication results and carries.
// Traverse both strings from right to left:
//     - Convert characters into digits.
//     - Multiply the digits.
//     - Add the multiplication result to the appropriate
//       positions in the result array.
//     - Store remainder at current position
//       and carry at previous position.
// After processing all digits, build the final string
// by skipping leading zeros.
// Return the resulting string.

// Time Complexity: O(n * m)
// Space Complexity: O(n + m)


class Solution {
    public String multiply(String num1, String num2) {
        //If any one of no. is "0" return 0
        if (num1.equals("0") || num2.equals("0")) return "0";

        //Array to hold result of multiplication
        int[] res = new int[num1.length() + num2.length()];

        //Reverse loop for easy calculation (rigthmost digit come first)
        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                //Multiply digits
                int digit1 = num1.charAt(i) - '0';
                int digit2 = num2.charAt(j) - '0';
                int mul = digit1 * digit2;

                //Find position in res array where to add product and carry
                int product = i + j + 1;
                int carry = i + j;

                //Add multiplication res to current position to handle carry
                int sum = mul + res[product];

                res[product] = sum % 10; //Set current position as the remainder
                res[carry] += sum / 10; //Add carry to next higher position
            }
        }

        //Convert res array into a string
        StringBuilder ans = new StringBuilder();
        for (int num : res) {
            //Skip leading zeroes
            if (!(ans.length() == 0 && num == 0)) {
                ans.append(num);
            }
        }

        return ans.length() == 0 ? "0" : ans.toString();
    }
}
