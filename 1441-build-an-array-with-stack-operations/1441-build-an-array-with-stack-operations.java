// Problem: Build an Array With Stack Operations
// Link: https://leetcode.com/problems/build-an-array-with-stack-operations/
// Difficulty: Medium

// Approach:
// 1. We process the numbers from 1 to n in increasing order.
//
// 2. For every number, we must first perform "Push" because
//    the number is read from the stream.
//
// 3. If the current number equals target[idx], we want to keep it,
//    so we only move idx to the next target element.
//
// 4. If the current number is not target[idx], we don't need it.
//    Push it first and then immediately perform "Pop".
//
// 5. Continue until we have built the entire target array.
//
// 6. We don't need to process numbers after target[target.length - 1],
//    because the target is already complete.

// Time Complexity: O(n)
// Space Complexity: O(n)  // output list


class Solution {
    public List<String> buildArray(int[] target, int n) {
        List<String> ans = new ArrayList<>();
        int take = 1;
        int idx = 0;

        while (idx < target.length && take <= n) {
            ans.add("Push");

            if (take == target[idx]) {
                idx++;
            } else {
                ans.add("Pop");
            }

            take++;
        }

        return ans;
    }
}
