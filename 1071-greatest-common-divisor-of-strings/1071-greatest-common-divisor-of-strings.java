// Problem: Greatest Common Divisor of Strings
// Link: https://leetcode.com/problems/greatest-common-divisor-of-strings/
// Difficulty: Easy

// Approach:
// A common divisor string must satisfy two conditions:
// 1. Both strings should be formed by repeating the same base pattern.
//    This can be verified by checking:
//        str1 + str2 == str2 + str1
//    If this condition fails, no common divisor string exists.
// 2. If both strings share the same repeating pattern,
//    then the length of the largest divisor string must divide
//    the lengths of both strings.
//    Therefore, compute:
//        gcd(str1.length(), str2.length())
//    using the Euclidean Algorithm.
// The prefix of str1 having this GCD length is the largest
// possible string that can divide both strings.
// Return:
//     str1.substring(0, gcdLength)

// Time Complexity:
//     O(n + m)
//     O(n + m) -> String concatenation and comparison
//     O(log(min(n, m))) -> Euclidean GCD
// Space Complexity:
//     O(n + m)
// Due to the temporary concatenated strings.


class Solution {
    public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }

        int g = gcd(str1.length(), str2.length());

        return str1.substring(0, g);
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }
}
