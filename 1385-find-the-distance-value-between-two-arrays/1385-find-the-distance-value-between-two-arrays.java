// Problem: Find the Distance Value Between Two Arrays
// Link: https://leetcode.com/problems/find-the-distance-value-between-two-arrays/
// Difficulty: Easy

// Approach:
// Use Brute Force + Early Termination.
//
// 1. Traverse every element of arr1.
// 2. For each element, compare it with every element of arr2.
// 3. Use absolute difference to check whether the two elements are
//    within distance d.
// 4. If even one arr2 element is within distance d, mark the current
//    arr1 element as invalid and stop checking further.
// 5. If no such element is found, increment the count.

// Time Complexity: O(n * m)
// Space Complexity: O(1)


class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int count = 0;

        for (int i = 0; i < arr1.length; i++) {
            boolean flag = true;
            for (int j = 0; j < arr2.length; j++) {
                if (Math.abs(arr1[i] - arr2[j]) <= d) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                count++;
            }
        }

        return count;
    }
}
