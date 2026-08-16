// Problem : Duplicate Zeros
// Link : https://leetcode.com/problems/duplicate-zeros/
// Difficulty : Easy

// Approach:
// Use a temporary array to simulate the duplication process.
//
// 1. Create an `ans` array of the same size as `arr`.
//
// 2. Maintain two pointers:
//    - `j` → current position in the original array.
//    - `i` → position where we are writing in the result.
//
// 3. Traverse the original array:
//
//    - If `arr[j]` is non-zero, copy it once.
//
//    - If `arr[j]` is zero, write two zeros because every zero
//      must be duplicated.
//
// 4. Stop when either the original array is completely processed
//    or the result array becomes full.
//
// 5. Copy `ans` back into `arr` because the problem requires modifying
//    the original array.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public void duplicateZeros(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        int i = 0;
        int j = 0;

        while (i < n && j < n) {
            if (i < n - 1 && arr[j] == 0) {
                ans[i] = 0;
                i++;

                if (i == n)
                    break;
                ans[i] = 0;
            } else {
                ans[i] = arr[j];
            }

            i++;
            j++;
        }

        System.arraycopy(ans, 0, arr, 0, n);
    }
}
