// Problem: Maximum Element After Decreasing and Rearranging
// Link: https://leetcode.com/problems/maximum-element-after-decreasing-and-rearranging/?envType=daily-question&envId=2026-06-28
// Difficulty: Medium

// Approach:
// Rearrange operation allows us to put elements in any order,
// so sort the array to process smaller values first.
// After sorting:
//     arr[0] should become 1.
// Maintain a variable m:
//     m represents the maximum possible value we can achieve
//     for the current processed elements.
// Traverse the sorted array from left to right:
//     If the current element is greater than m:
//          We can increase m by 1.
//     Otherwise:
//          Current element cannot increase the maximum value,
//          because we cannot increase numbers and the adjacent
//          difference condition would break.
// Why only +1?
// Because adjacent elements can differ by at most 1,
// so every next valid element can be at most previous + 1.
// Finally:
//     m will be the maximum possible value of the last element.

// Time Complexity: O(n log n)
// Space Complexity: O(1) (excluding sorting)


class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);

        int m = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > m) {
                m++;
            }
        }

        return m;
    }
}

/*
    Approach in which we replace but in above we will not replace 
    we assign m = 1and increment m iff m > arr[i] so that count every element of arr which is done
    without a arranging
    Arrays.sort(arr);
    arr[0] = 1;

    for (int i = 1; i < arr.length; i++) {
        // int newValue = Math.min(arr[i], arr[i - 1] + 1);

        // arr[i] = newValue;

        if(arr[i] > arr[i - 1] + 1) {
            arr[i] = arr[i - 1] + 1;
        }
    }

    return arr[arr.length - 1];
*/
