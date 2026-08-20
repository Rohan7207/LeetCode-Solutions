// Problem: Relative Sort Array
// Link: https://leetcode.com/problems/relative-sort-array/
// Difficulty: Easy

// Approach:
// Use a frequency array to count how many times each number appears.
//
// 1. Create a frequency array:
//      freq[x] = frequency of x in arr1.
//
// 2. Traverse arr2.
//    For every number x in arr2, place all its occurrences into arr1
//    in the same order as arr2.
//
// 3. Maintain an index pointer to know where to place the next value.
//
// 4. After processing arr2, some elements of arr1 are still left.
//    Traverse the frequency array from 0 to 1000.
//
// 5. This naturally places all remaining elements in ascending order.
//
// 6. Return arr1.

// Time Complexity: O(n + m + 1001) → O(n + m)
// Space Complexity: O(1001) → O(1)


class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] freq = new int[1001];

        for (int num : arr1) {
            freq[num]++;
        }

        int index = 0;

        for (int x : arr2) {
            while (freq[x] > 0) {
                arr1[index++] = x;
                freq[x]--;
            }
        }

        for (int i = 0; i < freq.length; i++) {
            while (freq[i] > 0) {
                arr1[index++] = i;
                freq[i]--;
            }
        }

        return arr1;
    }
}
