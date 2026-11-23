// Problem: Sum of All Odd Length Subarrays
// Link: https://leetcode.com/problems/sum-of-all-odd-length-subarrays/
// Difficulty: Easy

// Approach:
// 1. Instead of generating every odd-length subarray, calculate how many
//    odd-length subarrays contain each element.
// 2. For an element at index i:
//    - Number of possible starting positions = i + 1.
//    - Number of possible ending positions = n - i.
// 3. Multiply both to get the total number of subarrays containing arr[i].
// 4. Exactly half (rounded up) of these subarrays have odd length.
// 5. Calculate oddCount = (total + 1) / 2.
// 6. Add arr[i] * oddCount to the answer.
// 7. Return the total contribution.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            int total = (i + 1) * (n - i);
            int oddCount = (total + 1) / 2;

            sum += arr[i] * oddCount;
        }


        return sum;
    }
}
