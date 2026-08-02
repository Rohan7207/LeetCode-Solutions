// Problem: Numbers With Same Consecutive Differences
// Link: https://leetcode.com/problems/numbers-with-same-consecutive-differences/
// Difficulty: Medium

// Approach:
// Generate all valid numbers using Backtracking (DFS).
//
// A valid number cannot have a leading zero, so start DFS
// from every possible first digit (1 to 9).
//
// The recursive function keeps track of:
// - current number built so far.
// - remaining digits that still need to be appended.
//
// Base Case:
// When no more digits are left to append, a complete valid
// number of length n has been formed, so add it to the answer.
//
// Recursive Step:
// Find the last digit of the current number.
//
// The next digit must differ from the last digit by exactly k.
// Therefore, there are at most two choices:
//      last - k
//      last + k
//
// If a candidate digit lies between 0 and 9, append it to the
// current number and continue the recursion.
//
// Special Case:
// When k == 0, both choices become identical.
// Recurse only once to avoid generating duplicate numbers.

// Time Complexity:
// O(9 × 2^(n-1))
// (Each level has at most two recursive choices starting
// from each of the 9 possible first digits.)
//
// Space Complexity:
// O(n)
// (Recursion stack depth.)


class Solution {
    public int[] numsSameConsecDiff(int n, int k) {
        List<Integer> list = new ArrayList<>();

        for (int digit = 1; digit <= 9; digit++) {
            dfs(list, digit, n - 1, k);
        }

        int size = list.size();
        int[] ans = new int[size];
        for (int i = 0; i < size; i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

    private void dfs(List<Integer> list, int currNum, int remainingDigits, int k) {
        if (remainingDigits == 0) {
            list.add(currNum);
            return;
        }

        int last = currNum % 10;
        if (k == 0) {
            int newNumber = currNum * 10 + last;
            dfs(list, newNumber, remainingDigits - 1, k);
        } else {
            int nextDigit1 = last - k;
            int nextDigit2 = last + k;

            if (nextDigit1 >= 0 && nextDigit1 <= 9) {
                int newNumber = currNum * 10 + nextDigit1;
                dfs(list, newNumber, remainingDigits - 1, k);
            }

            if (nextDigit2 >= 0 && nextDigit2 <= 9) {
                int newNumber = currNum * 10 + nextDigit2;
                dfs(list, newNumber, remainingDigits - 1, k);
            }
        }
    }
}
