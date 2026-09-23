// Problem: Create Target Array in the Given Order
// Link: https://leetcode.com/problems/create-target-array-in-the-given-order/
// Difficulty: Easy

// Approach:
//
// 1. Start with an empty ArrayList because we need to insert elements
//    at arbitrary positions.
//
// 2. Traverse `nums` and `index` from left to right.
//
// 3. For every i:
//       nums[i]  → value to insert
//       index[i] → position where it should be inserted
//
// 4. Use:
//       target.add(index[i], nums[i])
//
//    ArrayList automatically shifts all existing elements at that
//    index and after it one position to the right.
//
// 5. After all insertions, convert the ArrayList into an int[].

// Time Complexity: O(n²)
// Space Complexity: O(n)


class Solution {
    public int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> target = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            target.add(index[i], nums[i]);
        }

        int len = target.size();
        int[] ans = new int[len];

        for (int i = 0; i < len; i++) {
            ans[i] = target.get(i);
        }

        return ans;
    }
}
