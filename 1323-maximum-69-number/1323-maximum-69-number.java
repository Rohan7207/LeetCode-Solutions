// Problem: Maximum 69 Number
// Link: https://leetcode.com/problems/maximum-69-number/
// Difficulty: Easy

// Approach:
// Use Digit Extraction + Greedy.
//
// 1. Traverse the digits from left to right.
//
// 2. The number is made only of 6 and 9.
//    To maximize it, changing the first 6 to 9 gives the
//    largest possible increase.
//
// 3. Change only the first occurrence of 6 to 9.
//
// 4. Reconstruct the number from the modified digits.
//
// 5. If there is no 6, return the original number.

// Time Complexity: O(log n)
// Space Complexity: O(log n)


class Solution {
    public int maximum69Number(int num) {
        int len = (num == 0) ? 1 : (int) Math.log10(Math.abs(num)) + 1;
        int[] arr = new int[len];
        int temp = num;
        int idx = len - 1;

        while (num > 0 && idx >= 0) {
            arr[idx--] = num % 10;

            num /= 10;
        }

        boolean flag = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 6) {
                arr[i] = 9;
                flag = true;
                break;
            }
        }

        if (!flag) {
            return temp;
        }

        int res = 0;
        for (int digit : arr) {
            res = res * 10 + digit;
        }

        return res;
    }
}
