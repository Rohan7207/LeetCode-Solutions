// Problem: Partition Array Into Three Parts With Equal Sum
// Link: https://leetcode.com/problems/partition-array-into-three-parts-with-equal-sum/
// Difficulty: Easy

// Approach:
// 1. Calculate the total sum of the array.
//
// 2. For three parts to have equal sum, the total sum must be
//    divisible by 3. If not, return false.
//
// 3. Calculate the required sum of each part:
//       target = totalSum / 3
//
// 4. Traverse the array and maintain a running sum.
//
// 5. Whenever the running sum becomes equal to `target`,
//    one valid non-empty part is formed. Reset the running sum
//    and increase `count`.
//
// 6. We need at least 3 such parts. If more than 3 are formed,
//    we can still combine consecutive parts to obtain exactly
//    three valid parts.
//
// 7. Return `count >= 3`.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public boolean canThreePartsEqualSum(int[] arr) {
        int totalSum = 0;

        for (int num : arr) {
            totalSum += num;
        }

        if (totalSum % 3 != 0)
            return false;

        int target = totalSum / 3;
        int count = 0;
        int currSum = 0;

        for (int num : arr) {
            currSum += num;

            if (currSum == target) {
                currSum = 0;
                count++;
            }
        }

        return count >= 3;
    }
}
