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