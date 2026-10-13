class Solution {
    public long maxAlternatingSum(int[] nums) {
        long ans = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            nums[i] *= nums[i];
        }

        Arrays.sort(nums);
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            if (left == right) {
                ans += nums[left];
                break;
            }

            ans += nums[right] - nums[left];
            left++;
            right--;
        }
        
        return ans;
    }
}


/*
    public long maxAlternatingSum(int[] nums) {
        long ans = 0;
        int n = nums.length;

        for(int i = 0; i < n; i++) {
            if(nums[i] < 0) {
                nums[i] = -(nums[i]);
            }
        }

        Arrays.sort(nums);
        int left = 0;
        int right = n - 1;

        while(left <= right) {
            if(left == right) {
                ans += (nums[left] * nums[left]);
                break;
            }

            ans += (nums[right] * nums[right]) - (nums[left] * nums[left]);
            left++;
            right--;
        }

        return ans;
    }
*/