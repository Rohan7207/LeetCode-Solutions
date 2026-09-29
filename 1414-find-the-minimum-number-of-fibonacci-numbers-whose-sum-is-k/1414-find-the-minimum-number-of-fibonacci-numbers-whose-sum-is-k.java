// Problem: Find the Minimum Number of Fibonacci Numbers Whose Sum Is K
// Link: https://leetcode.com/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k/
// Difficulty: Medium

// Approach:
// 1. Generate all Fibonacci numbers <= k.
//    There are only O(log k) of them because Fibonacci numbers grow quickly.
//
// 2. Start from the largest Fibonacci number and move towards the smallest.
//
// 3. If the current Fibonacci number <= k, choose it.
//    Add 1 to the answer and subtract it from k.
//
// 4. Continue until k becomes 0.
//
// 5. The greedy choice works because choosing the largest possible
//    Fibonacci number leaves the smallest possible remainder.

// Time Complexity: O(log k)
// Space Complexity: O(log k)


class Solution {
    public int findMinFibonacciNumbers(int k) {
        List<Integer> fib = new ArrayList<>();
        fib.add(1);
        fib.add(1);

        while (true) {
            int next = fib.get(fib.size() - 1) + fib.get(fib.size() - 2);

            if (next > k)
                break;

            fib.add(next);
        }

        int ans = 0;
        int len = fib.size();

        for (int i = len - 1; i >= 0; i--) {
            if (k == 0) {
                break;
            }

            if (fib.get(i) <= k) {
                ans++;
                k -= fib.get(i);
            }
        }

        return ans;
    }
}
