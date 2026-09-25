// Problem: Count Largest Group
// Link: https://leetcode.com/problems/count-largest-group/
// Difficulty: Easy

// Approach:
//
// 1. Every number from 1 to n belongs to a group based on its digit sum.
//
// 2. Calculate the digit sum of every number using:
//      digit = temp % 10
//      temp  = temp / 10
//
// 3. Use freq[sum] to store how many numbers have that digit sum.
//
// 4. While increasing the frequency, maintain maxSize so we know
//    the largest group size.
//
// 5. Traverse freq again and count how many groups have size == maxSize.

// Time Complexity: O(n * d)
// Space Complexity: O(1)
//
// d = number of digits in n.
// Since n <= 10^4 in this problem, d is at most 5.


class Solution {
    public int countLargestGroup(int n) {
        int[] freq = new int[37];
        int maxSize = -1;

        for (int i = 1; i <= n; i++) {
            int temp = i;
            int sum = 0;

            while (temp > 0) {
                sum += temp % 10;
                temp /= 10;
            }

            freq[sum]++;
            maxSize = Math.max(maxSize, freq[sum]);
        }

        int ans = 0;
        for (int val : freq) {
            if (val == maxSize) {
                ans++;
            }
        }

        return ans;
    }
}  
