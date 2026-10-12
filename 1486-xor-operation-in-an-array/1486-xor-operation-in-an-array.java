// Problem: XOR Operations in an Array
// Link: https://leetcode.com/problems/xor-operation-in-an-array/
// Difficulty: Easy

// Approach:
// 1. Initialize ans = 0 because XOR with 0 leaves a number unchanged.
// 2. Traverse i from 0 to n - 1.
// 3. Calculate the current number using start + 2 * i.
// 4. XOR the current number with ans and store the result back in ans.
// 5. After processing all n numbers, return ans.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int xorOperation(int n, int start) {
        int ans = 0;

        for (int i = 0; i < n; i++) {
            ans ^= (start + 2 * i);
        }

        return ans;
    }
}
