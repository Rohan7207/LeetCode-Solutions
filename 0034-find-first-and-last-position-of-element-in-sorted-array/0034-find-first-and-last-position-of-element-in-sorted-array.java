// Problem: Find First and Last Position of Element in Sorted Array
// Link: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
// Difficulty: Medium

// Approach:
// Use binary search to find the first and last occurrence of the target.
// Create a helper function findIndex() to search for either the first
// or last occurrence based on a flag.
// During binary search:
//     - If target is found:
//         - For first occurrence, continue searching on the left side.
//         - For last occurrence, continue searching on the right side.
//     - Otherwise, move left or right pointers based on comparison with target.
// Return [-1, -1] if the target is not found.

// Time Complexity: O(log n)
// Space Complexity: O(1)

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
