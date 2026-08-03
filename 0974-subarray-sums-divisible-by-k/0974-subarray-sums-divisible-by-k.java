class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int prefixSum = 0;
        int ans = 0;

        freqMap.put(prefixSum, 1);

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int rem = prefixSum % k;

            if (rem < 0) {
                rem += k;
            }

            ans += freqMap.getOrDefault(rem, 0);
            freqMap.put(rem, freqMap.getOrDefault(rem, 0) + 1);
        }

        return ans;
    }
}