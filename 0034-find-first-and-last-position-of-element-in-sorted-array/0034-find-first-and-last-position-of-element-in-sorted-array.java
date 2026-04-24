class Solution {
    public int[] searchRange(int[] nums, int target) {
        int firstOccurance = findIndex(nums, target, true);

        if(firstOccurance == -1) {
            return new int[]{-1, -1};
        }

        int secondOccurance = findIndex(nums, target, false);

        return new int[]{firstOccurance, secondOccurance};
    }

    private int findIndex(int[] nums, int target, boolean isFirst) {
        int left = 0;
        int right = nums.length - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2;

            if(nums[mid] == target) {
            //Now move the pointers and search again based on first or second occurenace search
                if(isFirst) {
                    //Check if mid - 1 is also same number as mid
                    if(mid == left || nums[mid - 1] != target) {
                        return mid;
                    }

                    // Search on left side
                    right = mid - 1;
                } else {
                    //Check if mid + 1 is also same number as mid 
                    if(mid == right || nums[mid + 1] != target) {
                        return mid;
                    } 

                    // Search on right side
                    left = mid + 1;
                }
            } else if(nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}

/*
    O(n) -
        if (nums.length == 0) {
            return new int[] { -1, -1 };
        }

        int first = -1;
        int last = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (first == -1) {
                    first = i; //First occurence
                }
                last = i;
            }
        }
        
        return new int[] { first, last };
*/