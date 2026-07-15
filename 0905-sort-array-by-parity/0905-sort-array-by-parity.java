class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int left = 0;
        int right = nums.length - 1;


        while (left <= right) {
            while (left < right && nums[left] % 2 == 0) {
                left++;
            }

            while (left < right && nums[right] % 2 == 1) {
                right--;
            }

            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;

            left++;
            right--;
        }

        return nums;
    }
}

/*
     while (l < r) {
            if (nums[l] % 2 == 0) {
                l++;
            } else {
                int temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;
                r--;
            }
        }

        return nums;
*/