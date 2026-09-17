class Solution {
    public boolean findSubarrays(int[] nums) {
        int currSum = 0;
        Set<Integer> seen = new HashSet<>();

        for(int i = 0; i < nums.length - 1; i++) {
            currSum = nums[i] + nums[i + 1];

            if(seen.contains(currSum)) {
                return true;
            }

            seen.add(currSum);
        }

        return false;
    }
}