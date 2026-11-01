// Problem: Find Kth Bit in Nth Binary String
// Link: https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/
// Difficulty: Medium

// Approach:
// 1. Observe the string construction pattern:
//    S1 = "0"
//    Sn = S(n-1) + "1" + reverse(invert(S(n-1))).
//
// 2. Calculate the middle position of the current string.
//    - Length of Sn = 2^n - 1.
//    - Middle position (1-indexed) = 2^(n-1).
//
// 3. Handle the base case.
//    - If n == 1, the string contains only "0", so return '0'.
//
// 4. Check the position k:
//    - If k == mid, return '1' because the middle bit is
//      always inserted as '1'.
//
//    - If k < mid, the required bit belongs to the left half.
//      Search the same position k in S(n-1).
//
//    - If k > mid, the required bit belongs to the right half.
//      Map it to its mirrored position in S(n-1):
//      k' = 2^n - k.
//      Recursively find that bit and invert the result.
//
// 5. Recursion continues until the base case or middle position
//    is reached. The results are inverted while recursion unwinds.

// Time Complexity: O(n)
// Auxiliary Space Complexity: O(n) due to recursion.


class Solution {
    public char findKthBit(int n, int k) {
        if (n == 1) {
            return '0';
        }

        int mid = (1 << (n - 1)); 

        if (k == mid) {
            return '1';
        }

        if (k < mid) {
            return findKthBit(n - 1, k);
        }

        char ch = findKthBit(n - 1, (1 << n) - k); 

        return ch == '0' ? '1' : '0';
    }
}
*/
