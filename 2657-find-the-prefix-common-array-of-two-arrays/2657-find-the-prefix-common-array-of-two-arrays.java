// Problem: Find the Prefix Common Array of two arrays
// Link: https://leetcode.com/problems/find-the-prefix-common-array-of-two-arrays/?envType=daily-question&envId=2026-05-20
// Difficulty: Medium

// Approach:
// Use a frequency array to track elements
// appearing in the prefixes of both arrays.
// Traverse both arrays together.
// For each index:
//     - Increase frequency of A[i].
//     - If its frequency becomes 2,
//       increase common count.
//     - Increase frequency of B[i].
//     - If its frequency becomes 2,
//       increase common count.
// Store common count in answer array for that index.
// Return the answer array.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] freq = new int[n + 1];
        int[] ans = new int[n];
        int commonCount = 0;

        for(int i = 0; i < n; i++) {
            freq[A[i]]++;
            if(freq[A[i]] == 2)  commonCount++;

            freq[B[i]]++;
            if(freq[B[i]] == 2) commonCount++;

            ans[i] = commonCount;
        }

        return ans;
    }
}
