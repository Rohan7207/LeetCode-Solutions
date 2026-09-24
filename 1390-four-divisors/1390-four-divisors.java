// Problem: Four Divisors
// Link: https://leetcode.com/problems/four-divisors/
// Difficulty: Medium

// Approach:
//
// 1. For every number `x`, we already know two divisors:
//       1 and x
//
//    So initialize:
//       count = 0       → extra divisors found between 1 and x
//       sum = 1 + x
//
// 2. Instead of checking every number up to x/2, only check up to
//    √x.
//
// 3. Divisors come in pairs:
//
//       j × (x / j) = x
//
//    Therefore, when `j` divides `x`, both `j` and `x / j` are
//    divisors.
//
// 4. For a normal divisor pair:
//       count += 2
//       sum += j + x / j
//
// 5. For a perfect square, when:
//       j * j == x
//
//    `j` and `x / j` are the same divisor, so it must be counted
//    only once.
//
// 6. We only need numbers having exactly FOUR divisors.
//    Since 1 and x are already included, we need exactly TWO
//    additional divisors.
//
// 7. Therefore, once the extra divisor count becomes greater than
//    2, we can stop checking this number.
//
// 8. If `count == 2`, the number has exactly four divisors, so add
//    its divisor sum to the answer.

// Time Complexity: O(n × √m), where m = max(nums)
// Space Complexity: O(1)


class Solution {
    public int sumFourDivisors(int[] nums) {
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            int count = 0;
            int sum = 1 + x;
            int sqrt = (int) Math.sqrt(x);

            for (int j = 2; j <= sqrt; j++) {
                if (j * j == x) {
                    count++;
                    sum += j;
                }

                if (x % j == 0) {
                    count += 2;
                    sum += j + x / j;
                }

                if (count > 2) {
                    break;
                }
            }

            if (count == 2) {
                ans += sum;
            }
        }

        return ans;
    }
}
