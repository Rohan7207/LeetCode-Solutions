// Problem: Number of Ways to Reorder Array to Get Same BST
// Link: https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst/?envType=daily-question&envId=2026-10-18
// Difficulty: Hard

// Approach:
// 1. Precompute Pascal's Triangle where pascalTable[i][j] = C(i, j).
// 2. The first element is the root of the BST.
// 3. Divide the remaining elements into leftArray (values < root)
//    and rightArray (values > root), preserving their original order.
// 4. Recursively calculate the number of valid insertion sequences
//    for the left and right subtrees.
// 5. The left and right subtree sequences can be interleaved while
//    preserving the relative order within each subtree.
// 6. Choose leftArray.size() positions from the remaining n - 1
//    positions for the left subtree using C(n - 1, leftArray.size()).
// 7. Multiply the left count, right count, and binomial coefficient,
//    taking modulo MOD.
// 8. Subtract 1 to exclude the original insertion sequence.

// Time Complexity: O(n^2) for Pascal's Triangle, plus recursive
// partitioning work; worst case O(n^2).
// Space Complexity: O(n^2) for Pascal's Triangle and O(n^2)
// auxiliary space in the worst case due to recursion and sublists.


class Solution {

    private int MOD = 1000000007;
    long[][] pascalTable;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        List<Integer> numsList = new ArrayList<>();
        pascalTable = new long[n + 1][n + 1];

        for (int num : nums) {
            numsList.add(num);
        }

        for (int i = 0; i <= n; i++) {
            pascalTable[i][0] = 1;
            pascalTable[i][i] = 1;

            for (int j = 1; j < i; j++) {
                pascalTable[i][j] = (pascalTable[i - 1][j] + pascalTable[i - 1][j - 1]) % MOD;
            }
        }

        long ans = (solve(numsList)) % MOD;

        return (int) ((ans - 1 + MOD) % MOD);
    }

    private long solve(List<Integer> nums) {
        int n = nums.size();

        if (n < 3) {
            return 1;
        }

        List<Integer> leftArray = new ArrayList<>();
        List<Integer> rightArray = new ArrayList<>();

        int root = nums.get(0);

        for (int i = 1; i < n; i++) {
            int val = nums.get(i);
            if (val < root) {
                leftArray.add(val);
            } else {
                rightArray.add(val);
            }
        }

        long x = solve(leftArray) % MOD;
        long y = solve(rightArray) % MOD;

        // ((n - 1) C (leftArray.size()))
        long z = pascalTable[n - 1][leftArray.size()];


        return (((x * y) % MOD) * z) % MOD;
    }
}
