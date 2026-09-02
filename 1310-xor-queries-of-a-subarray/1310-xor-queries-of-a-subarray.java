// Problem: XOR Queries of a Subarray
// Link: https://leetcode.com/problems/xor-queries-of-a-subarray/
// Difficulty: Medium

// Approach:
// Use Prefix XOR.
//
// 1. Build a prefix XOR array where:
//      prefixXOR[i] = XOR of elements from index 0 to i-1.
//
// 2. For every query [left, right], we need:
//      arr[left] ^ ... ^ arr[right]
//
// 3. The XOR of the unwanted prefix [0 ... left-1] exists in
//    prefixXOR[left].
//
// 4. XOR prefixXOR[right + 1] with prefixXOR[left].
//    The common elements cancel because x ^ x = 0.
//
// 5. Therefore:
//      answer = prefixXOR[right + 1] ^ prefixXOR[left]

// Time Complexity: O(n + q)
// Space Complexity: O(n + q)


class Solution {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] prefixXOR = new int[n + 1];
        prefixXOR[0] = 0;

        for (int i = 1; i <= n; i++) {
            prefixXOR[i] = prefixXOR[i - 1] ^ arr[i - 1];
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1];

            ans[i] = prefixXOR[left] ^ prefixXOR[right + 1];
        }
        
        return ans;
    }
}
