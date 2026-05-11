class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }

        return res;
    }
}

//We use XOR gate
//In XOR the X XOR X cancels out for ex 1 XOR 2 XOR 3 XOR 3 XOR 2 
// both 2 and 3 cancels leads to 1 which leads O(n) and O(1)

/*
    Arrays.sort(nums);
    for(int i = 1; i < nums.length; i += 2){
        if(nums[i] != nums[i - 1]){
            return nums[i - 1];
        }
    }
    return nums[nums.length - 1];
*/