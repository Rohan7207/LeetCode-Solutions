// Problem: Defanging an IP Address
// Link: https://leetcode.com/problems/defanging-an-ip-address/
// Difficulty: Easy

// Approach:
// Traverse the IP address character by character.
//
// 1. Create a StringBuilder to construct the defanged IP address.
//
// 2. For each character:
//    - If the character is '.', append "[.]".
//    - Otherwise, append the character unchanged.
//
// 3. Return the constructed string.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public String defangIPaddr(String address) {
        StringBuilder res = new StringBuilder();

        for (char c : address.toCharArray()) {
            if (c == '.') {
                res.append("[.]");
            } else {
                res.append(c);
            }
        }

        return res.toString();
    }
}
