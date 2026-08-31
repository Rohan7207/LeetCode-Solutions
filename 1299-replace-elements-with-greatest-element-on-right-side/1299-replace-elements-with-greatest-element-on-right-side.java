// Problem: Replace Elements with Greatest Element on Right Side
// Link: https://leetcode.com/problems/replace-elements-with-greatest-element-on-right-side/
// Difficulty: Easy

// Approach:
// Use a running maximum while traversing the array.
//
// 1. Keep a variable `max` to store the largest value found so far.
//
// 2. For every element `arr[i]`, compare it with the current `max`.
//
// 3. If `arr[i]` is larger, update `max`.
//
// 4. After the traversal, `max` contains the largest element.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int[] replaceElements(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        ans[n - 1] = -1;
        int max = arr[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            ans[i] = max;
            max = Math.max(max, arr[i]);
        }
        
        return ans;
    }
}
