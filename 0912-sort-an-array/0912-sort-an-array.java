// Problem: Sort an Array
// Link: https://leetcode.com/problems/sort-an-array/
// Difficulty: Medium

// Approach:
// Quick Sort follows the Divide and Conquer technique.
// Algorithm:
//     1. Choose a pivot element.
//        Here, a random pivot is selected to reduce the
//        chances of worst-case performance.
//     2. Partition the array into two parts:
//            • Elements smaller than the pivot are moved
//              to the left.
//            • Elements greater than the pivot are moved
//              to the right.
//        Two pointers are used:
//            i -> scans from the left.
//            j -> scans from the right.
//        Whenever nums[i] >= pivot and nums[j] <= pivot,
//        swap them and continue moving both pointers.
//     3. After partitioning:
//            • Left subarray contains values <= pivot.
//            • Right subarray contains values >= pivot.
//     4. Recursively sort both partitions.
//     5. When a partition has one or zero elements,
//        it is already sorted, so recursion stops.

// Time Complexity:
//     Average Case : O(n log n)
//     Best Case    : O(n log n)
//     Worst Case   : O(n²)
//
// Space Complexity:
//     Average Recursion Stack : O(log n)
//     Worst Case Stack        : O(n)


class Solution {
    public int[] sortArray(int[] nums) {
        int n = nums.length;
        return quickSort(nums, 0, n - 1);
    }

    private int[] quickSort(int[] nums, int start, int end) {
        int pivot = nums[start + new Random().nextInt(end - start + 1)];

        int i = start, j = end;

        while (i <= j) {
            while (nums[i] < pivot)
                i++;
            while (nums[j] > pivot)
                j--;

            if (i <= j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
        }

        if (i < end)
            quickSort(nums, i, end);
        if (j > start)
            quickSort(nums, start, j);
        return nums;
    }
}
