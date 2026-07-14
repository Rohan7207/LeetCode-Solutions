// Problem: Fruits Into Baskets
// Link: https://leetcode.com/problems/fruit-into-baskets/
// Difficulty: Medium

// Approach:
// We need to pick fruits from a contiguous sequence of trees
// while using at most two baskets.
// This is equivalent to finding the longest contiguous subarray
// containing at most two distinct fruit types.
//
// Use a sliding window:
//     • Expand the window by moving the right pointer.
//     • Store the frequency of each fruit type inside the
//       current window using a HashMap.
// If the window contains more than two distinct fruit types,
// it becomes invalid.
// Shrink the window by moving the left pointer:
//     • Decrease the frequency of fruits[left].
//     • Remove it from the map when its frequency becomes zero.
//     • Continue shrinking until only two fruit types remain.
//
// After every valid window, update the maximum window length.

// Time Complexity: O(n)
//
// Space Complexity:
//     O(1)
//     (The HashMap stores at most 3 fruit types before shrinking,
//      so the extra space is constant.)


class Solution {
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int ans = 0;

        int left = 0;
        for (int right = 0; right < fruits.length; right++) {
            freqMap.put(fruits[right], freqMap.getOrDefault(fruits[right], 0) + 1);

            while (freqMap.size() > 2) {
                int currFreq = freqMap.get(fruits[left]);
                currFreq--;

                if (currFreq == 0) {
                    freqMap.remove(fruits[left]);
                } else {
                    freqMap.put(fruits[left], currFreq);
                }

                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}
