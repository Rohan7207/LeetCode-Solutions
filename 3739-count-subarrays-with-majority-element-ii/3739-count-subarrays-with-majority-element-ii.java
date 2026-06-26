// Problem: Count Subarrays With Majority  Element II
// Link: https://leetcode.com/problems/count-subarrays-with-majority-element-ii/?envType=daily-question&envId=2026-06-26
// Difficulty: Hard

// Approach:
// The goal is to count subarrays where the target element appears more than
// half of the subarray length.
// Brute force checks every subarray, which takes O(n²).
// Since n can be large, we need an O(n) solution.
// Convert the problem into a prefix sum problem:
// Treat:
//      target element  -> +1
//      other elements  -> -1
// Now if a subarray has target as majority:
//      target count > non-target count
// then the transformed subarray sum will be positive.
// So we need to count subarrays with sum > 0.
// Maintain a running prefix sum:
//      prefixSum = target balance till current index
// For a subarray:
//      sum = prefix[right] - prefix[left]
// It is valid when:
//      prefix[right] > prefix[left]
// Instead of storing all prefix sums, use a frequency array because the
// prefix sum range is limited from -n to +n.
// Since array indexes cannot be negative, shift every prefix sum by n:
//      actual prefix value + n = array index
// Steps:
// 1. Create a frequency array to store occurrence count of prefix sums.
// 2. Initialize prefix sum 0 as already seen once because an empty prefix exists.
// 3. Traverse the array:
//      - If current element is target:
//              increase balance by 1
//      - Otherwise:
//              decrease balance by 1
// 4. Before storing the current prefix sum:
//      Count previous prefix sums that can form a valid majority subarray.
// 5. Store the current prefix sum occurrence for future elements.
// 6. Return the total count.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;

        int[] prefix = new int[2 * n + 1];
        prefix[n] = 1;
        int cnt = n;
        long ans = 0;
        long prefixSum = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == target) {
                prefixSum += prefix[cnt];
                cnt++;
                prefix[cnt]++;
            } else {
                cnt--;
                prefixSum -= prefix[cnt];
                prefix[cnt]++;
            }

            ans += prefixSum;
        }

        return ans;
    }
}
