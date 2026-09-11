// Problem: Unique 3-Digit Even Numbers
// Link: https://leetcode.com/problems/unique-3-digit-even-numbers/?envType=daily-question&envId=2026-09-11
// Difficulty: Easy

// Approach:
// Use three nested loops + HashSet to generate and count distinct numbers.
//
// 1. Choose the hundreds digit using i.
// 2. Skip 0 because a three-digit number cannot start with 0.
// 3. Choose the tens digit using j, ensuring its index differs from i.
// 4. Choose the units digit using k, ensuring all three indices are distinct.
// 5. The units digit must be even because the number must be even.
// 6. Construct the 3-digit number and store it in a HashSet.
// 7. The HashSet automatically removes duplicates caused by repeated digits.
// 8. Return the number of distinct values in the set.

// Time Complexity: O(n³)
// Space Complexity: O(n³)
// In the worst case, the set can contain O(n³) generated numbers.


class Solution {
    public int totalNumbers(int[] digits) {
        int n = digits.length;
        Set<Integer> seen = new HashSet<>();

        for (int i = 0; i < n; i++) {
            if (digits[i] == 0)
                continue;

            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;

                for (int k = 0; k < n; k++) {
                    if (i == k || j == k)
                        continue;

                    if (digits[k] % 2 == 0) {
                        int num = digits[i] * 100 + digits[j] * 10 + digits[k];

                        seen.add(num);
                    }
                }
            }
        }

        return seen.size();
    }
}
