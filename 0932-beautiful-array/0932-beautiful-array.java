// Problem: Beautiful Array
// Link: https://leetcode.com/problems/beautiful-array/
// Difficulty: Medium

// Approach:
// This problem is based on a recursive construction.
// A beautiful array has the property that no middle element is the average
// of two elements surrounding it.
// Instead of building the array directly, recursively construct beautiful
// arrays for the odd and even positions separately.
// Let:
// oddCount  = (n + 1) / 2
// evenCount = n / 2
// Step 1:
// Recursively build a beautiful array of size oddCount.
// Step 2:
// Convert every element x in this array into an odd number:
//      2 * x - 1
// This produces all odd numbers from 1 to n while preserving the beautiful
// property.
// Step 3:
// Recursively build a beautiful array of size evenCount.
// Step 4:
// Convert every element x into an even number:
//      2 * x
// This produces all even numbers from 1 to n while preserving the beautiful
// property.
// Step 5:
// Append the transformed odd part followed by the transformed even part.
// The odd and even groups can be safely concatenated because the average of
// an odd number and an even number is never an integer, so no new invalid
// triplet can be created between the two groups.
// Base Case:
// When n == 1, the only beautiful array is [1].

// Time Complexity: O(n log n)
// Space Complexity: O(n log n)    // Recursive lists + recursion stack


class Solution {
    public int[] beautifulArray(int n) {
        List<Integer> result = helper(n);

        int[] ans = new int[n];
        for(int i = 0; i < n; i++) {
            ans[i] = result.get(i);
        }

        return ans;
    }

    private List<Integer> helper(int n) {
        if(n == 1) {
            List<Integer> base = new ArrayList<>();
            base.add(1);
            return base;
        }

        List<Integer> oldPart = helper((n + 1) / 2);
        List<Integer> evenPart = helper(n / 2);

        List<Integer> ans = new ArrayList<>();

        for(int x : oldPart) {
            ans.add(2 * x - 1);
        }

        for(int x : evenPart) {
            ans.add(2 * x);
        }

        return ans;
    }
}
