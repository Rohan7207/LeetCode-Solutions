// Problem: Minimum Moves To Equal Array Elements II
// Link: https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/?envType=daily-question&envId=2026-11-02
// Difficulty: Medium

// Approach:
// 1. Find the median of the array using the QuickSelect algorithm.
//    - The median is the element at index n / 2 after sorting.
//    - QuickSelect finds this element without fully sorting the array.
//
// 2. Use randomized pivot selection.
//    - Select a random index between left and right.
//    - Swap the selected pivot with the last element.
//
// 3. Apply the partition operation.
//    - Place all elements <= pivot on its left.
//    - Place all elements > pivot on its right.
//    - Return the pivot's final index.
//
// 4. Narrow down the search range.
//    - If k == pivotIndex, the pivot is the required median.
//    - If k < pivotIndex, search the left partition.
//    - If k > pivotIndex, search the right partition.
//
// 5. Once the median is found, calculate the minimum moves.
//    - The optimal target for minimizing the sum of absolute
//      differences is the median.
//    - Calculate the sum of |nums[i] - median| for every element.

// Time Complexity:
// Average: O(n)
// Worst: O(n^2)

// Space Complexity:
// Average: O(log n) recursion stack
// Worst: O(n) recursion stack


class Solution {

    private Random random = new Random();

    public int minMoves2(int[] nums) {
        int n = nums.length;

        int mid = quickSelect(nums, 0, n - 1, n / 2);
        int moves = 0;

        for (int num : nums) {
            moves += Math.abs(num - mid);
        }

        return moves;
    }

    private int quickSelect(int[] arr, int left, int right, int k) {
        if (left == right) {
            return arr[left];
        }

        int pivotIndex = randomPartition(arr, left, right);

        if (k == pivotIndex) {
            return arr[k];
        } else if (k < pivotIndex) {
            return quickSelect(arr, left, pivotIndex - 1, k);
        } else {
            return quickSelect(arr, pivotIndex + 1, right, k);
        }
    }

    private int randomPartition(int[] arr, int left, int right) {
        int pivotIdx = left + random.nextInt(right - left + 1);
        swap(arr, pivotIdx, right);

        return partition(arr, left, right);
    }

    private int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }

        swap(arr, i, right);
        return i;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
