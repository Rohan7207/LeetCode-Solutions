// Problem: Rank Transform of an Array
// Link: https://leetcode.com/problems/rank-transform-of-an-array/?envType=daily-question&envId=2026-07-12
// Difficulty: Easy

// Approach:
// The rank of an element depends on its position in the
// sorted order, not its original index.
// First, create a copy of the original array.
// This preserves the original order while allowing us
// to sort the copied array.
// Sort the copied array in ascending order.
// Traverse the sorted array and assign ranks:
//     • Start rank = 1.
//     • If the current number has not been assigned a rank,
//       store (number -> rank) in a HashMap.
//     • Ignore duplicate values because equal elements
//       must receive the same rank.
// Finally, traverse the original array.
// Replace every element with its corresponding rank
// stored in the HashMap.

// Time Complexity: O(n log n)
// Space Complexity: O(n)


class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int[] temp = arr.clone();
        Arrays.sort(temp);

        Map<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;

        for (int num : temp) {
            if (!rankMap.containsKey(num)) {
                rankMap.put(num, rank);
                rank++;
            }
        }

        int n = arr.length;
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            ans[i] = rankMap.get(arr[i]);
        }

        return ans;
    }
}
