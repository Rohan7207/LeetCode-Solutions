// Problem: Make Lexicographically Smallest Array by Swapping Elements
// Link: https://leetcode.com/problems/make-lexicographically-smallest-array-by-swapping-elements/?envType=daily-question&envId=2026-08-29
// Difficulty: Medium

// Approach:
// Use Sorting + Grouping + Greedy.
//
// 1. Clone and sort the array.
//
//       numsSorted = sorted version of nums
//
//    Sorting lets us identify which values can be connected
//    through the allowed swap limit.
//
// 2. Divide the sorted array into groups.
//
//    If the difference between two consecutive sorted values
//    is <= limit, they belong to the same group.
//
//    If:
//
//       numsSorted[i] - numsSorted[i - 1] > limit
//
//    start a new group.
//
// 3. Why can elements inside the same group be rearranged freely?
//
//    Even if two elements are not directly within `limit`,
//    they can be connected through intermediate elements.
//    Therefore, all values in the same group can be rearranged
//    among their original positions.
//
// 4. Store which group each value belongs to:
//
//       numToGroup
//
//    and store the sorted values of each group:
//
//       groupToDeque
//
// 5. Iterate through the ORIGINAL array.
//
//    For each nums[i]:
//
//       - Find its group.
//       - Take the smallest remaining value from that group.
//
//    Since the group's values are already sorted, `pollFirst()`
//    gives the smallest available value.
//
// 6. This greedily puts the smallest possible value at every
//    position while respecting which values can be swapped.
//
// 7. Return the modified array.

// Time Complexity: O(n log n)
//    - Clone: O(n)
//    - Sorting: O(n log n)
//    - Grouping: O(n)
//    - Final traversal: O(n)
//
// Space Complexity: O(n)
//    - Sorted array
//    - Maps
//    - Deques


class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[] numsSorted = nums.clone();

        Arrays.sort(numsSorted);

        int currGroup = 0;
        Map<Integer, Integer> numToGroup = new HashMap<>();
        Map<Integer, Deque<Integer>> groupToDeque = new HashMap<>();

        numToGroup.put(numsSorted[0], currGroup);
        groupToDeque.put(currGroup, new ArrayDeque<>());
        groupToDeque.get(currGroup).add(numsSorted[0]);

        for (int i = 1; i < n; i++) {
            if ((numsSorted[i] - numsSorted[i - 1]) > limit) {
                currGroup++;
            }

            numToGroup.put(numsSorted[i], currGroup);

            groupToDeque.putIfAbsent(currGroup, new ArrayDeque<>());
            groupToDeque.get(currGroup).add(numsSorted[i]);
        }

        for (int i = 0; i < n; i++) {
            int group = numToGroup.get(nums[i]);

            nums[i] = groupToDeque.get(group).pollFirst();
        }

        return nums;
    }
}
