class Solution {
    public int missingNumber(int[] nums) {
        int res = 0;

        //Range
        for (int i = 0; i <= nums.length; i++) {
            res ^= i;
        }

        //Values
        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i];
        }

        return res;
    }
}

//Approach 2
//We can use XOR operator with O(n) and O(1)

/*
     We can use hashset but it takes space of O(n)
        int res = nums.length;
        for(int i = 0; i < nums.length; i++){
            res += i - nums[i];
        }

        return res;
*/