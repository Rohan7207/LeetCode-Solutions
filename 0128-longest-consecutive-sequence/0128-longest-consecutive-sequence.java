// Problem: Longest Consecutive Sequence
// Link: https://leetcode.com/problems/longest-consecutive-sequence/
// Difficulty: Medium

// Approach:
// Store all elements in a HashSet for O(1) lookup.
// Traverse through the set:
//     - Check whether current number is the start
//       of a sequence by verifying that num - 1
//       does not exist in the set.
//     - If it is a sequence start:
//         - Continuously check for next consecutive
//           numbers using currNum + 1.
//         - Count the sequence length.
//     - Update the maximum sequence length found.
// Return the longest consecutive sequence length.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        // Step 1: Add all numbers to a HashSet for O(1) lookups
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestSequence = 0;

        // Step 2: Iterate through the set to find sequence starts
        for (int num : numSet) {
            // Check if 'num' is the start of a sequence
            // (i.e., num - 1 is NOT in the set)
            if (!numSet.contains(num - 1)) {
                int currNum = num;
                int currSequence = 1;

                // Step 3: Expand the sequence as far as possible
                while (numSet.contains(currNum + 1)) {
                    currNum++;
                    currSequence++;
                }

                // Step 4: Keep track of the maximum length found
                longestSequence = Math.max(longestSequence, currSequence);
            }
        }

        return longestSequence;
    }
}
