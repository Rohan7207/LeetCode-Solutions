// Problem: Integer to Roman
// Link: https://leetcode.com/problems/integer-to-roman/
// Difficulty: Medium

// Approach:
// Use greedy approach by mapping values to Roman symbols.
// Always subtract the largest possible value and append its symbol
// until the number becomes zero.

// Key Concept:
// Always pick the largest possible Roman value to build the result greedily.

// Time Complexity: O(1)
// Space Complexity: O(1)

class Solution {
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder ans = new StringBuilder();

        for(int i = 0; i < values.length && num > 0; i++){
            while(num >= values[i]){
                ans.append(symbols[i]);
                num -= values[i];
            }
        }

        return ans.toString();
    }
}
