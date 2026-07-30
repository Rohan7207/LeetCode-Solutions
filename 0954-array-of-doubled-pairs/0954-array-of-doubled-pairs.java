// Problem: Array of Doubled Pairs
// Link: https://leetcode.com/problems/array-of-doubled-pairs/
// Difficulty: Medium

// Approach:
// Every number x must be paired with exactly one unused value 2*x.
//
// The main challenge is handling negative numbers.
// For example:
//
//      -2 -> -4
//
// If we process larger absolute values first (like -4 before -2),
// we would incorrectly search for -8 and fail.
//
// Therefore, sort the numbers by their absolute value so that
// smaller magnitudes are always processed before their doubles.
//
// Next, build a frequency map to keep track of how many unused
// occurrences of each number remain.
//
// Traverse the sorted array:
//
// 1. If the current number has already been used (frequency becomes 0),
//    skip it.
//
// 2. Otherwise, check whether an unused double (2*x) exists.
//
// 3. If no unused double exists, pairing is impossible, so return false.
//
// 4. Otherwise, consume one occurrence of x and one occurrence of 2*x
//    by decrementing their frequencies.
//
// If every number is successfully paired, return true.

// Time Complexity:
// O(n log n)
// (Sorting dominates; frequency map operations are O(1) on average.)
//
// Space Complexity:
// O(n)
// (Frequency map and Integer array.)


class Solution {
    public boolean canReorderDoubled(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int n = arr.length;

        Integer[] nums = new Integer[n];

        for (int i = 0; i < n; i++) {
            nums[i] = arr[i];
        }

        Arrays.sort(nums, (a, b) -> Integer.compare(Math.abs(a), Math.abs(b)));

        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        for (int x : nums) {
            if (freqMap.get(x) == 0) {
                continue;
            }

            // Is there at least one unused double available
            if (freqMap.getOrDefault(2 * x, 0) == 0) {
                return false;
            }

            freqMap.put(x, freqMap.get(x) - 1);
            freqMap.put(2 * x, freqMap.get(2 * x) - 1);
        }

        return true;
    }
}
