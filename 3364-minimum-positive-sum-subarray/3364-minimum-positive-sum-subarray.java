class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int ans = Integer.MAX_VALUE;

        for(int i = 0; i < n; i++) {   // O(n)
            int sum = 0;
            for(int j = i; j < n && (j - i + 1 <= r); j++) {  // O(r)
                sum += nums.get(j);

                if(j - i + 1 >= l && sum > 0) {
                    ans = Math.min(ans, sum);
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans; // O(n * r) and O(1)
    }
}

/*
    Brute Force would be use try every subarray:
        for(int i = 0; i < n; i++) {
            int sum = 0;
            for(int j = 0; j < n; j++) {
                sum += nums[j];

                ans = Math.min(ans, sum);
                if(j - i + 1 > r) {
                    break;
                }
            }
        }
*/