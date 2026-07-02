class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int count = 0;
        int left = 0;

        int product = 1;
        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];

            while (product >= k) {
                product /= nums[left];
                left++;
            }

            count += right - left + 1;
        }

        return count;
    }
}

/*
    int count = 0;

    for (int i = 0; i < nums.length; i++) {
        if (nums[i] < k) {
            count++;
        }

        int product = nums[i];
        for (int j = i + 1; j < nums.length; j++) {
            product *= nums[j];

            if (product >= k)
                break;

            count++;
        }
    }

    return count;
*/