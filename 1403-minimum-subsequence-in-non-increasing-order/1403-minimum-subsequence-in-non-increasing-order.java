// Problem: Minimum Subsequence in Non-Increasing Order
// Link: https://leetcode.com/problems/minimum-subsequence-in-non-increasing-order/
// Difficulty: Easy

// Approach:
//
// 1. Calculate the total sum of all elements.
//
// 2. Sort nums in ascending order.
//
// 3. Start selecting elements from the largest element.
//
// 4. Maintain currSum = sum of selected elements.
//
// 5. After selecting each element, the remaining sum is:
//      total - currSum
//
// 6. Stop as soon as:
//      currSum > total - currSum
//
//    This guarantees that the selected subsequence has a strictly
//    greater sum than the remaining elements.
//
// 7. Since we pick from largest to smallest, the answer is already
//    in non-increasing order.

// Time Complexity: O(n log n)
// Space Complexity: O(n) for the returned answer


class Solution {
    public List<Integer> minSubsequence(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int total = 0;

        for (int num : nums) {
            total += num;
        }

        Arrays.sort(nums);
        int n = nums.length;

        int currSum = 0;

        for (int i = n - 1; i >= 0; i--) {
            currSum += nums[i];
            ans.add(nums[i]);

            if (currSum > total - currSum) {
                break;
            }
        }
        
        return ans;
    }
}
