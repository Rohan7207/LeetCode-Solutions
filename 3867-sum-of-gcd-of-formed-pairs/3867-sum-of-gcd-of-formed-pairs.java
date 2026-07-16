// Problem: Sum of GCD of Formed Pairs
// Link: https://leetcode.com/problems/sum-of-gcd-of-formed-pairs/?envType=daily-question&envId=2026-07-16
// Difficulty: Medium

// Approach:
// Traverse the array while maintaining the maximum element
// seen so far.
// For every index i:
//     maxSoFar = max(maxSoFar, nums[i])
// Compute:
//     prefixGcd[i] = gcd(nums[i], maxSoFar)
// This constructs the required prefixGcd array.
// Next, sort prefixGcd in non-decreasing order.
// Use two pointers:
//     left  -> smallest element
//     right -> largest element
// Form pairs by taking one element from each end of the
// sorted array.
// For every pair:
//     (prefixGcd[left], prefixGcd[right])
// compute their GCD and add it to the answer.
// If the array has an odd number of elements,
// the middle element is automatically ignored because
// the loop stops when left >= right.

// Time Complexity:
//     O(n log n + n log M)
//
//     O(n log n)   -> Sorting
//     O(n log M)   -> GCD computations
//
// Space Complexity:
//     O(n)
//     (prefixGcd array)


class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int[] prefixGcd = new int[n];

        int max = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > max)
                max = nums[i];

            prefixGcd[i] = gcd(nums[i], max);
        }

        Arrays.sort(prefixGcd);

        int left = 0;
        int right = n - 1;
        long sum = 0;

        while (left < right) {
            sum += gcd(prefixGcd[left], prefixGcd[right]);
            left++;
            right--;
        }
        return sum;
    }

    private int gcd(int a, int b) {
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }
}
