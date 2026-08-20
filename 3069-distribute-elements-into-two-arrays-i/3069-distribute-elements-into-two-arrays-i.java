// Problem: Distribute Elements Into Two Arrays I
// Link: https://leetcode.com/problems/distribute-elements-into-two-arrays-i/?envType=daily-question&envId=2026-08-20
// Difficulty: Easy

// Approach:
// Divide the elements into two arrays based on the comparison of
// their current last elements.
//
// 1. Put nums[0] in arr1 and nums[1] in arr2.
//
// 2. For every remaining element:
//      - If the last element of arr1 > last element of arr2,
//        put the current element into arr1.
//      - Otherwise, put it into arr2.
//
// 3. Keep last1 and last2 to track the last filled position in
//    each array.
//
// 4. Finally, copy arr1 followed by arr2 into the result array.
//
// Important:
// Allocate arr1 and arr2 with size n because one array can receive
// more than half of the elements.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;

        int[] arr1 = new int[n];
        int[] arr2 = new int[n];

        arr1[0] = nums[0];
        arr2[0] = nums[1];
        int last1 = 0;
        int last2 = 0;

        for (int i = 2; i < n; i++) {
            if (arr1[last1] > arr2[last2]) {
                last1++;
                arr1[last1] = nums[i];
            } else {
                last2++;
                arr2[last2] = nums[i];
            }
        }

        int[] res = new int[n];

        for (int i = 0; i <= last1; i++) {
            res[i] = arr1[i];
        }

        for (int i = 0; i <= last2; i++) {
            res[last1 + 1 + i] = arr2[i];
        }

        return res;
    }
}
