class Solution {
    public int missingInteger(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int prefixSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                prefixSum += nums[i];
            } else {
                break;
            }
        }

        for (int num : nums) {
            set.add(num);
        }

        while (set.contains(prefixSum)) {
            prefixSum++;
        }

        return prefixSum;
    }
}