// Problem: Find Lucky Integer in an Array
// Link: https://leetcode.com/problems/find-lucky-integer-in-an-array/
// Difficulty: Easy

// Approach:
// Use a frequency array to count how many times each number appears.
//
// 1. Create a frequency array where:
//      count[x] = number of times x appears in arr.
//
// 2. Traverse the array and increment count[num].
//
// 3. Traverse possible values from largest to smallest.
//
// 4. A number i is a lucky integer if:
//      count[i] == i
//
// 5. Since we traverse from largest to smallest, the first matching
//    value is automatically the largest lucky integer.
//
// 6. If no value satisfies the condition, return -1.

// Time Complexity: O(n + 500) → O(n)
// Space Complexity: O(500) → O(1)


class Solution {
    public int findLucky(int[] arr) {
        int[] count = new int[501];

        for (int num : arr) {
            count[num]++;
        }

        for (int i = count.length - 1; i > 0; i--) {
            if (count[i] != 0 && count[i] == i) {
                return i;
            }
        }

        return -1;
    }
}
