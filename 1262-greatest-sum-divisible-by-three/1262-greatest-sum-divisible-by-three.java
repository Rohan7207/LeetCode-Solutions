// Problem: Freatest Sum Divisible by Three
// Link: https://leetcode.com/problems/greatest-sum-divisible-by-three/
// Difficulty: Medium

// Approach:
// Use Dynamic Programming + Modulo.
//
// 1. We only care about the remainder of a sum when divided by 3.
//    There are only 3 possible remainders:
//
//       0 → divisible by 3
//       1 → remainder 1
//       2 → remainder 2
//
// 2. Maintain `f[3]` where:
//
//       f[0] → maximum sum with remainder 0
//       f[1] → maximum sum with remainder 1
//       f[2] → maximum sum with remainder 2
//
// 3. Initially, sum = 0 has remainder 0:
//
//       f = {0, -∞, -∞}
//
//    `-∞` means that remainder is currently impossible.
//
// 4. For every `num`, create `g` as a copy of `f`.
//    This represents the option of NOT taking `num`.
//
// 5. Then consider taking `num` with every existing state.
//
//       newRemainder = (oldRemainder + num % 3) % 3
//
//       newSum = f[i] + num
//
// 6. For each new remainder, keep the maximum sum:
//
//       g[newRemainder] = Math.max(
//           g[newRemainder],
//           f[i] + num
//       );
//
// 7. After processing `num`, update:
//
//       f = g;
//
//    Now `f` represents the best sums using all numbers processed so far.
//
// 8. Finally, `f[0]` is the maximum sum divisible by 3,
//    because remainder 0 means the sum is divisible by 3.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int maxSumDivThree(int[] nums) {
        int[] f = { 0, Integer.MIN_VALUE, Integer.MIN_VALUE };

        for (int num : nums) {
            int[] g = new int[3];
            System.arraycopy(f, 0, g, 0, 3);

            for (int i = 0; i < 3; i++) {
                g[(i + (num % 3)) % 3] = Math.max(g[(i + (num % 3)) % 3], f[i] + num);
            }

            f = g;
        }

        return f[0];
    }
}

/*
    f represents the answers before using the current number.
    g represents the answers after considering the current number.

    Why use f[i] + num, not g[i] + num?

Because we want the current num to be used only once.

If we used g, a value that already included num could potentially be used again during the same iteration.
*/
