class Solution {
    public int rob(int[] nums) {
        int rob1 = 0;
        int rob2 = 0;
        int max = 0;

        for(int i = 0; i < nums.length; i++) {
            max = Math.max(rob1 + nums[i], rob2);
            rob1 = rob2;
            rob2 = max;
        }

        return max;
    }
}

/* 
    rob1: Represents the max money robbed up to two houses ago 
    rob2: Represents the max money robbed up to the previous house 
    max: The new maximum total including the current house.
*/