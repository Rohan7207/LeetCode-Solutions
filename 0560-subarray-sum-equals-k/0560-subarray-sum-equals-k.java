// Problem: Subarray Sum Equals K
// Link: https://leetcode.com/problems/subarray-sum-equals-k/
// Difficulty: Medium

// Approach:
// Count the number of subarrays whose sum is equal to k.
// Use Prefix Sum + HashMap.
// Prefix sum represents:
//     sum of elements from index 0 to current index
// For every index j:
//     - Add nums[j] to current prefix sum
//     - To get a subarray ending at j with sum k,
//       we need some previous prefix sum:
//           currentSum - k
//     - If currentSum - k exists in map,
//       then all those previous prefix sums form
//       valid subarrays ending at j.
//     - Add their frequency to answer.
//     - Store/update current prefix sum frequency
//       in the map.
// Initialize:
//     map.put(0, 1)
// This represents empty prefix sum and helps count
// subarrays that start from index 0.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(); 
        map.put(0, 1); 
        int ans = 0, sum = 0;

        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];

            if (map.containsKey(sum - k)) {
                ans += map.get(sum - k);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return ans;
    }
}
