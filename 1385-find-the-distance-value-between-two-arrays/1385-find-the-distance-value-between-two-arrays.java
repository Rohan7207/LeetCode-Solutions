// Problem: Find the Distance Value Between Two Arrays
// Link: https://leetcode.com/problems/find-the-distance-value-between-two-arrays/?envType=daily-question&envId=2026-11-05
// Difficulty: Easy

// Approach:
//
// 1. Initialize count = 0 to track valid elements from arr1.
//
// 2. Traverse each element in arr1.
//
// 3. Assume the current arr1 element is valid using flag = true.
//
// 4. Compare it with every element in arr2.
//
// 5. If Math.abs(arr1[i] - arr2[j]) <= d:
//    - A nearby element exists in arr2.
//    - Mark flag = false.
//    - Break because we don't need further comparisons.
//
// 6. If flag remains true after checking all elements
//    in arr2, increment count.
//
// 7. Return count.

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
