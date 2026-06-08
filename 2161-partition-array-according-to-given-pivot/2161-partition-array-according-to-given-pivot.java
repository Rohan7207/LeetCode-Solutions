class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int[] ans = new int[nums.length];
        int index = 0;

        for (int num : nums) {
            if (num < pivot) ans[index++] = num;
        }

        for (int num : nums) {
            if (num == pivot) ans[index++] = num;
        }

        for (int num : nums) {
            if (num > pivot) ans[index++] = num;
        }

        return ans;
    }
}

/*
    int[] ans = new int[nums.length];
    int lessCount = 0;
    int equalCount = 0;

    for(int num : nums) {
        if(num < pivot) lessCount++;
        else if(num == pivot) equalCount++;
    }

    // Reserve the positions in array for less, equal and greater
    int lessIndex = 0;
    int equalIndex = lessCount;
    int greaterIndex = lessCount + equalCount;

    for(int num : nums) {
        if(num < pivot) {
            ans[lessIndex++] = num;
        } else if(num == pivot) {
            ans[equalIndex++] = num;
        } else {
            ans[greaterIndex++] = num;
        }
    }

    return ans;
*/