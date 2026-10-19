// Problem: Can Make Airthmetic Progression From Sequence
// Link: https://leetcode.com/problems/can-make-arithmetic-progression-from-sequence/
// Difficulty: Easy

// Approach:
// 1. Sort the array in ascending order.
// 2. If the array contains exactly two elements, return true,
//    because any two elements form an arithmetic progression.
// 3. Calculate the absolute difference between the first two
//    elements as the common difference.
// 4. Traverse the array from index 2 to n - 1.
// 5. For each adjacent pair, calculate the absolute difference.
// 6. If any difference differs from the common difference,
//    return false.
// 7. If all adjacent differences match, return true.

// Time Complexity: O(n log n), due to sorting.
// Space Complexity: O(log n) auxiliary space for Java's
// primitive-array sorting implementation.


class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        int diff = Math.abs(arr[0] - arr[1]);

        if (n == 2) {
            return true;
        }

        for (int i = 2; i < n; i++) {
            if (Math.abs(arr[i] - arr[i - 1]) != diff) {
                return false;
            }
        }

        return true;
    }
}
