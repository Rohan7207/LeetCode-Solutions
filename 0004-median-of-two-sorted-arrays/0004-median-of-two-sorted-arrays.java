// Problem: Median of Two Sorted Arrays
// Link: https://leetcode.com/problems/median-of-two-sorted-arrays/
// Difficulty: Hard

// Approach:
// 1. Use binary search on the smaller array.
// 2. Partition both arrays such that left half contains smaller elements.
// 3. Ensure:
//    maxLeft1 <= minRight2 AND maxLeft2 <= minRight1
// 4. If condition fails, adjust partition using binary search.
// 5. Once correct partition is found:
//    - If total length is even → return average of max(left) and min(right)
//    - If odd → return max(left)

// Time Complexity: O(log(min(m, n)))

// Space Complexity: O(1)

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Time = O(m + n) and space = O(m + n)
        // int m = nums1.length;
        // int n = nums2.length;

        // int[] arr = new int[m + n];
        // int i = 0;
        // int j = 0;
        // int k = 0;

        // while(i < m && j < n){
        //     if(nums1[i] < nums2[j]){
        //         arr[k++] = nums1[i++];
        //     }else{
        //         arr[k++] = nums2[j++];
        //     }
        // }

        // while(i < m){
        //     arr[k++] = nums1[i++];
        // } 

        // while(j < n){
        //     arr[k++] = nums2[j++];
        // }

        // int len = arr.length;

        // if(len % 2 == 0){
        //     return ((double)arr[(len / 2) - 1] + (double)arr[len / 2]) / 2;
        // }else{
        //     return (double)arr[len / 2];
        // }


        // Binary Search Partition

        // Ensure that array is smaller 
        if(nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        int low = 0, high = m;

        while(low <= high) {
            // Partition nums1
            int cut1 = (low + high) / 2;

            // Partition nums2
            int cut2 = (m + n + 1) / 2 - cut1;

            // Get boundary values
            int left1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int right1 = (cut1 == m) ? Integer.MAX_VALUE : nums1[cut1];

            int left2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
            int right2 = (cut2 == n) ? Integer.MAX_VALUE : nums2[cut2];    

            // Check the correct partition 
            if(left1 <= right2 && left2 <= right1) {

                // Even length
                if((m + n) % 2 == 0) {
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                }

                // Odd length
                return Math.max(left1, left2);
            } 

            // We adjust only the first array because the second array’s partition depends on it, ensuring total elements stay balanced.
            // Move left = took too many from nums1
            else if(left1 > right2) {
                high = cut1 - 1;
            }

            // Move right =  Left side from nums2 has bigger elements nums1 is contributing too few elements
            else {
                low = cut1 + 1;
            }
        }

        return 0.0;
    }
}

