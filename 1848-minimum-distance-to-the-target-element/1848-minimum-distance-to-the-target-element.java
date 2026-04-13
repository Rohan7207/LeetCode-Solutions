class Solution {
    public int getMinDistance(int[] nums, int target, int start) {
        // // Time = O(n) and Space = O(1)
        // int min = Integer.MAX_VALUE;

        // for(int i = 0; i < nums.length; i++) {
        //     if(nums[i] == target) {
        //         min = Math.min(min, Math.abs(start - i));
        //     }
        // }

        // return min;

        // Solution using the start where time = O(n) and space = O(1)

        if(nums[start] == target) return 0;

        int n = nums.length;
        int d = 1;  //It is no.of steps of start moving outward or inward

        while(start - d >= 0 || start + d < n) {
            if(start - d >= 0 && nums[start - d] == target) return d;
            if(start + d < n && nums[start + d] == target) return d;

            d++; 
        }

        return -1;
    }
}