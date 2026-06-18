// Problem: Daily Temperatures
// Link: https://leetcode.com/problems/daily-temperatures/
// Difficulty: Medium

// Approach:
// We need to find, for every day,
// how many days we must wait until a warmer temperature appears.
// Use a Monotonic Decreasing Stack.
// The stack stores indexes of days whose warmer day
// has not been found yet.
// Stack Rule:
//     temperatures[indexes in stack]
//     are in decreasing order.
// Traverse temperatures from left to right.
// For current day i:
//     While stack is not empty
//     and current temperature is greater than
//     temperature at stack top:
//         Current day is the first warmer day
//         for the day at stack top.
//         Pop that previous index.
//         ans[previousIndex] = i - previousIndex
//     Push current index into stack.
// Any indexes left in stack at the end
// do not have a future warmer day.
// Their answer remains 0 by default.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        Stack<Integer> s = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            while (!s.isEmpty() && temperatures[i] > temperatures[s.peek()]) {
                int index = s.pop();
                ans[index] = i - index;
            }
            s.push(i);
        }

        return ans;
    }
}
