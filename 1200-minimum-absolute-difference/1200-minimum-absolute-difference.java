// Problem: Minimum Absolute Difference
// Link: https://leetcode.com/problems/minimum-absolute-difference/
// Difficulty: Easy

// Approach:
// Use Sorting + Greedy/One-Pass comparison.
//
// 1. Sort the array in ascending order.
//
// 2. After sorting, the minimum absolute difference must occur
//    between two adjacent elements.
//
// 3. Compare every adjacent pair:
//
//      arr[i + 1] - arr[i]
//
// 4. Maintain `minDiff`, the smallest difference found so far.
//
// 5. If the current difference is smaller than `minDiff`:
//      - Update `minDiff`
//      - Clear the previous answer pairs
//      - Add the current pair
//
// 6. If the current difference equals `minDiff`:
//      - Add the current pair as another valid answer.
//
// 7. Return all pairs.

// Time Complexity: O(n log n)  → sorting dominates
// Space Complexity: O(n)      → sorting/answer storage


class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> ans = new ArrayList<>();

        // Start with the maximum possible value so any first pair updates it
        int minDiff = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length - 1; i++) {
            int currDiff = arr[i + 1] - arr[i]; // Since array is sorted, arr[i+1] >= arr[i]

            if (currDiff < minDiff) {
                // Found a smaller difference! Reset the list and update minDiff
                minDiff = currDiff;
                ans.clear();
                ans.add(List.of(arr[i], arr[i + 1]));
            } else if (currDiff == minDiff) {
                // Found another pair with the exact same minimum difference
                ans.add(List.of(arr[i], arr[i + 1]));
            }
        }

        return ans;
    }
}
