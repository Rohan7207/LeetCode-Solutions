// Problem : Longest Increasing Subsequence
// Link : https://leetcode.com/problems/longest-increasing-subsequence/
// Difficulty : Medium

// Approach:
// Maintain a list 'sub' where:
// sub[i] represents the smallest possible
// tail of an increasing subsequence of
// length i + 1.
// For every number:
//     - If it is greater than the last
//       element of sub,
//       extend the subsequence.
//     - Otherwise find the first element
//       greater than or equal to it using
//       binary search and replace it.
// Replacing does not change LIS length,
// but creates a better opportunity for
// future elements to form longer subsequences.
// The size of sub is the length of LIS.

// Time Complexity: O(n log n)
// Space Complexity: O(n)


class Solution {
    public int lengthOfLIS(int[] nums) {
        ArrayList<Integer> sub = new ArrayList<>();
        sub.add(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > sub.get(sub.size() - 1)) {
                sub.add(nums[i]);
            } else {
                int j = binarySearch(sub, nums[i]);
                sub.set(j, nums[i]);
            }
        }

        return sub.size();
    }

    private int binarySearch(ArrayList<Integer> sub, int num) {
        int left = 0;
        int right = sub.size() - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (sub.get(mid) == num) {
                return mid;
            } else if (sub.get(mid) < num) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
