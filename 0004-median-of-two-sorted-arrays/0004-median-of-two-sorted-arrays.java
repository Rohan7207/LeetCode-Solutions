// Problem: Median of Two Sorted Arrays
// Link: https://leetcode.com/problems/median-of-two-sorted-arrays/
// Difficulty: Hard

// Approach:
// 1. Store the sorted elements of two arrays in new array
// 2. Find length of new array
// 3. Calculate the median differently when length is even and odd

// Time Complexity:
// O(n)

// Space Complexity:
// O(m + n) — m and n are size of two arrays

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    //    int n = nums1.length;
    //    int m = nums2.length;
    //    int[] arr = new int[n+m];
    //    int k = 0;

    //    for(int i = 0; i < n; i++){
    //     arr[k++] = nums1[i];
    //    } 

    //     for(int i = 0; i < m; i++){
    //      arr[k++] = nums2[i];
    //     } 

    //    Arrays.sort(arr);
    //    int total = arr.length;

    //    if(total % 2 == 1) return (double)arr[total / 2];
    //    else return ((double)arr[total / 2 - 1] + (double)arr[total / 2]) / 2.0;



        int m = nums1.length;
        int n = nums2.length;

        int[] arr = new int[m + n];
        int i = 0;
        int j = 0;
        int k = 0;

        while(i < m && j < n){
            if(nums1[i] < nums2[j]){
                arr[k++] = nums1[i++];
            }else{
                arr[k++] = nums2[j++];
            }
        }

        while(i < m){
            arr[k++] = nums1[i++];
        } 

        while(j < n){
            arr[k++] = nums2[j++];
        }

        int len = arr.length;

        if(len % 2 == 0){
            return ((double)arr[(len / 2) - 1] + (double)arr[len / 2]) / 2;
        }else{
            return (double)arr[len / 2];
        }
    }
}
