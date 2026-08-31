// Problem: Divide Array in Sets of K Consecutive Numbers
// Link: https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
// Difficulty: Medium

// Approach:
// Use Sorting + HashMap Frequency + Greedy.
//
// 1. First check whether the array can be divided into groups of size `k`.
//    If `nums.length % k != 0`, return false.
//
// 2. Sort the array so that the smallest available number can always
//    be found from left to right.
//
// 3. Store the frequency of every number in a HashMap.
//
// 4. Traverse the sorted array.
//
// 5. If `freq.get(num) == 0`, all occurrences of `num` have already
//    been used, so skip it.
//
// 6. Otherwise, `num` is the smallest currently unused number.
//    It must be the starting number of a consecutive group.
//
// 7. Try to form:
//
//      num, num + 1, ..., num + k - 1
//
//    For every required number:
//      - If its frequency is 0, the group cannot be formed → return false.
//      - Otherwise, decrease its frequency by 1.
//
// 8. Continue until all elements are used successfully.
//
// 9. If every group is formed, return true.

// Time Complexity: O(n log n + n * k)
// Space Complexity: O(n)


class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length % k != 0) {
            return false;
        }

        Arrays.sort(nums);
        Map<Integer, Integer> freq = new HashMap<>();

        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        for (int num : nums) {
            // num is used already
            if (freq.get(num) == 0) {
                continue;
            }

            // num is the smallest currently unused number
            for (int j = 0; j < k; j++) {
                int curr = num + j;

                if (freq.getOrDefault(curr, 0) == 0) {
                    return false;
                }

                freq.put(curr, freq.get(curr) - 1);
            }

        }

        return true;
    }
}
