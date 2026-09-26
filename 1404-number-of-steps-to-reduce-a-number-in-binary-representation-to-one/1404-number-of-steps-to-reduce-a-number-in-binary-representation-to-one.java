// Problem: Number of Steps to Reduce a Number in Binary Representation to One
// Link: https://leetcode.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one/
// Difficulty: Medium

// Approach:
//
// 1. We need to repeatedly perform:
//      - if number is even → divide by 2
//      - if number is odd  → add 1
//
// 2. Instead of converting the binary string to an integer, process
//    bits from right to left.
//
// 3. Maintain a carry:
//      carry = 0 → no effect from the right
//      carry = 1 → previous "+1" affected the current bit
//
// 4. For each bit, calculate:
//
//      currBit = bit + carry
//
// 5. If currBit == 1:
//      → current number is odd
//      → need "+1" and then "/2"
//      → 2 steps
//      → carry becomes 1
//
// 6. If currBit == 0:
//      → number is even
//      → only "/2"
//      → 1 step
//      → carry = 0
//
// 7. If currBit == 2:
//      → effectively 10 in binary
//      → only "/2"
//      → 1 step
//      → carry remains 1
//
// 8. We stop at index 1 because the first bit is guaranteed to be 1.
//    After processing all lower bits, the remaining carry determines
//    whether one final step is needed.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int numSteps(String s) {
        int count = 0;
        int carry = 0;

        // Loop from right to left, stopping before the first character (index 0)
        for (int i = s.length() - 1; i > 0; i--) {
            // Calculate the net value of the current position
            int currBit = (s.charAt(i) - '0') + carry;

            if (currBit == 1) {
                // Case 1: Odd number (1)
                // Needs 2 steps: +1 to make it even, then /2 to shift it out.
                count += 2;
                carry = 1;
            } else {
                // Case 2: Even number (0 or 2)
                // Needs 1 step: just /2 to shift it out.
                count += 1;

                // If currentBit was 2, it keeps a carry of 1. If it was 0, carry becomes 0.
                carry = (currBit == 2) ? 1 : 0;
            }
        }

        // Add the final remaining carry to complete the reduction to 1
        return carry + count;
    }
}
        return count;
    }
*/
