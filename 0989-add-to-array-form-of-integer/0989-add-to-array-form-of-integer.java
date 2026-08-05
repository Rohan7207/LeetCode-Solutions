// Problem: Add to Array-Form of Integer
// Link: https://leetcode.com/problems/add-to-array-form-of-integer/
// Difficulty: Easy

// Approach:
// Simulate the addition exactly like manual addition from the
// least significant digit (rightmost) to the most significant
// digit.
//
// Instead of maintaining a separate carry variable, use k itself
// to store both the remaining digits of the number and the carry.
//
// Let i point to the last digit of the array.
//
// While there are digits left in the array or k is still non-zero:
//
// 1. If the array still has digits remaining, add the current
//    digit to k.
//
//        k += num[i]
//
// 2. The last digit of k becomes the current digit of the answer.
//
//        k % 10
//
// 3. Remove the processed digit (including the carry) by
//    dividing k by 10.
//
//        k /= 10
//
// Repeat until both the array is exhausted and k becomes zero.
//
// Since digits are generated from right to left, reverse the
// result before returning.

// Time Complexity:
// O(max(n, digits in k))
//
// Space Complexity:
// O(max(n, digits in k))
// (excluding the output list)


class Solution {
    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> res = new ArrayList<>();
        int i = num.length - 1;

        // Loop as long as there are digits left in the array OR k has a value to carry over
        while (i >= 0 || k > 0) {
            if (i >= 0) {
                k += num[i]; // Add the current array digit to k
                i--;
            }

            res.add(k % 10); // Add the last digit of the running sum to the result
            k /= 10; // Carry over the remaining value
        }

        // Since we added digits from right to left, the list is backwards. Reverse it.
        Collections.reverse(res);
        return res;
    }
}
