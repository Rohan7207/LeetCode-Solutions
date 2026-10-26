// Problem: Kth Missing Positive Number
// Link: https://leetcode.com/problems/kth-missing-positive-number/
// Difficulty: Easy

// Approach:
// 1. Initialize idx = 0 to track the current array element.
// 2. Initialize counter = 1 to examine positive integers starting from 1.
// 3. Continue while k > 0:
//    a. If arr[idx] == counter, the number exists in the array;
//       move idx to the next element.
//    b. Otherwise, counter is a missing positive integer;
//       decrement k.
//    c. Increment counter to examine the next integer.
// 4. When k becomes 0, counter - 1 is the kth missing positive integer.
// 5. Return counter - 1.

// Time Complexity: O(n + k) in the worst case
// Space Complexity: O(1)


class Solution {
    public int findKthPositive(int[] arr, int k) {
        int n = arr.length;
        int idx = 0;
        int counter = 1;

        while (k > 0) {
            if (idx < n && arr[idx] == counter) {
                idx++;
            } else {
                k--;
            }

            counter++;
        }

        return counter - 1;
    }
}
