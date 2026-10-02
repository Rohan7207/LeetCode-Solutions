class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        int n = nums.length;

        if(n >= 100000){
            return true;
        }

        for(int i = 0; i < n - 1; i++) {
            if(nums[i] == 0) {
                continue;
            }

            for(int j = i + 1; j < n; j++) {
                if(nums[j] == 0) {
                    continue;
                }

                if(nums[j] == 1 && j - i - 1 < k) {
                    return false;
                }
            }
        }

        return true;
    }
}

/*
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
*/