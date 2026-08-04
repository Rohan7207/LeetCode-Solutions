// Problem: Largest Perimeter Triangle
// Link: https://leetcode.com/problems/largest-perimeter-triangle/
// Difficulty: Easy

// Approach:
// A triangle can be formed only if the triangle inequality
// holds:
//
//      a + b > c
//
// where c is the largest side.
//
// First, sort the array in non-decreasing order so that for
// every three consecutive elements:
//
//      nums[i-2], nums[i-1], nums[i]
//
// the largest side is nums[i].
//
// Since we need the largest possible perimeter, start checking
// triples from the end of the sorted array because they contain
// the largest side lengths.
//
// For every triple:
//
// - Check whether:
//       nums[i-2] + nums[i-1] > nums[i]
//
// - If true, these three sides form a valid triangle.
//
// - Since we are traversing from the largest values downward,
//   this is the first valid triangle with the maximum possible
//   perimeter. Return its perimeter immediately.
//
// If no valid triple satisfies the triangle inequality,
// return 0.

// Time Complexity:
// O(n log n)      // Sorting dominates
//
// Space Complexity:
// O(1) or O(log n)
// (depends on the sorting implementation)


class Solution {
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);

        for (int i = nums.length - 1; i >= 2; i--) {
            // Triangle inequality condition: sum of two smaller sides > largest side
            if (nums[i - 2] + nums[i - 1] > nums[i]) {
                return nums[i - 2] + nums[i - 1] + nums[i];
            }
        }

        return 0;
    }
}
