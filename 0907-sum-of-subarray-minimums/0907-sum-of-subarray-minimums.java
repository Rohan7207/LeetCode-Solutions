// Problem: Sum of SubArray Minimums
// Link: https://leetcode.com/problems/sum-of-subarray-minimums/
// Difficulty: Medium

// Approach:
// Instead of generating every subarray and finding its minimum,
// calculate the contribution of each element independently.
// For every element arr[i], determine how many subarrays have
// arr[i] as their minimum.
// To do this, find:
//     1. Previous Smaller Element (PSE)
//     2. Next Smaller or Equal Element (NSE)
// using two monotonic increasing stacks.
// Let:
//     left = i - PSE
// be the number of possible starting positions, and
//     right = NSE - i
// be the number of possible ending positions.
// Therefore, the number of subarrays where arr[i] is the minimum is:
//     left × right
// Hence, the contribution of arr[i] becomes:
//     arr[i] × left × right
// Sum the contribution of every element and take modulo
// 1,000,000,007.
// Why different comparisons?
// Left Pass:
//     Pop while stack value >= current value.
// This finds the Previous Strictly Smaller element.
// Right Pass:
//     Pop while stack value > current value.
// This finds the Next Smaller or Equal element.
// Using different comparisons prevents duplicate elements
// from counting the same subarray more than once.

// Time Complexity:
//     O(n)
// Every index is pushed and popped at most once.
//
// Space Complexity:
//     O(n)
// For the stack, left array, and right array.


class Solution {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int MOD = 1_000_000_007;

        int[] left = new int[n];
        int[] right = new int[n];
        Stack<Integer> s = new Stack<>();

        // Find Previous Smaller Element (Left Choices)
        for (int i = 0; i < n; i++) {
            while (!s.isEmpty() && arr[s.peek()] >= arr[i]) {
                s.pop();
            }

            left[i] = s.isEmpty() ? -1 : s.peek();
            s.push(i);
        }

        s.clear();

        // Find Next Smaller Element (Right Choices)
        for (int i = n - 1; i >= 0; i--) {
            while (!s.isEmpty() && arr[s.peek()] > arr[i]) {
                s.pop();
            }

            right[i] = s.isEmpty() ? n : s.peek();
            s.push(i);
        }

        // Calculate contribution of every element : Contribution = arr[i] × (i-PSE) × (NSE-i)
        long ans = 0;

        for (int i = 0; i < n; i++) {
            long contribution = (long) arr[i] * (i - left[i]) * (right[i] - i);

            ans = (ans + contribution) % MOD;
        }

        return (int) ans;
    }
}
