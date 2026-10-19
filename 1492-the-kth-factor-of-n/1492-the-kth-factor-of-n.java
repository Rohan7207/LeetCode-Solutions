// Problem: The kth Factor of n
// Link: https://leetcode.com/problems/the-kth-factor-of-n/
// Difficulty: Medium

// Approach:
// 1. Iterate i from 1 while i * i <= n to find factors up to sqrt(n).
// 2. Whenever n % i == 0, i is a factor in ascending order.
// 3. Decrement k for each factor found and return i if k becomes 0.
// 4. If the kth factor is not found in the first pass, traverse i
//    backward from sqrt(n) to 1.
// 5. For each factor i, its paired factor n / i is also a factor.
// 6. Skip i == n / i to avoid counting the square root twice
//    when n is a perfect square.
// 7. Decrement k for each paired factor and return n / i when k == 0.
// 8. If no kth factor exists, return -1.

// Time Complexity: O(sqrt(n))
// Space Complexity: O(1)


class Solution {
    public int kthFactor(int n, int k) {
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                k--;

                if (k == 0) {
                    return i;
                }
            }
        }

        for (int i = (int) Math.sqrt(n); i >= 1; i--) {
            if (n % i == 0 && i != n / i) {
                k--;

                if (k == 0) {
                    return n / i;
                }
            }
        }

        return -1;
    }
}
