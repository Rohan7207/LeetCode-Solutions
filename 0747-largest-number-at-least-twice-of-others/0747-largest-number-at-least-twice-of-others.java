class Solution {
    public int dominantIndex(int[] nums) {
        int max = -1;
        int secondMax = -1;
        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            if (max < nums[i]) {
                secondMax = max;
                max = nums[i];
                index = i;
            } else if (secondMax < nums[i]) {
                secondMax = nums[i];
            }
        }

        if (max >= 2 * secondMax) {
            return index;
        }

        return -1;
    }
}

/*
    int max = nums[0];
    int maxIndex = 0;

    for (int i = 1; i < nums.length; i++) {
        if (max < nums[i]) {
            max = nums[i];
            maxIndex = i;
        }
    }

    for (int i = 0; i < nums.length; i++) {
        if (nums[i] != max && max < 2 * nums[i]) {
            return -1;
        }
    }

    return maxIndex;
*/