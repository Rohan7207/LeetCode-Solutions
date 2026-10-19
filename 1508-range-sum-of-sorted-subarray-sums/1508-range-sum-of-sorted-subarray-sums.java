class Solution {

    private int MOD = 1000000007;

    public int rangeSum(int[] nums, int n, int left, int right) {
        int[] subArray = new int[n * (n + 1) / 2];

        int idx = 0;
        for(int i = 0; i < n; i++) {
            int sum = 0;
            for(int j = i; j < n; j++) {
                sum += nums[j];
                subArray[idx++] = sum;
            }
        }

        Arrays.sort(subArray);

        int ans = 0;
        for(int i = left - 1; i < right; i++) {
            ans = (ans + subArray[i]) % MOD;
        }

        return ans;
    }
}