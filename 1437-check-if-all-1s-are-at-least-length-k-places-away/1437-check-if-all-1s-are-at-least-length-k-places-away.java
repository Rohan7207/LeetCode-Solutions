class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        boolean firstOne = true;
        int prevOne = -1;

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 1 && firstOne) {
                prevOne = i;
                firstOne = false;
                continue;
            }

            if(nums[i] == 1) {
                int dist = i - prevOne - 1;

                if(dist < k) {
                    return false;
                }

                prevOne = i;
            }
        }

        return true;
    }
}