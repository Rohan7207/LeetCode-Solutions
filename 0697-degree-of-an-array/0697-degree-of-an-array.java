// Problem: Degree of an Array
// Link: https://leetcode.com/problems/degree-of-an-array/
// Difficulty: Easy

// Approach:
// Find the degree of the whole array and then find the smallest
// subarray having the same degree.
// Use HashMap to store:
//     1. Frequency of each number
//     2. First occurrence index of each number
//     3. Last occurrence index of each number
// Step 1:
// Traverse the array:
//     - Increase frequency of current number.
//     - Store first occurrence only once.
//     - Update last occurrence every time.
// Step 2:
// Find the degree of the array.
//     Degree = maximum frequency among all elements.
// Step 3:
// Check only the elements whose frequency is equal to degree.
//     For such elements:
//         The smallest subarray containing all occurrences is:
//             lastIndex - firstIndex + 1
//     Take the minimum length among them.
// Why this works:
// The required subarray must contain all occurrences of at least
// one element having the maximum frequency.
// The shortest possible range containing all those occurrences
// is from its first appearance to its last appearance.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> first = new HashMap<>();
        Map<Integer, Integer> last = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
            if (!first.containsKey(nums[i])) {
                first.put(nums[i], i);
            }
            last.put(nums[i], i);
        }

        int degree = 0;

        for (int count : freq.values()) {
            degree = Math.max(degree, count);
        }

        int ans = nums.length;

        for (int num : freq.keySet()) {
            if (freq.get(num) == degree) {
                int len = last.get(num) - first.get(num) + 1;

                ans = Math.min(ans, len);
            }
        }

        return ans;
    }
}
