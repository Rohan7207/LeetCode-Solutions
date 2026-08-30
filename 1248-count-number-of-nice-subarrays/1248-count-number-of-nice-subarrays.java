class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        return countAtMost(nums, k) - countAtMost(nums, k - 1);
    }

    private int countAtMost(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        int oddCount = 0;

        int left = 0;
        for(int right = 0; right < n; right++) {
            if(nums[right] % 2 == 1) oddCount++;

            while(oddCount > k) {
                if(nums[left] % 2 == 1) oddCount--;

                left++;
            }

            ans += right - left + 1;
        }

        return ans;
    }
}

/*
    However, for this problem, we need to calculate the number of subarrays with a sum exactly k (from Approach 1), not at most k. Observe that if we calculate the number of subarrays with sum at most k and at most k-1, their difference would give us the number of subarrays with sum exactly k.
*/

/*
    O(n) and O(n)
    public int numberOfSubarrays(int[] nums, int k) {
        int currSum = 0;
        int subArrays = 0;

        Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(currSum, 1);

        for(int i = 0; i < nums.length; i++) {
            currSum += nums[i] % 2;

            if(prefixSum.containsKey(currSum - k)) {
                subArrays += prefixSum.get(currSum - k);
            }

            prefixSum.put(currSum, prefixSum.getOrDefault(currSum, 0) + 1);
        }

        return subArrays;
    }
*/