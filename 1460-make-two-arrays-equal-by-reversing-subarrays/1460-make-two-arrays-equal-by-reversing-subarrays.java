// Problem: Make Two Arrays Equal by Reversing Subarrays
// Link: https://leetcode.com/problems/make-two-arrays-equal-by-reversing-subarrays/
// Difficulty: Easy

// Approach:
// 1. The operation allows us to reverse any subarray, so the order of
//    elements can be rearranged arbitrarily.
// 2. Therefore, `target` and `arr` can be made equal if they contain
//    exactly the same elements with the same frequencies.
// 3. Use a frequency array to count how many times each value appears in `target`.
// 4. Traverse `arr`:
//      - If count[num] == 0, arr contains an extra occurrence → return false.
//      - Otherwise, consume one occurrence by decrementing count[num].
// 5. If every element in `arr` can be matched, return true.

// Time Complexity: O(n)
// Space Complexity: O(1) because the frequency array has fixed size 1001.


class Solution {
    public boolean canBeEqual(int[] target, int[] arr) {
        int[] count = new int[1001];

        for (int num : target) {
            count[num]++;
        }

        for (int num : arr) {
            if (count[num] == 0) {
                return false;
            }

            count[num]--;
        }

        return true;
    }
}
