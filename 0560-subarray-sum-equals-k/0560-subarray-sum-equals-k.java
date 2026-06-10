class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(); //sum and frequency
        map.put(0, 1); //empty subarray
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

//k=sub(j)+sub(i-1) if we find sumof j then we get sub(i-1)
//key=sum value=count 