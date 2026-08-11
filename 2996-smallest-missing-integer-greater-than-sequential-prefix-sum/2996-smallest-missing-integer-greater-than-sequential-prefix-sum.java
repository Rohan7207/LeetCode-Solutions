class Solution {
    public int missingInteger(int[] nums) {
        Set<Integer> set = new HashSet<>();
        boolean flag = true;
        int prefixSum = nums[0];
        set.add(nums[0]);

        for(int i = 1; i < nums.length; i++) {
            if(!(nums[i] == nums[i - 1] + 1)) {
                flag = false;
            } 

            if(flag) prefixSum += nums[i];

            set.add(nums[i]);
        }

        while(set.contains(prefixSum)) {
            prefixSum++;
        }

        return prefixSum;
    }
}